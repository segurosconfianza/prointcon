package com.confianza.webapp.service.cierre.generacioncierrecartera;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.cierre.cieestaproc.CieEstaproc;
import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;
import com.confianza.webapp.service.cierre.cieestaproc.CieEstaprocService;
import com.confianza.webapp.service.excel.fileExcel.FileExcel;
import com.confianza.webapp.service.framework.frmconsulta.FrmConsultaService;
import com.confianza.webapp.service.framework.frmparametro.FrmParametroService;
import com.confianza.webapp.service.framework.frmtablas.FrmTablasService;
import com.confianza.webapp.service.security.userDetails;
import com.confianza.webapp.utils.Filter;
import com.confianza.webapp.utils.SheetExcel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class GeneracionCierreCarteraServiceImpl implements GeneracionCierreCarteraService{
	
	@Autowired
	Gson gson;
	
	@Autowired
	private FileExcel fileExcel;

	@Autowired
	private userDetails userDetails;
	
	@Autowired
	private FrmTablasService frmTablasService;
	
	@Autowired
	private FrmConsultaService frmConsultaService;
	
	@Autowired
	private FrmParametroService frmParametroService;
	
	@Autowired
	private CieEstaprocService cieEstaprocService;
	
	@Override
	public String executeProcessCierreCartera(String conscons, String params, HttpServletRequest request){
		
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> parameters = gson.fromJson(params, type);//charsetString.convertUTF8ToISO88591(params)   								
		
		FrmConsulta procedureOfQuery = frmConsultaService.listProcedureChild(conscons);
		List<FrmConsulta> hijosConsulta = frmConsultaService.listQueryChilds(conscons);
		
		List<FrmParametro> parametersProcedure = this.frmParametroService.listParamsCosuType(procedureOfQuery.getConscons());
		Map<String, Object> parametersEnterProcedure = createParametersQueryChild(parameters, parametersProcedure);
		
		List<CieEstaproc> activeEstaproc=cieEstaprocService.listAll(0, 0, null, createFilters(procedureOfQuery.getConsnomb()));
		Map<String, Object> resultProcess = startProcess(request, parameters, procedureOfQuery, hijosConsulta, parametersProcedure, parametersEnterProcedure, activeEstaproc);
		
		return gson.toJson(resultProcess);		
	}

	private Map<String, Object> startProcess(HttpServletRequest request, Map<String, Object> parameters, FrmConsulta procedureOfQuery, List<FrmConsulta> hijosConsulta, List<FrmParametro> parametersProcedure, Map<String, Object> parametersEnterProcedure, List<CieEstaproc> activeEstaproc) {
		Map<String, Object> resultProcess = new HashMap<String, Object>();
		long porcentaje = 100/(hijosConsulta.size()+1);
		if(activeEstaproc.size()==0)
			resultProcess = doProcess(request, parameters, procedureOfQuery, hijosConsulta, parametersProcedure, parametersEnterProcedure, resultProcess, porcentaje);		
		else
			resultProcess.put("Eror", generateDescriptionProcessActives(activeEstaproc));
		return resultProcess;
	}

	private String generateDescriptionProcessActives( List<CieEstaproc> activeEstaproc) {
		String error="";
		for(CieEstaproc process:activeEstaproc)
			error+="El proceso: "+process.getEsprnomb()+", identificado con el ID: "+process.getEsprcons()+", por el usuario:"+process.getEspruser()+" Se encuentra en estado: "+process.getEspresta()+"<br>";
		return error;
	}

	private Map<String, Object> doProcess(HttpServletRequest request, Map<String, Object> parameters, FrmConsulta procedureOfQuery, List<FrmConsulta> hijosConsulta, List<FrmParametro> parametersProcedure, Map<String, Object> parametersEnterProcedure, Map<String, Object> resultProcess, long porcentaje) {
		
		CieEstaproc cieEstaproc = cieEstaprocService.insert(procedureOfQuery.getConsnomb(), "Inicio del procedimiento: "+procedureOfQuery.getConsnomb(), userDetails.getUser(), "I");
		Map<String, Object> resultProcedure = frmConsultaService.loadProcedure(procedureOfQuery, parametersProcedure, parametersEnterProcedure, null);
		//Map<String, Object> resultProcedure = new HashMap<String, Object>();
		cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, "\nFinalizo la ejecucion del procedimiento: "+procedureOfQuery.getConsnomb(), null);
		
		if(resultProcedure.get("EROR") != null){
			cieEstaproc=modifyEstaProc(cieEstaproc, 100, null, resultProcedure.get("EROR").toString());
			resultProcess.put("Eror", resultProcedure.get("EROR"));
		}
		resultProcess = executeEachQueryChildren(request, parameters, hijosConsulta, cieEstaproc, porcentaje);
		
		return resultProcess;
	}

	private List<Filter> createFilters(String nameProcess) {
		List<Filter> filters = new ArrayList<Filter>();
		Filter filter=new Filter();
		filter.setCampo("espresta");
		filter.setTipo("=");
		filter.setTipodato("String");
		filter.setVal1("I");
		filters.add(filter);
		filter=new Filter();
		filter.setCampo("esprnomb");
		filter.setTipo("=");
		filter.setTipodato("String");
		filter.setVal1(nameProcess);
		filters.add(filter);
		return filters;
	}

	private CieEstaproc modifyEstaProc(CieEstaproc cieEstaproc, long porcentaje, String descripcion, String error) {
				
		if(porcentaje!=0)
			cieEstaproc.setEsprporc(cieEstaproc.getEsprporc()+porcentaje);
		if(descripcion!=null)
			cieEstaproc.setEsprdesc(cieEstaproc.getEsprdesc()+descripcion);
		if(error!=null)
			cieEstaproc.setEspreror(cieEstaproc.getEspreror()+error);
		cieEstaprocService.update(cieEstaproc);
		return cieEstaproc;
	}		

	private Map<String, Object> executeEachQueryChildren(HttpServletRequest request, Map<String, Object> parameters, List<FrmConsulta> queryChilds, CieEstaproc cieEstaproc, long porcentaje) {
		String success="";
		String error="";
		List<Object[]> resumen=new ArrayList<Object[]>();
		List<Object[]> resumenCancelaciones=new ArrayList<Object[]>();
		List<Object[]> resumenSinCancelaciones=new ArrayList<Object[]>();
		String fechaRuta[];
		String rutaArchivo;
		FrmConsulta queryDetalle=null;
		
		for(FrmConsulta query:queryChilds){		
			if(query.getConsnomb().equals("DET_SALDOS_PART_ESP")){
				cieEstaproc=modifyEstaProc(cieEstaproc, 0, "\nInicio de la ejecucion de la consulta: "+query.getConscons()+"-"+query.getConsnomb(), null);
				List<FrmParametro> parametersQueryChild=this.frmParametroService.listParamsCosuType(query.getConscons());
				
				List<Object[]> listAll=frmConsultaService.loadListData(query, createParametersQueryChild(parameters, parametersQueryChild), parametersQueryChild);
				
				if(listAll!=null)
					resumen.addAll(listAll);
				cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, "\nFinalizo de la ejecucion de la consulta: "+query.getConscons()+"-"+query.getConsnomb(), null);
				
				queryDetalle=query;
			}
			else{
				cieEstaproc=modifyEstaProc(cieEstaproc, 0, "\nInicio de la ejecucion de la consulta: "+query.getConsnomb(), null);
				List<FrmParametro> parametersQueryChild=this.frmParametroService.listParamsCosuType(query.getConscons());
				
				List<Object[]> listAll=frmConsultaService.loadListData(query, createParametersQueryChild(parameters, parametersQueryChild), parametersQueryChild);
				
				fechaRuta=parameters.get("FECHAFIN").toString().split("/");
				rutaArchivo=frmTablasService.listByTablcodi("ruta"+query.getConscons()).getTablvast()+fechaRuta[1]+"-"+fechaRuta[2];
				
				if(fileExcel.generateExcel(rutaArchivo, query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls", listAll, query.getConscolu().split(","), query.getConstico().split(","), request)){
					success+="Archivo Generado: "+query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\")+"<br>";
					cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, "\nFinalizo la ejecucion de la consulta: "+query.getConsnomb()+"\nArchivo Generado: "+query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\"), null);
				}
				else{
					error+="Se genero un error al crear el archivo: "+query.getConsnomb()+fechaRuta[1]+"-"+fechaRuta[2]+".xls";
					cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, null, "Se genero un error al crear el archivo: "+query.getConsnomb()+fechaRuta[1]+"-"+fechaRuta[2]+".xls");
				}
			}
		}
		
		for(Object[] row:resumen){
			if(row[3].toString().equals("N") && row[11].toString().equals("0") && row[21].toString().equals("1") && row[22].toString().equals("0") && row[23].toString().equals("1"))
				resumenCancelaciones.add(row);
			else
				resumenSinCancelaciones.add(row);			
		}
		
		return generateDetPart(request, parameters, cieEstaproc, porcentaje, success, error, resumenCancelaciones, resumenSinCancelaciones, queryDetalle);
	}

	private Map<String, Object> generateDetPart(HttpServletRequest request, Map<String, Object> parameters, CieEstaproc cieEstaproc, long porcentaje, String success, String error, List<Object[]> resumenCancelaciones, List<Object[]> resumenSinCancelaciones, FrmConsulta queryDetalle) {
		String[] fechaRuta;
		String rutaArchivo;
		Map<String, Object> result=new HashMap<String, Object>();
		try{
			fechaRuta=parameters.get("FECHAFIN").toString().split("/");
			rutaArchivo=frmTablasService.listByTablcodi("ruta"+queryDetalle.getConscons()).getTablvast()+fechaRuta[1]+"-"+fechaRuta[2];
			List<SheetExcel> sheetsExcel = generateSheetsDetPart( resumenCancelaciones, resumenSinCancelaciones, queryDetalle);
			
			if(fileExcel.generateExcelManySheets(rutaArchivo, queryDetalle.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls", sheetsExcel, request)){
				success+="Archivo Generado: "+queryDetalle.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\")+"<br>";
				cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, "\nFinalizo la ejecucion de la consulta: "+queryDetalle.getConsnomb()+"\nArchivo Generado: "+queryDetalle.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\"), null);
			}
			else{
				error+="Se genero un error al crear el archivo: "+queryDetalle.getConsnomb()+fechaRuta[1]+"-"+fechaRuta[2]+".xls";
				cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, null, "Se genero un error al crear el archivo: "+queryDetalle.getConsnomb()+fechaRuta[1]+"-"+fechaRuta[2]+".xls");
			}
		}catch(Exception e){
			error+="Se genero un error al crear el archivo: "+queryDetalle.getConsnomb();
			cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, null, e.getMessage()+"\n"+e.getLocalizedMessage()+"\n"+e.getCause());
		}
		
		cieEstaprocService.closeFinal(cieEstaproc);
		
		if(error.isEmpty())
			result.put("Success", success);
		else
			result.put("Eror", error);
		
		return result;
	}

	private List<SheetExcel> generateSheetsDetPart( List<Object[]> resumenCancelaciones, List<Object[]> resumenSinCancelaciones, FrmConsulta queryDetalle) {
		List<SheetExcel> sheetsExcel=new ArrayList<SheetExcel>();
		SheetExcel sheetExcel=new SheetExcel(resumenSinCancelaciones, queryDetalle.getConscolu().split(","), queryDetalle.getConstico().split(","), HSSFColor.WHITE.index, "DET_PART_SALDOS");
		sheetsExcel.add(sheetExcel);
		sheetExcel=new SheetExcel(resumenCancelaciones, queryDetalle.getConscolu().split(","), queryDetalle.getConstico().split(","), HSSFColor.WHITE.index, "CANCELACIONES");
		sheetsExcel.add(sheetExcel);
		return sheetsExcel;
	}

	private Map<String, Object> createParametersQueryChild( Map<String, Object> parameters, List<FrmParametro> parametros) {
		Map<String, Object> parametersChild=new HashMap<String, Object>();			
		for(FrmParametro parameter:parametros)
			parametersChild.put(parameter.getParanomb(), parameters.get(parameter.getParanomb()));
		return parametersChild;
	}

}

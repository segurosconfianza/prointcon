package com.confianza.webapp.service.cierre.cierrecartera;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.cierre.cieestaproc.CieEstaproc;
import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;
import com.confianza.webapp.repository.framework.frmtablas.FrmTablas;
import com.confianza.webapp.service.cierre.cieestaproc.FacEstaprocService;
import com.confianza.webapp.service.excel.fileExcel.FileExcel;
import com.confianza.webapp.service.framework.frmconsulta.FrmConsultaService;
import com.confianza.webapp.service.framework.frmparametro.FrmParametroService;
import com.confianza.webapp.service.framework.frmtablas.FrmTablasService;
import com.confianza.webapp.service.security.userDetails;
import com.confianza.webapp.utils.Filter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class CierreCarteraServiceImpl implements CierreCarteraService{
	
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
	private FacEstaprocService cieEstaprocService;
	
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
		cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, "\nFinalizo la ejecucion del procedimiento: "+procedureOfQuery.getConsnomb(), null);
		
		if(resultProcedure.get("EROR") == null)		
			resultProcess = executeEachQueryChildren(request, parameters, hijosConsulta, cieEstaproc, porcentaje);
		else{
			cieEstaproc=modifyEstaProc(cieEstaproc, 100, null, resultProcedure.get("EROR").toString());
			resultProcess.put("Eror", resultProcedure.get("EROR"));
		}
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
		Map<String, Object> result=new HashMap<String, Object>();
		
		for(FrmConsulta query:queryChilds){		
			cieEstaproc=modifyEstaProc(cieEstaproc, 0, "\nInicio de la ejecucion de la consulta: "+query.getConsnomb(), null);
			List<FrmParametro> parametersQueryChild=this.frmParametroService.listParamsCosuType(query.getConscons());
			
			List<Object[]> listAll=frmConsultaService.loadListData(query, createParametersQueryChild(parameters, parametersQueryChild), parametersQueryChild);
			
			String fechaRuta[]=parameters.get("XFECINI").toString().split("/");
			
			List<FrmTablas> listRutas=frmTablasService.listByCodi("ruta"+query.getConscons());
			String rutaArchivo;
			for(FrmTablas tabla:listRutas){
				rutaArchivo=tabla.getTablvast()+fechaRuta[1]+"-"+fechaRuta[2];
				
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
		cieEstaprocService.closeFinal(cieEstaproc);
		
		if(error.isEmpty())
			result.put("Success", success);
		else
			result.put("Eror", error);
		
		return result;
	}

	private Map<String, Object> createParametersQueryChild( Map<String, Object> parameters, List<FrmParametro> parametros) {
		Map<String, Object> parametersChild=new HashMap<String, Object>();			
		for(FrmParametro parameter:parametros)
			parametersChild.put(parameter.getParanomb(), parameters.get(parameter.getParanomb()));
		return parametersChild;
	}

}

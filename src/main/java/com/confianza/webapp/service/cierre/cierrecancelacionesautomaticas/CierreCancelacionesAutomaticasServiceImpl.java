package com.confianza.webapp.service.cierre.cierrecancelacionesautomaticas;

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

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
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
import com.confianza.webapp.utils.JSONUtil;
import com.confianza.webapp.utils.SheetExcel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class CierreCancelacionesAutomaticasServiceImpl implements CierreCancelacionesAutomaticasService{
	
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
		
		Map<String, Object> parameters=getParametersMap(params);
		
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
		try{
			Map<String, Object> resultProcedure = frmConsultaService.loadProcedure(procedureOfQuery, parametersProcedure, parametersEnterProcedure, null);
			cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, "\nFinalizo la ejecucion del procedimiento: "+procedureOfQuery.getConsnomb(), null);
			
			if(resultProcedure.get("EROR") == null)		
				resultProcess = executeEachQueryChildren(request, parameters, hijosConsulta, cieEstaproc, porcentaje);
			else{
				cieEstaproc=modifyEstaProc(cieEstaproc, 100, null, resultProcedure.get("EROR").toString());
				resultProcess.put("Eror", resultProcedure.get("EROR"));
			}			
		}catch(Exception e){
			cieEstaprocService.closeFinal(cieEstaproc);
			resultProcess.put("Eror", e.getStackTrace());
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
		
		try{
			for(FrmConsulta query:queryChilds){		
				cieEstaproc=modifyEstaProc(cieEstaproc, 0, "\nInicio de la ejecucion de la consulta: "+query.getConsnomb(), null);
				List<FrmParametro> parametersQueryChild=this.frmParametroService.listParamsCosuType(query.getConscons());
				
				List<Object[]> listAll=frmConsultaService.loadListData(query, createParametersQueryChild(parameters, parametersQueryChild), parametersQueryChild);
				result.put("listAll", JSONUtil.toNameList2(query.getConscolu().split(","),listAll));
				result.put("columns", query.getConscolu().split(","));
				result.put("count", listAll.size());
				
				String fechaRuta[]=parameters.get("FECHAFIN").toString().split("/");
				String rutaArchivo=frmTablasService.listByTablcodi("ruta"+query.getConscons()).getTablvast()+fechaRuta[1]+"-"+fechaRuta[2];
				
				if(fileExcel.generateExcel(rutaArchivo, query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls", listAll, query.getConscolu().split(","), query.getConstico().split(","), request)){
					success+="Archivo Generado: "+query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\")+"<br>";
					cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, "\nFinalizo la ejecucion de la consulta: "+query.getConsnomb()+"\nArchivo Generado: "+query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\"), null);
				}
				else{
					error+="Se genero un error al crear el archivo: "+query.getConsnomb()+fechaRuta[1]+"-"+fechaRuta[2]+".xls";
					cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, null, "Se genero un error al crear el archivo: "+query.getConsnomb()+fechaRuta[1]+"-"+fechaRuta[2]+".xls");
				}
			}
			cieEstaprocService.closeFinal(cieEstaproc);
			
			if(error.isEmpty())
				result.put("Success", success);
			else
				result.put("Eror", error);
		}catch(Exception e){
			cieEstaprocService.closeFinal(cieEstaproc);
			result.put("Eror", e.getStackTrace());
		}
		return result;
	}

	private Map<String, Object> createParametersQueryChild( Map<String, Object> parameters, List<FrmParametro> parametros) {
		Map<String, Object> parametersChild=new HashMap<String, Object>();			
		for(FrmParametro parameter:parametros)
			parametersChild.put(parameter.getParanomb(), parameters.get(parameter.getParanomb()));
		return parametersChild;
	}
	
	@Override
	public String cierreCreateCancelaciones(String selectedItems, int countItems, String params, HttpServletRequest request){
		
		List<Map<String, Object>> myList = getListMap(selectedItems);									
		Map<String, Object> parameters=getParametersMap(params);
		
		FrmConsulta query = frmConsultaService.listName("CRUZAR_CANCELACIONES");
		List<FrmParametro> parametersQueryChild=this.frmParametroService.listParamsCosuType(query.getConscons());
		List<Object[]> listAll=frmConsultaService.loadListData(query, createParametersQueryChild(parameters, parametersQueryChild), parametersQueryChild);
		
		generateListForSheets(myList, listAll, query.getConscolu().split(","), query.getConstico().split(","), parameters.get("FECHAFIN").toString(), query, request);
		
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("Success", "Acabo");
		
		return gson.toJson(result);		
	}

	private void generateListForSheets(List<Map<String, Object>> myList, List<Object[]> listAll, String[] headers, String[] typeData, String fecha, FrmConsulta query,HttpServletRequest request) {
		List<Object[]> forCancel=new ArrayList<Object[]>();
		List<Object[]> forNoCancel=new ArrayList<Object[]>();
		Map<String, Object> result;
		for(Object[] queryRow:listAll){
			
			if(myList.size()>0){
				result = rowIsInList(myList, queryRow);				
			
				if(result!=null){
					forCancel.add(queryRow);
					myList.remove(result);
				}
				else
					forNoCancel.add(queryRow);
			}
			else
				forNoCancel.add(queryRow);
		}
		
		generateFileExcel(listAll, headers, typeData, forCancel, forNoCancel, fecha, query, request);
	}

	private void generateFileExcel(List<Object[]> listAll, String[] headers, String[] typeData, List<Object[]> forCancel, List<Object[]> forNoCancel, String fecha, FrmConsulta query, HttpServletRequest request) {
		String success="";
		String error="";
		List<SheetExcel> sheetsExcel=createSheets(listAll, headers, typeData, forCancel, forNoCancel);
		String fechaRuta[]=fecha.split("/");
		String rutaArchivo=frmTablasService.listByTablcodi("ruta"+query.getConscons()).getTablvast()+fechaRuta[1]+"-"+fechaRuta[2];
		
		if(fileExcel.generateExcelManySheets(rutaArchivo, query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls", sheetsExcel, request)){
			success+="Archivo Generado: "+query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\")+"<br>";
		}
		else{
			error+="Se genero un error al crear el archivo: "+query.getConsnomb()+fechaRuta[1]+"-"+fechaRuta[2]+".xls";
		}
		
		System.out.println("listAll: "+listAll.size());
		System.out.println("forCancel: "+forCancel.size());
		System.out.println("forNoCancel: "+forNoCancel.size());
	}

	private List<SheetExcel> createSheets(List<Object[]> listAll, String[] headers, String[] typeData, List<Object[]> forCancel, List<Object[]> forNoCancel) {
		List<SheetExcel> sheetsExcel=new ArrayList<SheetExcel>();
		SheetExcel sheetExcel=new SheetExcel(forCancel, headers, typeData, HSSFColor.YELLOW.index, "Cancel");
		sheetsExcel.add(sheetExcel);
		sheetExcel=new SheetExcel(forNoCancel, headers, typeData, HSSFColor.WHITE.index, "NoCancel");
		sheetsExcel.add(sheetExcel);
		sheetExcel=new SheetExcel(listAll, headers, typeData, HSSFColor.WHITE.index, "Total");
		sheetsExcel.add(sheetExcel);
		return sheetsExcel;
	}

	private Map<String, Object> rowIsInList(List<Map<String, Object>> myList, Object[] queryRow) {
		for(Map<String, Object> row:myList)
			if(validateColumnsBetweenList(myList, queryRow, row))
				return row;			
		return null;
	}

	private boolean validateColumnsBetweenList( List<Map<String, Object>> myList, Object[] queryRow, Map<String, Object> row) {
		if(row.get("SUCUR").equals(queryRow[0]) && row.get("POLIZA").equals(queryRow[1]) && row.get("CERTIF").equals(queryRow[2]) && row.get("NUMDOC").equals(queryRow[3]))
			return true;
		else 
			return false;
	}

	private Map<String, Object> getParametersMap(String params) {
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> parameters = gson.fromJson(params, type);//charsetString.convertUTF8ToISO88591(params)
		return parameters;
	}

	private List<Map<String, Object>> getListMap(String selectedItems) {
		List<Map<String, Object>> myList= new ArrayList<Map<String, Object>>();
		Type type = TypeToken.get(myList.getClass()).getType();
		myList = gson.fromJson(selectedItems, type);
		return myList;
	}

}

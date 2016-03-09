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

import org.apache.poi.hssf.util.HSSFColor;
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
	private FacEstaprocService cieEstaprocService;
	
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
			e.printStackTrace();
			cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, null, e.getMessage());
			cieEstaprocService.closeFinal(cieEstaproc);
			resultProcess.put("Eror", e.getMessage());
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
				
				if(query.getConsnomb().equals("DETALLE_DE_SALDOS_PARA_CIERRE_CANCELACIONES_Y_TOTA")){
					/*FrmConsulta queryPropertys = frmConsultaService.listName("DETALLE_DE_SALDOS_PARA_CIERRE_CANCELACIONES_Y_TOTA PROPERTY");
					query.setConslsql(query.getConslsql()+" UNION "+queryPropertys.getConslsql());*/
					
					System.out.println(query.getConslsql());
				}
					
				cieEstaproc=modifyEstaProc(cieEstaproc, 0, "\nInicio de la ejecucion de la consulta: "+query.getConsnomb(), null);
				List<FrmParametro> parametersQueryChild=this.frmParametroService.listParamsCosuType(query.getConscons());
				
				List<Object[]> listAll=frmConsultaService.loadListData(query, createParametersQueryChild(parameters, parametersQueryChild), parametersQueryChild);
				result.put("listAll", JSONUtil.toNameList2(query.getConscolu().split(","),listAll));
				result.put("columns", query.getConscolu().split(","));
				result.put("count", listAll.size());
				
				String fechaRuta[]=parameters.get("FECHAFIN").toString().split("/");
				List<FrmTablas> listRutas=frmTablasService.listByCodi("ruta"+query.getConscons());
				String rutaArchivo;
				
				for(FrmTablas tabla:listRutas){
					rutaArchivo=tabla.getTablvast()+fechaRuta[1]+"-"+fechaRuta[2];
					if(query.getConsnomb().equals("DETALLE_DE_SALDOS_PARA_CIERRE_CANCELACIONES_Y_TOTA")){						
						List<SheetExcel> sheetsExcel = createSheetsforAnexo(parameters, query, listAll);
						
						if(fileExcel.generateExcelManySheets(rutaArchivo, query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls", sheetsExcel, request)){
							success+="Archivo Generado: "+query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\")+"<br>";
							cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, "\nFinalizo la ejecucion de la consulta: "+query.getConsnomb()+"\nArchivo Generado: "+query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\"), null);
						}
						else{
							error+="Se genero un error al crear el archivo: "+query.getConsnomb()+fechaRuta[1]+"-"+fechaRuta[2]+".xls";
							cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, null, "Se genero un error al crear el archivo: "+query.getConsnomb()+fechaRuta[1]+"-"+fechaRuta[2]+".xls");
						}
					}
					else
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
		}catch(Exception e){
			e.printStackTrace();
			cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, null, e.getMessage());
			cieEstaprocService.closeFinal(cieEstaproc);
			result.put("Eror",e.getMessage());
		}
		return result;
	}

	private List<SheetExcel> createSheetsforAnexo( Map<String, Object> parameters, FrmConsulta query,  List<Object[]> listAll) {
		List<FrmParametro> parametersQueryChild;
		FrmConsulta anexo=frmConsultaService.listName("DETALLE_DE_SALDOS_TOTALES_SUCURSAL");
		parametersQueryChild=this.frmParametroService.listParamsCosuType(anexo.getConscons());
		List<Object[]> listAllAnexo=frmConsultaService.loadListData(anexo, createParametersQueryChild(parameters, parametersQueryChild), parametersQueryChild);
		
		FrmConsulta anexo2=frmConsultaService.listName("DETALLE_DE_SALDOS_PARA_CIERRE_CANCELACIONES_RN");
		parametersQueryChild=this.frmParametroService.listParamsCosuType(anexo2.getConscons());
		List<Object[]> listAllAnexo2=frmConsultaService.loadListData(anexo2, createParametersQueryChild(parameters, parametersQueryChild), parametersQueryChild);
		
		List<SheetExcel> sheetsExcel=new ArrayList<SheetExcel>();
		SheetExcel sheetExcel=new SheetExcel(listAll, query.getConscolu().split(","), query.getConstico().split(","), HSSFColor.WHITE.index, "DATA");
		sheetsExcel.add(sheetExcel);
		sheetExcel=new SheetExcel(listAllAnexo2, anexo2.getConscolu().split(","), anexo2.getConstico().split(","), HSSFColor.WHITE.index, "RN");
		sheetsExcel.add(sheetExcel);
		sheetExcel=new SheetExcel(listAllAnexo, anexo.getConscolu().split(","), anexo.getConstico().split(","), HSSFColor.WHITE.index, "TOTALES");
		sheetsExcel.add(sheetExcel);
		return sheetsExcel;
	}

	private Map<String, Object> createParametersQueryChild( Map<String, Object> parameters, List<FrmParametro> parametros) {
		Map<String, Object> parametersChild=new HashMap<String, Object>();			
		for(FrmParametro parameter:parametros)
			parametersChild.put(parameter.getParanomb(), parameters.get(parameter.getParanomb()));
		return parametersChild;
	}
	
	@Override
	public String cierreCreateCancelaciones(String selectedItems, int countItems, String params, HttpServletRequest request){
		
		Map<String, Object> result=new HashMap<String, Object>();
		List<Map<String, Object>> myList = getListMap(selectedItems);									
		Map<String, Object> parameters=getParametersMap(params);
		
		FrmConsulta query = frmConsultaService.listName("SALDOS CANCEL SIN CANCEL TOTAL");
		/*FrmConsulta queryPropertys = frmConsultaService.listName("SALDOS CANCEL SIN CANCEL TOTAL PROPERTY");
		query.setConslsql(query.getConslsql()+" UNION "+queryPropertys.getConslsql());*/ 
		List<FrmParametro> parametersQueryChild=this.frmParametroService.listParamsCosuType(query.getConscons());
		
		CieEstaproc cieEstaproc = cieEstaprocService.insert(query.getConsnomb(), "Inicio de la consulta: "+query.getConsnomb(), userDetails.getUser(), "I");		
		
		try{
			List<Object[]> listAll=frmConsultaService.loadListData(query, createParametersQueryChild(parameters, parametersQueryChild), parametersQueryChild);
			cieEstaproc=modifyEstaProc(cieEstaproc, 15, "\nFinalizo la ejecucion de la consulta: "+query.getConsnomb(), null);
			
			result=generateListForSheets(myList, listAll, query.getConscolu().split(","), query.getConstico().split(","), parameters.get("FECHAFIN").toString(), query, cieEstaproc, parameters, request);						
		}catch(Exception e){
			cieEstaproc=modifyEstaProc(cieEstaproc, 15, null, e.getMessage());
			cieEstaprocService.closeFinal(cieEstaproc);
			result.put("Eror", e.getMessage()+" | "+e.getStackTrace());
			e.printStackTrace();
		}
		
		return gson.toJson(result);	
	}

	private boolean generateProvision(String[] fechaRuta, List<Object[]> listAll, String[] headers, String[] typeData, List<Object[]> cancel, HttpServletRequest request){
		
		Map<String, Object> indexs=new HashMap<String, Object>();
		indexs.put("indexPrimaAceptada", getIndexof(headers, "PRIMAACEP"));
		indexs.put("indexPrimaDirecta", getIndexof(headers, "PRIMADIR"));
		indexs.put("indexCedidoCompania", getIndexof(headers, "CEDIDOCIA"));
		indexs.put("indexGastos", getIndexof(headers, "GASTOS"));
		indexs.put("indexIva",  getIndexof(headers, "IVA"));
		indexs.put("indexMadurez", getIndexof(headers, "MADUREZ"));
		indexs.put("indexPrimaAceptada", getIndexof(headers, "PRIMAACEP"));
		
		Map<String, Object> data=this.processCancelProvision(cancel, indexs);
		List<Object[]> listCancel=(List<Object[]>) data.get("listAll");
		double total= (double) data.get("total");
		System.out.println("cancel total: "+total);
		double totalIva= (double) data.get("totalIva");
		System.out.println("cancel totalIva: "+totalIva);
		return processProvision(fechaRuta, headers, typeData, listAll, listCancel, indexs, total, totalIva, request);
	}

	private boolean processProvision(String[] fechaRuta, String[] headers, String[] typeData,List<Object[]> listAll, List<Object[]> cancel, Map<String, Object> indexs, double primaGastosCancel, double ivaCancel, HttpServletRequest request) {
		double provisionSince0To75 = 0, provisionSince76To90 = 0, provisionSince91To180 = 0, provisionSince181To270 = 0, provisionSince271To360 = 0, provisionMayor360 = 0;		
		double ivaSince76To90 = 0, ivaSince91To180 = 0, ivaSince181To270 = 0, ivaSince271To360 = 0, ivaMayor360 = 0;
		
		double porcentaje=0, factor=0;
		
		//sin cancel 
		List<Object[]> provisiones=new ArrayList<Object[]>();
		Object[] newRow;
		double total;
		double madurez;
		int cont=1;
		for(Object[] row:listAll)
		{
			newRow = new Object[row.length+6];
			System.arraycopy(row, 0, newRow, 0, row.length);
			total  = castToDouble(row[(int) indexs.get("indexPrimaAceptada")].toString()) + castToDouble(row[(int) indexs.get("indexPrimaDirecta")].toString()) + castToDouble(row[(int) indexs.get("indexCedidoCompania")].toString()) + castToDouble(row[(int) indexs.get("indexGastos")].toString());
			newRow[row.length]=total;
			newRow[row.length+1]=total*0.20;
			porcentaje=total-(total*0.20);
			newRow[row.length+2]=total-(total*0.20);			
			madurez=castToDouble(row[(int) indexs.get("indexMadurez")].toString());
			if(madurez >=0 && madurez < 76){
				provisionSince0To75+=total;
				ivaSince76To90+=castToDouble(row[(int) indexs.get("indexIva")].toString());
				
				newRow[row.length+3]="0";
				newRow[row.length+4]="0";
				newRow[row.length+5]="0";				
			}
			else if(madurez >=76 && madurez < 91){
				provisionSince76To90+=total;
				ivaSince76To90+=castToDouble(row[(int) indexs.get("indexIva")].toString());
				
				newRow[row.length+3]="83/360";
				factor=((total-porcentaje)*83)/360;
				newRow[row.length+4]=factor;
				newRow[row.length+5]=(total*0.20)+factor;
			}
			else if(madurez >=91 && madurez < 181){
				provisionSince91To180+=total;
				ivaSince91To180+=castToDouble(row[(int) indexs.get("indexIva")].toString());
				
				newRow[row.length+3]="3/8";
				factor=((total-porcentaje)*3)/8;
				newRow[row.length+4]=factor;
				newRow[row.length+5]=(total*0.20)+factor;
			}
			else if(madurez >=181 && madurez < 271){
				provisionSince181To270+=total;
				ivaSince181To270+=castToDouble(row[(int) indexs.get("indexIva")].toString());
				
				newRow[row.length+3]="5/8";
				factor=((total-porcentaje)*5)/8;
				newRow[row.length+4]=factor;
				newRow[row.length+5]=(total*0.20)+factor;
			}
			else if(madurez >=271 && madurez < 361){
				provisionSince271To360+=total;
				ivaSince271To360+=castToDouble(row[(int) indexs.get("indexIva")].toString());
				
				newRow[row.length+3]="7/8";
				factor=((total-porcentaje)*7)/8;
				newRow[row.length+4]=factor;
				newRow[row.length+5]=(total*0.20)+factor;
			}
			else if(madurez >360){
				provisionMayor360+=total;
				ivaMayor360+=castToDouble(row[(int) indexs.get("indexIva")].toString());
				
				newRow[row.length+3]="0";
				newRow[row.length+4]="0";
				newRow[row.length+5]="0";
			}			
			provisiones.add(newRow);
		}
		
		return calculateProvisiones(fechaRuta, headers, typeData, provisiones, cancel, primaGastosCancel, ivaCancel, provisionSince0To75, provisionSince76To90, provisionSince91To180, provisionSince181To270, provisionSince271To360, provisionMayor360, ivaSince76To90, ivaSince91To180, ivaSince181To270, ivaSince271To360, ivaMayor360, request);
	}

	private boolean calculateProvisiones(String[] fechaRuta,String[] headers, String[] typeData, List<Object[]> provisiones, List<Object[]> cancel, double primaGastosCancel, double ivaCancel, double provisionSince0To75, double provisionSince76To90, double provisionSince91To180, double provisionSince181To270, double provisionSince271To360, double provisionMayor360, double ivaSince76To90, double ivaSince91To180, double ivaSince181To270, double ivaSince271To360, double ivaMayor360, HttpServletRequest request) {
		
		double columnaFConfisco=new Double(frmTablasService.listByTablcodi("columnaFProvisiones").getTablvast());
		double columnaGConfisco=new Double(frmTablasService.listByTablcodi("columnaGProvisiones").getTablvast());
		double columnaMProvisionesIva=new Double(frmTablasService.listByTablcodi("columnaMProvisionesIva").getTablvast());
		
		provisionMayor360+=columnaFConfisco;
		primaGastosCancel+=columnaGConfisco;
		
		double provision20PorcentSince76To90   = provisionSince76To90*0.2;
		double provision20PorcentSince91To180  = provisionSince91To180*0.2;		
		double provision20PorcentSince181To270 = provisionSince181To270*0.2;
		double provision20PorcentSince271To360 = provisionSince271To360*0.2;
		
		double provisionMinus20PorcentSince76To90   = provisionSince76To90-provision20PorcentSince76To90;
		double provisionMinus20PorcentSince91To180  = provisionSince91To180-provision20PorcentSince91To180;		
		double provisionMinus20PorcentSince181To270 = provisionSince181To270-provision20PorcentSince181To270;
		double provisionMinus20PorcentSince271To360 = provisionSince271To360-provision20PorcentSince271To360;
		
		double factorSince76To90   = (provisionMinus20PorcentSince76To90 *83)/360;
		double factorSince91To180  = (provisionMinus20PorcentSince91To180 *3)/8;		
		double factorSince181To270 = (provisionMinus20PorcentSince181To270*5)/8;
		double factorSince271To360 = (provisionMinus20PorcentSince271To360*7)/8;
		
		double totalProvisiones=provisionSince271To360+provisionSince181To270+provisionSince91To180+provisionSince76To90;
		double totalProvisiones20Porcent=totalProvisiones*0.2;
		double totalProvisionMinus20Porcent=totalProvisiones-totalProvisiones20Porcent;
		double totalFactores=factorSince76To90+factorSince91To180+factorSince181To270+factorSince271To360;
		double totalIva=ivaSince76To90+ivaSince91To180+ivaSince181To270+ivaSince271To360;
				
		double totalProvisionesFinal=totalProvisiones+provisionMayor360;
		double totalProvisionMinus20PorcentFinal=totalProvisionMinus20Porcent+provisionMayor360+primaGastosCancel;
		double totalFactoresFinal=totalFactores+provisionMayor360+primaGastosCancel;
		double totalProvisionGastosCancel=provisionMayor360+primaGastosCancel;
		
		double totalProvesiones=(totalIva+ivaMayor360+ivaCancel)+columnaMProvisionesIva;
		return exportProvisiones(fechaRuta, headers, typeData, provisiones, cancel, primaGastosCancel, ivaCancel, provisionSince0To75, provisionSince76To90, provisionSince91To180, provisionSince181To270, provisionSince271To360, provisionMayor360, ivaSince76To90, ivaSince91To180, ivaSince181To270, ivaSince271To360, ivaMayor360, provision20PorcentSince76To90, provision20PorcentSince91To180, provision20PorcentSince181To270, provision20PorcentSince271To360, provisionMinus20PorcentSince76To90, provisionMinus20PorcentSince91To180, provisionMinus20PorcentSince181To270, provisionMinus20PorcentSince271To360, factorSince76To90, factorSince91To180, factorSince181To270, factorSince271To360, totalProvisiones, totalProvisiones20Porcent, totalProvisionMinus20Porcent, totalFactores, totalIva, totalProvisionesFinal, totalProvisionMinus20PorcentFinal, totalFactoresFinal, totalProvisionGastosCancel, totalProvesiones, columnaMProvisionesIva, request);
	}

	private boolean exportProvisiones(String[] fechaRuta,String[] headers, String[] typeData,List<Object[]> provisiones, List<Object[]> cancel, double primaGastosCancel, double ivaCancel, double provisionSince0To75, double provisionSince76To90, double provisionSince91To180, double provisionSince181To270, double provisionSince271To360, double provisionMayor360, double ivaSince76To90, double ivaSince91To180, double ivaSince181To270, double ivaSince271To360, double ivaMayor360, double provision20PorcentSince76To90, double provision20PorcentSince91To180, double provision20PorcentSince181To270, double provision20PorcentSince271To360, double provisionMinus20PorcentSince76To90, double provisionMinus20PorcentSince91To180, double provisionMinus20PorcentSince181To270, double provisionMinus20PorcentSince271To360, double factorSince76To90, double factorSince91To180, double factorSince181To270, double factorSince271To360, double totalProvisiones, double totalProvisiones20Porcent, double totalProvisionMinus20Porcent, double totalFactores, double totalIva, double totalProvisionesFinal, double totalProvisionMinus20PorcentFinal, double totalFactoresFinal, double totalProvisionGastosCancel, double totalProvesiones, double columnaMProvisionesIva,HttpServletRequest request) {
		List<Object[]> listProvision=new ArrayList<Object[]>();
		Object[] row1= {"1005","0","0","0","0",				   provisionSince271To360,"0"			   ,provision20PorcentSince271To360,provisionMinus20PorcentSince271To360,"7/8"	 ,factorSince271To360		 ,provision20PorcentSince271To360+factorSince271To360,ivaSince271To360 	  		   };		
		Object[] row2= {"1010","0","0","0","0",				   provisionSince181To270,"0"			   ,provision20PorcentSince181To270,provisionMinus20PorcentSince181To270,"5/8"	 ,factorSince181To270		 ,provision20PorcentSince181To270+factorSince181To270,ivaSince181To270	  		   };
		Object[] row3= {"1015","0","0","0","0",				   provisionSince91To180 ,"0"			   ,provision20PorcentSince91To180 ,provisionMinus20PorcentSince91To180 ,"3/8"	 ,factorSince91To180 		 ,provision20PorcentSince91To180 +factorSince91To180 ,ivaSince91To180 	  		   };
		Object[] row4= {"1020","0","0","0",provisionSince0To75,provisionSince76To90  ,"0"			   ,provision20PorcentSince76To90  ,provisionMinus20PorcentSince76To90  ,"83/360",factorSince76To90  		 ,provision20PorcentSince76To90  +factorSince76To90  ,ivaSince76To90  	  		   };
		Object[] row5= {"1999","0","0","0",provisionSince0To75,totalProvisiones      ,"0"			   ,totalProvisiones20Porcent      ,totalProvisionMinus20Porcent        ,""   	 ,totalFactores      		 ,totalProvisiones20Porcent+totalFactores          	 ,totalIva        	  		   };
		Object[] row6= {"6005","0","0","0","0",				   provisionMayor360     ,primaGastosCancel,"0"      					   ,totalProvisionGastosCancel 			,""   	 ,totalProvisionGastosCancel,totalProvisionGastosCancel  			  			 ,ivaMayor360+ivaCancel+columnaMProvisionesIva };
		Object[] row7= {"6999","0","0","0","0",				   provisionMayor360     ,primaGastosCancel,"0"      					   ,totalProvisionGastosCancel 			,""   	 ,totalProvisionGastosCancel,totalProvisionGastosCancel  			  			 ,ivaMayor360+ivaCancel+columnaMProvisionesIva };
		Object[] row8= {"7005","0","0","0",provisionSince0To75,totalProvisionesFinal ,primaGastosCancel,totalProvisiones20Porcent      ,totalProvisionMinus20PorcentFinal	,""	  	 ,totalFactoresFinal		 ,totalProvisiones20Porcent+totalFactores+totalProvisionGastosCancel,totalProvesiones};		
		listProvision.add(row1);
		listProvision.add(row2);
		listProvision.add(row3);
		listProvision.add(row4);
		listProvision.add(row5);
		listProvision.add(row6);
		listProvision.add(row7);
		listProvision.add(row8);
		
		List<SheetExcel> sheetsExcel=new ArrayList<SheetExcel>();
		SheetExcel sheetExcel=new SheetExcel(provisiones, headers, typeData, HSSFColor.WHITE.index, "Provisiones");
		sheetsExcel.add(sheetExcel);
		
		sheetExcel=new SheetExcel(cancel, headers, typeData, HSSFColor.WHITE.index, "Cancelaciones");
		sheetsExcel.add(sheetExcel);
		
		sheetExcel=new SheetExcel(listProvision, " , , , , , , , , , , , , ".split(","), "string,integer,integer,integer,double,double,double,double,double,string,double,double,double".split(","), HSSFColor.WHITE.index, "TotalProvisiones");
		sheetsExcel.add(sheetExcel);
		
		List<FrmTablas> listRutas=frmTablasService.listByCodi("rutaProvisiones");
		boolean respuesta=false;
		String rutaArchivo="";
		for(FrmTablas tabla:listRutas){
			rutaArchivo=tabla.getTablvast()+fechaRuta[1]+"-"+fechaRuta[2];
			respuesta=fileExcel.generateExcelManySheets(rutaArchivo, "Provisiones "+fechaRuta[1]+"-"+fechaRuta[2]+".xls", sheetsExcel, request);
		}
		return respuesta;
	}
	
	private Double castToDouble(String camp){
		return Double.parseDouble(camp);
	}
	
	private int getIndexof(String[] headers, String header){
		for(int i=0;i<headers.length;i++)
			if(headers[i].equals(header))
				return i;
		return -1;
	}
	
	private Map<String, Object> generateListForSheets(List<Map<String, Object>> myList, List<Object[]> listAll, String[] headers, String[] typeData, String fecha, FrmConsulta query, CieEstaproc cieEstaproc, Map<String, Object> parameters, HttpServletRequest request) {
		List<Object[]> forCancel=new ArrayList<Object[]>();
		List<Object[]> forNoCancel=new ArrayList<Object[]>();
		List<Object[]> total=new ArrayList<Object[]>();
		Map<String, Object> result;
		
		cieEstaproc=modifyEstaProc(cieEstaproc, 30, "\nIncio del cruce de cancelaciones", null);
		for(Object[] queryRow:listAll){
			
			if(myList.size()>0){
				result = rowIsInList(myList, queryRow);				
			
				if(result!=null){
					queryRow[19]='C';//la consulta en la columna 19 esta en '' para colocarle la C de cancel sino en blanco
					forCancel.add(queryRow);
					myList.remove(result);					
				}
				else
					forNoCancel.add(queryRow);
			}
			else
				forNoCancel.add(queryRow);
			
			total.add(queryRow);
		}
		cieEstaproc=modifyEstaProc(cieEstaproc, 45, "\nFinalizacion del cruce de cancelaciones", null);
		
		return generateFileExcel(total, headers, typeData, forCancel, forNoCancel, fecha, query, cieEstaproc, parameters, request);
	}
	
	private Map<String, Object> generateFileExcel(List<Object[]> listAll, String[] headers, String[] typeData, List<Object[]> forCancel, List<Object[]> forNoCancel, String fecha, FrmConsulta query, CieEstaproc cieEstaproc, Map<String, Object> parameters, HttpServletRequest request) {
		
		Map<String, Object> result=new HashMap<String, Object>();
		
		List<SheetExcel> sheetsExcel=createSheets(listAll, headers, typeData, forCancel, forNoCancel, parameters);
		String fechaRuta[]=fecha.split("/");
		
		List<FrmTablas> listRutas=frmTablasService.listByCodi("ruta"+query.getConscons());
		boolean respuesta=false;
		String rutaArchivo="";
		for(FrmTablas tabla:listRutas){
			rutaArchivo=tabla.getTablvast()+fechaRuta[1]+"-"+fechaRuta[2];
			
			cieEstaproc=modifyEstaProc(cieEstaproc, 60, "\nInicio de la creacion del archivo del excel", null);
			
			respuesta=fileExcel.generateExcelManySheets(rutaArchivo, query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls", sheetsExcel, request);
			
			if(respuesta)
				cieEstaproc=modifyEstaProc(cieEstaproc, 75, "\nFinalizacion de creacion del archivo del excel\n"+query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\")+"<br>", null);
			else{
				result.put("Eror","Se genero un error al crear el archivo: "+query.getConsnomb()+fechaRuta[1]+"-"+fechaRuta[2]+".xls");
				cieEstaproc=modifyEstaProc(cieEstaproc, 90, "\nSe genero un error al crear el archivo: "+query.getConsnomb()+fechaRuta[1]+"-"+fechaRuta[2]+".xls", null);
			}
		}	
											
		cieEstaproc=modifyEstaProc(cieEstaproc, 75, "\nInicio de creacion del archivo del excel de provisiones\n"+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\")+"<br>", null);
		String[] headersProvision = createHeadersProvision(headers);
		String[] typeDataProvision = createTypeDataProvision(typeData);
		List<Object[]> cancelImplicitas = generateCancelacionesImplicitas(parameters);
		forCancel.addAll(cancelImplicitas);
		
		if(generateProvision(fechaRuta, forNoCancel, headersProvision, typeDataProvision, forCancel, request))
			result.put("Success","Archivo Generado: "+query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\")+"<br>Archivo generado de provisiones");
		else
			result.put("Success","Archivo Generado: "+query.getConsnomb()+" "+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\")+"<br>No se genero el archivo de provisiones");
		
		cieEstaproc=modifyEstaProc(cieEstaproc, 75, "\nFinalizacion de creacion del archivo del excel de provisiones\n"+fechaRuta[1]+"-"+fechaRuta[2]+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\")+"<br>", null);
		
		cieEstaprocService.closeFinal(cieEstaproc);
		return result;
	}

	private String[] createTypeDataProvision(String[] typeData) {
		String typeDataProvision[]=new String[typeData.length+6];
		System.arraycopy(typeData, 0, typeDataProvision, 0, typeData.length);
		typeDataProvision[typeData.length]="double";
		typeDataProvision[typeData.length+1]="double";
		typeDataProvision[typeData.length+2]="double";
		typeDataProvision[typeData.length+3]="string";
		typeDataProvision[typeData.length+4]="double";
		typeDataProvision[typeData.length+5]="double";
		return typeDataProvision;
	}

	private String[] createHeadersProvision(String[] headers) {
		String headersProvision[]=new String[headers.length+6];
		System.arraycopy(headers, 0, headersProvision, 0, headers.length);
		headersProvision[headers.length]="Prima+Gastos";
		headersProvision[headers.length+1]="P+G 20%";
		headersProvision[headers.length+2]="P+G - P+G 20%";
		headersProvision[headers.length+3]="Factor";
		headersProvision[headers.length+4]="P+G - P+G 20% * Factor";
		headersProvision[headers.length+5]="Total";
		return headersProvision;
	}

	private List<SheetExcel> createSheets(List<Object[]> listAll, String[] headers, String[] typeData, List<Object[]> forCancel, List<Object[]> forNoCancel, Map<String, Object> parameters) {
		List<Object[]> cancelImplicitas = generateCancelacionesImplicitas(parameters);					
		
		List<SheetExcel> sheetsExcel=new ArrayList<SheetExcel>();
		SheetExcel sheetExcel=new SheetExcel(forCancel, headers, typeData, HSSFColor.YELLOW.index, "Cancel", cancelImplicitas, HSSFColor.BLUE_GREY.index);
		sheetsExcel.add(sheetExcel);
		sheetExcel=new SheetExcel(forNoCancel, headers, typeData, HSSFColor.WHITE.index, "NoCancel");
		sheetsExcel.add(sheetExcel);
		sheetExcel=new SheetExcel(listAll, headers, typeData, HSSFColor.WHITE.index, "Total", cancelImplicitas, HSSFColor.BLUE_GREY.index);
		sheetsExcel.add(sheetExcel);
		return sheetsExcel;
	}

	private List<Object[]> generateCancelacionesImplicitas(Map<String, Object> parameters) {
		List<FrmParametro> parametersQueryChild;
		FrmConsulta anexo=frmConsultaService.listName("CRUZAR_CANCELACIONES_RN");
		parametersQueryChild=this.frmParametroService.listParamsCosuType(anexo.getConscons());
		List<Object[]> listAllAnexo=frmConsultaService.loadListData(anexo, createParametersQueryChild(parameters, parametersQueryChild), parametersQueryChild);
		
		return listAllAnexo;
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

	private Map<String, Object> processCancelProvision(List<Object[]> listAllCancel, Map<String, Object> indexs) {
		
		//cancel 
		List<Object[]> result =new ArrayList<Object[]>();
		Object[] newRow;
		double subtotal,total=0,totalIva=0;
		for(Object[] row:listAllCancel)
		{
			newRow = new Object[row.length+1];
			System.arraycopy(row, 0, newRow, 0, row.length);
			subtotal = castToDouble(row[(int) indexs.get("indexPrimaAceptada")].toString()) + castToDouble(row[(int) indexs.get("indexPrimaDirecta")].toString()) + castToDouble(row[(int) indexs.get("indexCedidoCompania")].toString()) + castToDouble(row[(int) indexs.get("indexGastos")].toString());
			total+= subtotal;
			totalIva+= castToDouble(row[(int) indexs.get("indexIva")].toString());
			newRow[row.length]=subtotal;
			result.add(newRow);
		}
		
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("listAll", result);
		data.put("total", total);
		data.put("totalIva", totalIva);
		
		return data;
	}
}

package com.confianza.webapp.service.cierre.cierreregeneral;

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
import java.util.Date;
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
import com.confianza.webapp.service.email.sendEmail.SendEmail;
import com.confianza.webapp.service.excel.fileExcel.FileExcel;
import com.confianza.webapp.service.framework.frmconsulta.FrmConsultaService;
import com.confianza.webapp.service.framework.frmparametro.FrmParametroService;
import com.confianza.webapp.service.framework.frmtablas.FrmTablasService;
import com.confianza.webapp.service.security.userDetails;
import com.confianza.webapp.utils.Filter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class CierreGeneralServiceImpl implements CierreGeneralService{
	
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
	private FacEstaprocService facEstaprocService;
	
	@Autowired
	private SendEmail sendEmail;
	
	@Override
	public String executeProcessCierreGeneral(String conscons, String params, HttpServletRequest request){
		
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> parameters = gson.fromJson(params, type);//charsetString.convertUTF8ToISO88591(params)   								
		
		List<FrmConsulta> proceduresOfQuery = frmConsultaService.listProcedureChildren(conscons);
		
		FrmConsulta consulta=frmConsultaService.listId(conscons);
		List<CieEstaproc> activeEstaproc=facEstaprocService.listAll(0, 0, null, createFilters(consulta.getConsnomb()));
		Map<String, Object> resultProcess = startProcess(request, parameters, proceduresOfQuery, activeEstaproc, consulta);
		
		return gson.toJson(resultProcess);		
	}

	private Map<String, Object> startProcess(HttpServletRequest request, Map<String, Object> parameters, List<FrmConsulta> proceduresOfQuery, List<CieEstaproc> activeEstaproc, FrmConsulta consulta) {
		
		Map<String, Object> resultProcess = new HashMap<String, Object>();
		long porcentaje = 100/(proceduresOfQuery.size()+1);
		if(activeEstaproc.size()==0)
			resultProcess = doProcess(request, parameters, proceduresOfQuery, resultProcess, porcentaje, consulta);		
		else
			resultProcess.put("Eror", generateDescriptionProcessActives(activeEstaproc));
		return resultProcess;
	}

	private Map<String, Object> doProcess(HttpServletRequest request, Map<String, Object> parameters, List<FrmConsulta> proceduresOfQuery, Map<String, Object> resultProcess, long porcentaje, FrmConsulta consulta) {
		
		String Success="", error="";
		CieEstaproc cieEstaproc = facEstaprocService.insert(consulta.getConsnomb(), "Inicio de la ejecucion: "+consulta.getConsnomb(), userDetails.getUser(), "I");
		Map<String, Object> result;
		
		for(FrmConsulta procedure:proceduresOfQuery){
			result=executeProcedure(cieEstaproc, porcentaje, procedure, parameters, request);
			Success+=result.get("Success");
			error+=result.get("Eror");
		}
		
		result=new HashMap<String, Object>();
		result.put("Success", Success);
		result.put("Eror", error);
		
		facEstaprocService.closeFinal(cieEstaproc);
		
		sendNotification(request, consulta, cieEstaproc);
		
		return result;
	}

	private void sendNotification(HttpServletRequest request, FrmConsulta consulta, CieEstaproc cieEstaproc) {
		List<FrmTablas> listEmails=frmTablasService.listByCodi("email"+consulta.getConscons());
		String [] mails=new String[listEmails.size()];
		int posicion=0;
		for(FrmTablas usuario:listEmails){
			mails[posicion++]=usuario.getTablvast();
		}
		sendEmail.sendMessage("Cierre", "Finalizo el Proceso", "Finalizo la ejecucion de "+consulta.getConsnomb()+"\n "+cieEstaproc.getEsprdesc(), mails[0], mails, request);
	}

	private String generateDescriptionProcessActives( List<CieEstaproc> activeEstaproc) {
		String error="";
		for(CieEstaproc process:activeEstaproc)
			error+="El proceso: "+process.getEsprnomb()+", identificado con el ID: "+process.getEsprcons()+", por el usuario:"+process.getEspruser()+" Se encuentra en estado: "+process.getEspresta()+"<br>";
		return error;
	}
	
	private Map<String, Object> executeProcedure(CieEstaproc cieEstaproc, long porcentaje, FrmConsulta procedure, Map<String, Object> parameters, HttpServletRequest request){
		cieEstaproc=modifyEstaProc(cieEstaproc, 0, "\nInicio de la ejecucion del procedimiento: "+procedure.getConsnomb()+"\tHora: "+new Date(), null);
		String success="";
		String error="";
		
		List<FrmParametro> parametersProcedure = this.frmParametroService.listParamsCosuType(procedure.getConscons());
		Map<String, Object> parametersEnterProcedure = createParametersQueryChild(parameters, parametersProcedure);
		
		Map<String, Object> resultProcedure = new HashMap<String, Object>();
		Map<String, Object> result=new HashMap<String, Object>();
		try{
			resultProcedure = frmConsultaService.loadProcedure(procedure, parametersProcedure, parametersEnterProcedure, null);
			cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, "\nFin de la ejecucion del procedimiento: "+procedure.getConsnomb(), null);			
			
			if(resultProcedure.get("SUCCESS").equals("true"))	{
				List<FrmConsulta> querysOfProcedure = frmConsultaService.listQueryChilds(procedure.getConscons().toString());
				if(querysOfProcedure!=null){
					result = executeEachQueryChildren(request, parameters, querysOfProcedure, cieEstaproc, porcentaje);
					cieEstaproc = (CieEstaproc) result.get("cieEstaproc");
					success += (String) result.get("Success");
					error = (String) result.get("Eror");
				}
			}
			else{
				cieEstaproc=modifyEstaProc(cieEstaproc, 0, null, resultProcedure.get("EROR").toString());
				result=resultProcedure;
			}		
		}catch(Exception e){
			cieEstaproc=modifyEstaProc(cieEstaproc, 0, null, e.getCause()+"-"+e.getMessage()+"-"+e.getLocalizedMessage() );
		}
		
		result.put("Success", success);
		result.put("Eror", error);		
		result.put("cieEstaproc", cieEstaproc);
		return result;
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
		facEstaprocService.update(cieEstaproc);
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
			
			List<FrmTablas> listRutas=frmTablasService.listByCodi("ruta"+query.getConscons());
			String rutaArchivo;
			for(FrmTablas tabla:listRutas){
				rutaArchivo=tabla.getTablvast();
				
				if(fileExcel.generateExcel(rutaArchivo, query.getConsnomb()+".xls", listAll, query.getConscolu().split(","), query.getConstico().split(","), request)){
					success+="Archivo Generado: "+query.getConsnomb()+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\")+"<br>";
					cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, "\nFinalizo la ejecucion de la consulta: "+query.getConsnomb()+"\nArchivo Generado: "+query.getConsnomb()+".xls Ruta: "+rutaArchivo.replace("\\\\", "\\"), null);
				}
				else{
					error+="Se genero un error al crear el archivo: "+query.getConsnomb()+".xls";
					cieEstaproc=modifyEstaProc(cieEstaproc, porcentaje, null, "Se genero un error al crear el archivo: "+query.getConsnomb()+".xls");
				}
			}
		}		
		
		result.put("Success", success);
		result.put("Eror", error);		
		result.put("cieEstaproc", cieEstaproc);
		
		return result;
	}

	private Map<String, Object> createParametersQueryChild( Map<String, Object> parameters, List<FrmParametro> parametros) {
		Map<String, Object> parametersChild=new HashMap<String, Object>();			
		for(FrmParametro parameter:parametros)
			parametersChild.put(parameter.getParanomb(), parameters.get(parameter.getParanomb()));
		return parametersChild;
	}

}

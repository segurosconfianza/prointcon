package com.confianza.webapp.service.framework.frmconsulta;

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

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmconsulta.FrmConsultaRepository;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;
import com.confianza.webapp.service.framework.frmparametro.FrmParametroService;
import com.confianza.webapp.service.soporte.sopmotivo.SopMotivoService;
import com.confianza.webapp.utils.CharsetString;
import com.confianza.webapp.utils.JSONUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class FrmConsultaServiceImpl implements FrmConsultaService{
	
	@Autowired
	private FrmConsultaRepository frmConsultaRepository;
	
	@Autowired
	Gson gson;
	
	@Autowired
	private FrmParametroService frmParametroService;
	
	@Autowired
	private SopMotivoService sopMotivoService;
	
	private CharsetString charsetString=new CharsetString();
	
	/**
	 * @return the frmconsultaRepository
	 */
	public FrmConsultaRepository getFrmConsultaRepository() {
		return frmConsultaRepository;
	}

	/**
	 * @param frmconsultaRepository the frmconsultaRepository to set
	 */
	public void setFrmConsultaRepository(FrmConsultaRepository frmconsultaRepository) {
		this.frmConsultaRepository = frmconsultaRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMCONSULTA_ALL", "APP_FRMCONSULTA_READ"})
	public String list(Long id){
		
		return gson.toJson(frmConsultaRepository.list(id));
	}
	
	@Override	
	public FrmConsulta listName(String id){
		return frmConsultaRepository.listName(id);
	}
	
	@Override	
	public FrmConsulta listChild(String id){
		return frmConsultaRepository.listChild(id);
	}
	
	@Override	
	public FrmConsulta listProcedureChild(String id){
		return frmConsultaRepository.listProcedureChild(id);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOPORTE_ALL", "SOPORTE_READ"})
	public String loadRecord(String conscons, String params){
		
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> parameters=gson.fromJson(params, type);   						
		
		//carga la consulta dinamica
		FrmConsulta frmConsulta=this.listName(conscons);
		List<FrmParametro> parametros=this.frmParametroService.listParamsCosuType(new Long(conscons));
		
		//carga los datos de la consulta
		List<Object[]> rAll=this.loadListData(frmConsulta, parameters, parametros);
		//cast delresultado a ser mapeado por cada campo
		List<Map<String, Object>> listAll = JSONUtil.toNameList2(frmConsulta.getConscolu().split(","),rAll);			
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("camp", frmConsulta.getConscolu().split(","));

		return gson.toJson(result);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOPORTE_ALL", "SOPORTE_READ"})
	public String listCombo(String conscons){
		//carga la consulta dinamica
		FrmConsulta frmConsulta=this.listName(conscons);
		
		//carga los datos de la consulta
		List<Object[]> rAll=this.loadListData(frmConsulta, null, null);
		
		//cast de los menu a ser mapeados por cada campo
		List<Map<String, Object>> rolAll = JSONUtil.toNameList(
				new String[]{"value", "label"},rAll
		);											
						
		return gson.toJson(rolAll);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOPORTE_ALL", "SOPORTE_READ"})
	public String listComboDynamic(String conscons){
		
		//carga la consulta dinamica
		FrmConsulta frmConsulta=this.listName(conscons);
		
		//carga los datos de la consulta
		List<Object[]> rAll=this.loadListData(frmConsulta, null, null);		
	
		//cast de los menu a ser mapeados por cada campo
		List<Map<String, Object>> rolAll = JSONUtil.toNameList(
				new String[]{"value", "label"},rAll
		);											
			
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", rolAll);
		result.put("combo", conscons);
		
		return gson.toJson(result);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOPORTE_ALL", "SOPORTE_READ"})
	public List<Object[]> loadListData(FrmConsulta frmConsulta, Map<String, Object> parameters, List<FrmParametro> parametros){
		
		if(frmConsulta.getConscaco().equals("dataSource")){
			return frmConsultaRepository.loadData(frmConsulta, parameters);
		}
		else if(frmConsulta.getConscaco().equals("dataSourceOsiris")){
			return frmConsultaRepository.loadDataOsiris(frmConsulta, parameters, parametros);
		}
		return null;
		
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMCONSULTA_ALL", "APP_FRMCONSULTA_READ"})
	public String listAll(int pageSize, int page){
	
			int limit=pageSize*page;
			int init=limit-pageSize;
			
			List<FrmConsulta> listAll=frmConsultaRepository.listAll(init, limit);
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("data", listAll);
			result.put("count", this.getCount());
			
			return gson.toJson(result);		
	}	
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMCONSULTA_ALL", "APP_FRMCONSULTA_READ"})
	public int getCount(){
				
		return frmConsultaRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMCONSULTA_ALL", "APP_FRMCONSULTA_UPDATE"})
	public String update(FrmConsulta frmconsulta){
		
		return gson.toJson(frmConsultaRepository.update(frmconsulta));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMCONSULTA_ALL", "APP_FRMCONSULTA_DELETE"})
	public void delete(FrmConsulta frmconsulta){
		frmConsultaRepository.delete(frmconsulta);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMCONSULTA_ALL", "APP_FRMCONSULTA_CREATE"})
	public String insert(FrmConsulta frmconsulta){
		
			//frmconsulta.setesta("A");
			//frmconsulta.setfecr(new Date());
			
			return gson.toJson(frmConsultaRepository.insert(frmconsulta));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOPORTE_ALL", "SOPORTE_UPDATE"})
	public String updateRecord(String conscons, String params, String paramsData, ArrayList<MultipartFile> file){
		
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> parameters=gson.fromJson(params, type);//charsetString.convertUTF8ToISO88591(params)   						
		Map<String, Object> parametersData=gson.fromJson(paramsData, type);
		
		//carga la consulta dinamica					
		FrmConsulta frmConsulta=this.listProcedureChild(conscons);
		List<FrmParametro> parametros=this.frmParametroService.listParamsCosuType(new Long(conscons));
		
		Map<String, Object> p=this.loadProcedure(frmConsulta, parametros, parameters, parametersData);	
		
		return gson.toJson(p);								
	} 
	
	@Override
	public void uploadFiles(String motidesc, ArrayList<MultipartFile> file, String result){
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> resultData=gson.fromJson(result, type);
		
		try{
			Long transcons= Long.parseLong((resultData.get("TRANSACCION").toString()));
		
			this.sopMotivoService.insertMotivo(motidesc, transcons, file);
		}catch(NullPointerException e){
			
		}
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOPORTE_ALL", "SOPORTE_UPDATE"})
	public Map<String, Object> loadProcedure(FrmConsulta frmConsulta, List<FrmParametro> parametros, Map<String, Object> parameters, Map<String, Object> parametersData){
		
		if(frmConsulta.getConscaco().equals("dataSource")){
			return frmConsultaRepository.loadProcedure(frmConsulta, parametros, parameters, parametersData);
		}
		else if(frmConsulta.getConscaco().equals("dataSourceOsiris")){
			return frmConsultaRepository.loadProcedureOsiris(frmConsulta, parametros, parameters, parametersData);
		}
		return null;
		
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOPORTE_ALL", "SOPORTE_READ"})
	public String loadData(String conscons){
			        
		//carga la consulta dinamica
		FrmConsulta frmConsulta=this.listName(conscons);
							
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("titulo", frmConsulta.getConsnomb());
		result.put("descri", frmConsulta.getConsdesc());
		return gson.toJson(result);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOPORTE_ALL", "SOPORTE_READ"})
	public String loadConsChield(String conscons){
		
		//carga la consulta dinamica
		FrmConsulta frmConsulta=this.listChild(conscons);
		return gson.toJson(frmConsulta.getConscons());	
	}
}

package com.confianza.webapp.service.framework.frmparametro;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametroRepository;
import com.google.gson.Gson;

@Service
public class FrmParametroServiceImpl implements FrmParametroService{
	
	@Autowired
	private FrmParametroRepository frmParametroRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the frmparametroRepository
	 */
	public FrmParametroRepository getFrmParametroRepository() {
		return frmParametroRepository;
	}

	/**
	 * @param frmparametroRepository the frmparametroRepository to set
	 */
	public void setFrmParametroRepository(FrmParametroRepository frmparametroRepository) {
		this.frmParametroRepository = frmparametroRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PARAMETRO_ALL", "FRM_PARAMETRO_READ"})
	public String list(Long id){
		return gson.toJson(frmParametroRepository.list(id));
	}
	
	@Override
	public String listParamsCosu(Long id){
	
		Map<String, Object> result = new HashMap<String, Object>();
		List<FrmParametro> listAll=frmParametroRepository.listParamsCosu(id);
		
		result.put("data", listAll);
		return gson.toJson(result);
	}	
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PARAMETRO_ALL", "FRM_PARAMETRO_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize*page;
		int init=limit-pageSize;
		
		List<FrmParametro> listAll=frmParametroRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);
	}	
	
	@Override
	public int getCount(){
				
		return frmParametroRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PARAMETRO_ALL", "FRM_PARAMETRO_UPDATE"})
	public String update(FrmParametro frmparametro){
		return gson.toJson(frmParametroRepository.update(frmparametro));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PARAMETRO_ALL", "FRM_PARAMETRO_DELETE"})
	public void delete(FrmParametro frmparametro){
		frmParametroRepository.delete(frmparametro);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PARAMETRO_ALL", "FRM_PARAMETRO_CREATE"})
	public String insert(FrmParametro frmparametro){
		return gson.toJson(frmParametroRepository.insert(frmparametro));
	}
	
	@Override
	public List<FrmParametro> listParamsCosuType(Long id){
	
		return frmParametroRepository.listParamsCosu(id);
	}	
}

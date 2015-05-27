package com.confianza.webapp.service.formatos.fmtcampo;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.confianza.webapp.repository.formatos.fmtcampo.FmtCampo;
import com.confianza.webapp.repository.formatos.fmtcampo.FmtCampoRepository;

@Service
public class FmtCampoServiceImpl implements FmtCampoService{
	
	@Autowired
	private FmtCampoRepository fmtcampoRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the fmtcampoRepository
	 */
	public FmtCampoRepository getFmtCampoRepository() {
		return fmtcampoRepository;
	}

	/**
	 * @param fmtcampoRepository the fmtcampoRepository to set
	 */
	public void setFmtCampoRepository(FmtCampoRepository fmtcampoRepository) {
		this.fmtcampoRepository = fmtcampoRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_CAMPO_ALL", "FMT_CAMPO_READ"})
	public String list(Long id){
		FmtCampo listAll=fmtcampoRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_CAMPO_ALL", "FMT_CAMPO_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtCampo> listAll=fmtcampoRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return fmtcampoRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_CAMPO_ALL", "FMT_CAMPO_UPDATE"})
	public String update(FmtCampo fmtcampo){
		return gson.toJson(fmtcampoRepository.update(fmtcampo));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_CAMPO_ALL", "FMT_CAMPO_DELETE"})
	public void delete(FmtCampo fmtcampo){
		fmtcampoRepository.delete(fmtcampo);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_CAMPO_ALL", "FMT_CAMPO_CREATE"})
	public String insert(FmtCampo fmtcampo){
		return gson.toJson(fmtcampoRepository.insert(fmtcampo));
	}
	
	@Override
	public String listCamposCosu(Long id){
	
		Map<String, Object> result = new HashMap<String, Object>();
		List<FmtCampo> listAll=fmtcampoRepository.listCamposCosu(id);
		
		result.put("data", listAll);
		return gson.toJson(result);
	}
	
}

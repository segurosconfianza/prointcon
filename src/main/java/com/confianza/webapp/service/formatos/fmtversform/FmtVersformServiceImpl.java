package com.confianza.webapp.service.formatos.fmtversform;

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
import com.confianza.webapp.repository.formatos.fmtversform.FmtVersform;
import com.confianza.webapp.repository.formatos.fmtversform.FmtVersformRepository;

@Service
public class FmtVersformServiceImpl implements FmtVersformService{
	
	@Autowired
	private FmtVersformRepository fmtversformRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the fmtversformRepository
	 */
	public FmtVersformRepository getFmtVersformRepository() {
		return fmtversformRepository;
	}

	/**
	 * @param fmtversformRepository the fmtversformRepository to set
	 */
	public void setFmtVersformRepository(FmtVersformRepository fmtversformRepository) {
		this.fmtversformRepository = fmtversformRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VERSFORM_ALL", "FMT_VERSFORM_READ"})
	public String list(Long id){
		FmtVersform listAll=fmtversformRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VERSFORM_ALL", "FMT_VERSFORM_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtVersform> listAll=fmtversformRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return fmtversformRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VERSFORM_ALL", "FMT_VERSFORM_UPDATE"})
	public String update(FmtVersform fmtversform){
		return gson.toJson(fmtversformRepository.update(fmtversform));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VERSFORM_ALL", "FMT_VERSFORM_DELETE"})
	public void delete(FmtVersform fmtversform){
		fmtversformRepository.delete(fmtversform);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VERSFORM_ALL", "FMT_VERSFORM_CREATE"})
	public String insert(FmtVersform fmtversform){
		return gson.toJson(fmtversformRepository.insert(fmtversform));
	}
	
	@Override	
	public String lastVersion(Long id){
		FmtVersform listAll=fmtversformRepository.lastVersion(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		
		return gson.toJson(result);	
	}
	
	@Override	
	public FmtVersform lastVersionEntity(Long id){
		FmtVersform fmtVersform=fmtversformRepository.lastVersion(id);
		
		return fmtVersform;	
	}
	
}

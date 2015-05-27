package com.confianza.webapp.service.formatos.fmtvalocamp;

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
import com.confianza.webapp.repository.formatos.fmtvalocamp.FmtValocamp;
import com.confianza.webapp.repository.formatos.fmtvalocamp.FmtValocampRepository;

@Service
public class FmtValocampServiceImpl implements FmtValocampService{
	
	@Autowired
	private FmtValocampRepository fmtvalocampRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the fmtvalocampRepository
	 */
	public FmtValocampRepository getFmtValocampRepository() {
		return fmtvalocampRepository;
	}

	/**
	 * @param fmtvalocampRepository the fmtvalocampRepository to set
	 */
	public void setFmtValocampRepository(FmtValocampRepository fmtvalocampRepository) {
		this.fmtvalocampRepository = fmtvalocampRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VALOCAMP_ALL", "FMT_VALOCAMP_READ"})
	public String list(Long id){
		FmtValocamp listAll=fmtvalocampRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VALOCAMP_ALL", "FMT_VALOCAMP_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtValocamp> listAll=fmtvalocampRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return fmtvalocampRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VALOCAMP_ALL", "FMT_VALOCAMP_UPDATE"})
	public String update(FmtValocamp fmtvalocamp){
		return gson.toJson(fmtvalocampRepository.update(fmtvalocamp));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VALOCAMP_ALL", "FMT_VALOCAMP_DELETE"})
	public void delete(FmtValocamp fmtvalocamp){
		fmtvalocampRepository.delete(fmtvalocamp);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VALOCAMP_ALL", "FMT_VALOCAMP_CREATE"})
	public String insert(FmtValocamp fmtvalocamp){
		return gson.toJson(fmtvalocampRepository.insert(fmtvalocamp));
	}
	
}

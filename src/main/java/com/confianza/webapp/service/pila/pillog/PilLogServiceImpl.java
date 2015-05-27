package com.confianza.webapp.service.pila.pillog;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.confianza.webapp.repository.pila.pillog.PilLog;
import com.confianza.webapp.repository.pila.pillog.PilLogRepository;

@Service
public class PilLogServiceImpl implements PilLogService{
	
	@Autowired
	private PilLogRepository pillogRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the pillogRepository
	 */
	public PilLogRepository getPilLogRepository() {
		return pillogRepository;
	}

	/**
	 * @param pillogRepository the pillogRepository to set
	 */
	public void setPilLogRepository(PilLogRepository pillogRepository) {
		this.pillogRepository = pillogRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILLOG__ALL", "APP_PILLOG__READ"})
	public String list(Long id){
		PilLog listAll=pillogRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILLOG__ALL", "APP_PILLOG__READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<PilLog> listAll=pillogRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return pillogRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILLOG__ALL", "APP_PILLOG__UPDATE"})
	public String update(PilLog pillog){
		return gson.toJson(pillogRepository.update(pillog));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILLOG__ALL", "APP_PILLOG__DELETE"})
	public void delete(PilLog pillog){
		pillogRepository.delete(pillog);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILLOG__ALL", "APP_PILLOG__CREATE"})
	public String insert(PilLog pillog){
		return gson.toJson(pillogRepository.insert(pillog));
	}
	
}

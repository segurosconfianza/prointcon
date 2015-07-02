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
	private PilLogRepository pilLogRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the pillogRepository
	 */
	public PilLogRepository getPilLogRepository() {
		return pilLogRepository;
	}

	/**
	 * @param pillogRepository the pillogRepository to set
	 */
	public void setPilLogRepository(PilLogRepository pillogRepository) {
		this.pilLogRepository = pillogRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_LOG_ALL", "PIL_LOG_READ"})
	public String list(Long id){
		PilLog listAll=pilLogRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_LOG_ALL", "PIL_LOG_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<PilLog> listAll=pilLogRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return pilLogRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_LOG_ALL", "PIL_LOG_UPDATE"})
	public String update(PilLog pillog){
		return gson.toJson(pilLogRepository.update(pillog));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_LOG_ALL", "PIL_LOG_DELETE"})
	public void delete(PilLog pillog){
		pilLogRepository.delete(pillog);
	}
	
	@Override
	public String insert(PilLog pillog){
		return gson.toJson(pilLogRepository.insert(pillog));
	}
	
}

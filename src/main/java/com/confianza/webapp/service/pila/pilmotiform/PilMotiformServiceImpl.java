package com.confianza.webapp.service.pila.pilmotiform;

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
import com.confianza.webapp.repository.pila.pilmotiform.PilMotiform;
import com.confianza.webapp.repository.pila.pilmotiform.PilMotiformRepository;

@Service
public class PilMotiformServiceImpl implements PilMotiformService{
	
	@Autowired
	private PilMotiformRepository pilMotiformRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the pilmotiformRepository
	 */
	public PilMotiformRepository getPilMotiformRepository() {
		return pilMotiformRepository;
	}

	/**
	 * @param pilmotiformRepository the pilmotiformRepository to set
	 */
	public void setPilMotiformRepository(PilMotiformRepository pilmotiformRepository) {
		this.pilMotiformRepository = pilmotiformRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILMOTIFORM__ALL", "APP_PILMOTIFORM__READ"})
	public String list(Long id){
		PilMotiform listAll=pilMotiformRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILMOTIFORM__ALL", "APP_PILMOTIFORM__READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<PilMotiform> listAll=pilMotiformRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return pilMotiformRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILMOTIFORM__ALL", "APP_PILMOTIFORM__UPDATE"})
	public String update(PilMotiform pilmotiform){
		return gson.toJson(pilMotiformRepository.update(pilmotiform));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILMOTIFORM__ALL", "APP_PILMOTIFORM__DELETE"})
	public void delete(PilMotiform pilmotiform){
		pilMotiformRepository.delete(pilmotiform);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILMOTIFORM__ALL", "APP_PILMOTIFORM__CREATE"})
	public String insert(PilMotiform pilmotiform){
		return gson.toJson(pilMotiformRepository.insert(pilmotiform));
	}
	
}

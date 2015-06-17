package com.confianza.webapp.service.pila.pilmotivo;

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
import com.confianza.webapp.repository.pila.pilmotivo.PilMotivo;
import com.confianza.webapp.repository.pila.pilmotivo.PilMotivoRepository;

@Service
public class PilMotivoServiceImpl implements PilMotivoService{
	
	@Autowired
	private PilMotivoRepository pilmotivoRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the pilmotivoRepository
	 */
	public PilMotivoRepository getPilMotivoRepository() {
		return pilmotivoRepository;
	}

	/**
	 * @param pilmotivoRepository the pilmotivoRepository to set
	 */
	public void setPilMotivoRepository(PilMotivoRepository pilmotivoRepository) {
		this.pilmotivoRepository = pilmotivoRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILMOTIVO__ALL", "APP_PILMOTIVO__READ"})
	public String list(Long id){
		PilMotivo listAll=pilmotivoRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILMOTIVO__ALL", "APP_PILMOTIVO__READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<PilMotivo> listAll=pilmotivoRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return pilmotivoRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILMOTIVO__ALL", "APP_PILMOTIVO__UPDATE"})
	public String update(PilMotivo pilmotivo){
		return gson.toJson(pilmotivoRepository.update(pilmotivo));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILMOTIVO__ALL", "APP_PILMOTIVO__DELETE"})
	public void delete(PilMotivo pilmotivo){
		pilmotivoRepository.delete(pilmotivo);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILMOTIVO__ALL", "APP_PILMOTIVO__CREATE"})
	public String insert(PilMotivo pilmotivo){
		return gson.toJson(pilmotivoRepository.insert(pilmotivo));
	}
	
}
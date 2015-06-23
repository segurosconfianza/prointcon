package com.confianza.webapp.service.pila.pilauditoria;

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
import com.confianza.webapp.repository.pila.pilauditoria.PilAuditoria;
import com.confianza.webapp.repository.pila.pilauditoria.PilAuditoriaRepository;

@Service
public class PilAuditoriaServiceImpl implements PilAuditoriaService{
	
	@Autowired
	private PilAuditoriaRepository pilAuditoriaRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the pilauditoriaRepository
	 */
	public PilAuditoriaRepository getPilAuditoriaRepository() {
		return pilAuditoriaRepository;
	}

	/**
	 * @param pilauditoriaRepository the pilauditoriaRepository to set
	 */
	public void setPilAuditoriaRepository(PilAuditoriaRepository pilauditoriaRepository) {
		this.pilAuditoriaRepository = pilauditoriaRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILAUDITORIA__ALL", "APP_PILAUDITORIA__READ"})
	public String list(Long id){
		PilAuditoria listAll=pilAuditoriaRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILAUDITORIA__ALL", "APP_PILAUDITORIA__READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<PilAuditoria> listAll=pilAuditoriaRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return pilAuditoriaRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILAUDITORIA__ALL", "APP_PILAUDITORIA__UPDATE"})
	public String update(PilAuditoria pilauditoria){
		return gson.toJson(pilAuditoriaRepository.update(pilauditoria));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILAUDITORIA__ALL", "APP_PILAUDITORIA__DELETE"})
	public void delete(PilAuditoria pilauditoria){
		pilAuditoriaRepository.delete(pilauditoria);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILAUDITORIA__ALL", "APP_PILAUDITORIA__CREATE"})
	public String insert(PilAuditoria pilauditoria){
		return gson.toJson(pilAuditoriaRepository.insert(pilauditoria));
	}
	
}

package com.confianza.webapp.service.pila.pilususucu;

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
import com.confianza.webapp.repository.pila.pilususucu.PilUsusucu;
import com.confianza.webapp.repository.pila.pilususucu.PilUsusucuRepository;

@Service
public class PilUsusucuServiceImpl implements PilUsusucuService{
	
	@Autowired
	private PilUsusucuRepository pilususucuRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the pilususucuRepository
	 */
	public PilUsusucuRepository getPilUsusucuRepository() {
		return pilususucuRepository;
	}

	/**
	 * @param pilususucuRepository the pilususucuRepository to set
	 */
	public void setPilUsusucuRepository(PilUsusucuRepository pilususucuRepository) {
		this.pilususucuRepository = pilususucuRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILUSUSUCU__ALL", "APP_PILUSUSUCU__READ"})
	public String list(Long id){
		PilUsusucu listAll=pilususucuRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILUSUSUCU__ALL", "APP_PILUSUSUCU__READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<PilUsusucu> listAll=pilususucuRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return pilususucuRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILUSUSUCU__ALL", "APP_PILUSUSUCU__UPDATE"})
	public String update(PilUsusucu pilususucu){
		return gson.toJson(pilususucuRepository.update(pilususucu));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILUSUSUCU__ALL", "APP_PILUSUSUCU__DELETE"})
	public void delete(PilUsusucu pilususucu){
		pilususucuRepository.delete(pilususucu);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_PILUSUSUCU__ALL", "APP_PILUSUSUCU__CREATE"})
	public String insert(PilUsusucu pilususucu){
		return gson.toJson(pilususucuRepository.insert(pilususucu));
	}
	
}

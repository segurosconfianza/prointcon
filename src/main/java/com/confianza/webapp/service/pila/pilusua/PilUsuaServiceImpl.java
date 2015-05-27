package com.confianza.webapp.service.pila.pilusua;

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
import org.springframework.web.bind.annotation.PathVariable;

import com.google.gson.Gson;
import com.confianza.webapp.repository.pila.pilusua.PilUsua;
import com.confianza.webapp.repository.pila.pilusua.PilUsuaRepository;

@Service
public class PilUsuaServiceImpl implements PilUsuaService{
	
	@Autowired
	private PilUsuaRepository pilusuaRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the pilusuaRepository
	 */
	public PilUsuaRepository getPilUsuaRepository() {
		return pilusuaRepository;
	}

	/**
	 * @param pilusuaRepository the pilusuaRepository to set
	 */
	public void setPilUsuaRepository(PilUsuaRepository pilusuaRepository) {
		this.pilusuaRepository = pilusuaRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_READ"})
	public String list(Long id){
		PilUsua listAll=pilusuaRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<PilUsua> listAll=pilusuaRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return pilusuaRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_UPDATE"})
	public String update(PilUsua pilusua){
		return gson.toJson(pilusuaRepository.update(pilusua));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_DELETE"})
	public void delete(PilUsua pilusua){
		pilusuaRepository.delete(pilusua);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_CREATE"})
	public String insert(PilUsua pilusua){
		return gson.toJson(pilusuaRepository.insert(pilusua));
	}
	
	@Override
	public String validateUsua(String user, String password){
		
		PilUsua usuario=pilusuaRepository.validateUsua(user,password);
		if(usuario!=null)
			return gson.toJson("true");
		else
			return gson.toJson("false");
	}
}

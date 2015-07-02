package com.confianza.webapp.service.pila.pilmotiform;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.confianza.webapp.repository.pila.pilmotiform.PilMotiform;
import com.confianza.webapp.repository.pila.pilmotiform.PilMotiformRepository;
import com.confianza.webapp.service.formatos.fmtformregi.FmtFormregiService;
import com.confianza.webapp.service.security.userDetails;
import com.confianza.webapp.utils.Filter;

import java.lang.reflect.Type;

@Service
public class PilMotiformServiceImpl implements PilMotiformService{
	
	@Autowired
	private FmtFormregiService fmtFormregiService;
	
	@Autowired
	private PilMotiformRepository pilmotiformRepository;
	
	@Autowired
	userDetails userDetails;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the pilmotiformRepository
	 */
	public PilMotiformRepository getPilMotiformRepository() {
		return pilmotiformRepository;
	}

	/**
	 * @param pilmotiformRepository the pilmotiformRepository to set
	 */
	public void setPilMotiformRepository(PilMotiformRepository pilmotiformRepository) {
		this.pilmotiformRepository = pilmotiformRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_MOTIFORM_ALL", "PIL_MOTIFORM_READ"})
	public String list(Long id){
		PilMotiform listAll=pilmotiformRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(null));
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_MOTIFORM_ALL", "PIL_MOTIFORM_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<PilMotiform> listAll=pilmotiformRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return pilmotiformRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_MOTIFORM_ALL", "PIL_MOTIFORM_UPDATE"})
	public String update(PilMotiform pilmotiform){
		return gson.toJson(pilmotiformRepository.update(pilmotiform));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_MOTIFORM_ALL", "PIL_MOTIFORM_DELETE"})
	public void delete(PilMotiform pilmotiform){
		pilmotiformRepository.delete(pilmotiform);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_MOTIFORM_ALL", "PIL_MOTIFORM_CREATE"})
	public String insert(PilMotiform pilmotiform){
		
		pilmotiformRepository.insert(pilmotiform);
		
		Map<String, Object> result = new HashMap<String, Object>();
		if(pilmotiform.getMofocons()!=null || !pilmotiform.getMofocons().equals(0))
			result.put("data", "Se registro el motivo id: "+pilmotiform.getMofocons());
		else{
			result.put("error", "Error al registrar el motivo");
			result.put("tituloError", "Error de registro");
		}
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_MOTIFORM_ALL", "PIL_MOTIFORM_CREATE"})
	public String insertDevolucion(PilMotiform pilmotiform){
		
		pilmotiform.setMofofech(new Date());
		pilmotiform.setMofouser(userDetails.getUser());
		pilmotiformRepository.insert(pilmotiform);
		
		fmtFormregiService.devolverRecord(pilmotiform.getMofofore());
		
		Map<String, Object> result = new HashMap<String, Object>();
		if(pilmotiform.getMofocons()!=null || !pilmotiform.getMofocons().equals(0))
			result.put("data", "Se registro el motivo id: "+pilmotiform.getMofocons());
		else{
			result.put("error", "Error al registrar el motivo");
			result.put("tituloError", "Error de registro");
		}
		
		return gson.toJson(result);	
	}
	
}

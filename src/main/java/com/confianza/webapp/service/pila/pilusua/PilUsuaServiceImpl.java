package com.confianza.webapp.service.pila.pilusua;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.confianza.webapp.repository.framework.security.Person;
import com.confianza.webapp.repository.framework.security.PersonAttributesMapperImpl;
import com.confianza.webapp.repository.pila.pilusua.PilUsua;
import com.confianza.webapp.repository.pila.pilusua.PilUsuaRepository;
import com.confianza.webapp.service.security.AutenticateImpl;
import com.confianza.webapp.utils.Filter;

@Service
public class PilUsuaServiceImpl implements PilUsuaService{
	
	@Autowired
	private PilUsuaRepository pilUsuaRepository;
	
	@Autowired
	private	AutenticateImpl autenticateImpl;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the pilusuaRepository
	 */
	public PilUsuaRepository getPilUsuaRepository() {
		return pilUsuaRepository;
	}

	/**
	 * @param pilusuaRepository the pilusuaRepository to set
	 */
	public void setPilUsuaRepository(PilUsuaRepository pilusuaRepository) {
		this.pilUsuaRepository = pilusuaRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_READ"})
	public String list(Long id){
		PilUsua listAll=pilUsuaRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		//result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<PilUsua> listAll=pilUsuaRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return pilUsuaRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_UPDATE"})
	public String update(PilUsua pilusua){
		return gson.toJson(pilUsuaRepository.update(pilusua));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_DELETE"})
	public void delete(PilUsua pilusua){
		pilUsuaRepository.delete(pilusua);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_CREATE"})
	public String insert(PilUsua pilusua){
		return gson.toJson(pilUsuaRepository.insert(pilusua));
	}
	
	@Override
	public String validateUsua(String user, String password){
		
		PilUsua usuario=pilUsuaRepository.validateUsua(user,password);
		if(usuario!=null)
			return gson.toJson("true");
		else
			return gson.toJson("false");
	}
	
	@Override
	public List<PilUsua> listAllFormregi(List<Long> codigosFormRegi){
		return pilUsuaRepository.listAllFormregi(codigosFormRegi);
	}
	
	@Override
	public String validateUser(String username){
		
		return gson.toJson(autenticateImpl.validateUser(username));
	}
}

package com.confianza.webapp.service.osiris.osisucursal;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		osiris  
  */                          

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.confianza.webapp.repository.osiris.osisucursal.OsiSucursal;
import com.confianza.webapp.repository.osiris.osisucursal.OsiSucursalRepository;
import com.confianza.webapp.utils.Filter;
import com.google.gson.reflect.TypeToken;

@Service
public class OsiSucursalServiceImpl implements OsiSucursalService{
	
	@Autowired
	private OsiSucursalRepository osisucursalRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the osisucursalRepository
	 */
	public OsiSucursalRepository getOsiSucursalRepository() {
		return osisucursalRepository;
	}

	/**
	 * @param osisucursalRepository the osisucursalRepository to set
	 */
	public void setOsiSucursalRepository(OsiSucursalRepository osisucursalRepository) {
		this.osisucursalRepository = osisucursalRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "OSI_SUCURSAL_ALL", "OSI_SUCURSAL_READ"})
	public String list(Long id){
		OsiSucursal listAll=osisucursalRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override	
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
				
		List<Filter> filters = null;
		if(stringFilters!=null)
		  filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<OsiSucursal> listAll=osisucursalRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return osisucursalRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "OSI_SUCURSAL_ALL", "OSI_SUCURSAL_UPDATE"})
	public String update(OsiSucursal osisucursal){
		return gson.toJson(osisucursalRepository.update(osisucursal));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "OSI_SUCURSAL_ALL", "OSI_SUCURSAL_DELETE"})
	public void delete(OsiSucursal osisucursal){
		osisucursalRepository.delete(osisucursal);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "OSI_SUCURSAL_ALL", "OSI_SUCURSAL_CREATE"})
	public String insert(OsiSucursal osisucursal){
		return gson.toJson(osisucursalRepository.insert(osisucursal));
	}
	
}

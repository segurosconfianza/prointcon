package com.confianza.webapp.service.pila.pilususucu;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.confianza.webapp.repository.pila.pilusua.PilUsua;
import com.confianza.webapp.repository.pila.pilususucu.PilUsusucu;
import com.confianza.webapp.repository.pila.pilususucu.PilUsusucuRepository;
import com.confianza.webapp.service.security.userDetails;
import com.confianza.webapp.utils.Filter;
import com.confianza.webapp.utils.JSONUtil;

@Service
public class PilUsusucuServiceImpl implements PilUsusucuService{
	
	@Autowired
	private PilUsusucuRepository pilUsusucuRepository;
	
	@Autowired
	userDetails userDetails;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the pilususucuRepository
	 */
	public PilUsusucuRepository getPilUsusucuRepository() {
		return pilUsusucuRepository;
	}

	/**
	 * @param pilususucuRepository the pilususucuRepository to set
	 */
	public void setPilUsusucuRepository(PilUsusucuRepository pilususucuRepository) {
		this.pilUsusucuRepository = pilususucuRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUSUCU_ALL", "PIL_USUSUCU_READ"})
	public String list(Long id){
		PilUsusucu listAll=pilUsusucuRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUSUCU_ALL", "PIL_USUSUCU_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<PilUsusucu> listAll=pilUsusucuRepository.listAll(init, limit, order, filters); 
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUSUCU_ALL", "PIL_USUSUCU_READ"})
	public String listAllAnalistas(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<Object[]> listAllAnalistas=pilUsusucuRepository.listAllAnalistas(init, limit, order, filters); 
		
		//cast a ser mapeados por cada campo
		List<Map<String, Object>> listAll = JSONUtil.toNameList(this.getColumMap(),listAllAnalistas);
				
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCountAnalistas(filters));
		
		return gson.toJson(result);	
	}
	
	private String[] getColumMap() {
		String columnas[] = new String[PilUsusucu.getNames().length+PilUsua.getNames().length];
		System.arraycopy(PilUsusucu.getNames(), 0, columnas, 0, PilUsusucu.getNames().length);
		System.arraycopy(PilUsua.getNames(), 0, columnas, PilUsusucu.getNames().length, PilUsua.getNames().length);
		return columnas;
	}
	
	@Override
	public int getCount(List<Filter> filters){
				
		return pilUsusucuRepository.getCount(filters); 
	}
	
	@Override
	public int getCountAnalistas(List<Filter> filters){
				
		return pilUsusucuRepository.getCountAnalistas(filters); 
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUSUCU_ALL", "PIL_USUSUCU_UPDATE"})
	public String update(PilUsusucu pilususucu){
		return gson.toJson(pilUsusucuRepository.update(pilususucu));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUSUCU_ALL", "PIL_USUSUCU_DELETE"})
	public void delete(PilUsusucu pilususucu){
		pilUsusucuRepository.delete(pilususucu);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUSUCU_ALL", "PIL_USUSUCU_CREATE"})  
	public String insert(PilUsusucu pilususucu){
		return gson.toJson(pilUsusucuRepository.insert(pilususucu));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUSUCU_ALL", "PIL_USUSUCU_READ"})
	public String listSucur(){
	
		List<PilUsusucu> listAll=pilUsusucuRepository.listSucur(userDetails.getUser());
		
		List<Long> sucurs=new ArrayList<Long>();
		for(PilUsusucu sucursal:listAll)
			sucurs.add(sucursal.getUssusucu());
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", sucurs);
		
		return gson.toJson(result);	
	}
}

package com.confianza.webapp.service.cierre.cieestaproc;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		cierre  
  */                          

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.confianza.webapp.repository.cierre.cieestaproc.CieEstaproc;
import com.confianza.webapp.repository.cierre.cieestaproc.CieEstaprocRepository;
import com.confianza.webapp.utils.Filter;
import com.google.gson.reflect.TypeToken;

@Service
public class CieEstaprocServiceImpl implements FacEstaprocService{
	
	@Autowired
	private CieEstaprocRepository cieestaprocRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the cieestaprocRepository
	 */
	public CieEstaprocRepository getCieEstaprocRepository() {
		return cieestaprocRepository;
	}

	/**
	 * @param cieestaprocRepository the cieestaprocRepository to set
	 */
	public void setCieEstaprocRepository(CieEstaprocRepository cieestaprocRepository) {
		this.cieestaprocRepository = cieestaprocRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "CIE_ESTAPROC_ALL", "CIE_ESTAPROC_READ"})
	public String list(Long id){
		CieEstaproc listAll=cieestaprocRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "CIE_ESTAPROC_ALL", "CIE_ESTAPROC_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = null;
		if(stringFilters!=null)
		  filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<CieEstaproc> listAll=cieestaprocRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "CIE_ESTAPROC_ALL", "CIE_ESTAPROC_READ"})
	public List<CieEstaproc> listAll(int pageSize, int page, String order, List<Filter> filters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<CieEstaproc> listAll=cieestaprocRepository.listAll(init, limit, order, filters);				
		
		return listAll;	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return cieestaprocRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "CIE_ESTAPROC_ALL", "CIE_ESTAPROC_UPDATE"})
	public String update(CieEstaproc cieestaproc){
		return gson.toJson(cieestaprocRepository.update(cieestaproc));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "CIE_ESTAPROC_ALL", "CIE_ESTAPROC_DELETE"})
	public void delete(CieEstaproc cieestaproc){
		cieestaprocRepository.delete(cieestaproc);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "CIE_ESTAPROC_ALL", "CIE_ESTAPROC_CREATE"})
	public String insert(CieEstaproc cieestaproc){
		return gson.toJson(cieestaprocRepository.insert(cieestaproc));
	}
	
	@Override	
	public CieEstaproc insert(String esprnomb, String esprdesc, String espruser, String espresta){
		CieEstaproc cieestaproc=createCieEstaproc(esprnomb, esprdesc, espruser, espresta);
		
		return cieestaprocRepository.insert(cieestaproc);
	}

	@Override	
	public CieEstaproc closeFinal(CieEstaproc cieestaproc){
		cieestaproc.setEsprporc((long) 100);
		cieestaproc.setEsprdesc(cieestaproc.getEsprdesc()+"\nEl proceso finalizo");
		cieestaproc.setEspresta("F");
		cieestaproc.setEsprfefi(new Date());
		cieestaproc.setEsprduho(calculateHours(cieestaproc.getEsprfefi(), cieestaproc.getEsprfein()));		
		return cieestaprocRepository.update(cieestaproc);
	}
	
	private double calculateHours(Date fefi, Date feini){
		double diferenciaEn_ms = fefi.getTime() - feini.getTime();
		double hours = diferenciaEn_ms / (1000 * 60 * 60);
		return hours;
	}
	
	private CieEstaproc createCieEstaproc(String esprnomb, String esprdesc, String espruser, String espresta) {
		CieEstaproc cieestaproc=new CieEstaproc();
		cieestaproc.setEsprnomb(esprnomb);
		cieestaproc.setEsprdesc(esprdesc);
		cieestaproc.setEspresta(espresta);
		cieestaproc.setEspruser(espruser);
		
		cieestaproc.setEsprduho(0.0);
		cieestaproc.setEspreror("");		
		cieestaproc.setEsprfein(new Date());
		cieestaproc.setEsprporc(new Long(0));
		return cieestaproc;
	}
	
}

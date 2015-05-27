package com.confianza.webapp.service.formatos.fmtformregi;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregi;
import com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregiRepository;

@Service
public class FmtFormregiServiceImpl implements FmtFormregiService{
	
	@Autowired
	private FmtFormregiRepository fmtformregiRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the fmtformregiRepository
	 */
	public FmtFormregiRepository getFmtFormregiRepository() {
		return fmtformregiRepository;
	}

	/**
	 * @param fmtformregiRepository the fmtformregiRepository to set
	 */
	public void setFmtFormregiRepository(FmtFormregiRepository fmtformregiRepository) {
		this.fmtformregiRepository = fmtformregiRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_READ"})
	public String list(Long id){
		FmtFormregi listAll=fmtformregiRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtFormregi> listAll=fmtformregiRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return fmtformregiRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_UPDATE"})
	public String update(FmtFormregi fmtformregi){
		return gson.toJson(fmtformregiRepository.update(fmtformregi));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_DELETE"})
	public void delete(FmtFormregi fmtformregi){
		fmtformregiRepository.delete(fmtformregi);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_CREATE"})
	public String insert(FmtFormregi fmtformregi){
		return gson.toJson(fmtformregiRepository.insert(fmtformregi));
	}
	
}

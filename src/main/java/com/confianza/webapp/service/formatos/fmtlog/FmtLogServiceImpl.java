package com.confianza.webapp.service.formatos.fmtlog;

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
import com.confianza.webapp.repository.formatos.fmtlog.FmtLog;
import com.confianza.webapp.repository.formatos.fmtlog.FmtLogRepository;

@Service
public class FmtLogServiceImpl implements FmtLogService{
	
	@Autowired
	private FmtLogRepository fmtLogRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the fmtlogRepository
	 */
	public FmtLogRepository getFmtLogRepository() {
		return fmtLogRepository;
	}

	/**
	 * @param fmtlogRepository the fmtlogRepository to set
	 */
	public void setFmtLogRepository(FmtLogRepository fmtlogRepository) {
		this.fmtLogRepository = fmtlogRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_LOG_ALL", "FMT_LOG_READ"})
	public String list(Long id){
		FmtLog listAll=fmtLogRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_LOG_ALL", "FMT_LOG_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtLog> listAll=fmtLogRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return fmtLogRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_LOG_ALL", "FMT_LOG_UPDATE"})
	public String update(FmtLog fmtlog){
		return gson.toJson(fmtLogRepository.update(fmtlog));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_LOG_ALL", "FMT_LOG_DELETE"})
	public void delete(FmtLog fmtlog){
		fmtLogRepository.delete(fmtlog);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_LOG_ALL", "FMT_LOG_CREATE"})
	public String insert(FmtLog fmtlog){
		return gson.toJson(fmtLogRepository.insert(fmtlog));
	}
	
	@Override
	public String insertIntermediario(FmtLog fmtlog){
		return gson.toJson(fmtLogRepository.insert(fmtlog));  
	}
}

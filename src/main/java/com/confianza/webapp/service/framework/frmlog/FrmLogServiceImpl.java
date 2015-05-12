package com.confianza.webapp.service.framework.frmlog;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.confianza.webapp.repository.framework.frmlog.FrmLog;
import com.confianza.webapp.repository.framework.frmlog.FrmLogRepository;

@Service
public class FrmLogServiceImpl implements FrmLogService{
	
	@Autowired
	private FrmLogRepository frmlogRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the frmlogRepository
	 */
	public FrmLogRepository getFrmLogRepository() {
		return frmlogRepository;
	}

	/**
	 * @param frmlogRepository the frmlogRepository to set
	 */
	public void setFrmLogRepository(FrmLogRepository frmlogRepository) {
		this.frmlogRepository = frmlogRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_LOG_ALL", "FRM_LOG_READ"})
	public String list(Long id){
		FrmLog listAll=frmlogRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_LOG_ALL", "FRM_LOG_READ"})
	public String listAll(int pageSize, int page, Long slogtran){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FrmLog> listAll=frmlogRepository.listAll(init, limit, slogtran);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(slogtran));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(Long slogtran){
				
		return frmlogRepository.getCount(slogtran);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_LOG_ALL", "FRM_LOG_UPDATE"})
	public String update(FrmLog frmlog){
		return gson.toJson(frmlogRepository.update(frmlog));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_LOG_ALL", "FRM_LOG_DELETE"})
	public void delete(FrmLog frmlog){
		frmlogRepository.delete(frmlog);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_LOG_ALL", "FRM_LOG_CREATE"})
	public String insert(FrmLog frmlog){
		return gson.toJson(frmlogRepository.insert(frmlog));
	}
	
}

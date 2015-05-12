package com.confianza.webapp.service.framework.frmauditoria;

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
import com.confianza.webapp.repository.framework.frmauditoria.FrmAuditoria;
import com.confianza.webapp.repository.framework.frmauditoria.FrmAuditoriaRepository;

@Service
public class FrmAuditoriaServiceImpl implements FrmAuditoriaService{
	
	@Autowired
	private FrmAuditoriaRepository frmauditoriaRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the frmauditoriaRepository
	 */
	public FrmAuditoriaRepository getFrmAuditoriaRepository() {
		return frmauditoriaRepository;
	}

	/**
	 * @param frmauditoriaRepository the frmauditoriaRepository to set
	 */
	public void setFrmAuditoriaRepository(FrmAuditoriaRepository frmauditoriaRepository) {
		this.frmauditoriaRepository = frmauditoriaRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_AUDITORIA_ALL", "FRM_AUDITORIA_READ"})
	public String list(Long id){
		FrmAuditoria listAll=frmauditoriaRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_AUDITORIA_ALL", "FRM_AUDITORIA_READ"})
	public String listAll(int pageSize, int page, Long auditran){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FrmAuditoria> listAll=frmauditoriaRepository.listAll(init, limit, auditran);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(auditran));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(Long auditran){
				
		return frmauditoriaRepository.getCount(auditran);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_AUDITORIA_ALL", "FRM_AUDITORIA_UPDATE"})
	public String update(FrmAuditoria frmauditoria){
		return gson.toJson(frmauditoriaRepository.update(frmauditoria));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_AUDITORIA_ALL", "FRM_AUDITORIA_DELETE"})
	public void delete(FrmAuditoria frmauditoria){
		frmauditoriaRepository.delete(frmauditoria);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_AUDITORIA_ALL", "FRM_AUDITORIA_CREATE"})
	public String insert(FrmAuditoria frmauditoria){
		return gson.toJson(frmauditoriaRepository.insert(frmauditoria));
	}
	
}

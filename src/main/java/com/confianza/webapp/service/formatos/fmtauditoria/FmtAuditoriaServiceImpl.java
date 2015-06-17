package com.confianza.webapp.service.formatos.fmtauditoria;

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
import com.confianza.webapp.repository.formatos.fmtauditoria.FmtAuditoria;
import com.confianza.webapp.repository.formatos.fmtauditoria.FmtAuditoriaRepository;

@Service
public class FmtAuditoriaServiceImpl implements FmtAuditoriaService{
	
	@Autowired
	private FmtAuditoriaRepository fmtauditoriaRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the fmtauditoriaRepository
	 */
	public FmtAuditoriaRepository getFmtAuditoriaRepository() {
		return fmtauditoriaRepository;
	}

	/**
	 * @param fmtauditoriaRepository the fmtauditoriaRepository to set
	 */
	public void setFmtAuditoriaRepository(FmtAuditoriaRepository fmtauditoriaRepository) {
		this.fmtauditoriaRepository = fmtauditoriaRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_AUDITORIA_ALL", "FMT_AUDITORIA_READ"})
	public String list(Long id){
		FmtAuditoria listAll=fmtauditoriaRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_AUDITORIA_ALL", "FMT_AUDITORIA_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtAuditoria> listAll=fmtauditoriaRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return fmtauditoriaRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_AUDITORIA_ALL", "FMT_AUDITORIA_UPDATE"})
	public String update(FmtAuditoria fmtauditoria){
		return gson.toJson(fmtauditoriaRepository.update(fmtauditoria));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_AUDITORIA_ALL", "FMT_AUDITORIA_DELETE"})
	public void delete(FmtAuditoria fmtauditoria){
		fmtauditoriaRepository.delete(fmtauditoria);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_AUDITORIA_ALL", "FMT_AUDITORIA_CREATE"})
	public String insert(FmtAuditoria fmtauditoria){
		return gson.toJson(fmtauditoriaRepository.insert(fmtauditoria));
	}
	
	@Override
	public String listAll(int pageSize, int page, long forecons){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtAuditoria> listAll=fmtauditoriaRepository.listAll(init, limit, forecons);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
}

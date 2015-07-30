package com.confianza.webapp.service.formatos.fmtauditoria;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.confianza.webapp.repository.formatos.fmtauditoria.FmtAuditoria;
import com.confianza.webapp.repository.formatos.fmtauditoria.FmtAuditoriaRepository;
import com.confianza.webapp.utils.Filter;

@Service
public class FmtAuditoriaServiceImpl implements FmtAuditoriaService{
	
	@Autowired
	private FmtAuditoriaRepository fmtAuditoriaRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the fmtauditoriaRepository
	 */
	public FmtAuditoriaRepository getFmtAuditoriaRepository() {
		return fmtAuditoriaRepository;
	}

	/**
	 * @param fmtauditoriaRepository the fmtauditoriaRepository to set
	 */
	public void setFmtAuditoriaRepository(FmtAuditoriaRepository fmtauditoriaRepository) {
		this.fmtAuditoriaRepository = fmtauditoriaRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_AUDITORIA_ALL", "FMT_AUDITORIA_READ"})
	public String list(Long id){
		FmtAuditoria listAll=fmtAuditoriaRepository.list(id);
		
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
		
		List<FmtAuditoria> listAll=fmtAuditoriaRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return fmtAuditoriaRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_AUDITORIA_ALL", "FMT_AUDITORIA_UPDATE"})
	public String update(FmtAuditoria fmtauditoria){
		return gson.toJson(fmtAuditoriaRepository.update(fmtauditoria));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_AUDITORIA_ALL", "FMT_AUDITORIA_DELETE"})
	public void delete(FmtAuditoria fmtauditoria){
		fmtAuditoriaRepository.delete(fmtauditoria);
	}
	
	@Override
	public String insert(FmtAuditoria fmtauditoria){
		return gson.toJson(fmtAuditoriaRepository.insert(fmtauditoria));
	}		
	
	@Override
	public void generateAudit(String audicamp, Long audicopk, String tabla, String audivaan, String audivanu, Long trancons) {
		FmtAuditoria fmtAuditoria=new FmtAuditoria();
		fmtAuditoria.setAudicamp(audicamp);
		fmtAuditoria.setAudicopk(audicopk);
		fmtAuditoria.setAuditabl(tabla);
		fmtAuditoria.setAudivaan(audivaan);
		fmtAuditoria.setAudivanu(audivanu);		
		fmtAuditoria.setAuditran(trancons);
		
		fmtAuditoriaRepository.insert(fmtAuditoria);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_AUDITORIA_ALL", "FMT_AUDITORIA_READ"})
	public String listAllFrmFormregi(int pageSize, int page, String order, String stringFilters, Long forecons){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = null;
		if(!stringFilters.equals("null"))
			filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result=fmtAuditoriaRepository.listAllFrmFormregi(init, limit, order, filters, forecons);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	 
	}
}

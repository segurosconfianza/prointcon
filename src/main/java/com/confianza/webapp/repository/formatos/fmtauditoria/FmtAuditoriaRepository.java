package com.confianza.webapp.repository.formatos.fmtauditoria;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;
import java.util.Map;

import com.confianza.webapp.utils.Filter;

public interface FmtAuditoriaRepository {
	
	public FmtAuditoria list(Long id);
	
	public List<FmtAuditoria> listAll(int init, int limit);	
	
	public FmtAuditoria update(FmtAuditoria fmtauditoria);
	
	public void delete(FmtAuditoria fmtauditoria);
	
	public FmtAuditoria insert(FmtAuditoria fmtauditoria);
	
	public int getCount();

	public Map<String, Object> listAllFrmFormregi(int init, int limit, String order, List<Filter> filters, long forecons);
}

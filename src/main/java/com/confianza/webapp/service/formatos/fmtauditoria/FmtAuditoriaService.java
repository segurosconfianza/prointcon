package com.confianza.webapp.service.formatos.fmtauditoria;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

import com.confianza.webapp.repository.formatos.fmtauditoria.FmtAuditoria;

public interface FmtAuditoriaService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FmtAuditoria fmtauditoria);
	
	public String update(FmtAuditoria fmtauditoria);
	
	public void delete(FmtAuditoria fmtauditoria);	
	
	public int getCount();

	public void generateAudit(String audicamp, Long audicopk, String tabla, String audivaan, String audivanu, Long trancons);

	public String listAllFrmFormregi(int pageSize, int page, String order, String stringFilters, Long forecons);
	
}

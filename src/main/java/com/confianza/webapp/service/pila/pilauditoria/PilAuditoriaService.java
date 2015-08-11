package com.confianza.webapp.service.pila.pilauditoria;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;

import com.confianza.webapp.repository.pila.pilauditoria.PilAuditoria;

public interface PilAuditoriaService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(PilAuditoria pilauditoria);
	
	public String update(PilAuditoria pilauditoria);
	
	public void delete(PilAuditoria pilauditoria);	
	
	public int getCount();

	public void generateAudit(String audicamp, Long audicopk, String tabla, String audivaan, String audivanu, Long trancons);
	
}

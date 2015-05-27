package com.confianza.webapp.repository.pila.pilauditoria;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;

public interface PilAuditoriaRepository {
	
	public PilAuditoria list(Long id);
	
	public List<PilAuditoria> listAll(int init, int limit);	
	
	public PilAuditoria update(PilAuditoria pilauditoria);
	
	public void delete(PilAuditoria pilauditoria);
	
	public PilAuditoria insert(PilAuditoria pilauditoria);
	
	public int getCount();
}

package com.confianza.webapp.repository.pila.pillog;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;

public interface PilLogRepository {
	
	public PilLog list(Long id);
	
	public List<PilLog> listAll(int init, int limit);	
	
	public PilLog update(PilLog pillog);
	
	public void delete(PilLog pillog);
	
	public PilLog insert(PilLog pillog);
	
	public int getCount();
}

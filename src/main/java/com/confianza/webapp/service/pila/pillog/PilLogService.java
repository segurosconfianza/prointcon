package com.confianza.webapp.service.pila.pillog;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;
import com.confianza.webapp.repository.pila.pillog.PilLog;

public interface PilLogService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(PilLog pillog);
	
	public String update(PilLog pillog);
	
	public void delete(PilLog pillog);	
	
	public int getCount();
	
}

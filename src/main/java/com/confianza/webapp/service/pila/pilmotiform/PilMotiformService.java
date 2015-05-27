package com.confianza.webapp.service.pila.pilmotiform;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;
import com.confianza.webapp.repository.pila.pilmotiform.PilMotiform;

public interface PilMotiformService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(PilMotiform pilmotiform);
	
	public String update(PilMotiform pilmotiform);
	
	public void delete(PilMotiform pilmotiform);	
	
	public int getCount();
	
}

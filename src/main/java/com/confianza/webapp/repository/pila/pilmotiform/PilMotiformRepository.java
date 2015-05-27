package com.confianza.webapp.repository.pila.pilmotiform;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;

public interface PilMotiformRepository {
	
	public PilMotiform list(Long id);
	
	public List<PilMotiform> listAll(int init, int limit);	
	
	public PilMotiform update(PilMotiform pilmotiform);
	
	public void delete(PilMotiform pilmotiform);
	
	public PilMotiform insert(PilMotiform pilmotiform);
	
	public int getCount();
}

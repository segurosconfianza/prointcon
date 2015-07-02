package com.confianza.webapp.repository.pila.pilmotiform;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

import com.confianza.webapp.utils.Filter;

public interface PilMotiformRepository {
	
	public PilMotiform list(Long id);
	
	public List<PilMotiform> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public PilMotiform update(PilMotiform pilmotiform);
	
	public void delete(PilMotiform pilmotiform);
	
	public PilMotiform insert(PilMotiform pilmotiform);
	
	public int getCount(List<Filter> filters);
	
}

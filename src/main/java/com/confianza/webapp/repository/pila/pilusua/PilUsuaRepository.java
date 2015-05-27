package com.confianza.webapp.repository.pila.pilusua;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;

public interface PilUsuaRepository {
	
	public PilUsua list(Long id);
	
	public List<PilUsua> listAll(int init, int limit);	
	
	public PilUsua update(PilUsua pilusua);
	
	public void delete(PilUsua pilusua);
	
	public PilUsua insert(PilUsua pilusua);
	
	public int getCount();

	public PilUsua validateUsua(String user, String password);
}

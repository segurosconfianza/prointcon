package com.confianza.webapp.service.pila.pilusua;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;

import com.confianza.webapp.repository.pila.pilusua.PilUsua;

public interface PilUsuaService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(PilUsua pilusua);
	
	public String update(PilUsua pilusua);
	
	public void delete(PilUsua pilusua);	
	
	public int getCount();

	public String validateUsua(String user, String password);
	
}

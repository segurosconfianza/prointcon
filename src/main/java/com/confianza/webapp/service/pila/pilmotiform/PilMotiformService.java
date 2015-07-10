package com.confianza.webapp.service.pila.pilmotiform;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.confianza.webapp.repository.pila.pilmotiform.PilMotiform;
import com.confianza.webapp.utils.Filter;

public interface PilMotiformService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(PilMotiform pilmotiform);
	
	public String update(PilMotiform pilmotiform);
	
	public void delete(PilMotiform pilmotiform);	
	
	public int getCount(List<Filter> filters);

	public String insertDevolucion(PilMotiform pilmotiform, HttpServletRequest request);

	String listAllIntermediario(int pageSize, int page, String order,
			String stringFilters);
	
}

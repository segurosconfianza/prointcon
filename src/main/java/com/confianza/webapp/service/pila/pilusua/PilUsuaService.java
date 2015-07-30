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
import com.confianza.webapp.utils.Filter;

public interface PilUsuaService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(PilUsua pilusua);
	
	public void delete(PilUsua pilusua);	
	
	public int getCount(List<Filter> filters);

	public String validateUsua(String user, String password);

	public List<PilUsua> listAllFormregi(List<Long> codigosFormRegi);

	public String validateUser(String username);

	public String update(PilUsua pilusua);

}

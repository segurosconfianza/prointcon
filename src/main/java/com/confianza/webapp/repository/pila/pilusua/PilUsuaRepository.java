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

import com.confianza.webapp.utils.Filter;

public interface PilUsuaRepository {
	
	public PilUsua list(Long id);
	
	public List<PilUsua> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public PilUsua update(PilUsua pilusua);
	
	public void delete(PilUsua pilusua);
	
	public PilUsua insert(PilUsua pilusua);
	
	public int getCount(List<Filter> filters);

	public PilUsua validateUsua(String user, String password);

	public List<PilUsua> listAllFormregi(List<Long> codigosFormRegi);
}

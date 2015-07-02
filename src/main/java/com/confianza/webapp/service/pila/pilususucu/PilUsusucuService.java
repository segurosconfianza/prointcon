package com.confianza.webapp.service.pila.pilususucu;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;

import com.confianza.webapp.repository.pila.pilususucu.PilUsusucu;
import com.confianza.webapp.utils.Filter;

public interface PilUsusucuService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(PilUsusucu pilususucu);
	
	public String update(PilUsusucu pilususucu);
	
	public void delete(PilUsusucu pilususucu);	
	
	public int getCount(List<Filter> filters);

	public String listSucur();

	public int getCountAnalistas(List<Filter> filters);

	public String listAllAnalistas(int pageSize, int page, String order, String stringFilters);
	
}

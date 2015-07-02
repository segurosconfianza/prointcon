package com.confianza.webapp.repository.pila.pilususucu;

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

public interface PilUsusucuRepository {
	
	public PilUsusucu list(Long id);
	
	public List<PilUsusucu> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public PilUsusucu update(PilUsusucu pilususucu);
	
	public void delete(PilUsusucu pilususucu);
	
	public PilUsusucu insert(PilUsusucu pilususucu);
	
	public int getCount(List<Filter> filters);

	public List<PilUsusucu> listSucur(String user);

	public List<Object[]> listAllAnalistas(int init, int limit, String order, List<Filter> filters);

	public int getCountAnalistas(List<Filter> filters);
}

package com.confianza.webapp.repository.cierre.cieestaproc;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		cierre  
  */                          

import java.util.List;
import com.confianza.webapp.utils.Filter;

public interface CieEstaprocRepository {
	
	public CieEstaproc list(Long id);
	
	public List<CieEstaproc> listAll(int init, int limit, String order, List<Filter> filters);	
	
	public CieEstaproc update(CieEstaproc cieestaproc);
	
	public void delete(CieEstaproc cieestaproc);
	
	public CieEstaproc insert(CieEstaproc cieestaproc);
	
	public int getCount(List<Filter> filters);
}

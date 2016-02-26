package com.confianza.webapp.service.cierre.cieestaproc;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		cierre  
  */                          

import java.util.List;

import com.confianza.webapp.repository.cierre.cieestaproc.CieEstaproc;
import com.confianza.webapp.utils.Filter;

public interface FacEstaprocService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page, String order, String stringFilters);	
	
	public String insert(CieEstaproc cieestaproc);
	
	public String update(CieEstaproc cieestaproc);
	
	public void delete(CieEstaproc cieestaproc);	
	
	public int getCount(List<Filter> filters);

	public CieEstaproc insert(String esprnomb, String esprdesc, String espruser, String espresta);

	public  CieEstaproc closeFinal(CieEstaproc cieestaproc);

	public List<CieEstaproc> listAll(int pageSize, int page, String order, List<Filter> filters);
	
}

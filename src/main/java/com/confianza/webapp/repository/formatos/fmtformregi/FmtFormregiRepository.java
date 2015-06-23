package com.confianza.webapp.repository.formatos.fmtformregi;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.ArrayList;
import java.util.List;

import com.confianza.webapp.utils.Filter;

public interface FmtFormregiRepository {
	
	public FmtFormregi list(Long id);
	
	public List<FmtFormregi> listAll(int init, int limit);	
	
	public FmtFormregi update(FmtFormregi fmtformregi);
	
	public void delete(FmtFormregi fmtformregi);
	
	public FmtFormregi insert(FmtFormregi fmtformregi);
	
	public List<FmtFormregi> listAll(int init, int limit, Long vefocons, String order, List<Filter> filters);

	public int getCount(List<Filter> filters);

	public List<FmtFormregi> listAllAdmin(int init, int limit, Long vefocons, String order, List<Filter> filters);

	public int getCountAdmin(List<Filter> filters);
}

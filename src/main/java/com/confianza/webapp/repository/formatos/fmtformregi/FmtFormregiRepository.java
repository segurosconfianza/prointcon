package com.confianza.webapp.repository.formatos.fmtformregi;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

public interface FmtFormregiRepository {
	
	public FmtFormregi list(Long id);
	
	public List<FmtFormregi> listAll(int init, int limit);	
	
	public FmtFormregi update(FmtFormregi fmtformregi);
	
	public void delete(FmtFormregi fmtformregi);
	
	public FmtFormregi insert(FmtFormregi fmtformregi);
	
	public int getCount();
}

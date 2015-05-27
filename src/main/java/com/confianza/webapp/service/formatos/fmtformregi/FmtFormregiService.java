package com.confianza.webapp.service.formatos.fmtformregi;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;
import com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregi;

public interface FmtFormregiService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FmtFormregi fmtformregi);
	
	public String update(FmtFormregi fmtformregi);
	
	public void delete(FmtFormregi fmtformregi);	
	
	public int getCount();
	
}

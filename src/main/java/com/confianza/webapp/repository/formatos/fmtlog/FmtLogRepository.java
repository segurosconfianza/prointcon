package com.confianza.webapp.repository.formatos.fmtlog;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

public interface FmtLogRepository {
	
	public FmtLog list(Long id);
	
	public List<FmtLog> listAll(int init, int limit);	
	
	public FmtLog update(FmtLog fmtlog);
	
	public void delete(FmtLog fmtlog);
	
	public FmtLog insert(FmtLog fmtlog);
	
	public int getCount();
}

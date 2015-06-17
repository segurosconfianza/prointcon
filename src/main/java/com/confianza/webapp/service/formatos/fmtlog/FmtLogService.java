package com.confianza.webapp.service.formatos.fmtlog;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

import com.confianza.webapp.repository.formatos.fmtlog.FmtLog;

public interface FmtLogService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FmtLog fmtlog);
	
	public String update(FmtLog fmtlog);
	
	public void delete(FmtLog fmtlog);	
	
	public int getCount();

	public String insertIntermediario(FmtLog fmtlog);
	
}

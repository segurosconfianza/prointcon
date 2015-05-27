package com.confianza.webapp.service.formatos.fmtadjunto;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;
import com.confianza.webapp.repository.formatos.fmtadjunto.FmtAdjunto;

public interface FmtAdjuntoService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FmtAdjunto fmtadjunto);
	
	public String update(FmtAdjunto fmtadjunto);
	
	public void delete(FmtAdjunto fmtadjunto);	
	
	public int getCount();
	
}

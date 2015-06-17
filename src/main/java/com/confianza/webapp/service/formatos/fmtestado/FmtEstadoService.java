package com.confianza.webapp.service.formatos.fmtestado;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

import com.confianza.webapp.repository.formatos.fmtestado.FmtEstado;

public interface FmtEstadoService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FmtEstado fmtestado);
	
	public String update(FmtEstado fmtestado);
	
	public void delete(FmtEstado fmtestado);	
	
	public int getCount();

	public String listAll(int pageSize, int page, long forecons);
	
}

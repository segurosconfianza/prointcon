package com.confianza.webapp.service.formatos.fmtformato;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

import com.confianza.webapp.repository.formatos.fmtformato.FmtFormato;

public interface FmtFormatoService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FmtFormato fmtformato);
	
	public String update(FmtFormato fmtformato);
	
	public void delete(FmtFormato fmtformato);	
	
	public int getCount();

	public String loadData(String formcons);

	public String loadDataIntermediario(String formcons);
	
}

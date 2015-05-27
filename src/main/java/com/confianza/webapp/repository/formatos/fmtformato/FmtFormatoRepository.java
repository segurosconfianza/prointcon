package com.confianza.webapp.repository.formatos.fmtformato;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

public interface FmtFormatoRepository {
	
	public FmtFormato list(Long id);
	
	public List<FmtFormato> listAll(int init, int limit);	
	
	public FmtFormato update(FmtFormato fmtformato);
	
	public void delete(FmtFormato fmtformato);
	
	public FmtFormato insert(FmtFormato fmtformato);
	
	public int getCount();
}

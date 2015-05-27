package com.confianza.webapp.repository.formatos.fmtvalocamp;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

public interface FmtValocampRepository {
	
	public FmtValocamp list(Long id);
	
	public List<FmtValocamp> listAll(int init, int limit);	
	
	public FmtValocamp update(FmtValocamp fmtvalocamp);
	
	public void delete(FmtValocamp fmtvalocamp);
	
	public FmtValocamp insert(FmtValocamp fmtvalocamp);
	
	public int getCount();
}

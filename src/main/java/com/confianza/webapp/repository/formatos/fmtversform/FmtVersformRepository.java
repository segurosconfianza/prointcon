package com.confianza.webapp.repository.formatos.fmtversform;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

public interface FmtVersformRepository {
	
	public FmtVersform list(Long id);
	
	public List<FmtVersform> listAll(int init, int limit);	
	
	public FmtVersform update(FmtVersform fmtversform);
	
	public void delete(FmtVersform fmtversform);
	
	public FmtVersform insert(FmtVersform fmtversform);
	
	public int getCount();

	public FmtVersform lastVersion(Long id);
}

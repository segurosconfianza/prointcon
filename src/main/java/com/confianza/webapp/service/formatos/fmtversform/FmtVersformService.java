package com.confianza.webapp.service.formatos.fmtversform;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

import com.confianza.webapp.repository.formatos.fmtversform.FmtVersform;

public interface FmtVersformService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FmtVersform fmtversform);
	
	public String update(FmtVersform fmtversform);
	
	public void delete(FmtVersform fmtversform);	
	
	public int getCount();

	public String lastVersion(Long id);

	public FmtVersform lastVersionEntity(Long id);
	
}

package com.confianza.webapp.repository.framework.frmi18n;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

public interface FrmI18nRepository {
	
	public FrmI18n list(Long id);
	
	public List<FrmI18n> listAll(int init, int limit);	
	
	public FrmI18n update(Long id);
	
	public void delete(Long id);
	
	public void insert(FrmI18n frmi18n);
	
	public int getCount();	

	public List<Object[]> listModulo(String[] modulos);
}

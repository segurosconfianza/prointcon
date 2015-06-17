package com.confianza.webapp.service.framework.frmi18n;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

import com.confianza.webapp.repository.framework.frmi18n.FrmI18n;

public interface FrmI18nService{
	
	public FrmI18n list(Long id);
	
	public List<FrmI18n> listAll(int pageSize, int page);	
	
	public FrmI18n update(Long id);
	
	public void delete(Long id);
	
	public FrmI18n insert(FrmI18n frmi18n);
	
	public int getCount();

	public String listModulo(String modulo);
	
}

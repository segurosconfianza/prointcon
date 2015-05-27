package com.confianza.webapp.service.framework.frmmenu;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

import com.confianza.webapp.repository.framework.frmmenu.FrmMenu;

public interface FrmMenuService{
	
	public String list(Long id);
	
	public String listAll();	
	
	public FrmMenu update(Long id);
	
	public void delete(Long id);
	
	public String insert(FrmMenu frmmenu);

	public List<Object[]> loadMenu(Long id, List<String> roles);

	public String listAllIntermediario();
	
}

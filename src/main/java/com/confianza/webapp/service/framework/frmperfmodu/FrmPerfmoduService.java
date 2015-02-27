package com.confianza.webapp.service.framework.frmperfmodu;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

import com.confianza.webapp.repository.framework.frmperfmodu.FrmPerfmodu;

public interface FrmPerfmoduService{
	
	public String list(Long id);	
	
	public String update(FrmPerfmodu frmperfmodu);
	
	public String delete(FrmPerfmodu frmperfmodu);
	
	public String insert(FrmPerfmodu frmperfmodu);

	public int getCount();

	public String listAll(int pageSize, int page, Long pemopefi);

	public String listComboMoro();
	
}

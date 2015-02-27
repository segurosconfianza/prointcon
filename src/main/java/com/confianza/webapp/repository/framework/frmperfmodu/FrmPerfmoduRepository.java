package com.confianza.webapp.repository.framework.frmperfmodu;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

public interface FrmPerfmoduRepository {
	
	public FrmPerfmodu list(Long id);
	
	public FrmPerfmodu update(FrmPerfmodu frmperfmodu);
	
	public FrmPerfmodu delete(FrmPerfmodu frmperfmodu);
	
	public FrmPerfmodu insert(FrmPerfmodu frmperfmodu);

	public int getCount();

	public List<Object[]> listAll(int init, int limit, Long pemopefi);

	public List<Object[]> listComboMoro();
}

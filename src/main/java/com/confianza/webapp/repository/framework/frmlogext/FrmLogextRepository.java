package com.confianza.webapp.repository.framework.frmlogext;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

public interface FrmLogextRepository {
	
	public FrmLogext list(Long id);
	
	public List<FrmLogext> listAll(int init, int limit);	
	
	public FrmLogext update(FrmLogext frmlogext);
	
	public void delete(FrmLogext frmlogext);
	
	public FrmLogext insert(FrmLogext frmlogext);
	
	public int getCount();
}

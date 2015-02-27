package com.confianza.webapp.repository.soporte.sopadjunto;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

public interface SopAdjuntoRepository {
	
	public SopAdjunto list(Long id);
	
	public List<SopAdjunto> listAll(int init, int limit);	
	
	public SopAdjunto update(SopAdjunto sopadjunto);
	
	public void delete(SopAdjunto sopadjunto);
	
	public SopAdjunto insert(SopAdjunto sopadjunto);
	
	public int getCount();
}

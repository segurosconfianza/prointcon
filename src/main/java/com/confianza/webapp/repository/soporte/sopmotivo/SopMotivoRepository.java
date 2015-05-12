package com.confianza.webapp.repository.soporte.sopmotivo;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

public interface SopMotivoRepository {
	
	public SopMotivo list(Long id);
	
	public SopMotivo update(SopMotivo sopmotivo);
	
	public void delete(SopMotivo sopmotivo);
	
	public SopMotivo insert(SopMotivo sopmotivo);
	
	public List<Object[]> listAll(int init, int limit, Long motitran);

	public int getCount(Long motitran);
}

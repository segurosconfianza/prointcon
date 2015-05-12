package com.confianza.webapp.repository.framework.frmlog;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

public interface FrmLogRepository {
	
	public FrmLog list(Long id);
	
	public FrmLog update(FrmLog frmlog);
	
	public void delete(FrmLog frmlog);
	
	public FrmLog insert(FrmLog frmlog);
	
	public List<FrmLog> listAll(int init, int limit, Long slogtran);

	public int getCount(Long slogtran);
}

package com.confianza.webapp.service.framework.frmlog;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

import com.confianza.webapp.repository.framework.frmlog.FrmLog;

public interface FrmLogService{
	
	public String list(Long id);	
	
	public String insert(FrmLog frmlog);
	
	public String update(FrmLog frmlog);
	
	public void delete(FrmLog frmlog);	
	
	String listAll(int pageSize, int page, Long slogtran);

	public int getCount(Long slogtran);
	
}

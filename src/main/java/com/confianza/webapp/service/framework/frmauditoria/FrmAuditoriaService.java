package com.confianza.webapp.service.framework.frmauditoria;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

import com.confianza.webapp.repository.framework.frmauditoria.FrmAuditoria;

public interface FrmAuditoriaService{
	
	public String list(Long id);
	
	public String insert(FrmAuditoria frmauditoria);
	
	public String update(FrmAuditoria frmauditoria);
	
	public void delete(FrmAuditoria frmauditoria);	
	
	public String listAll(int pageSize, int page, Long slogtran);

	public int getCount(Long auditran);
	
}

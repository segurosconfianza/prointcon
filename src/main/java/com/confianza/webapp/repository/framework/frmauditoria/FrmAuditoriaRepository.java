package com.confianza.webapp.repository.framework.frmauditoria;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

public interface FrmAuditoriaRepository {
	
	public FrmAuditoria list(Long id);
	
	public FrmAuditoria update(FrmAuditoria frmauditoria);
	
	public void delete(FrmAuditoria frmauditoria);
	
	public FrmAuditoria insert(FrmAuditoria frmauditoria);
	
	public List<FrmAuditoria> listAll(int init, int limit, Long auditran);

	public int getCount(Long auditran);
}

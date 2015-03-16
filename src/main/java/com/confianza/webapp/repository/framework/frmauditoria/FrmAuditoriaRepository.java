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
	
	public List<FrmAuditoria> listAll();	
	
	public void delete(Long id);
	
	public FrmAuditoria insert(FrmAuditoria frmauditoria);

	public FrmAuditoria update(FrmAuditoria frmauditoria);
}

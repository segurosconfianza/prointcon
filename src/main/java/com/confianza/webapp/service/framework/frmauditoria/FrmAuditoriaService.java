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
	
	public FrmAuditoria list(Long id);
	
	public List<FrmAuditoria> listAll();	
	
	public void delete(Long id);
	
	public FrmAuditoria insert(FrmAuditoria frmauditoria);

	public FrmAuditoria update(FrmAuditoria frmAuditoria);
	
}

package com.confianza.webapp.service.framework.frmtransaccion;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

import com.confianza.webapp.repository.framework.frmtransaccion.FrmTransaccion;

public interface FrmTransaccionService{
	
	public FrmTransaccion list(Long id);
	
	public List<FrmTransaccion> listAll();	
	
	public FrmTransaccion update(Long id);
	
	public void delete(Long id);
	
	public FrmTransaccion insert(FrmTransaccion frmtransaccion);

	public FrmTransaccion insert(Long frmSesion);
	
}

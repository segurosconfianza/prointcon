package com.confianza.webapp.service.transfiriendo.consultapoliza;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import javax.servlet.http.HttpServletRequest;

import com.confianza.webapp.repository.transfiriendo.consultapoliza.Poliza;

public interface ConsultaPolizaService{	

	public String listPoliza(String SUCURSAL, String PRODUCTO, String POLIZA, String CERTIFICADO,HttpServletRequest request);	
}

package com.confianza.webapp.service.cierre.cierrecancelacionesautomaticas;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import javax.servlet.http.HttpServletRequest;

public interface CierreCancelacionesAutomaticasService{	

	public String executeProcessCierreCartera(String conscons, String params, HttpServletRequest request);

	public String cierreCreateCancelaciones(String selectedItems, int countItems, String params, HttpServletRequest request);	
}

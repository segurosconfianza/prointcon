package com.confianza.webapp.service.soporte.sopadjunto;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

import com.confianza.webapp.repository.framework.frmarchivo.FrmArchivo;
import com.confianza.webapp.repository.soporte.sopadjunto.SopAdjunto;

public interface SopAdjuntoService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(SopAdjunto sopadjunto);
	
	public String update(SopAdjunto sopadjunto);
	
	public void delete(SopAdjunto sopadjunto);	
	
	public int getCount();

	public void insertAdjuntos(Long adjumoti, List<FrmArchivo> listAll);
	
}

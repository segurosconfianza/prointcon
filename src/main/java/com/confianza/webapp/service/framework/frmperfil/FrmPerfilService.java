package com.confianza.webapp.service.framework.frmperfil;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

import com.confianza.webapp.repository.framework.frmperfil.FrmPerfil;

public interface FrmPerfilService{
	
	public String list(Long id) throws Exception;	

	public String insert(FrmPerfil frmperfil) throws Exception;

	public String listAll(int pageSize, int page) throws Exception;

	public int getCount() throws Exception;

	public String update(FrmPerfil frmperfil) throws Exception;

	public void delete(FrmPerfil frmperfil) throws Exception;
	
}

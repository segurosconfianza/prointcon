package com.confianza.webapp.service.framework.frmlogext;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmlogext.FrmLogext;

public interface FrmLogextService{
	
	public FrmLogext list(Long id);
	
	public List<FrmLogext> listAll(int pageSize, int page);	
	
	public FrmLogext insert(FrmLogext frmlogext);
	
	public FrmLogext update(FrmLogext frmlogext);
	
	public void delete(FrmLogext frmlogext);	
	
	public int getCount();

	void insert(HttpServletRequest servletRequest, FrmConsulta frmConsulta,	String data) throws Throwable;

	
}

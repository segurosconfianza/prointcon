package com.confianza.webapp.service.framework.frmparametro;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;

public interface FrmParametroService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FrmParametro frmparametro);
	
	public String update(FrmParametro frmparametro);
	
	public void delete(FrmParametro frmparametro);	
	
	public int getCount();

	public String listParamsCosu(Long id);

	public List<FrmParametro> listParamsCosuType(Long id);
	
}

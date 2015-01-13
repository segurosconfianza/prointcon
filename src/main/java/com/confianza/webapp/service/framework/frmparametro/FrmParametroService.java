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
	
	public FrmParametro list(Long id);
	
	public List<FrmParametro> listAll(int pageSize, int page);	
	
	public FrmParametro insert(FrmParametro frmparametro);
	
	public FrmParametro update(FrmParametro frmparametro);
	
	public void delete(FrmParametro frmparametro);	
	
	public int getCount();

	public List<FrmParametro> listParamsCosu(Long id);
	
}

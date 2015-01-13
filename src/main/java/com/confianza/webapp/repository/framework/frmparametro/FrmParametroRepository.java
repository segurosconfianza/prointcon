package com.confianza.webapp.repository.framework.frmparametro;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

public interface FrmParametroRepository {
	
	public FrmParametro list(Long id);
	
	public List<FrmParametro> listAll(int init, int limit);	
	
	public FrmParametro update(FrmParametro frmparametro);
	
	public void delete(FrmParametro frmparametro);
	
	public FrmParametro insert(FrmParametro frmparametro);
	
	public int getCount();

	public List<FrmParametro> listParamsCosu(Long id);
}

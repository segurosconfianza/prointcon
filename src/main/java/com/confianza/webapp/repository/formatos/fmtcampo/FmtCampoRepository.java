package com.confianza.webapp.repository.formatos.fmtcampo;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

public interface FmtCampoRepository {
	
	public FmtCampo list(Long id);
	
	public List<FmtCampo> listAll(int init, int limit);	
	
	public FmtCampo update(FmtCampo fmtcampo);
	
	public void delete(FmtCampo fmtcampo);
	
	public FmtCampo insert(FmtCampo fmtcampo);
	
	public int getCount();

	public List<FmtCampo> listCamposCosu(Long id);
}

package com.confianza.webapp.service.formatos.fmtcampo;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

import com.confianza.webapp.repository.formatos.fmtcampo.FmtCampo;

public interface FmtCampoService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FmtCampo fmtcampo);
	
	public String update(FmtCampo fmtcampo);
	
	public void delete(FmtCampo fmtcampo);	
	
	public int getCount();

	public String listCamposCosu(Long id);

	public List<FmtCampo> listEntityCamposCosu(Long id);
	
}

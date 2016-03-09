package com.confianza.webapp.service.formatos.fmtvalocamp;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;
import java.util.Map;

import com.confianza.webapp.repository.formatos.fmtcampo.FmtCampo;
import com.confianza.webapp.repository.formatos.fmtvalocamp.FmtValocamp;

public interface FmtValocampService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FmtValocamp fmtvalocamp);
	
	public String update(FmtValocamp fmtvalocamp);
	
	public void delete(FmtValocamp fmtvalocamp);	
	
	public int getCount();

	public boolean insertValuesIntermediario(Long vefocons, Long forecons, Map<String, Object> parametersData);

	public List<FmtValocamp> listAll(int init, int limit, Long vacafore, List<Long> codigosFormRegi);

	public FmtValocamp updateFmtValocamp(Map<String, Object> parametersData, FmtCampo campo, FmtValocamp fmtValocamp, String user, Long trancons);

	public boolean updateValuesIntermediario(Long vefocons, Long vacafore, Map<String, Object> parametersData, String user, Long trancons);
	
}

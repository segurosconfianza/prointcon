package com.confianza.webapp.service.framework.frmconsulta;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;
import java.util.Map;

import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;

public interface FrmConsultaService{
	
	public FrmConsulta list(Long id);
	
	public List<FrmConsulta> listAll(int pageSize, int page);	
	
	public FrmConsulta insert(FrmConsulta frmconsulta);
	
	public FrmConsulta update(FrmConsulta frmconsulta);
	
	public void delete(FrmConsulta frmconsulta);	
	
	public int getCount();

	public FrmConsulta listName(String conscons);

	public List<Object[]> loadData(FrmConsulta frmConsulta, Map<String, Object> parameters);
	
}

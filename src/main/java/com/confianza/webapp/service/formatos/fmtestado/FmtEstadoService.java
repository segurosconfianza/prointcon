package com.confianza.webapp.service.formatos.fmtestado;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

import com.confianza.webapp.repository.formatos.fmtestado.FmtEstado;
import com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregi;
import com.confianza.webapp.utils.Filter;

public interface FmtEstadoService{
	
	public String list(Long id);	
	
	public String insert(FmtEstado fmtestado);
	
	public String update(FmtEstado fmtestado);
	
	public void delete(FmtEstado fmtestado);	
	
	public int getCount(List<Filter> filters);

	public String listAll(int pageSize, int page, String order, String stringFilters);

	public void insertLastEstado(FmtFormregi fmtformregi, String user);
	
}

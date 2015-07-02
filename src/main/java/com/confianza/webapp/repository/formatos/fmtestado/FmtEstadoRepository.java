package com.confianza.webapp.repository.formatos.fmtestado;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

import com.confianza.webapp.utils.Filter;

public interface FmtEstadoRepository {
	
	public FmtEstado list(Long id);
	
	public List<FmtEstado> listAll(int init, int limit);	
	
	public FmtEstado update(FmtEstado fmtestado);
	
	public void delete(FmtEstado fmtestado);
	
	public FmtEstado insert(FmtEstado fmtestado);
	
	public int getCount(List<Filter> filters);

	public List<FmtEstado> listAll(int init, int limit, String order, List<Filter> filters);
}

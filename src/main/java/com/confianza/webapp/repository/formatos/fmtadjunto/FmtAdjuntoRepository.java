package com.confianza.webapp.repository.formatos.fmtadjunto;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

public interface FmtAdjuntoRepository {
	
	public FmtAdjunto list(Long id);
	
	public List<FmtAdjunto> listAll(int init, int limit);	
	
	public FmtAdjunto update(FmtAdjunto fmtadjunto);
	
	public void delete(FmtAdjunto fmtadjunto);
	
	public FmtAdjunto insert(FmtAdjunto fmtadjunto);
	
	public int getCount();

	public FmtAdjunto listAdjunto(long forecons);

	List<FmtAdjunto> listAdjuntoActivos(long forecons);
}

package com.confianza.webapp.repository.pila.pilmotivo;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;

public interface PilMotivoRepository {
	
	public PilMotivo list(Long id);
	
	public List<PilMotivo> listAll(int init, int limit);	
	
	public PilMotivo update(PilMotivo pilmotivo);
	
	public void delete(PilMotivo pilmotivo);
	
	public PilMotivo insert(PilMotivo pilmotivo);
	
	public int getCount();
}

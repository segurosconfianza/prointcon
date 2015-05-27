package com.confianza.webapp.service.pila.pilmotivo;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;
import com.confianza.webapp.repository.pila.pilmotivo.PilMotivo;

public interface PilMotivoService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(PilMotivo pilmotivo);
	
	public String update(PilMotivo pilmotivo);
	
	public void delete(PilMotivo pilmotivo);	
	
	public int getCount();
	
}

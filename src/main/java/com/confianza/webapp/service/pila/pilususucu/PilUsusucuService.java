package com.confianza.webapp.service.pila.pilususucu;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;

import com.confianza.webapp.repository.pila.pilususucu.PilUsusucu;

public interface PilUsusucuService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(PilUsusucu pilususucu);
	
	public String update(PilUsusucu pilususucu);
	
	public void delete(PilUsusucu pilususucu);	
	
	public int getCount();

	public String listSucur();
	
}

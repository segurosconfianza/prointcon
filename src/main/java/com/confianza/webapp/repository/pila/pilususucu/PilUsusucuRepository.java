package com.confianza.webapp.repository.pila.pilususucu;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;

public interface PilUsusucuRepository {
	
	public PilUsusucu list(Long id);
	
	public List<PilUsusucu> listAll(int init, int limit);	
	
	public PilUsusucu update(PilUsusucu pilususucu);
	
	public void delete(PilUsusucu pilususucu);
	
	public PilUsusucu insert(PilUsusucu pilususucu);
	
	public int getCount();

	public List<PilUsusucu> listSucur(String user);
}

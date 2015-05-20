package com.confianza.webapp.repository.framework.frmarchivo;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.ArrayList;
import java.util.List;

public interface FrmArchivoRepository {
	
	public FrmArchivo list(Long id);
	
	public List<FrmArchivo> listAll(int init, int limit);	
	
	public FrmArchivo update(FrmArchivo frmarchivo);
	
	public void delete(FrmArchivo frmarchivo);
	
	public FrmArchivo insert(FrmArchivo frmarchivo);
	
	public int getCount();

	public List<FrmArchivo> listAllMd5(ArrayList<String> mime);
}

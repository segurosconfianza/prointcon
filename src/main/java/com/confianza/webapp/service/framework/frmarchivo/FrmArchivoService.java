package com.confianza.webapp.service.framework.frmarchivo;

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

import org.springframework.web.multipart.MultipartFile;

import com.confianza.webapp.repository.framework.frmarchivo.FrmArchivo;
import com.confianza.webapp.utils.CFile;

public interface FrmArchivoService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FrmArchivo frmarchivo);
	
	public String update(FrmArchivo frmarchivo);
	
	public void delete(FrmArchivo frmarchivo);	
	
	public int getCount();

	public List<FrmArchivo> ingresarArchivos(ArrayList<MultipartFile> file) throws Exception;

	public String ingresarArchivoSoporte(String nombreArchivo, String valor) throws Exception;

}

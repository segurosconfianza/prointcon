package com.confianza.webapp.service.soporte.sopmotivo;

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

import com.confianza.webapp.repository.soporte.sopmotivo.SopMotivo;

public interface SopMotivoService{
	
	public String list(Long id);
	
	public String insert(SopMotivo sopmotivo);
	
	public String update(SopMotivo sopmotivo);
	
	public void delete(SopMotivo sopmotivo);	
	
	public SopMotivo insertMotivo(String motidesc, Long motitran, ArrayList<MultipartFile> file);

	public String listAll(int pageSize, int page, Long motitran);

	public int getCount(Long motitran);
	
}

package com.confianza.webapp.service.formatos.fmtadjunto;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.confianza.webapp.repository.formatos.fmtadjunto.FmtAdjunto;
import com.confianza.webapp.repository.framework.frmarchivo.FrmArchivo;

public interface FmtAdjuntoService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FmtAdjunto fmtadjunto);
	
	public String update(FmtAdjunto fmtadjunto);
	
	public void delete(FmtAdjunto fmtadjunto);	
	
	public int getCount();

	public boolean insertAdjuntos(Long forecons, String user, List<FrmArchivo> listAll);

	public void listAdjunto(long forecons, HttpServletRequest request, HttpServletResponse response);

	public List<FmtAdjunto> listAdjuntoActivos(long forecons);

	public String updateIntermediario(FmtAdjunto fmtadjunto);
	
}

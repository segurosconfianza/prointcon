package com.confianza.webapp.service.formatos.fmtadjunto;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.confianza.webapp.repository.formatos.fmtadjunto.FmtAdjunto;
import com.confianza.webapp.repository.formatos.fmtadjunto.FmtAdjuntoRepository;
import com.confianza.webapp.repository.framework.frmarchivo.FrmArchivo;
import com.confianza.webapp.repository.soporte.sopadjunto.SopAdjunto;
import com.confianza.webapp.service.framework.frmarchivo.FrmArchivoService;
import com.confianza.webapp.utils.User;

@Service
public class FmtAdjuntoServiceImpl implements FmtAdjuntoService{
	
	@Autowired
	private FmtAdjuntoRepository fmtadjuntoRepository;
	
	@Autowired
	private FrmArchivoService frmArchivoService;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the fmtadjuntoRepository
	 */
	public FmtAdjuntoRepository getFmtAdjuntoRepository() {
		return fmtadjuntoRepository;
	}

	/**
	 * @param fmtadjuntoRepository the fmtadjuntoRepository to set
	 */
	public void setFmtAdjuntoRepository(FmtAdjuntoRepository fmtadjuntoRepository) {
		this.fmtadjuntoRepository = fmtadjuntoRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_ADJUNTO_ALL", "FMT_ADJUNTO_READ"})
	public String list(Long id){
		FmtAdjunto listAll=fmtadjuntoRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_ADJUNTO_ALL", "FMT_ADJUNTO_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtAdjunto> listAll=fmtadjuntoRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return fmtadjuntoRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_ADJUNTO_ALL", "FMT_ADJUNTO_UPDATE"})
	public String update(FmtAdjunto fmtadjunto){
		return gson.toJson(fmtadjuntoRepository.update(fmtadjunto));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_ADJUNTO_ALL", "FMT_ADJUNTO_DELETE"})
	public void delete(FmtAdjunto fmtadjunto){
		fmtadjuntoRepository.delete(fmtadjunto);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_ADJUNTO_ALL", "FMT_ADJUNTO_CREATE"})
	public String insert(FmtAdjunto fmtadjunto){
		return gson.toJson(fmtadjuntoRepository.insert(fmtadjunto));
	}
	
	@Override	
	public boolean insertAdjuntos(Long forecons, String user, List<FrmArchivo> listAll){
		
		try{
			for(FrmArchivo frmArchivo:listAll){
				FmtAdjunto fmtAdjunto=new FmtAdjunto();
				fmtAdjunto.setAdjuarch(frmArchivo.getArchcodi());
				fmtAdjunto.setAdjuesta("A");
				fmtAdjunto.setAdjufech(Calendar.getInstance().getTime());
				fmtAdjunto.setAdjufore(forecons);
				fmtAdjunto.setAdjunomb(frmArchivo.getArchnomb());
				fmtAdjunto.setAdjuuser(user);
				fmtadjuntoRepository.insert(fmtAdjunto);
			}
			return true;
		}catch(Exception e){ 
			e.printStackTrace();
			return false; 
		}
	}
	
	@Override
	public void listAdjunto(long forecons, HttpServletRequest request, HttpServletResponse response){		
		
		FmtAdjunto adjunto=fmtadjuntoRepository.listAdjunto(forecons);
		if(adjunto!=null)
			frmArchivoService.getfrmArchivo(adjunto.getAdjuarch(), adjunto.getAdjunomb(), request, response);
	}	
	
	@Override
	public List<FmtAdjunto> listAdjuntoActivos(long forecons){
		return fmtadjuntoRepository.listAdjuntoActivos(forecons);
	}
	
	@Override
	public String updateIntermediario(FmtAdjunto fmtadjunto){
		return gson.toJson(fmtadjuntoRepository.update(fmtadjunto));
	}
}

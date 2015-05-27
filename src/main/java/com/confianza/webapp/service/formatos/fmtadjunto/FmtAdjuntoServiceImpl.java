package com.confianza.webapp.service.formatos.fmtadjunto;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.confianza.webapp.repository.formatos.fmtadjunto.FmtAdjunto;
import com.confianza.webapp.repository.formatos.fmtadjunto.FmtAdjuntoRepository;

@Service
public class FmtAdjuntoServiceImpl implements FmtAdjuntoService{
	
	@Autowired
	private FmtAdjuntoRepository fmtadjuntoRepository;
	
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
	
}

package com.confianza.webapp.service.soporte.sopadjunto;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.framework.frmarchivo.FrmArchivo;
import com.confianza.webapp.repository.soporte.sopadjunto.SopAdjunto;
import com.confianza.webapp.repository.soporte.sopadjunto.SopAdjuntoRepository;
import com.confianza.webapp.utils.User;
import com.google.gson.Gson;

@Service
public class SopAdjuntoServiceImpl implements SopAdjuntoService{
	
	@Autowired
	private SopAdjuntoRepository sopAdjuntoRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the sopadjuntoRepository
	 */
	public SopAdjuntoRepository getSopAdjuntoRepository() {
		return sopAdjuntoRepository;
	}

	/**
	 * @param sopadjuntoRepository the sopadjuntoRepository to set
	 */
	public void setSopAdjuntoRepository(SopAdjuntoRepository sopadjuntoRepository) {
		this.sopAdjuntoRepository = sopadjuntoRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOP_ADJUNTO_ALL", "SOP_ADJUNTO_READ"})
	public String list(Long id){
		SopAdjunto listAll=sopAdjuntoRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOP_ADJUNTO_ALL", "SOP_ADJUNTO_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize*page;
		int init=limit-pageSize;
		
		List<SopAdjunto> listAll=sopAdjuntoRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return sopAdjuntoRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOP_ADJUNTO_ALL", "SOP_ADJUNTO_UPDATE"})
	public String update(SopAdjunto sopadjunto){
		return gson.toJson(sopAdjuntoRepository.update(sopadjunto));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOP_ADJUNTO_ALL", "SOP_ADJUNTO_DELETE"})
	public void delete(SopAdjunto sopadjunto){
		sopAdjuntoRepository.delete(sopadjunto);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOP_ADJUNTO_ALL", "SOP_ADJUNTO_CREATE"})
	public String insert(SopAdjunto sopadjunto){
		return gson.toJson(sopAdjuntoRepository.insert(sopadjunto));
	}
	
	@Override	
	public void insertAdjuntos(Long adjumoti, List<FrmArchivo> listAll){
		
		for(FrmArchivo frmArchivo:listAll){
			SopAdjunto sopAdjunto=new SopAdjunto();
			sopAdjunto.setAdjuarch(frmArchivo.getArchcodi());
			sopAdjunto.setAdjuesta("A");
			sopAdjunto.setAdjufech(new Date());
			sopAdjunto.setAdjumoti(adjumoti);
			sopAdjunto.setAdjunomb(frmArchivo.getArchnomb());
			sopAdjunto.setAdjuuser(new User().getUser());
			sopAdjuntoRepository.insert(sopAdjunto);
		}
	}
	
}

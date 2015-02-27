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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.confianza.webapp.repository.framework.frmarchivo.FrmArchivo;
import com.confianza.webapp.repository.soporte.sopmotivo.SopMotivo;
import com.confianza.webapp.repository.soporte.sopmotivo.SopMotivoRepository;
import com.confianza.webapp.service.framework.frmarchivo.FrmArchivoService;
import com.confianza.webapp.service.soporte.sopadjunto.SopAdjuntoService;
import com.google.gson.Gson;

@Service
public class SopMotivoServiceImpl implements SopMotivoService{
	
	@Autowired
	private SopMotivoRepository sopMotivoRepository;
	
	@Autowired
	private FrmArchivoService frmArchivoService;
	
	@Autowired
	private SopAdjuntoService sopAdjuntoService;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the sopmotivoRepository
	 */
	public SopMotivoRepository getSopMotivoRepository() {
		return sopMotivoRepository;
	}

	/**
	 * @param sopmotivoRepository the sopmotivoRepository to set
	 */
	public void setSopMotivoRepository(SopMotivoRepository sopmotivoRepository) {
		this.sopMotivoRepository = sopmotivoRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_SOPMOTIVO__ALL", "APP_SOPMOTIVO__READ"})
	public String list(Long id){
		SopMotivo listAll=sopMotivoRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_SOPMOTIVO__ALL", "APP_SOPMOTIVO__READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize*page;
		int init=limit-pageSize;
		
		List<SopMotivo> listAll=sopMotivoRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return sopMotivoRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_SOPMOTIVO__ALL", "APP_SOPMOTIVO__UPDATE"})
	public String update(SopMotivo sopmotivo){
		return gson.toJson(sopMotivoRepository.update(sopmotivo));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_SOPMOTIVO__ALL", "APP_SOPMOTIVO__DELETE"})
	public void delete(SopMotivo sopmotivo){
		sopMotivoRepository.delete(sopmotivo);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_SOPMOTIVO__ALL", "APP_SOPMOTIVO__CREATE"})
	public String insert(SopMotivo sopmotivo){
		return gson.toJson(sopMotivoRepository.insert(sopmotivo));
	}
	
	@Override	
	public SopMotivo insertMotivo(String motidesc, Long motitran, ArrayList<MultipartFile> file){
		SopMotivo sopMotivo=new SopMotivo();
		sopMotivo.setMotidesc(motidesc);
		sopMotivo.setMotitran(motitran);
		
		List<FrmArchivo> listAll;
		try {
			listAll = this.frmArchivoService.ingresarArchivos(file);
			this.sopAdjuntoService.insertAdjuntos(sopMotivo.getMoticons(), listAll);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sopMotivoRepository.insert(sopMotivo);
	}
	
}

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
import com.confianza.webapp.repository.soporte.sopadjunto.SopAdjunto;
import com.confianza.webapp.repository.soporte.sopmotivo.SopMotivo;
import com.confianza.webapp.repository.soporte.sopmotivo.SopMotivoRepository;
import com.confianza.webapp.service.framework.frmarchivo.FrmArchivoService;
import com.confianza.webapp.service.soporte.sopadjunto.SopAdjuntoService;
import com.confianza.webapp.utils.JSONUtil;
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
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOP_MOTIVO_ALL", "SOP_MOTIVO_READ"})
	public String list(Long id){
		SopMotivo listAll=sopMotivoRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOP_MOTIVO_ALL", "SOP_MOTIVO_READ"})
	public String listAll(int pageSize, int page, Long motitran){
	
		int limit=pageSize*page;
		int init=(pageSize*page)-(pageSize);
		
		List<Object[]> listAll=sopMotivoRepository.listAll(init, limit, motitran);
		
		String maestroDetalle[] = new String[SopMotivo.getNames().length+SopAdjunto.getNames().length];
		System.arraycopy(SopMotivo.getNames(), 0, maestroDetalle, 0, SopMotivo.getNames().length);
		System.arraycopy(SopAdjunto.getNames(), 0, maestroDetalle, SopMotivo.getNames().length, SopAdjunto.getNames().length);
		
		List<Map<String, Object>> resAll = JSONUtil.toNameList(maestroDetalle,listAll);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", resAll);
		result.put("count", this.getCount(motitran));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(Long motitran){
				
		return sopMotivoRepository.getCount(motitran);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOP_MOTIVO_ALL", "SOP_MOTIVO_UPDATE"})
	public String update(SopMotivo sopmotivo){
		return gson.toJson(sopMotivoRepository.update(sopmotivo));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOP_MOTIVO_ALL", "SOP_MOTIVO_DELETE"})
	public void delete(SopMotivo sopmotivo){
		sopMotivoRepository.delete(sopmotivo);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOP_MOTIVO_ALL", "SOP_MOTIVO_CREATE"})
	public String insert(SopMotivo sopmotivo){
		return gson.toJson(sopMotivoRepository.insert(sopmotivo));
	}
	
	@Override	
	public SopMotivo insertMotivo(String motidesc, Long motitran, ArrayList<MultipartFile> file){
		SopMotivo sopMotivo=new SopMotivo();
		sopMotivo.setMotidesc(motidesc);
		sopMotivo.setMotitran(motitran);
		
		sopMotivo=sopMotivoRepository.insert(sopMotivo);
		
		List<FrmArchivo> listAll;
		try {
			listAll = this.frmArchivoService.ingresarArchivos(file,"archruta");
			this.sopAdjuntoService.insertAdjuntos(sopMotivo.getMoticons(), listAll);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sopMotivo;
	}
	
}

package com.confianza.webapp.service.framework.frmperfil;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.framework.frmperfil.FrmPerfil;
import com.confianza.webapp.repository.framework.frmperfil.FrmPerfilRepository;
import com.google.gson.Gson;

@Service
public class FrmPerfilServiceImpl implements FrmPerfilService{
	
	@Autowired
	Gson gson;
	
	@Autowired
	private FrmPerfilRepository frmPerfilRepository;
	
	/**
	 * @return the frmperfilRepository
	 */
	public FrmPerfilRepository getFrmPerfilRepository() {
		return frmPerfilRepository;
	}

	/**
	 * @param frmperfilRepository the frmperfilRepository to set
	 */
	public void setFrmPerfilRepository(FrmPerfilRepository frmperfilRepository) {
		this.frmPerfilRepository = frmperfilRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFIL_ALL", "FRM_PERFIL_READ"})
	public String list(Long id) throws Exception{
		return gson.toJson(frmPerfilRepository.list(id));
	}
		
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFIL_ALL", "FRM_PERFIL_READ"})
	public String listAll(int pageSize, int page) throws Exception{
		
		int limit=pageSize*page;
		int init=limit-pageSize;
		
		List<FrmPerfil> listAll=this.frmPerfilRepository.listAll(pageSize, page);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);
	}
	
	@Override
	public int getCount(){
				
		return frmPerfilRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFIL_ALL", "FRM_PERFIL_UPDATE"})
	public String update(FrmPerfil frmPerfil) throws Exception{
		return gson.toJson(frmPerfilRepository.update(frmPerfil));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFIL_ALL", "FRM_PERFIL_DELETE"})
	public void delete(FrmPerfil frmperfil) throws Exception{
		frmPerfilRepository.delete(frmperfil);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFIL_ALL", "FRM_PERFIL_CREATE"})
	public String insert(FrmPerfil frmperfil) throws Exception{
		return gson.toJson(frmPerfilRepository.insert(frmperfil));
	}
	
}

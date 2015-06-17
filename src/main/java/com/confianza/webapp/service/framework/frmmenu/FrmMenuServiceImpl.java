package com.confianza.webapp.service.framework.frmmenu;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.framework.frmmenu.FrmMenu;
import com.confianza.webapp.repository.framework.frmmenu.FrmMenuRepository;
import com.confianza.webapp.service.security.RolService;
import com.confianza.webapp.service.security.userDetails;
import com.confianza.webapp.utils.JSONUtil;
import com.google.gson.Gson;

@Service
public class FrmMenuServiceImpl implements FrmMenuService{
	
	@Autowired
	Gson gson;
	
	@Autowired
	userDetails userDetails;
	
	@Autowired
	private FrmMenuRepository frmMenuRepository;
	
	@Autowired
	private RolService rolService;
	
	/**
	 * @return the frmmenuRepository
	 */
	public FrmMenuRepository getFrmMenuRepository() {
		return frmMenuRepository;
	}

	/**
	 * @param frmmenuRepository the frmmenuRepository to set
	 */
	public void setFrmMenuRepository(FrmMenuRepository frmmenuRepository) {
		this.frmMenuRepository = frmmenuRepository;
	}
	
	@Override
	public String list(Long id){
		return gson.toJson(frmMenuRepository.list(id));
	}
	
	@Override
	public String listAll(){		
		//cargo los menus padres
		List<String> roles = userDetails.getRoles();
		
		List<Object[]> menu=this.loadMenu(null,roles);		
		List<Map<String, Object>> menuAll;
				
		if(menu!=null)		
			menuAll = createMenuWithChildren(menu, roles);
		else
			menuAll = createMenuWithoutProfiles();
		
		menuAll.add(0, createUser());
		menuAll.add(createLogout());
		
		return gson.toJson(menuAll);
	}

	private List<Map<String, Object>> createMenuWithoutProfiles() {
		
		Map<String, Object> withoutProfile=new HashMap<String, Object>();
		withoutProfile.put("menucons", "0");
		withoutProfile.put("menuicon", "glyphicon glyphicon-log-out");
		withoutProfile.put("menutitu", "Logout");
		withoutProfile.put("modudurl", "<c:url value=\"j_spring_security_logout\" />");
		withoutProfile.put("menuhijo", null);		
		
		List<Map<String, Object>> menuAll=new ArrayList<Map<String,Object>>();
		menuAll.add(withoutProfile);
		return menuAll;
	}

	private List<Map<String, Object>> createMenuWithChildren(List<Object[]> menu, List<String> roles) {
		
		//cast de los menu a ser mapeados por cada campo
		List<Map<String, Object>> menuAll = JSONUtil.toNameList(new String[]{"menucons", "menuicon", "menutitu", "modudurl", "menuhijo"},menu);
		//por cada menu se recorre para asignarle sus hijos
		for(Map<String, Object> map:menuAll){
			List<Map<String, Object>> menuhijos=loadChildren(Long.parseLong(map.get("menucons").toString()), roles);
			map.put("menuhijo", menuhijos);
		}
		return menuAll;
	}

	private Map<String, Object> createLogout() {
		Map<String, Object> logout=new HashMap<String, Object>();
		logout.put("menucons", "0");
		logout.put("menuicon", "glyphicon glyphicon-log-out");
		logout.put("menutitu", "Logout");
		logout.put("modudurl", "<c:url value=\"j_spring_security_logout\" />");
		logout.put("menuhijo", null);		
		return logout;
	}
	
	private Map<String, Object> createUser() {
		Map<String, Object> logout=new HashMap<String, Object>();
		logout.put("menucons", "0");
		logout.put("menuicon", "glyphicon glyphicon-user");
		logout.put("menutitu", userDetails.getUser());
		logout.put("modudurl", "icono");
		logout.put("menuhijo", null);		
		return logout;
	}
	
	private Map<String, Object> createIntermediario() {
		Map<String, Object> logout=new HashMap<String, Object>();
		logout.put("menucons", "0");
		logout.put("menuicon", "glyphicon glyphicon-user");
		logout.put("menutitu", "Intermediario");
		logout.put("modudurl", "icono");
		logout.put("menuhijo", null);		
		return logout;
	}
    
	@Override
	public FrmMenu update(Long id){
		return frmMenuRepository.update(id);
	}
	
	@Override
	public void delete(Long id){
		frmMenuRepository.delete(id);
	}
	
	@Override
	public String insert(FrmMenu frmmenu){
		return gson.toJson(frmMenuRepository.insert(frmmenu));
	}
	
	@Override
	public List<Object[]> loadMenu(Long id, List<String> roles) {

		if(roles.size()>0)
			return frmMenuRepository.loadMenu(roles, id);
		else
			return null;
	}			
	
	private List<Map<String, Object>> loadChildren(Long id, List<String> roles) {
		//cargo los hijos del papa
		List<Object[]> menu=this.loadMenu(id, roles);
		
		//cast de los hijos a ser mapeados por cada campo
		List<Map<String, Object>> menuAll = JSONUtil.toNameList(new String[]{"menucons", "menuicon", "menutitu", "modudurl", "menuhijo"},menu);
		
		//por cada menu se recorre para asignarle sus hijos		
		for(Map<String, Object> map:menuAll){
			List<Map<String, Object>> menuhijos=loadChildren(Long.parseLong(map.get("menucons").toString()), roles);
			map.put("menuhijo", menuhijos);
		}
		
		return menuAll;
	}
	
	@Override
	public String listAllIntermediario(){		
		//cargo los menus padres
		
		List<String> perfil =new ArrayList<String>();
		perfil.add("INTERMEDIARIO");
		
		List<Object[]> roles=rolService.loadRoles(perfil);
		List<String> perfilRoles =new ArrayList<String>();
		
		for(Object[] obj:roles)
			perfilRoles.add(obj[0].toString());
		
		List<Object[]> menu=this.loadMenu(null,perfilRoles);		
		List<Map<String, Object>> menuAll;
				
		if(menu!=null)		
			menuAll = createMenuWithChildren(menu, perfilRoles);
		else
			menuAll = createMenuWithoutProfiles();
		
		menuAll.add(0, createIntermediario());
		menuAll.add(createLogout());
		
		return gson.toJson(menuAll);
	}
}

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
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.framework.frmmenu.FrmMenu;
import com.confianza.webapp.repository.framework.frmmenu.FrmMenuRepository;
import com.confianza.webapp.utils.JSONUtil;
import com.google.gson.Gson;

@Service
public class FrmMenuServiceImpl implements FrmMenuService{
	
	@Autowired
	Gson gson;
	
	@Autowired
	private FrmMenuRepository frmMenuRepository;
	
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
		List<Object[]> menu=this.loadMenu(null);		
		List<Map<String, Object>> menuAll;
		
		if(menu!=null){
			
			//cast de los menu a ser mapeados por cada campo
			menuAll = JSONUtil.toNameList(new String[]{"menucons", "menuicon", "menutitu", "modudurl", "menuhijo"},menu
			);
			
			//por cada menu se recorre para asignarle sus hijos
			for(Map<String, Object> map:menuAll){
				List<Map<String, Object>> menuhijos=loadChildren(Long.parseLong(map.get("menucons").toString()));
				map.put("menuhijo", menuhijos);
			}
		}
		else{
			Object obj[]={0,"","No tiene permisos",null,null};
			menu=new ArrayList<Object[]>();
			menu.add(obj);
			//cast de los menu a ser mapeados por cada campo
			menuAll = JSONUtil.toNameList(new String[]{"menucons", "menuicon", "menutitu", "modudurl", "menuhijo"},menu
			);
		}
		return gson.toJson(menuAll);
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
	public List<Object[]> loadMenu(Long id) {

		List<String> roles = getRoles();
				
		if(roles.size()>0)
			return frmMenuRepository.loadMenu(roles, id);
		else
			return null;
	}
		
	private List<String> getRoles() {
		
		List<String> roles = new ArrayList<String>();
		List<GrantedAuthority> autorities = new ArrayList<GrantedAuthority>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {			 
			autorities=(List<GrantedAuthority>) auth.getAuthorities();
			for(GrantedAuthority obj:autorities){
				String[] auxRol= obj.toString().split("_");
				String rol="";
				for(int i=0;i<auxRol.length-1;i++){				
					if(rol.isEmpty())
						rol+=auxRol[i];
					else
						rol+="_"+auxRol[i];
				}
				roles.add(rol);	
			}
	    }
		
		return roles;
	}
	
	private List<Map<String, Object>> loadChildren(Long id) {
		//cargo los hijos del papa
		List<Object[]> menu=this.loadMenu(id);
		
		//cast de los hijos a ser mapeados por cada campo
		List<Map<String, Object>> menuAll = JSONUtil.toNameList(
				new String[]{"menucons", "menuicon", "menutitu", "modudurl", "menuhijo"},menu
		);
		
		//por cada menu se recorre para asignarle sus hijos		
		for(Map<String, Object> map:menuAll){
			List<Map<String, Object>> menuhijos=loadChildren(Long.parseLong(map.get("menucons").toString()));
			map.put("menuhijo", menuhijos);
		}
		
		return menuAll;
	}
}

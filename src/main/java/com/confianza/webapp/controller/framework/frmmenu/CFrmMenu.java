package com.confianza.webapp.controller.framework.frmmenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.confianza.webapp.service.framework.frmmenu.FrmMenuService;
import com.confianza.webapp.utils.JSONUtil;
import com.confianza.webapp.repository.framework.frmmenu.FrmMenu;
import com.confianza.webapp.repository.framework.frmsesion.FrmSesion;

@Controller
@RequestMapping("/FrmMenu")
public class CFrmMenu {

	private FrmMenuService frmMenuService;
	
	@Autowired
	Gson gson;
	
	@Autowired
	public CFrmMenu(FrmMenuService frmmenuService) {
		this.frmMenuService = frmmenuService;
	}
	
	public CFrmMenu() {
		super();
	}

	@RequestMapping("/")
	public String index(Model model) {
		return "framework/frmmenu/FrmMenu";
	}

	@RequestMapping(value = "/{menucons}.json", method = RequestMethod.GET, produces={"application/json"})
	public @ResponseBody String list(@PathVariable("menucons") Long menucons){
		
		return gson.toJson(this.frmMenuService.list(menucons));
	}
	
	@RequestMapping(value = "/listAll.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(HttpServletRequest request){

		//cargo los menus padres
		List<Object[]> menu=frmMenuService.loadMenu(null);		
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

	private List<Map<String, Object>> loadChildren(Long id) {
		//cargo los hijos del papa
		List<Object[]> menu=frmMenuService.loadMenu(id);
		
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
	
	@RequestMapping(value = "/{menucons}", method = RequestMethod.PUT)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public FrmMenu update(@PathVariable("menucons") Long menucons){
		return this.frmMenuService.update(menucons);
	}
	
	@RequestMapping(value = "/{menucons}.json", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public void delete(@PathVariable("menucons") Long menucons){
		this.frmMenuService.delete(menucons);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FrmMenu frmmenu){
		return gson.toJson(this.frmMenuService.insert(frmmenu));
	}		
}

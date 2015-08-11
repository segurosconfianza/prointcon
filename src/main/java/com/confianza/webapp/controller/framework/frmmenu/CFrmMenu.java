package com.confianza.webapp.controller.framework.frmmenu;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.confianza.webapp.service.framework.frmmenu.FrmMenuService;
import com.confianza.webapp.repository.framework.frmmenu.FrmMenu;

@Controller
@RequestMapping("/FrmMenu")
public class CFrmMenu {

	@Autowired
	private FrmMenuService frmMenuService;		

	public CFrmMenu() {
		super();
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		return "framework/frmmenu/FrmMenu";
	}
	
	@RequestMapping("/defaultView")
	public String defaul() {
		return "default";
	}

	@RequestMapping(value = "/{menucons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	public @ResponseBody String list(@PathVariable("menucons") Long menucons){
		
		return this.frmMenuService.list(menucons);
	}
	
	@RequestMapping(value = "/listAll.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(HttpServletRequest request){
		
		return frmMenuService.listAll();				
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
	
	@RequestMapping(method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FrmMenu frmmenu){
		return this.frmMenuService.insert(frmmenu);
	}		
}

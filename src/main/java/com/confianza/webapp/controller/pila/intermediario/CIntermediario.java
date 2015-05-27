package com.confianza.webapp.controller.pila.intermediario;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.confianza.webapp.service.framework.frmmenu.FrmMenuService;
import com.confianza.webapp.service.pila.pilusua.PilUsuaService;

@Controller
@RequestMapping("/Intermediario")
public class CIntermediario {

	@Autowired
	private PilUsuaService pilusuaService;
	
	@Autowired
	private FrmMenuService frmMenuService;
	
	public CIntermediario() {
		super();
	}
			
	@RequestMapping(value = "/validateUsua.json", params = {"user","password"}, method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String validateUsua(@RequestParam("user") String user, @RequestParam("password") String password){
		
		return this.pilusuaService.validateUsua(user, password);
	}
	
	@RequestMapping(value = "/listMenu.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String listMenu(HttpServletRequest request){
		
		return frmMenuService.listAllIntermediario();				
	}
}

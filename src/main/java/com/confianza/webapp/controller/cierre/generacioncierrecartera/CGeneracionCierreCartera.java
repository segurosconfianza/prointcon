package com.confianza.webapp.controller.cierre.generacioncierrecartera; 

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.confianza.webapp.service.cierre.generacioncierrecartera.GeneracionCierreCarteraService;
import com.confianza.webapp.service.security.RolService;

@Controller
@RequestMapping("/GeneracionCierreCartera")
public class CGeneracionCierreCartera {
	
	@Autowired
	private GeneracionCierreCarteraService cierreCarteraService;
	
	@Autowired
	private RolService rolService;
	
	public CGeneracionCierreCartera() {
		super();
	}
	
	@RequestMapping("/GeneracionCierreCartera/")
	public String soporteCierreCarteraCuadre() {
		return "cierre/generacioncierrecartera/GeneracionCierreCartera";
	}
	
	@RequestMapping(value = "/ExecuteProcessGeneracionCierreCartera.json", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String ExecuteProcess(@RequestParam("conscons") String conscons, @RequestParam("params") String params, HttpServletRequest request ) throws Throwable{
			
		String result=this.cierreCarteraService.executeProcessCierreCartera(conscons, params, request);  
		
		return result;
	}
}

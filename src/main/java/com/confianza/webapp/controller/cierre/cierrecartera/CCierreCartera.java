package com.confianza.webapp.controller.cierre.cierrecartera;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.confianza.webapp.service.cierre.cierrecartera.CierreCarteraService;
import com.confianza.webapp.service.security.RolService;

@Controller
@RequestMapping("/CierreCartera")
public class CCierreCartera {
	
	@Autowired
	private CierreCarteraService cierreCarteraService;
	
	@Autowired
	private RolService rolService;
	
	public CCierreCartera() {
		super();
	}
	
	@RequestMapping("/CierreCarteraCuadre/")
	public String soporteCierreCarteraCuadre() {
		return "cierre/cierrecartera/CierreCarteraCuadre";
	}
	
	@RequestMapping(value = "/ExecuteProcessCierreCartera.json", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String ExecuteProcess(@RequestParam("conscons") String conscons, @RequestParam("params") String params, HttpServletRequest request ) throws Throwable{
			
		String result=this.cierreCarteraService.executeProcessCierreCartera(conscons, params, request);  
		
		return result;
	}
}

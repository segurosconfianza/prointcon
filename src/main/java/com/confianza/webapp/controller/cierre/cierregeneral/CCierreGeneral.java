package com.confianza.webapp.controller.cierre.cierregeneral;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.confianza.webapp.service.cierre.cierreregeneral.CierreGeneralService;
import com.confianza.webapp.service.security.RolService;

@Controller
@RequestMapping("/CierreGeneral")
public class CCierreGeneral {
	
	@Autowired
	private CierreGeneralService cierreGeneralService;
	
	@Autowired
	private RolService rolService;
	
	public CCierreGeneral() {
		super();
	}
	
	@RequestMapping("/CierreGeneral/")
	public String soporteCierreCarteraCuadre() {
		return "cierre/cierregeneral/CierreGeneral";
	}
	
	@RequestMapping(value = "/ExecuteProcessCierreGeneral.json", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String ExecuteProcess(@RequestParam("conscons") String conscons, @RequestParam("params") String params, HttpServletRequest request ) throws Throwable{
			
		String result=this.cierreGeneralService.executeProcessCierreGeneral(conscons, params, request);  
		
		return result;
	}
}

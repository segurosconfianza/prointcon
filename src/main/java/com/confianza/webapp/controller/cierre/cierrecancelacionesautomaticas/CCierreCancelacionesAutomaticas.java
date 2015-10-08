package com.confianza.webapp.controller.cierre.cierrecancelacionesautomaticas;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.confianza.webapp.service.cierre.cierrecancelacionesautomaticas.CierreCancelacionesAutomaticasService;
import com.confianza.webapp.service.security.RolService;

@Controller
@RequestMapping("/CierreCancelaciones")
public class CCierreCancelacionesAutomaticas {
	
	@Autowired
	private CierreCancelacionesAutomaticasService cierreCancelacionesAutomaticasService;
	
	@Autowired
	private RolService rolService;
	
	public CCierreCancelacionesAutomaticas() {
		super();
	}
	
	@RequestMapping("/CierreCancelacionesAutomaticas/")
	public String soporteCierreCarteraCuadre() {
		return "cierre/cierrecancelacionesautomaticas/CierreCancelacionesAutomaticas";
	}
	
	@RequestMapping(value = "/ExecuteProcessCierreCancelacionesAuto.json", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String ExecuteProcess(@RequestParam("conscons") String conscons, @RequestParam("params") String params, HttpServletRequest request ) throws Throwable{
			
		return this.cierreCancelacionesAutomaticasService.executeProcessCierreCartera(conscons, params, request);  
	}
	
	@RequestMapping(value = "/ExecuteProcessCreateCancelaciones.json", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String ExecuteProcessCancel(@RequestParam("selectedItems") String selectedItems, @RequestParam("countItems") int countItems, @RequestParam("params") String params, HttpServletRequest request ) throws Throwable{
			
		return this.cierreCancelacionesAutomaticasService.cierreCreateCancelaciones(selectedItems, countItems, params, request);  
	}
}

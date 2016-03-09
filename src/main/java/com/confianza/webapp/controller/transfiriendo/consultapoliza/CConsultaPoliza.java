package com.confianza.webapp.controller.transfiriendo.consultapoliza;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.confianza.webapp.service.transfiriendo.consultapoliza.ConsultaPolizaService;

@Controller
@RequestMapping("/Transfiriendo")
public class CConsultaPoliza {
	
	@Autowired
	private ConsultaPolizaService consultaPolizaService;
		
	public CConsultaPoliza() {
		super();
	}   
	
	@RequestMapping(value = "/listPoliza.xml", method = RequestMethod.GET, produces={"application/xml; charset=ISO-8859-1"})
	@ResponseBody
	public String listPoliza(@RequestParam("SUCURSAL") String SUCURSAL, @RequestParam("PRODUCTO") String PRODUCTO, @RequestParam("POLIZA") String POLIZA, @RequestParam(value="CERTIFICADO", required=false) String CERTIFICADO, HttpServletRequest request ) {
		return consultaPolizaService.listPoliza(SUCURSAL, PRODUCTO, POLIZA, CERTIFICADO, request); 
	} 
		
}

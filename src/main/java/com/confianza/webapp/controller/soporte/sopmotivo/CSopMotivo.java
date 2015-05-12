package com.confianza.webapp.controller.soporte.sopmotivo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.confianza.webapp.service.soporte.sopmotivo.SopMotivoService;
import com.confianza.webapp.repository.soporte.sopmotivo.SopMotivo;

@Controller
@RequestMapping("/SopMotivo")
public class CSopMotivo {

	@Autowired
	private SopMotivoService sopMotivoService;
	
	public CSopMotivo() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "framework/sopmotivo/SopMotivo";
	}
	
	@RequestMapping(value = "/{moticons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("moticons") Long moticons){
		
		return this.sopMotivoService.list(moticons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize","motitran"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("motitran") Long motitran){
	
		return this.sopMotivoService.listAll(pageSize, page, motitran);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody SopMotivo sopmotivo, HttpServletRequest request){
	
		return this.sopMotivoService.update(sopmotivo);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody SopMotivo sopmotivo, HttpServletRequest request){
	
		//sopmotivo.setesta("B");
		return this.sopMotivoService.update(sopmotivo);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody SopMotivo sopmotivo, HttpServletRequest request){
		
		return this.sopMotivoService.insert(sopmotivo);		
	}
}

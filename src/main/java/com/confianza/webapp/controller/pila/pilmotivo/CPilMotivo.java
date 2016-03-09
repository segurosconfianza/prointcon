package com.confianza.webapp.controller.pila.pilmotivo;

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


import com.confianza.webapp.service.pila.pilmotivo.PilMotivoService;
import com.confianza.webapp.repository.pila.pilmotivo.PilMotivo;

@Controller
@RequestMapping("/PilMotivo")
public class CPilMotivo {

	@Autowired
	private PilMotivoService pilMotivoService;
	
	public CPilMotivo() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "pila/pilmotivo/PilMotivo";
	}
	
	@RequestMapping(value = "/{devocons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("devocons") Long devocons){
		
		return this.pilMotivoService.list(devocons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.pilMotivoService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody PilMotivo pilmotivo, HttpServletRequest request){
	
		return this.pilMotivoService.update(pilmotivo);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody PilMotivo pilmotivo, HttpServletRequest request){
	
		//pilmotivo.setesta("B");
		return this.pilMotivoService.update(pilmotivo);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody PilMotivo pilmotivo, HttpServletRequest request){
		
		return this.pilMotivoService.insert(pilmotivo);		
	}
}

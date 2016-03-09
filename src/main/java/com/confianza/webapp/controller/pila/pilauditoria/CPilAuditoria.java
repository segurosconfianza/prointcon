package com.confianza.webapp.controller.pila.pilauditoria;

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


import com.confianza.webapp.service.pila.pilauditoria.PilAuditoriaService;
import com.confianza.webapp.repository.pila.pilauditoria.PilAuditoria;

@Controller
@RequestMapping("/PilAuditoria")
public class CPilAuditoria {

	@Autowired
	private PilAuditoriaService pilAuditoriaService;
	
	public CPilAuditoria() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "pila/pilauditoria/PilAuditoria";
	}
	
	@RequestMapping(value = "/{audicons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("audicons") Long audicons){
		
		return this.pilAuditoriaService.list(audicons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.pilAuditoriaService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody PilAuditoria pilauditoria, HttpServletRequest request){
	
		return this.pilAuditoriaService.update(pilauditoria);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody PilAuditoria pilauditoria, HttpServletRequest request){
	
		//pilauditoria.setesta("B");
		return this.pilAuditoriaService.update(pilauditoria);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody PilAuditoria pilauditoria, HttpServletRequest request){
		
		return this.pilAuditoriaService.insert(pilauditoria);		
	}
}

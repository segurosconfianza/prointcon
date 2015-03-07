package com.confianza.webapp.controller.soporte.sopadjunto;

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


import com.confianza.webapp.service.soporte.sopadjunto.SopAdjuntoService;
import com.confianza.webapp.repository.soporte.sopadjunto.SopAdjunto;

@Controller
@RequestMapping("/SopAdjunto")
public class CSopAdjunto {

	@Autowired
	private SopAdjuntoService sopAdjuntoService;		
	
	public CSopAdjunto() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "framework/sopadjunto/SopAdjunto";
	}
	
	@RequestMapping(value = "/{adjucodi}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("adjucodi") Long adjucodi){
		
		return this.sopAdjuntoService.list(adjucodi);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.sopAdjuntoService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody SopAdjunto sopadjunto, HttpServletRequest request){
	
		return this.sopAdjuntoService.update(sopadjunto);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody SopAdjunto sopadjunto, HttpServletRequest request){
	
		//sopadjunto.setesta("B");
		return this.sopAdjuntoService.update(sopadjunto);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody SopAdjunto sopadjunto, HttpServletRequest request){
		
		return this.sopAdjuntoService.insert(sopadjunto);		
	}
}

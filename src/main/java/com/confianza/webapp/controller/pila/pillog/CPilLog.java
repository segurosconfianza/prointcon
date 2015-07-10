package com.confianza.webapp.controller.pila.pillog;

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


import com.confianza.webapp.service.pila.pillog.PilLogService;
import com.confianza.webapp.repository.pila.pillog.PilLog;

@Controller
@RequestMapping("/PilLog")
public class CPilLog {

	@Autowired
	private PilLogService pilLogService;
	
	public CPilLog() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "pila/pillog/PilLog";
	}
	
	@RequestMapping(value = "/{slogcons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("slogcons") Long slogcons){
		
		return this.pilLogService.list(slogcons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.pilLogService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody PilLog pillog, HttpServletRequest request){
	
		return this.pilLogService.update(pillog);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody PilLog pillog, HttpServletRequest request){
	
		//pillog.setesta("B");
		return this.pilLogService.update(pillog);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody PilLog pillog, HttpServletRequest request){
		
		return this.pilLogService.insert(pillog);		
	}
}

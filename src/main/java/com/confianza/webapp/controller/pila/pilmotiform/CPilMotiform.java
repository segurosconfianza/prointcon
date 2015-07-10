package com.confianza.webapp.controller.pila.pilmotiform;

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

import com.confianza.webapp.service.pila.pilmotiform.PilMotiformService;
import com.confianza.webapp.repository.pila.pilmotiform.PilMotiform;

@Controller
@RequestMapping("/PilMotiform")
public class CPilMotiform {

	@Autowired
	private PilMotiformService pilmotiformService;
	
	public CPilMotiform() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "formatos/pilmotiform/PilMotiform";
	}
	
	@RequestMapping(value = "/{mofocons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("mofocons") Long mofocons){
		
		return this.pilmotiformService.list(mofocons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize","order","filter"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("order") String order, @RequestParam("filter") String filters){
	
		return this.pilmotiformService.listAll(pageSize, page, order, filters);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody PilMotiform pilmotiform, HttpServletRequest request){
	
		return this.pilmotiformService.update(pilmotiform);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody PilMotiform pilmotiform, HttpServletRequest request){
	
		//pilmotiform.setesta("B");
		return this.pilmotiformService.update(pilmotiform);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody PilMotiform pilmotiform, HttpServletRequest request){
		
		return this.pilmotiformService.insert(pilmotiform);		
	}
	
	@RequestMapping(value = "/insertDevolucion", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insertDevolucion(@RequestBody PilMotiform pilmotiform, HttpServletRequest request){
		
		return this.pilmotiformService.insertDevolucion(pilmotiform, request);
	}
}

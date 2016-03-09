package com.confianza.webapp.controller.cierre.cieestaproc;

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


import com.confianza.webapp.service.cierre.cieestaproc.FacEstaprocService;
import com.confianza.webapp.repository.cierre.cieestaproc.CieEstaproc;

@Controller
@RequestMapping("/CieEstaproc")
public class CCieEstaproc {

	@Autowired
	private FacEstaprocService cieestaprocService;
	
	public CCieEstaproc() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "cierre/cieestaproc/CieEstaproc";
	}
	
	@RequestMapping(value = "/{esprcons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("esprcons") Long esprcons){
		
		return this.cieestaprocService.list(esprcons);
	}
	
	@RequestMapping(value = "/listAll.json",  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("order") String order, @RequestParam(value ="filter", required=false) String filters){
	
		return this.cieestaprocService.listAll(pageSize, page, order, filters);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody CieEstaproc cieestaproc, HttpServletRequest request){
	
		return this.cieestaprocService.update(cieestaproc);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody CieEstaproc cieestaproc, HttpServletRequest request){
	
		//cieestaproc.setesta("B");
		return this.cieestaprocService.update(cieestaproc);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody CieEstaproc cieestaproc, HttpServletRequest request){
		
		return this.cieestaprocService.insert(cieestaproc);		
	}
}

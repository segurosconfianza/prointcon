package com.confianza.webapp.controller.formatos.fmtvalocamp;

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


import com.confianza.webapp.service.formatos.fmtvalocamp.FmtValocampService;
import com.confianza.webapp.repository.formatos.fmtvalocamp.FmtValocamp;

@Controller
@RequestMapping("/FmtValocamp")
public class CFmtValocamp {

	@Autowired
	private FmtValocampService fmtvalocampService;
	
	public CFmtValocamp() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "formatos/fmtvalocamp/FmtValocamp";
	}
	
	@RequestMapping(value = "/{vacacons}.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String list(@PathVariable("vacacons") Long vacacons){
		
		return this.fmtvalocampService.list(vacacons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.fmtvalocampService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FmtValocamp fmtvalocamp, HttpServletRequest request){
	
		return this.fmtvalocampService.update(fmtvalocamp);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FmtValocamp fmtvalocamp, HttpServletRequest request){
	
		//fmtvalocamp.setesta("B");
		return this.fmtvalocampService.update(fmtvalocamp);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FmtValocamp fmtvalocamp, HttpServletRequest request){
		
		return this.fmtvalocampService.insert(fmtvalocamp);		
	}
}

package com.confianza.webapp.controller.formatos.fmtformregi;

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


import com.confianza.webapp.service.formatos.fmtformregi.FmtFormregiService;
import com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregi;

@Controller
@RequestMapping("/FmtFormregi")
public class CFmtFormregi {

	@Autowired
	private FmtFormregiService fmtformregiService;
	
	public CFmtFormregi() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "formatos/fmtformregi/FmtFormregi";
	}
	
	@RequestMapping(value = "/{forecons}.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String list(@PathVariable("forecons") Long forecons){
		
		return this.fmtformregiService.list(forecons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.fmtformregiService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FmtFormregi fmtformregi, HttpServletRequest request){
	
		return this.fmtformregiService.update(fmtformregi);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FmtFormregi fmtformregi, HttpServletRequest request){
	
		//fmtformregi.setesta("B");
		return this.fmtformregiService.update(fmtformregi);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FmtFormregi fmtformregi, HttpServletRequest request){
		
		return this.fmtformregiService.insert(fmtformregi);		
	}
}

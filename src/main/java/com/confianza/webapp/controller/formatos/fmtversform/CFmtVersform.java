package com.confianza.webapp.controller.formatos.fmtversform;

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



import com.confianza.webapp.service.formatos.fmtversform.FmtVersformService;
import com.confianza.webapp.repository.formatos.fmtversform.FmtVersform;

@Controller
@RequestMapping("/FmtVersform")
public class CFmtVersform {

	@Autowired
	private FmtVersformService fmtVersformService;
	
	public CFmtVersform() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "formatos/fmtversform/FmtVersform";
	}
	
	@RequestMapping(value = "/{vefocons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("vefocons") Long vefocons){
		
		return this.fmtVersformService.list(vefocons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.fmtVersformService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FmtVersform fmtversform, HttpServletRequest request){
	
		return this.fmtVersformService.update(fmtversform);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FmtVersform fmtversform, HttpServletRequest request){
	
		//fmtversform.setesta("B");
		return this.fmtVersformService.update(fmtversform);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FmtVersform fmtversform, HttpServletRequest request){
		
		return this.fmtVersformService.insert(fmtversform);		
	}
	
	@RequestMapping(value = "/lastVersion.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String lastVersion(@PathVariable("vefoform") Long vefoform){
		
		return this.fmtVersformService.lastVersion(vefoform);
	}
}

package com.confianza.webapp.controller.formatos.fmtlog;

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


import com.confianza.webapp.service.formatos.fmtlog.FmtLogService;
import com.confianza.webapp.repository.formatos.fmtlog.FmtLog;

@Controller
@RequestMapping("/FmtLog")
public class CFmtLog {

	@Autowired
	private FmtLogService fmtLogService;
	
	public CFmtLog() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "formatos/fmtlog/FmtLog";
	}
	
	@RequestMapping(value = "/{slogcons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("slogcons") Long slogcons){
		
		return this.fmtLogService.list(slogcons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.fmtLogService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FmtLog fmtlog, HttpServletRequest request){
	
		return this.fmtLogService.update(fmtlog);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FmtLog fmtlog, HttpServletRequest request){
	
		//fmtlog.setesta("B");
		return this.fmtLogService.update(fmtlog);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FmtLog fmtlog, HttpServletRequest request){
		
		return this.fmtLogService.insert(fmtlog);		
	}
}

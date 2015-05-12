package com.confianza.webapp.controller.framework.frmlog;

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



import com.confianza.webapp.service.framework.frmlog.FrmLogService;
import com.confianza.webapp.repository.framework.frmlog.FrmLog;

@Controller
@RequestMapping("/FrmLog")
public class CFrmLog {

	@Autowired
	private FrmLogService frmlogService;
	
	public CFrmLog() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "framework/frmlog/FrmLog";
	}
	
	@RequestMapping(value = "/{slogcons}.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String list(@PathVariable("slogcons") Long slogcons){
		
		return this.frmlogService.list(slogcons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize","slogtran"},  method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("slogtran") Long slogtran){
	
		return this.frmlogService.listAll(pageSize, page, slogtran);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FrmLog frmlog, HttpServletRequest request){
	
		return this.frmlogService.update(frmlog);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FrmLog frmlog, HttpServletRequest request){
	
		//frmlog.setesta("B");
		return this.frmlogService.update(frmlog);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FrmLog frmlog, HttpServletRequest request){
		
		return this.frmlogService.insert(frmlog);		
	}
}

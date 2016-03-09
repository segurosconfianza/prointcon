package com.confianza.webapp.controller.formatos.fmtauditoria;

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



import com.confianza.webapp.service.formatos.fmtauditoria.FmtAuditoriaService;
import com.confianza.webapp.repository.formatos.fmtauditoria.FmtAuditoria;

@Controller
@RequestMapping("/FmtAuditoria")
public class CFmtAuditoria {

	@Autowired
	private FmtAuditoriaService fmtAuditoriaService;
	
	public CFmtAuditoria() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "formatos/fmtauditoria/FmtAuditoria";
	}
	
	@RequestMapping(value = "/{audicons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("audicons") Long audicons){
		
		return this.fmtAuditoriaService.list(audicons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.fmtAuditoriaService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FmtAuditoria fmtauditoria, HttpServletRequest request){
	
		return this.fmtAuditoriaService.update(fmtauditoria);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FmtAuditoria fmtauditoria, HttpServletRequest request){
	
		//fmtauditoria.setesta("B");
		return this.fmtAuditoriaService.update(fmtauditoria);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FmtAuditoria fmtauditoria, HttpServletRequest request){
		
		return this.fmtAuditoriaService.insert(fmtauditoria);		
	}
	
	@RequestMapping(value = "/listAllFormregi.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAllFrmFormregi(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam(value ="order", required=false) String order, @RequestParam(value ="filter", required=false) String filters, @RequestParam("forecons") Long forecons){
		
		return this.fmtAuditoriaService.listAllFrmFormregi(pageSize, page, order, filters, forecons);
	}
}

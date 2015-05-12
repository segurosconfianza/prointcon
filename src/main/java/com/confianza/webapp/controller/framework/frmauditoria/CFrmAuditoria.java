package com.confianza.webapp.controller.framework.frmauditoria;

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



import com.confianza.webapp.service.framework.frmauditoria.FrmAuditoriaService;
import com.confianza.webapp.repository.framework.frmauditoria.FrmAuditoria;

@Controller
@RequestMapping("/FrmAuditoria")
public class CFrmAuditoria {

	@Autowired
	private FrmAuditoriaService frmauditoriaService;
	
	public CFrmAuditoria() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "framework/frmauditoria/FrmAuditoria";
	}
	
	@RequestMapping(value = "/{audicons}.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String list(@PathVariable("audicons") Long audicons){
		
		return this.frmauditoriaService.list(audicons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize","auditran"},  method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("auditran") Long auditran){
	
		return this.frmauditoriaService.listAll(pageSize, page, auditran);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FrmAuditoria frmauditoria, HttpServletRequest request){
	
		return this.frmauditoriaService.update(frmauditoria);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FrmAuditoria frmauditoria, HttpServletRequest request){
	
		//frmauditoria.setesta("B");
		return this.frmauditoriaService.update(frmauditoria);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FrmAuditoria frmauditoria, HttpServletRequest request){
		
		return this.frmauditoriaService.insert(frmauditoria);		
	}
}

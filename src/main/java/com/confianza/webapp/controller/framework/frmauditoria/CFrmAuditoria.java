package com.confianza.webapp.controller.framework.frmauditoria;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
 
import com.confianza.webapp.service.framework.frmauditoria.FrmAuditoriaService;
import com.confianza.webapp.repository.framework.frmauditoria.FrmAuditoria;

@Controller
@RequestMapping("/FrmAuditoria")
public class CFrmAuditoria {

	@Autowired
	private FrmAuditoriaService frmAuditoriaService;
	
	public CFrmAuditoria(){
		super();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public FrmAuditoria list(Long id){
		
		return this.frmAuditoriaService.list(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<FrmAuditoria> listAll(){
	
		return this.frmAuditoriaService.listAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public void delete(Long id){
		this.frmAuditoriaService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public FrmAuditoria insert(@RequestBody FrmAuditoria frmauditoria){
		return this.frmAuditoriaService.insert(frmauditoria);
	}
}

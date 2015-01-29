package com.confianza.webapp.controller.framework.frmroleperm;

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
 
import com.confianza.webapp.service.framework.frmroleperm.FrmRolepermService;
import com.confianza.webapp.repository.framework.frmroleperm.FrmRoleperm;

@Controller
@RequestMapping("/FrmRoleperm")
public class CFrmRoleperm {

	@Autowired
	private FrmRolepermService frmRolepermService;
	
	public CFrmRoleperm(){
		super();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public FrmRoleperm list(Long id){
		
		return this.frmRolepermService.list(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<FrmRoleperm> listAll(){
	
		return this.frmRolepermService.listAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public FrmRoleperm update(Long id){
		return this.frmRolepermService.update(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public void delete(Long id){
		this.frmRolepermService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public FrmRoleperm insert(@RequestBody FrmRoleperm frmroleperm){
		return this.frmRolepermService.insert(frmroleperm);
	}
}

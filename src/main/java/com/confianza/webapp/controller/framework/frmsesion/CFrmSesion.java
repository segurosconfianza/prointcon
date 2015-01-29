package com.confianza.webapp.controller.framework.frmsesion;

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
 
import com.confianza.webapp.service.framework.frmsesion.FrmSesionService;
import com.confianza.webapp.repository.framework.frmsesion.FrmSesion;

@Controller
@RequestMapping("/FrmSesion")
public class CFrmSesion {

	@Autowired
	private FrmSesionService frmSesionService;
	
	public CFrmSesion(){
		super();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public FrmSesion list(Long id){
		
		return this.frmSesionService.list(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<FrmSesion> listAll(){
	
		return this.frmSesionService.listAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public FrmSesion update(Long id){
		return this.frmSesionService.update(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public void delete(Long id){
		this.frmSesionService.delete(id);
	}
		
}

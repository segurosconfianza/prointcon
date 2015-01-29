package com.confianza.webapp.controller.framework.frmmodulo;

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
 
import com.confianza.webapp.service.framework.frmmodulo.FrmModuloService;
import com.confianza.webapp.repository.framework.frmmodulo.FrmModulo;

@Controller
@RequestMapping("/FrmModulo")
public class CFrmModulo {

	@Autowired
	private FrmModuloService frmmoduloService;
	
	public CFrmModulo(){
		super();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public FrmModulo list(Long id){
		
		return this.frmmoduloService.list(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<FrmModulo> listAll(){
	
		return this.frmmoduloService.listAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public FrmModulo update(Long id){
		return this.frmmoduloService.update(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public void delete(Long id){
		this.frmmoduloService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public FrmModulo insert(@RequestBody FrmModulo frmmodulo){
		return this.frmmoduloService.insert(frmmodulo);
	}
}

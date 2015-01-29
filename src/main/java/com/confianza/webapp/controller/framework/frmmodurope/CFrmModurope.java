package com.confianza.webapp.controller.framework.frmmodurope;

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
 
import com.confianza.webapp.service.framework.frmmodurope.FrmModuropeService;
import com.confianza.webapp.repository.framework.frmmodurope.FrmModurope;

@Controller
@RequestMapping("/FrmModurope")
public class CFrmModurope {

	@Autowired
	private FrmModuropeService frmModuropeService;
	
	public CFrmModurope(){
		super();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public FrmModurope list(Long id){
		
		return this.frmModuropeService.list(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<FrmModurope> listAll(){
	
		return this.frmModuropeService.listAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public FrmModurope update(Long id){
		return this.frmModuropeService.update(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public void delete(Long id){
		this.frmModuropeService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public FrmModurope insert(@RequestBody FrmModurope frmmodurope){
		return this.frmModuropeService.insert(frmmodurope);
	}
}

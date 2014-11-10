package com.confianza.webapp.controller.framework.frmperfmodu;

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
 
import com.confianza.webapp.service.framework.frmperfmodu.FrmPerfmoduService;
import com.confianza.webapp.repository.framework.frmperfmodu.FrmPerfmodu;

@Controller
@RequestMapping("/FrmPerfmodu")
public class CFrmPerfmodu {

	private FrmPerfmoduService frmPerfmoduService;
	
	@Autowired
	public CFrmPerfmodu(FrmPerfmoduService frmperfmoduService) {
		this.frmPerfmoduService = frmperfmoduService;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public FrmPerfmodu list(Long id){
		
		return this.frmPerfmoduService.list(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<FrmPerfmodu> listAll(){
	
		return this.frmPerfmoduService.listAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public FrmPerfmodu update(Long id){
		return this.frmPerfmoduService.update(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public void delete(Long id){
		this.frmPerfmoduService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public FrmPerfmodu insert(@RequestBody FrmPerfmodu frmperfmodu){
		return this.frmPerfmoduService.insert(frmperfmodu);
	}
}

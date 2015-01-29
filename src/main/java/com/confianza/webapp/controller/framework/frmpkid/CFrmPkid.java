package com.confianza.webapp.controller.framework.frmpkid;

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
 
import com.confianza.webapp.service.framework.frmpkid.FrmPkidService;
import com.confianza.webapp.repository.framework.frmpkid.FrmPkid;

@Controller
@RequestMapping("/FrmPkid")
public class CFrmPkid {

	@Autowired
	private FrmPkidService frmPkidService;
	
	public CFrmPkid(){
		super();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public FrmPkid list(Long id){
		
		return this.frmPkidService.list(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<FrmPkid> listAll(){
	
		return this.frmPkidService.listAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public FrmPkid update(Long id){
		return this.frmPkidService.update(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public void delete(Long id){
		this.frmPkidService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public FrmPkid insert(@RequestBody FrmPkid frmpkid){
		return this.frmPkidService.insert(frmpkid);
	}
}

package com.confianza.webapp.controller.framework.frmperfmodu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.confianza.webapp.service.framework.frmperfmodu.FrmPerfmoduService;
import com.confianza.webapp.utils.JSONUtil;
import com.confianza.webapp.repository.framework.frmperfmodu.FrmPerfmodu;
import com.google.gson.Gson;

@Controller
@RequestMapping("/FrmPerfmodu")
public class CFrmPerfmodu {

	@Autowired
	private FrmPerfmoduService frmPerfmoduService;
	
	public CFrmPerfmodu() {
		super();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String list(Long id){

		return this.frmPerfmoduService.list(id);
	}
		
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("pemopefi") Long pemopefi) throws Exception{
		
		return this.frmPerfmoduService.listAll(pageSize, page, pemopefi);
	}
		
	@RequestMapping(value = "/listComboMoro.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listComboMoro() throws Exception{

		return this.frmPerfmoduService.listComboMoro();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FrmPerfmodu frmperfmodu){
			
		return this.frmPerfmoduService.update(frmperfmodu);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FrmPerfmodu frmperfmodu, HttpServletRequest request){

		return this.frmPerfmoduService.delete(frmperfmodu);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FrmPerfmodu frmperfmodu, HttpServletRequest request){

		return this.frmPerfmoduService.insert(frmperfmodu);
	}
}

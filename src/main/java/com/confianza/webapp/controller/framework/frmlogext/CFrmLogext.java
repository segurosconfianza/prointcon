package com.confianza.webapp.controller.framework.frmlogext;

import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;
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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.confianza.webapp.utils.JSONUtil;

import com.confianza.webapp.service.framework.frmlogext.FrmLogextService;
import com.confianza.webapp.repository.framework.frmlogext.FrmLogext;

@Controller
@RequestMapping("/FrmLogext")
public class CFrmLogext {

	private FrmLogextService frmlogextService;
	
	@Autowired
	Gson gson;
	
	public CFrmLogext() {
		super();
	}
	
	@Autowired
	public CFrmLogext(FrmLogextService frmlogextService) {
		this.frmlogextService = frmlogextService;
	}
		
	@RequestMapping("/")
	public String index() {
		return "framework/frmlogext/FrmLogext";
	}
	
	@RequestMapping(value = "/{loexcons}.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String list(@PathVariable("loexcons") Long loexcons){
		
		try{
			return gson.toJson(this.frmlogextService.list(loexcons));
		}catch(AccessDeniedException e){
			return "Acceso denegado";
		}
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		try{
			List<FrmLogext> listAll=this.frmlogextService.listAll(pageSize, page);
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("data", listAll);
			result.put("count", this.frmlogextService.getCount());
			
			return gson.toJson(result);
		}catch(AccessDeniedException e){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tituloError", "Acceso denegado");
			result.put("error", "No posee los permisos para esta accion");
			return gson.toJson(result);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FrmLogext frmlogext, HttpServletRequest request){
	
		try{		
			return gson.toJson(this.frmlogextService.update(frmlogext) );
		}catch(AccessDeniedException e){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tituloError", "Acceso denegado");
			result.put("error", "No posee los permisos para esta accion");
			return gson.toJson(result);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FrmLogext frmlogext, HttpServletRequest request){
	
		try{
			//frmlogext.setesta("B");
			return gson.toJson(this.frmlogextService.update(frmlogext));
		}catch(AccessDeniedException e){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tituloError", "Acceso denegado");
			result.put("error", "No posee los permisos para esta accion");
			return gson.toJson(result);
		}
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FrmLogext frmlogext, HttpServletRequest request){
		
		try{
			//frmlogext.setesta("A");
			//frmlogext.setfecr(new Date());
			
			return gson.toJson(this.frmlogextService.insert(frmlogext));
		}catch(AccessDeniedException e){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tituloError", "Acceso denegado");
			result.put("error", "No posee los permisos para esta accion");
			return gson.toJson(result);
		}
	}
}

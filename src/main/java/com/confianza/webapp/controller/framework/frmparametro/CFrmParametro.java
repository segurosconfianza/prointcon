package com.confianza.webapp.controller.framework.frmparametro;

import java.util.ArrayList;
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
import com.confianza.webapp.service.framework.frmparametro.FrmParametroService;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;

@Controller
@RequestMapping("/FrmParametro")
public class CFrmParametro {

	private FrmParametroService frmParametroService;
	
	@Autowired
	Gson gson;
	
	public CFrmParametro() {
		super();
	}
	
	@Autowired
	public CFrmParametro(FrmParametroService frmparametroService) {
		this.frmParametroService = frmparametroService;
	}
		
	@RequestMapping("/")
	public String index() {
		return "framework/frmparametro/FrmParametro";
	}
	
	@RequestMapping(value = "/{paracons}.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String list(@PathVariable("paracons") Long paracons){
		
		try{
			return gson.toJson(this.frmParametroService.list(paracons));
		}catch(AccessDeniedException e){
			return "Acceso denegado";
		}
	}
	
	@RequestMapping(value = "/params.json", params = {"paracons"}, method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listParamsCosu(@RequestParam("paracons") Long paracons){
		
		try{			
			Map<String, Object> result = new HashMap<String, Object>();
			List<FrmParametro> listAll=this.frmParametroService.listParamsCosu(paracons);
			
			result.put("data", listAll);
			
			return gson.toJson(result);
		}catch(AccessDeniedException e){
			return "Acceso denegado";
		}
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		try{
			List<FrmParametro> listAll=this.frmParametroService.listAll(pageSize, page);
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("data", listAll);
			result.put("count", this.frmParametroService.getCount());
			
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
	public String update(@RequestBody FrmParametro frmparametro, HttpServletRequest request){
	
		try{		
			return gson.toJson(this.frmParametroService.update(frmparametro) );
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
	public String delete(@RequestBody FrmParametro frmparametro, HttpServletRequest request){
	
		try{
			//frmparametro.setesta("B");
			return gson.toJson(this.frmParametroService.update(frmparametro));
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
	public String insert(@RequestBody FrmParametro frmparametro, HttpServletRequest request){
		
		try{
			//frmparametro.setesta("A");
			//frmparametro.setfecr(new Date());
			
			return gson.toJson(this.frmParametroService.insert(frmparametro));
		}catch(AccessDeniedException e){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tituloError", "Acceso denegado");
			result.put("error", "No posee los permisos para esta accion");
			return gson.toJson(result);
		}
	}
}

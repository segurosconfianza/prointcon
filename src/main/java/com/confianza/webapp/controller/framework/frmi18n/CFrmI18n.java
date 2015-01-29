package com.confianza.webapp.controller.framework.frmi18n;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.confianza.webapp.service.framework.frmi18n.FrmI18nService;
import com.confianza.webapp.repository.framework.frmi18n.FrmI18n;
import com.confianza.webapp.repository.framework.frmsesion.FrmSesion;

@Controller
@RequestMapping("/FrmI18n")
public class CFrmI18n {

	@Autowired
	private FrmI18nService frmI18nService;
	
	@Autowired
	Gson gson;
	
	public CFrmI18n(){
		super();
	}
	
	@RequestMapping("/")
	public String index() {
		return "framework/frmi18n/FrmI18n";
	}
	
	@RequestMapping(value = "/{etincons}.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String list(@PathVariable("etincons") Long etincons){
		
		return gson.toJson(this.frmI18nService.list(etincons));
	}
	
	@RequestMapping(value = "/listModulo.json", params = {"modulo"}, method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listModulo(@RequestParam("modulo") String modulo){
		
		return gson.toJson(this.frmI18nService.listModulo(modulo));
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		List<FrmI18n> listAll=this.frmI18nService.listAll(pageSize, page);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.frmI18nService.getCount());
		
		return gson.toJson(result);
	}
	
	@RequestMapping(value = "/{etincons}", method = RequestMethod.PUT)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public FrmI18n update(@PathVariable("etincons") Long etincons, HttpServletRequest request){
	
		HttpSession session = request.getSession();
		FrmSesion frmSesion = (FrmSesion) session.getAttribute("frmSesion");
		
		return this.frmI18nService.update(etincons);
	}
	
	@RequestMapping(value = "/{etincons}.json", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public void delete(@PathVariable("etincons") Long etincons, HttpServletRequest request){
	
		HttpSession session = request.getSession();
		FrmSesion frmSesion = (FrmSesion) session.getAttribute("frmSesion");
		
		this.frmI18nService.delete(etincons);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FrmI18n frmi18n, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		FrmSesion frmSesion = (FrmSesion) session.getAttribute("frmSesion");
		
		return gson.toJson(this.frmI18nService.insert(frmi18n));
	}
}

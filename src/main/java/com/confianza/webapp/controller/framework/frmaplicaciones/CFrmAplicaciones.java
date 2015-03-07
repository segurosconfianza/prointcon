package com.confianza.webapp.controller.framework.frmaplicaciones;

import java.util.List;
import com.google.gson.Gson;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.confianza.webapp.service.framework.frmaplicaciones.FrmAplicacionesService;
import com.confianza.webapp.repository.framework.frmaplicaciones.FrmAplicaciones;

@Controller
@RequestMapping("/FrmAplicaciones")
public class CFrmAplicaciones {

	@Autowired
	private FrmAplicacionesService frmaplicacionesService;
	
	@Autowired
	Gson gson;
	
	public CFrmAplicaciones(){
		super();
	}
	
	@RequestMapping(value = "/{aplicons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("aplicons") Long aplicons){
		
		return gson.toJson(this.frmaplicacionesService.list(aplicons));
	}
	
	@RequestMapping(value = "/.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(){
	
		return gson.toJson(this.frmaplicacionesService.listAll());
	}
	
	@RequestMapping(value = "/{aplicons}", method = RequestMethod.PUT)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public FrmAplicaciones update(@PathVariable("aplicons") Long aplicons){
		
		return this.frmaplicacionesService.update(aplicons);
	}
	
	@RequestMapping(value = "/{aplicons}.json", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public void delete(@PathVariable("aplicons") Long aplicons){
		
		this.frmaplicacionesService.delete(aplicons);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FrmAplicaciones frmaplicaciones){
		
		return gson.toJson(this.frmaplicacionesService.insert(frmaplicaciones));
	}
}

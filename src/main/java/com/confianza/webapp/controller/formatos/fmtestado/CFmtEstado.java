package com.confianza.webapp.controller.formatos.fmtestado;

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



import com.confianza.webapp.service.formatos.fmtestado.FmtEstadoService;
import com.confianza.webapp.repository.formatos.fmtestado.FmtEstado;

@Controller
@RequestMapping("/FmtEstado")
public class CFmtEstado {

	@Autowired
	private FmtEstadoService fmtestadoService;
	
	public CFmtEstado() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "formatos/fmtestado/FmtEstado";
	}
	
	@RequestMapping(value = "/{estacons}.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String list(@PathVariable("estacons") Long estacons){
		
		return this.fmtestadoService.list(estacons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.fmtestadoService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FmtEstado fmtestado, HttpServletRequest request){
	
		return this.fmtestadoService.update(fmtestado);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FmtEstado fmtestado, HttpServletRequest request){
	
		//fmtestado.setesta("B");
		return this.fmtestadoService.update(fmtestado);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FmtEstado fmtestado, HttpServletRequest request){
		
		return this.fmtestadoService.insert(fmtestado);		
	}
}

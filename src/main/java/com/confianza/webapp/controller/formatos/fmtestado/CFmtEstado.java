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
	private FmtEstadoService fmtEstadoService;
	
	public CFmtEstado() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "formatos/fmtestado/FmtEstado";
	}
	
	@RequestMapping(value = "/{estacons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("estacons") Long estacons){
		
		return this.fmtEstadoService.list(estacons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize","order","filter"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("order") String order, @RequestParam("filter") String filters){
	
		return this.fmtEstadoService.listAll(pageSize, page, order, filters );
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FmtEstado fmtestado, HttpServletRequest request){
	
		return this.fmtEstadoService.update(fmtestado);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FmtEstado fmtestado, HttpServletRequest request){
	
		//fmtestado.setesta("B");
		return this.fmtEstadoService.update(fmtestado);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FmtEstado fmtestado, HttpServletRequest request){
		
		return this.fmtEstadoService.insert(fmtestado);		
	}
}

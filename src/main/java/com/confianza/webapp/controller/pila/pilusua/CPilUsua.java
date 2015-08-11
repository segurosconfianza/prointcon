package com.confianza.webapp.controller.pila.pilusua;

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





import com.confianza.webapp.service.framework.frmtablas.FrmTablasService;
import com.confianza.webapp.service.pila.pilusua.PilUsuaService;
import com.confianza.webapp.repository.pila.pilusua.PilUsua;

@Controller
@RequestMapping("/PilUsua")
public class CPilUsua {

	@Autowired
	private PilUsuaService pilUsuaService;
	
	public CPilUsua() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "pila/pilusua/PilUsua";
	}
	
	@RequestMapping("/PilUsuaanalis") 
	public String indexanalis() {
		return "pila/pilusuaanalis/PilUsuaanalis";
	}
	
	@RequestMapping("/PilSucuranalisis") 
	public String indexsucursales() {
		return "pila/pilsucuranalisis/PilSucuranalisis";
	}
	
	@RequestMapping(value = "/{usuacons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("usuacons") Long usuacons){
		
		return this.pilUsuaService.list(usuacons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize","order","filter"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("order") String order, @RequestParam(value ="filter", required=false) String filters){
	
		return this.pilUsuaService.listAll(pageSize, page, order, filters);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody PilUsua pilusua, HttpServletRequest request){
	
		return this.pilUsuaService.update(pilusua);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody PilUsua pilusua, HttpServletRequest request){
	
		return this.pilUsuaService.update(pilusua);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody PilUsua pilusua,HttpServletRequest request){
		
		return this.pilUsuaService.insert(pilusua);		
	}
	
	@RequestMapping(value = "/validateUser", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String validateUser(@RequestParam String username){
		
		return this.pilUsuaService.validateUser(username);		
	}
}

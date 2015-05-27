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
	private PilUsuaService pilusuaService;
	
	public CPilUsua() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "pila/pilusua/PilUsua";
	}
	
	@RequestMapping(value = "/{usuacons}.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String list(@PathVariable("usuacons") Long usuacons){
		
		return this.pilusuaService.list(usuacons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.pilusuaService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody PilUsua pilusua, HttpServletRequest request){
	
		return this.pilusuaService.update(pilusua);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody PilUsua pilusua, HttpServletRequest request){
	
		//pilusua.setesta("B");
		return this.pilusuaService.update(pilusua);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody PilUsua pilusua, HttpServletRequest request){
		
		return this.pilusuaService.insert(pilusua);		
	}		
}

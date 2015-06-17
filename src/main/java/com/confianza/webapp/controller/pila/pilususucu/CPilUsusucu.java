package com.confianza.webapp.controller.pila.pilususucu;

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


import com.confianza.webapp.service.pila.pilususucu.PilUsusucuService;
import com.confianza.webapp.repository.pila.pilususucu.PilUsusucu;

@Controller
@RequestMapping("/PilUsusucu")
public class CPilUsusucu {

	@Autowired
	private PilUsusucuService pilususucuService;
	
	public CPilUsusucu() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "pila/pilususucu/PilUsusucu";
	}
	
	@RequestMapping(value = "/{ussucons}.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String list(@PathVariable("ussucons") Long ussucons){
		
		return this.pilususucuService.list(ussucons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.pilususucuService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody PilUsusucu pilususucu, HttpServletRequest request){
	
		return this.pilususucuService.update(pilususucu);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody PilUsusucu pilususucu, HttpServletRequest request){
	
		//pilususucu.setesta("B");
		return this.pilususucuService.update(pilususucu);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody PilUsusucu pilususucu, HttpServletRequest request){
		
		return this.pilususucuService.insert(pilususucu);		
	}
}
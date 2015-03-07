package com.confianza.webapp.controller.framework.frmarchivo;

import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import com.google.gson.Gson;

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


import com.confianza.webapp.service.framework.frmarchivo.FrmArchivoService;
import com.confianza.webapp.repository.framework.frmarchivo.FrmArchivo;

@Controller
@RequestMapping("/FrmArchivo")
public class CFrmArchivo {

	@Autowired
	Gson gson;
	
	@Autowired
	FrmArchivoService frmArchivoService;
	
	public CFrmArchivo() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "framework/frmarchivo/FrmArchivo";
	}
	
	@RequestMapping(value = "/{archcodi}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"} )
	@ResponseBody
	public String list(@PathVariable("archcodi") Long archcodi){
		
		return this.frmArchivoService.list(archcodi);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.frmArchivoService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FrmArchivo frmarchivo, HttpServletRequest request){
	
		return this.frmArchivoService.update(frmarchivo);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FrmArchivo frmarchivo, HttpServletRequest request){
	
		//frmarchivo.setesta("B");
		return this.frmArchivoService.update(frmarchivo);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FrmArchivo frmarchivo, HttpServletRequest request){
		
		return this.frmArchivoService.insert(frmarchivo);		
	}
}

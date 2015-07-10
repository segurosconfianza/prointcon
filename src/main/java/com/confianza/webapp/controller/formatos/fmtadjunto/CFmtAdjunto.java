package com.confianza.webapp.controller.formatos.fmtadjunto; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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




import com.confianza.webapp.service.formatos.fmtadjunto.FmtAdjuntoService;
import com.confianza.webapp.service.framework.frmarchivo.FrmArchivoService;
import com.confianza.webapp.repository.formatos.fmtadjunto.FmtAdjunto;

@Controller
@RequestMapping("/FmtAdjunto")
public class CFmtAdjunto {

	@Autowired
	private FmtAdjuntoService fmtAdjuntoService;
	
	@Autowired
	private FrmArchivoService frmArchivoService;
	
	public CFmtAdjunto() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "formatos/fmtadjunto/FmtAdjunto";
	}
	
	@RequestMapping(value = "/{adjucons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("adjucons") Long adjucons){
		
		return this.fmtAdjuntoService.list(adjucons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.fmtAdjuntoService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FmtAdjunto fmtadjunto, HttpServletRequest request){
	
		return this.fmtAdjuntoService.update(fmtadjunto);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FmtAdjunto fmtadjunto, HttpServletRequest request){
	
		//fmtadjunto.setesta("B");
		return this.fmtAdjuntoService.update(fmtadjunto);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FmtAdjunto fmtadjunto, HttpServletRequest request){
		
		return this.fmtAdjuntoService.insert(fmtadjunto);		
	}
	
	@RequestMapping(value = "/downloadFile", params = {"adjuarch","adjunomb"},  method = RequestMethod.GET)
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public void downloadFile(@RequestParam("adjuarch") Long adjuarch, @RequestParam("adjunomb") String adjunomb, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		frmArchivoService.getfrmArchivo(adjuarch, adjunomb, request, response);
		
	}
	
	@RequestMapping(value = "/listAdjunto.json", params = {"forecons"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public void listAdjuntoFmtAdjunto(@RequestParam("forecons") long forecons, HttpServletRequest request, HttpServletResponse response) {
	
		this.fmtAdjuntoService.listAdjunto(forecons, request, response);
	}
}

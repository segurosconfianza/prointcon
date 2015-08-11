package com.confianza.webapp.controller.formatos.fmtcampo;

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




import com.confianza.webapp.service.formatos.fmtcampo.FmtCampoService;
import com.confianza.webapp.repository.formatos.fmtcampo.FmtCampo;

@Controller
@RequestMapping("/FmtCampo")
public class CFmtCampo {

	@Autowired
	private FmtCampoService fmtCampoService;
	
	public CFmtCampo() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "formatos/fmtcampo/FmtCampo";
	}
	
	@RequestMapping(value = "/{campcons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("campcons") Long campcons){
		
		return this.fmtCampoService.list(campcons);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.fmtCampoService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FmtCampo fmtcampo, HttpServletRequest request){
	
		return this.fmtCampoService.update(fmtcampo);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FmtCampo fmtcampo, HttpServletRequest request){
	
		//fmtcampo.setesta("B");
		return this.fmtCampoService.update(fmtcampo);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FmtCampo fmtcampo, HttpServletRequest request){
		
		return this.fmtCampoService.insert(fmtcampo);		
	}		
	
	@RequestMapping(value = "/campos.json", params = {"campvefo"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listCamposCosu(@RequestParam("campvefo") Long campvefo){
		
		return this.fmtCampoService.listCamposCosu(campvefo);
	}
}

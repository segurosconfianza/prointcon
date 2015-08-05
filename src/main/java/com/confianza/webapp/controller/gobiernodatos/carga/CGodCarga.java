package com.confianza.webapp.controller.gobiernodatos.carga;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.confianza.webapp.service.gobiernodatos.carga.GodConsultaService;;



@Controller
@RequestMapping("/GodCarga")
public class CGodCarga {
	
	@Autowired
	private GodConsultaService godConsultaService;


	@RequestMapping("/")
	public String index() {
		return "gobiernoDatos/carga/gobiernoDatos";
	}
	
	@RequestMapping(value = "/updateRecord.json", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String updateRecord(@RequestParam("conscons") String conscons, @RequestParam("params") String params, @RequestParam("file") ArrayList<MultipartFile> file ) throws Throwable{
			
		//this.godConsultaService.loadFiles(file);
		return this.godConsultaService.loadFiles(conscons, params, file);
	}
	
}

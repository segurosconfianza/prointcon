package com.confianza.webapp.controller.framework.frmconsulta;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.service.framework.frmconsulta.FrmConsultaService;
import com.confianza.webapp.service.security.RolService;
import com.confianza.webapp.utils.CFile;
import com.confianza.webapp.utils.FileImpl;
import com.confianza.webapp.utils.UploadFile;

@Controller
@RequestMapping("/FrmConsulta")
public class CFrmConsulta {
	
	@Autowired
	private FrmConsultaService frmConsultaService;
	
	@Autowired
	private RolService rolService;
	
	public CFrmConsulta() {
		super();
	}
	
	@RequestMapping("/")
	public String index() {
		return "framework/frmconsulta/FrmConsulta";
	}
	
	@RequestMapping("/PolizaConsulta/")
	public String polizaConsulta() {
		return "poliza/poliza/Poliza";
	}
	
	@RequestMapping("/Soporte/")
	public String soporteConsulta() {
		return "soporte/soporte/Soporte";
	}
	
	@RequestMapping(value = "/{conscons}.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String list(@PathVariable("conscons") Long conscons){
		
		return this.frmConsultaService.list(conscons);
	}
		
	@RequestMapping(value = "/loadRecord.json", params = {"conscons","params"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String loadRecord(@RequestParam("conscons") String conscons, @RequestParam("params") String params) throws Throwable{
			
		return this.frmConsultaService.loadRecord(conscons, params);
	}
	
	@RequestMapping(value = "/updateRecord.json", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String updateRecord(@RequestParam("conscons") String conscons, @RequestParam("motidesc") String motidesc, @RequestParam("file") ArrayList<MultipartFile> file, @RequestParam("params") String params,  @RequestParam("paramsData") String paramsData ) throws Throwable{
			
		String result=this.frmConsultaService.updateRecord(conscons, params, paramsData, file);
		this.frmConsultaService.uploadFiles(motidesc, file, result);
		return result;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FrmConsulta frmconsulta, HttpServletRequest request){
	
		return this.frmConsultaService.update(frmconsulta);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FrmConsulta frmconsulta, HttpServletRequest request){
	
		//frmconsulta.setesta("B");
		return this.frmConsultaService.update(frmconsulta);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FrmConsulta frmconsulta, HttpServletRequest request){
		
		return this.frmConsultaService.insert(frmconsulta);
	}
	
	@RequestMapping(value = "/listCombo.json", params = {"conscons"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listCombo(@RequestParam("conscons") String conscons) throws Exception{

		return this.frmConsultaService.listCombo(conscons);
	}
	
	@RequestMapping(value = "/listComboDynamic.json", params = {"conscons"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listComboDynamic(@RequestParam("conscons") String conscons) throws Exception{
		
		return this.frmConsultaService.listComboDynamic(conscons);
	}
	
	@RequestMapping(value = "/loadData.json", params = {"conscons"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String loadData(@RequestParam("conscons") String conscons) throws Throwable{
			
		return this.frmConsultaService.loadData(conscons);
	}
	
	@RequestMapping(value = "/validateRol.json", params = {"roles"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String validateRol(@RequestParam("roles") String roles){
	
		boolean resp=rolService.validateRoles(roles);
		
		if(resp)
			return "true";
		else 
			return "false";
	}
	
	@RequestMapping(value = "/loadConsChield.json", params = {"conscons"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String loadConsChield(@RequestParam("conscons") String conscons) throws Throwable{
			
		return this.frmConsultaService.loadConsChield(conscons);
	}	
	
	@RequestMapping("/CierreCarteraCuadre/")
	public String soporteCierreCarteraCuadre() {
		return "soporte/cierrecartera/CierreCarteraCuadre";
	}
	
	@RequestMapping(value = "/ExecuteProcess.json", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String ExecuteProcess(@RequestParam("conscons") String conscons, @RequestParam("params") String params, HttpServletRequest request ) throws Throwable{
			
		String result=this.frmConsultaService.ExecuteProcess(conscons, params, request);  
		
		return result;
	}
}

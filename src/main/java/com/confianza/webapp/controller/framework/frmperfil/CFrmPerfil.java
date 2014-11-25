package com.confianza.webapp.controller.framework.frmperfil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.confianza.webapp.service.framework.frmperfil.FrmPerfilService;
import com.confianza.webapp.repository.framework.frmperfil.FrmPerfil;

@Controller
@EnableWebMvc
@RequestMapping("/FrmPerfil")
public class CFrmPerfil {

	private FrmPerfilService frmPerfilService;		
	
	@Autowired
	Gson gson;
	
	@Autowired
	public CFrmPerfil(FrmPerfilService frmperfilService) {
		this.frmPerfilService = frmperfilService;
	}		
	
	public CFrmPerfil() {
		super();
	}

	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFIL_ALL", "FRM_PERFIL_READ"})
	@RequestMapping("/")
	public String index() {
		return "framework/frmperfil/FrmPerfil";
	}
	
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFIL_ALL", "FRM_PERFIL_READ"})
	@RequestMapping(value = "/{peficons}.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String list(@PathVariable("peficons") Long peficons){
		
		return gson.toJson(this.frmPerfilService.list(peficons));
	}
	
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFIL_ALL", "FRM_PERFIL_READ"})
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"}, method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page) throws Exception{
		
		List<FrmPerfil> listAll=this.frmPerfilService.listAll(pageSize, page);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.frmPerfilService.getCount());
		
		return gson.toJson(result);
	}
	
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFIL_ALL", "FRM_PERFIL_DELETE"})
	@RequestMapping(value = "/deleteR", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FrmPerfil frmperfil, HttpServletRequest request){
	
		/*HttpSession session = request.getSession();
		FrmSesion frmSesion = (FrmSesion) session.getAttribute("frmSesion");*/
		
		frmperfil.setPefiesta("B");
		
		return gson.toJson(this.frmPerfilService.update(frmperfil));
	}
	
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFIL_ALL", "FRM_PERFIL_CREATE"})
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody	
	public String insert(@RequestBody FrmPerfil frmperfil, HttpServletRequest request){
				
		frmperfil.setPefiesta("A");
		frmperfil.setPefifecr(new Date());
			
		return gson.toJson(this.frmPerfilService.insert(frmperfil));
	}
		
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFIL_ALL", "FRM_PERFIL_UPDATE"})
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FrmPerfil frmperfil, HttpServletRequest request){
			
		return gson.toJson(this.frmPerfilService.update(frmperfil));
	}
		
}

package com.confianza.webapp.controller.framework.frmperfmodu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.confianza.webapp.service.framework.frmperfmodu.FrmPerfmoduService;
import com.confianza.webapp.utils.JSONUtil;
import com.confianza.webapp.repository.framework.frmperfmodu.FrmPerfmodu;
import com.google.gson.Gson;

@Controller
@RequestMapping("/FrmPerfmodu")
public class CFrmPerfmodu {

	@Autowired
	private FrmPerfmoduService frmPerfmoduService;
	
	@Autowired
	Gson gson;
	
	public CFrmPerfmodu() {
		super();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String list(Long id){
		try{
			return gson.toJson(this.frmPerfmoduService.list(id));
		}catch(AccessDeniedException e){
			return "Acceso denegado";
		}
	}
		
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"}, method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("pemopefi") Long pemopefi) throws Exception{
		
		try{
			Map<String, Object> result = new HashMap<String, Object>();
			
			List<Object[]> listAll=this.frmPerfmoduService.listAll(pageSize, page, pemopefi);
			
			//cast de los menu a ser mapeados por cada campo
			List<Map<String, Object>> rolAll = JSONUtil.toNameList(
					new String[]{"pemocons", "pemopefi", "pemomoro", "morocons", "moromodu", "mororope", "moducons", "moduapli", "modunomb", "modunemo", "modudurl", "ropecons", "ropenomb", "ropedesc", "ropetipo", "aplicons", "aplinomb", "aplidesc", "apliesta", "aplifecr", "tablvast"},listAll
			);
			
			result.put("data", rolAll);
			result.put("count", this.frmPerfmoduService.getCount());
			
			return gson.toJson(result);
		}catch(AccessDeniedException e){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tituloError", "Acceso denegado");
			result.put("error", "No posee los permisos para esta accion");
			return gson.toJson(result);
		}
				
	}
		
	@RequestMapping(value = "/listComboMoro.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listComboMoro() throws Exception{
		try{
			List<Object[]> listAll=this.frmPerfmoduService.listComboMoro();
			
			//cast de los menu a ser mapeados por cada campo
			List<Map<String, Object>> rolAll = JSONUtil.toNameList(
					new String[]{"value", "label"},listAll
			);
			
			return gson.toJson(rolAll);
		}catch(AccessDeniedException e){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tituloError", "Acceso denegado");
			result.put("error", "No posee los permisos para esta accion");
			return gson.toJson(result);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody FrmPerfmodu frmperfmodu){
		try{
			return gson.toJson(this.frmPerfmoduService.update(frmperfmodu));
		}catch(AccessDeniedException e){
			return "Acceso denegado";
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody FrmPerfmodu frmperfmodu, HttpServletRequest request){
		try{
			this.frmPerfmoduService.delete(frmperfmodu);
			return "Borrado";
		}catch(AccessDeniedException e){
			return "Acceso denegado";
		}
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody FrmPerfmodu frmperfmodu, HttpServletRequest request){
		try{
			return gson.toJson(this.frmPerfmoduService.insert(frmperfmodu));
		}catch(AccessDeniedException e){
			return "Acceso denegado";
		}
	}
}

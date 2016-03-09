package com.confianza.webapp.controller.osiris.osisurcursal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.confianza.webapp.service.osiris.osisucursal.OsiSucursalService;
import com.confianza.webapp.repository.osiris.osisucursal.OsiSucursal;

@Controller
@RequestMapping("/OsiSucursal")
public class COsiSucursal {

	@Autowired
	private OsiSucursalService osisucursalService;
	
	public COsiSucursal() {
		super();
	}
			
	@RequestMapping("/")
	public String index() {
		return "osiris/osisucursal/OsiSucursal";
	}		
	
	@RequestMapping(value = "/listAll.json", method = RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("order") String order, @RequestParam(value ="filter", required=false) String filters){
	
		return this.osisucursalService.listAll(pageSize, page, order, filters);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String update(@RequestBody OsiSucursal osisucursal, HttpServletRequest request){
	
		return this.osisucursalService.update(osisucursal);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String delete(@RequestBody OsiSucursal osisucursal, HttpServletRequest request){
	
		//osisucursal.setesta("B");
		return this.osisucursalService.update(osisucursal);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces={"application/json"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public String insert(@RequestBody OsiSucursal osisucursal, HttpServletRequest request){
		
		return this.osisucursalService.insert(osisucursal);		
	}
}

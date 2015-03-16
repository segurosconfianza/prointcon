package com.confianza.webapp.controller.framework.frmtransaccion;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
 





import com.confianza.webapp.service.framework.frmtransaccion.FrmTransaccionService;
import com.confianza.webapp.controller.framework.frmlog.CFrmLog;
import com.confianza.webapp.repository.framework.frmsesion.FrmSesion;
import com.confianza.webapp.repository.framework.frmtransaccion.FrmTransaccion;

@Controller
@RequestMapping("/FrmTransaccion")
public class CFrmTransaccion {

	@Autowired
	private FrmTransaccionService frmTransaccionService;
	
	public CFrmTransaccion(){
		super();
	}
	
	@RequestMapping("/")
	public String index() {
		return "framework/frmtransaccion/FrmTransaccion";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public FrmTransaccion list(Long id){
		
		return this.frmTransaccionService.list(id);
	}
	
	@RequestMapping(value = "/listAll.json", params = {"page","pageSize"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAll(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.frmTransaccionService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public FrmTransaccion update(Long id){
		return this.frmTransaccionService.update(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public void delete(Long id){
		this.frmTransaccionService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public FrmTransaccion insert(@RequestBody FrmTransaccion frmtransaccion){
		return this.frmTransaccionService.insert(frmtransaccion);
	}
	
	public void insertLog(FrmSesion frmSesion, String tabla, String accion, String registro){
		
		FrmTransaccion frmTransaccion=new FrmTransaccion();
		frmTransaccion.setTransesi(frmSesion.getSesicons());
		frmTransaccion.setTranfecr(new Date());
		this.frmTransaccionService.insert(frmTransaccion);
				
	}
}

package com.confianza.webapp.controller.pila.intermediario;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.confianza.webapp.repository.formatos.fmtcampo.FmtCampo;
import com.confianza.webapp.service.formatos.fmtadjunto.FmtAdjuntoService;
import com.confianza.webapp.service.formatos.fmtauditoria.FmtAuditoriaService;
import com.confianza.webapp.service.formatos.fmtcampo.FmtCampoService;
import com.confianza.webapp.service.formatos.fmtestado.FmtEstadoService;
import com.confianza.webapp.service.formatos.fmtformato.FmtFormatoService;
import com.confianza.webapp.service.formatos.fmtformregi.FmtFormregiService;
import com.confianza.webapp.service.framework.frmconsulta.FrmConsultaService;
import com.confianza.webapp.service.framework.frmi18n.FrmI18nService;
import com.confianza.webapp.service.framework.frmmenu.FrmMenuService;
import com.confianza.webapp.service.framework.frmtablas.FrmTablasService;
import com.confianza.webapp.service.pila.pilmotiform.PilMotiformService;
import com.confianza.webapp.service.pila.pilmotivo.PilMotivoService;
import com.confianza.webapp.service.pila.pilusua.PilUsuaService;
import com.confianza.webapp.utils.Filter;
import com.confianza.webapp.utils.JSONUtil;

@Controller
@RequestMapping("/Intermediario")
public class CIntermediario {

	@Autowired
	private PilUsuaService pilusuaService;
	
	@Autowired
	private FrmMenuService frmMenuService;
	
	@Autowired
	private FmtFormatoService fmtformatoService;
	
	@Autowired
	private FmtFormregiService fmtformregiService;
	
	@Autowired
	private FmtCampoService fmtcampoService;
	
	@Autowired
	private FrmI18nService frmI18nService;
	
	@Autowired
	private FmtAuditoriaService fmtauditoriaService;
	
	@Autowired
	private FmtEstadoService fmtEstadoService;
	
	@Autowired
	private FmtAdjuntoService fmtAdjuntoService;
	
	@Autowired
	private FrmTablasService frmTablasService;
	
	@Autowired
	private FrmConsultaService frmConsultaService;
	
	@Autowired
	private PilMotivoService pilMotivoService;
	
	@Autowired
	private PilMotiformService pilmotiformService;
	
	public CIntermediario() {
		super();
	}
			
	@RequestMapping(value = "/validateUsua.json", params = {"user","password"}, method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String validateUsua(@RequestParam("user") String user, @RequestParam("password") String password){
		
		return this.pilusuaService.validateUsua(user, password);
	}
	
	@RequestMapping(value = "/listMenu.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.OK )
	@ResponseBody
	public String listMenu(HttpServletRequest request){
		
		return frmMenuService.listAllIntermediario();				
	}
	
	@RequestMapping(value = "/FrmFormato/loadData.json", params = {"formcons"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String loadData(@RequestParam("formcons") String formcons) throws Throwable{
			
		return this.fmtformatoService.loadDataIntermediario(formcons);
	}
	
	@RequestMapping(value = "/FrmCampo/campos.json", params = {"campvefo"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listCamposCosu(@RequestParam("campvefo") Long campvefo){
		
		return this.fmtcampoService.listCamposCosu(campvefo);
	}
	
	@RequestMapping(value = "/FmtFormregi/insertRecord.json", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String FmtFormregiInsertRecord(@RequestParam("vefocons") Long vefocons, @RequestParam("user") String user,  @RequestParam("file") ArrayList<MultipartFile> file, @RequestParam("paramsData") String paramsData ) throws Throwable{
			
		return this.fmtformregiService.insertRecordIntermediario(vefocons, user, paramsData, file);
	}
	
	@RequestMapping(value = "/FmtFormregi/updateRecord.json", method = RequestMethod.POST, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String FmtFormregiUpdateRecord(@RequestParam("vefocons") Long vefocons, @RequestParam("forecons") Long forecons, @RequestParam("user") String user,  @RequestParam("file") ArrayList<MultipartFile> file, @RequestParam("paramsData") String paramsData ) throws Throwable{
			
		return this.fmtformregiService.updateRecordIntermediario(vefocons, forecons, user, paramsData, file);
	}
	
	@RequestMapping(value = "/FmtFormregi/loadFormatos.json", method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String loadFormatos(@RequestParam("vefocons") Long vefocons, @RequestParam("user") String user, @RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("order") String order, @RequestParam("filter") String filters) throws Throwable{
		
		return this.fmtformregiService.loadFormRegiIntermediario(vefocons, pageSize, page, order, filters);
	}
	
	@RequestMapping(value = "/FmrI18n/listModulo.json", params = {"modulo"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listModulo(@RequestParam("modulo") String modulo){
		
		return this.frmI18nService.listModulo(modulo);
	}
	
	@RequestMapping(value = "/FmtAuditoria/listAll.json", params = {"page","pageSize", "forecons"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAllFmtAuditoria(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page,@RequestParam("order") String order,@RequestParam("stringFilters") String stringFilters, @RequestParam("forecons") long forecons){
	
		return this.fmtauditoriaService.listAllFrmFormregi(pageSize, page, order, stringFilters, forecons);
	}
	
	@RequestMapping(value = "/FmtEstado/listAll.json", params = {"page","pageSize","order","filter"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAllFmtEstado(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("order") String order, @RequestParam("filter") String filters){
	
		return this.fmtEstadoService.listAll(pageSize, page, order, filters);
	}
	
	@RequestMapping(value = "/FmtAdjunto/listAdjunto.json", params = {"forecons"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseStatus( HttpStatus.CREATED )
	@ResponseBody
	public void listAdjuntoFmtAdjunto(@RequestParam("forecons") long forecons, HttpServletRequest request, HttpServletResponse response) {
	
		this.fmtAdjuntoService.listAdjunto(forecons, request, response);
	}
	
	@RequestMapping(value = "/FrmTablas/listByTablcodi.json", params = {"tablcodi"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listByTablcodis(@RequestParam("tablcodi") String tablcodi){
		
		return this.frmTablasService.listByTablcodis(tablcodi);
	}
	
	@RequestMapping(value = "/FrmConsulta/listComboDynamic.json", params = {"conscons"}, method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listComboDynamic(@RequestParam("conscons") String conscons) throws Exception{
		
		return this.frmConsultaService.listComboDynamic(conscons);
	}
	
	@RequestMapping(value = "/PilMotivo/listAll.json", params = {"page","pageSize"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAllPilMotivo(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page){
	
		return this.pilMotivoService.listAll(pageSize, page);
	}
	
	@RequestMapping(value = "/PilMotiform/listAll.json", params = {"page","pageSize","order","filter"},  method = RequestMethod.GET, produces={"application/json; charset=ISO-8859-1"})
	@ResponseBody
	public String listAllPilMotiform(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page, @RequestParam("order") String order, @RequestParam("filter") String filters){
	
		return this.pilmotiformService.listAllIntermediario(pageSize, page, order, filters); 
		
	}
		
}

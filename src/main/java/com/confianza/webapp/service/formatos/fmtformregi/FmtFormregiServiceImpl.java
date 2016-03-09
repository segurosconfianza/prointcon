package com.confianza.webapp.service.formatos.fmtformregi;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.confianza.webapp.repository.formatos.fmtadjunto.FmtAdjunto;
import com.confianza.webapp.repository.formatos.fmtcampo.FmtCampo;
import com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregi;
import com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregiRepository;
import com.confianza.webapp.repository.formatos.fmtvalocamp.FmtValocamp;
import com.confianza.webapp.repository.framework.frmarchivo.FrmArchivo;
import com.confianza.webapp.repository.framework.frmtablas.FrmTablas;
import com.confianza.webapp.repository.pila.pilusua.PilUsua;
import com.confianza.webapp.service.formatos.fmtadjunto.FmtAdjuntoService;
import com.confianza.webapp.service.formatos.fmtauditoria.FmtAuditoriaService;
import com.confianza.webapp.service.formatos.fmtcampo.FmtCampoService;
import com.confianza.webapp.service.formatos.fmtestado.FmtEstadoService;
import com.confianza.webapp.service.formatos.fmtvalocamp.FmtValocampService;
import com.confianza.webapp.service.framework.frmarchivo.FrmArchivoService;
import com.confianza.webapp.service.framework.frmtablas.FrmTablasService;
import com.confianza.webapp.service.framework.frmtransaccion.FrmTransaccionService;
import com.confianza.webapp.service.pila.pilusua.PilUsuaService;
import com.confianza.webapp.service.security.userDetails;
import com.confianza.webapp.utils.Filter;
import com.confianza.webapp.utils.JSONUtil;

@Service
public class FmtFormregiServiceImpl implements FmtFormregiService{
	
	@Autowired
	private FmtFormregiRepository fmtFormregiRepository;
	
	@Autowired
	private FmtValocampService fmtValocampService;
	
	@Autowired
	private FmtAdjuntoService fmtAdjuntoService;
	
	@Autowired
	private FrmArchivoService frmArchivoService;
	
	@Autowired
	private FmtCampoService fmtCampoService;
	
	@Autowired
	private FrmTablasService frmTablasService;
	
	@Autowired
	private FmtEstadoService fmtEstadoService;
	
	@Autowired
	private PilUsuaService pilUsuaService;
	
	@Autowired
	private FrmTransaccionService frmTransaccionService;
	
	@Autowired
	private FmtAuditoriaService fmtAuditoriaService;
	
	@Autowired
	userDetails userDetails;
	
	@Autowired  
	Gson gson;
	
	private static int tamanoFila=7;
	
	/**
	 * @return the fmtformregiRepository
	 */
	public FmtFormregiRepository getFmtFormregiRepository() {
		return fmtFormregiRepository;
	}

	/**
	 * @param fmtformregiRepository the fmtformregiRepository to set
	 */
	public void setFmtFormregiRepository(FmtFormregiRepository fmtformregiRepository) {
		this.fmtFormregiRepository = fmtformregiRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_READ"})
	public String list(Long id){
		FmtFormregi listAll=fmtFormregiRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		//result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	public FmtFormregi listEntity(Long id){
		FmtFormregi listAll=fmtFormregiRepository.list(id);
		
		return listAll;	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtFormregi> listAll=fmtFormregiRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		//result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}			
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_UPDATE"})
	public String update(FmtFormregi fmtformregi){
		return gson.toJson(fmtFormregiRepository.update(fmtformregi));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_DELETE"})
	public void delete(FmtFormregi fmtformregi){
		fmtFormregiRepository.delete(fmtformregi);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_CREATE"})
	public String insert(FmtFormregi fmtformregi){
		return gson.toJson(fmtFormregiRepository.insert(fmtformregi));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_UPDATE"})
	public String aprobarRecord(Long forecons){
		FmtFormregi fmtformregi=fmtFormregiRepository.list(forecons);
		
		fmtEstadoService.insertLastEstado(fmtformregi, userDetails.getUser());
		
		if(fmtformregi.getForeesta().equals("N") || fmtformregi.getForeesta().equals("M")){
			Long transcons=frmTransaccionService.generateTransaction("");
			generateAudit("foreesta",fmtformregi.getForecons(),"FmtFormregi", fmtformregi.getForeesta(),"A", transcons);
			fmtformregi.setForeesta("A");
			fmtFormregiRepository.update(fmtformregi);
			
			return gson.toJson("El id: "+forecons+" ha sido aprobado");
		}
		else
			return gson.toJson("El id: "+forecons+" ha no sido aprobado, por el estado en el que se encuentra");
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_UPDATE"})
	public String devolverRecord(Long forecons){
		FmtFormregi fmtformregi=fmtFormregiRepository.list(forecons);
		
		Long transcons=frmTransaccionService.generateTransaction("");
		generateAudit("foreesta",fmtformregi.getForecons(),"FmtFormregi", fmtformregi.getForeesta(),"D", transcons);
		fmtEstadoService.insertLastEstado(fmtformregi, userDetails.getUser());
		
		fmtformregi.setForeesta("D");
		fmtFormregiRepository.update(fmtformregi);
		
		return gson.toJson("El id: "+forecons+" ha sido Devuelto");
	}
	
	@Override	
	public String insertRecordIntermediario(Long vefocons, String user, String paramsData, ArrayList<MultipartFile> file){
		
		try{
			Type type = new TypeToken<Map<String, Object>>(){}.getType();   						
			Map<String, Object> parametersData=gson.fromJson(paramsData, type);
			
			FmtFormregi fmtformregi = createFormRegi(vefocons, user);
			
			if(fmtValocampService.insertValuesIntermediario(vefocons, fmtformregi.getForecons(), parametersData)){
				List<FrmArchivo> listAll=this.frmArchivoService.ingresarArchivos(file, "archinte");
				if(fmtAdjuntoService.insertAdjuntos(fmtformregi.getForecons(), user, listAll))
					return gson.toJson("Registro creado Satisfactoriamente Id:"+fmtformregi.getForecons());
				else
					return gson.toJson("Se presentaron errores en la creacion del registro");
			}
			else
				return gson.toJson("Se presentaron errores en la creacion del registro");
		}catch (Exception e) {
			e.printStackTrace();
			return gson.toJson("Se presentaron errores en la creacion del registro"); 
		}
	}

	private FmtFormregi createFormRegi(Long vefocons, String user) {
		FmtFormregi fmtformregi=new FmtFormregi();
		fmtformregi.setForevefo(vefocons);
		fmtformregi.setForeesta("N");
		fmtformregi.setForefech(Calendar.getInstance().getTime());
		fmtformregi.setForeuser(user);
		fmtFormregiRepository.insert(fmtformregi);
		return fmtformregi;
	} 	
	
	@Override	
	public String loadFormRegiIntermediario(Long vefocons, int pageSize, int page, String order, String stringFilters){ 
		
		Map<String, Object> result = new HashMap<String, Object>();
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		try{			
			List<FmtFormregi> registros=fmtFormregiRepository.listAll((pageSize*page)-(pageSize), pageSize, vefocons, order, filters);
			if(registros.size()>0){
				List<FmtCampo> campos=fmtCampoService.listEntityCamposCosu(vefocons);
				List<PilUsua> usuarios=pilUsuaService.listAllFormregi(generateListCodes(registros));
				List<FmtValocamp> valoresRegistros=fmtValocampService.listAll((pageSize*page)-(pageSize), pageSize*campos.size(), vefocons, generateListCodes(registros));
				List<FrmTablas> estados=frmTablasService.listByCodi("foreesta");
				result.put("data", JSONUtil.toNameList(generateMaps(campos),generateRows(campos, registros, valoresRegistros, estados, usuarios)));
			}
			else
				result.put("data", null);
			result.put("count", this.getCount(filters));
			
		}catch (Exception e) {
			e.printStackTrace();
			result.put("error", "Ocurrio un error");
		}
		
		return gson.toJson(result);
	}
	
	@Override	
	public String loadFormRegiAdmin(Long vefocons, int pageSize, int page, String order, String stringFilters){ 
		
		Map<String, Object> result = new HashMap<String, Object>();
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		try{			
			List<FmtFormregi> registros=fmtFormregiRepository.listAllAdmin((pageSize*page)-(pageSize), pageSize, vefocons, order, filters);
			if(registros.size()>0){
				List<FmtCampo> campos=fmtCampoService.listEntityCamposCosu(vefocons);
				List<PilUsua> usuarios=pilUsuaService.listAllFormregi(generateListCodes(registros));
				List<FmtValocamp> valoresRegistros=fmtValocampService.listAll((pageSize*page)-(pageSize), pageSize*campos.size(), vefocons, generateListCodes(registros));
				List<FrmTablas> estados=frmTablasService.listByCodi("foreesta");
				result.put("data", JSONUtil.toNameList(generateMaps(campos),generateRows(campos, registros, valoresRegistros, estados, usuarios)));
			}
			else
				result.put("data", null);
			result.put("count", this.getCountAdmin(filters));
			  
		}catch (Exception e) {
			e.printStackTrace();
			result.put("error", "Ocurrio un error ");
		}
		
		return gson.toJson(result);
	}

	private List<Long> generateListCodes(List<FmtFormregi> registros) {
		List<Long> codigosFormRegi = new ArrayList<Long>();
		for(FmtFormregi registro:registros)
			codigosFormRegi.add(registro.getForecons());
		return codigosFormRegi;
	}

	private String[] generateMaps(List<FmtCampo> campos) {
		String[] mapa = initMap(campos);
		int i=tamanoFila;
		for(FmtCampo campo:campos)
			mapa[i++]=campo.getCampnomb();
		return mapa;
	}

	private String[] initMap(List<FmtCampo> campos) {
		String[] mapa= new String[tamanoFila+campos.size()];
		mapa[0]="forecons";
		mapa[1]="forefech";
		mapa[2]="foreesta";
		mapa[3]="tablvast";
		mapa[4]="usuaunit";
		mapa[5]="usuarazo";
		mapa[6]="usuasucu";
		return mapa;
	}

	private List<Object[]> generateRows(List<FmtCampo> campos, List<FmtFormregi> registros, List<FmtValocamp> valoresRegistros, List<FrmTablas> estados, List<PilUsua> usuarios) {
		List<Object[]> registrosResult=new ArrayList<Object[]>();
		for(FmtFormregi registro:registros){
			Object[] fila = generateColumns(campos, valoresRegistros, registro, estados, usuarios);
			
			registrosResult.add(fila);
		}
		return registrosResult;
	}

	private Object[] generateColumns(List<FmtCampo> campos, List<FmtValocamp> valoresRegistros, FmtFormregi registro, List<FrmTablas> estados, List<PilUsua> usuarios) {
		int i=tamanoFila;
		
		Object[] fila = initRow(campos, registro, estados, searchUsuario(registro, usuarios));
		for(FmtCampo campo:campos){
			for(FmtValocamp valoresRegistro:valoresRegistros){
				if(valoresRegistro.getVacacamp().equals(campo.getCampcons()) && valoresRegistro.getVacafore().equals(registro.getForecons())){
					fila[i++]=valoresRegistro.getVacavalo();
					valoresRegistros.remove(valoresRegistro);
					break;
				}
			}
		}
		return fila;
	}

	private PilUsua searchUsuario(FmtFormregi registro, List<PilUsua> usuarios) {
		for(PilUsua usuario:usuarios)
			if(usuario.getUsuausua().equals(registro.getForeuser()))
				return usuario;
		return null;
	}

	private Object[] initRow(List<FmtCampo> campos, FmtFormregi registro, List<FrmTablas> estados, PilUsua usuario) {
		Object[] fila=new Object[tamanoFila+campos.size()];
		
		fila[0]=registro.getForecons();
		fila[1]=registro.getForefech();
		fila[2]=registro.getForeesta();
		for(FrmTablas estado:estados)
			if(estado.getTablclav().equals(registro.getForeesta()))
				fila[3]=estado.getTablvast();
		
		fila[4]=usuario.getUsuaunit();
		fila[5]=usuario.getUsuarazo();
		fila[6]=usuario.getUsuasucu();
		return fila;
	}
	
	@Override
	public int getCount(List<Filter> filters){
						
		return fmtFormregiRepository.getCount(filters);
	}
	
	@Override
	public int getCountAdmin(List<Filter> filters){
						
		return fmtFormregiRepository.getCountAdmin(filters);
	}
	
	@Override	
	public String updateRecordIntermediario(Long vefocons, Long forecons, String user, String paramsData, ArrayList<MultipartFile> file){
		
		try{
			Type type = new TypeToken<Map<String, Object>>(){}.getType();   						
			Map<String, Object> parametersData=gson.fromJson(paramsData, type);
			
			FmtFormregi fmtformregi = fmtFormregiRepository.list(forecons);
			Long trancons=frmTransaccionService.generateTransaction(user);
			
			if(fmtformregi.getForeesta().equals("N") || fmtformregi.getForeesta().equals("D") || fmtformregi.getForeesta().equals("M")){			
				if(fmtValocampService.updateValuesIntermediario(vefocons, forecons, parametersData, user, trancons))
					if(insertFiles(user, file, fmtformregi, trancons))
						return gson.toJson("Se actualizaron los datos correctamente");					
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tituloError", "Error");
			result.put("error", "No se puede actualizar el formato por el estado en el que se encuentra");
			return gson.toJson(result);
		}catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tituloError", "Error");
			result.put("error", "Se presentaron errores en la actualizacion del registro");
			return gson.toJson(result);
		}
	}

	private boolean insertFiles(String user, ArrayList<MultipartFile> file, FmtFormregi fmtformregi, Long transcons) throws Exception {
		if(file.size()>0){
			inactivarAdjuntos(fmtformregi);
			List<FrmArchivo> listAll=this.frmArchivoService.ingresarArchivos(file, "archinte");
			if(fmtAdjuntoService.insertAdjuntos(fmtformregi.getForecons(), user, listAll)){
				updateFmtFormRegi(fmtformregi, user, transcons);
				return true;
			}
			return false;
		}
		return true;
	}

	private void inactivarAdjuntos(FmtFormregi fmtformregi) {
		List<FmtAdjunto> adjuntos=fmtAdjuntoService.listAdjuntoActivos(fmtformregi.getForecons());
		for(FmtAdjunto adjunto:adjuntos){
			adjunto.setAdjuesta("I");
			fmtAdjuntoService.updateIntermediario(adjunto);
		}
	}

	private FmtFormregi updateFmtFormRegi(FmtFormregi fmtformregi, String user, Long transcons) {
		if(!fmtformregi.getForeesta().equals("N")){
			generateAudit("foreesta",fmtformregi.getForecons(),"FmtFormregi", fmtformregi.getForeesta(),"M", transcons);
			fmtEstadoService.insertLastEstado(fmtformregi, user);
			fmtformregi.setForeesta("M");
			fmtFormregiRepository.update(fmtformregi);
		}
		return fmtformregi;
	}
	
	private void generateAudit(String audicamp, Long audicopk, String tabla, String audivaan, String audivanu, Long trancons) {
		fmtAuditoriaService.generateAudit(audicamp, audicopk, tabla, audivaan, audivanu, trancons);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_UPDATE"})
	public String cancelarRecord(Long forecons){
		FmtFormregi fmtformregi=fmtFormregiRepository.list(forecons);
		
		fmtEstadoService.insertLastEstado(fmtformregi, userDetails.getUser());
		
		
		Long transcons=frmTransaccionService.generateTransaction("");
		generateAudit("foreesta",fmtformregi.getForecons(),"FmtFormregi", fmtformregi.getForeesta(),"C", transcons);
		fmtformregi.setForeesta("C");
		fmtFormregiRepository.update(fmtformregi);
		
		return gson.toJson("El id: "+forecons+" ha sido cancelado");
	}
}

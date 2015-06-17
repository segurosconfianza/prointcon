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
import com.confianza.webapp.service.formatos.fmtadjunto.FmtAdjuntoService;
import com.confianza.webapp.service.formatos.fmtcampo.FmtCampoService;
import com.confianza.webapp.service.formatos.fmtvalocamp.FmtValocampService;
import com.confianza.webapp.service.framework.frmarchivo.FrmArchivoService;
import com.confianza.webapp.service.framework.frmtablas.FrmTablasService;
import com.confianza.webapp.utils.Filter;
import com.confianza.webapp.utils.JSONUtil;

@Service
public class FmtFormregiServiceImpl implements FmtFormregiService{
	
	@Autowired
	private FmtFormregiRepository fmtformregiRepository;
	
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
	Gson gson;
	
	/**
	 * @return the fmtformregiRepository
	 */
	public FmtFormregiRepository getFmtFormregiRepository() {
		return fmtformregiRepository;
	}

	/**
	 * @param fmtformregiRepository the fmtformregiRepository to set
	 */
	public void setFmtFormregiRepository(FmtFormregiRepository fmtformregiRepository) {
		this.fmtformregiRepository = fmtformregiRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_READ"})
	public String list(Long id){
		FmtFormregi listAll=fmtformregiRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		//result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	public FmtFormregi listEntity(Long id){
		FmtFormregi listAll=fmtformregiRepository.list(id);
		
		return listAll;	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtFormregi> listAll=fmtformregiRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		//result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}			
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_UPDATE"})
	public String update(FmtFormregi fmtformregi){
		return gson.toJson(fmtformregiRepository.update(fmtformregi));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_DELETE"})
	public void delete(FmtFormregi fmtformregi){
		fmtformregiRepository.delete(fmtformregi);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMREGI_ALL", "FMT_FORMREGI_CREATE"})
	public String insert(FmtFormregi fmtformregi){
		return gson.toJson(fmtformregiRepository.insert(fmtformregi));
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
					return gson.toJson("Registro creado Satisfactoriamente");
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
		fmtformregi.setForeesta("A");
		fmtformregi.setForefech(Calendar.getInstance().getTime());
		fmtformregi.setForeuser(user);
		fmtformregiRepository.insert(fmtformregi);
		return fmtformregi;
	} 	
	
	@Override	
	public String loadFormRegiIntermediario(Long vefocons, String user, int pageSize, int page, String order, String stringFilters){ 
		
		Map<String, Object> result = new HashMap<String, Object>();
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		try{
			List<FmtCampo> campos=fmtCampoService.listEntityCamposCosu(vefocons);
			List<FmtFormregi> registros=fmtformregiRepository.listAll((pageSize*page)-(pageSize), pageSize, vefocons, user, order, filters);
			List<FmtValocamp> valoresRegistros=fmtValocampService.listAll((pageSize*page)-(pageSize), pageSize*campos.size(), vefocons, user);
			List<FrmTablas> estados=frmTablasService.listByCodi("foreesta");
			result.put("data", JSONUtil.toNameList(generateMaps(campos),generateRows(campos, registros, valoresRegistros, estados)));
			result.put("count", this.getCount(filters));
			
		}catch (Exception e) {
			e.printStackTrace();
			result.put("error", "Ocurrio un error");
		}
		
		return gson.toJson(result);
	}

	private String[] generateMaps(List<FmtCampo> campos) {
		String[] mapa = initMap(campos);
		int i=4;
		for(FmtCampo campo:campos)
			mapa[i++]=campo.getCampnomb();
		return mapa;
	}

	private String[] initMap(List<FmtCampo> campos) {
		String[] mapa= new String[4+campos.size()];
		mapa[0]="forecons";
		mapa[1]="forefech";
		mapa[2]="foreesta";
		mapa[3]="tablvast";
		return mapa;
	}

	private List<Object[]> generateRows(List<FmtCampo> campos, List<FmtFormregi> registros, List<FmtValocamp> valoresRegistros, List<FrmTablas> estados) {
		List<Object[]> registrosResult=new ArrayList<Object[]>();
		for(FmtFormregi registro:registros){
			Object[] fila = generateColumns(campos, valoresRegistros, registro, estados);
			
			registrosResult.add(fila);
		}
		return registrosResult;
	}

	private Object[] generateColumns(List<FmtCampo> campos, List<FmtValocamp> valoresRegistros, FmtFormregi registro, List<FrmTablas> estados) {
		int i=4;
		Object[] fila = initRow(campos, registro, estados);
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

	private Object[] initRow(List<FmtCampo> campos, FmtFormregi registro, List<FrmTablas> estados) {
		Object[] fila=new Object[4+campos.size()];
		
		fila[0]=registro.getForecons();
		fila[1]=registro.getForefech();
		fila[2]=registro.getForeesta();
		for(FrmTablas estado:estados)
			if(estado.getTablclav().equals(registro.getForeesta()))
				fila[3]=estado.getTablvast();
		return fila;
	}
	
	@Override
	public int getCount(List<Filter> filters){
						
		return fmtformregiRepository.getCount(filters);
	}
	
	@Override	
	public String updateRecordIntermediario(Long vefocons, Long forecons, String user, String paramsData, ArrayList<MultipartFile> file){
		
		try{
			Type type = new TypeToken<Map<String, Object>>(){}.getType();   						
			Map<String, Object> parametersData=gson.fromJson(paramsData, type);
			
			FmtFormregi fmtformregi = fmtformregiRepository.list(forecons);
			
			if(fmtValocampService.updateValuesIntermediario(vefocons, forecons, parametersData, user))
				if(insertFiles(user, file, fmtformregi))
					return gson.toJson("Se actualizaron los datos correctamente");
			return gson.toJson("Se presentaron errores en la actualizacion del registro");
		}catch (Exception e) {
			e.printStackTrace();
			return gson.toJson("Se presentaron errores en la actualizacion del registro"); 
		}
	}

	private boolean insertFiles(String user, ArrayList<MultipartFile> file, FmtFormregi fmtformregi) throws Exception {
		if(file.size()>0){
			inactivarAdjuntos(fmtformregi);
			List<FrmArchivo> listAll=this.frmArchivoService.ingresarArchivos(file, "archinte");
			if(fmtAdjuntoService.insertAdjuntos(fmtformregi.getForecons(), user, listAll)){
				updateFmtFormRegi(fmtformregi, user);
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

	private FmtFormregi updateFmtFormRegi(FmtFormregi fmtformregi, String user) {
		fmtformregi.setForeesta("M");
		return fmtformregiRepository.update(fmtformregi);
	}

}

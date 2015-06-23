package com.confianza.webapp.service.formatos.fmtformato;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.confianza.webapp.repository.formatos.fmtformato.FmtFormato;
import com.confianza.webapp.repository.formatos.fmtformato.FmtFormatoRepository;
import com.confianza.webapp.repository.formatos.fmtversform.FmtVersform;
import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.service.formatos.fmtversform.FmtVersformService;

@Service
public class FmtFormatoServiceImpl implements FmtFormatoService{
	
	@Autowired
	private FmtFormatoRepository fmtFormatoRepository;
	
	@Autowired
	private FmtVersformService fmtversformService;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the fmtformatoRepository
	 */
	public FmtFormatoRepository getFmtFormatoRepository() {
		return fmtFormatoRepository;
	}

	/**
	 * @param fmtformatoRepository the fmtformatoRepository to set
	 */
	public void setFmtFormatoRepository(FmtFormatoRepository fmtformatoRepository) {
		this.fmtFormatoRepository = fmtformatoRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMATO_ALL", "FMT_FORMATO_READ"})
	public String list(Long id){
		FmtFormato listAll=fmtFormatoRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMATO_ALL", "FMT_FORMATO_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtFormato> listAll=fmtFormatoRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return fmtFormatoRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMATO_ALL", "FMT_FORMATO_UPDATE"})
	public String update(FmtFormato fmtformato){
		return gson.toJson(fmtFormatoRepository.update(fmtformato));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMATO_ALL", "FMT_FORMATO_DELETE"})
	public void delete(FmtFormato fmtformato){
		fmtFormatoRepository.delete(fmtformato);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMATO_ALL", "FMT_FORMATO_CREATE"})
	public String insert(FmtFormato fmtformato){
		return gson.toJson(fmtFormatoRepository.insert(fmtformato));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_FORMATO_ALL", "FMT_FORMATO_READ", "INTERMEDIARIO_ALL"})
	public String loadData(String formcons){
			        
		//carga la consulta dinamica
		FmtFormato fmtFormato=fmtFormatoRepository.list(new Long(formcons));
					
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("titulo", fmtFormato.getFormnomb());
		result.put("descri", fmtFormato.getFormdesc());
		result.put("version", this.fmtversformService.lastVersionEntity(new Long(formcons)));
		return gson.toJson(result);
	}
	
	@Override	
	public String loadDataIntermediario(String formcons){
			        
		//carga la consulta dinamica
		FmtFormato fmtFormato=fmtFormatoRepository.list(new Long(formcons));
					
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("titulo", fmtFormato.getFormnomb());
		result.put("descri", fmtFormato.getFormdesc());
		result.put("version", this.fmtversformService.lastVersionEntity(new Long(formcons)));
		return gson.toJson(result);
	}
}

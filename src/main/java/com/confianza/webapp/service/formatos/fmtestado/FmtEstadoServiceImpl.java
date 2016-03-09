package com.confianza.webapp.service.formatos.fmtestado;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.confianza.webapp.repository.formatos.fmtestado.FmtEstado;
import com.confianza.webapp.repository.formatos.fmtestado.FmtEstadoRepository;   
import com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregi;
import com.confianza.webapp.utils.Filter;

@Service
public class FmtEstadoServiceImpl implements FmtEstadoService{
	
	@Autowired
	private FmtEstadoRepository fmtEstadoRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the fmtestadoRepository
	 */
	public FmtEstadoRepository getFmtEstadoRepository() {
		return fmtEstadoRepository;
	}

	/**
	 * @param fmtestadoRepository the fmtestadoRepository to set
	 */
	public void setFmtEstadoRepository(FmtEstadoRepository fmtestadoRepository) {
		this.fmtEstadoRepository = fmtestadoRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_ESTADO_ALL", "FMT_ESTADO_READ"})
	public String list(Long id){
		FmtEstado listAll=fmtEstadoRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", 1);
		
		return gson.toJson(result);	
	}		
	
	@Override
	public int getCount(List<Filter> filters){
				
		return fmtEstadoRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_ESTADO_ALL", "FMT_ESTADO_UPDATE"})
	public String update(FmtEstado fmtestado){
		return gson.toJson(fmtEstadoRepository.update(fmtestado));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_ESTADO_ALL", "FMT_ESTADO_DELETE"})
	public void delete(FmtEstado fmtestado){
		fmtEstadoRepository.delete(fmtestado);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_ESTADO_ALL", "FMT_ESTADO_CREATE"})
	public String insert(FmtEstado fmtestado){
		return gson.toJson(fmtEstadoRepository.insert(fmtestado));
	}
	
	@Override
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<FmtEstado> listAll=fmtEstadoRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public void insertLastEstado(FmtFormregi fmtformregi, String user) {
		FmtEstado fmtestado=new FmtEstado();
		fmtestado.setEstaesta(fmtformregi.getForeesta());
		fmtestado.setEstafech(new Date());
		fmtestado.setEstafore(fmtformregi.getForecons());
		fmtestado.setEstauser(user);
		fmtEstadoRepository.insert(fmtestado);
	}
	
}

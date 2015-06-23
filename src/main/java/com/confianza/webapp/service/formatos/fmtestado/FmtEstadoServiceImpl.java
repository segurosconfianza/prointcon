package com.confianza.webapp.service.formatos.fmtestado;

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
import com.confianza.webapp.repository.formatos.fmtestado.FmtEstado;
import com.confianza.webapp.repository.formatos.fmtestado.FmtEstadoRepository;

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
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_ESTADO_ALL", "FMT_ESTADO_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtEstado> listAll=fmtEstadoRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return fmtEstadoRepository.getCount();
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
	public String listAll(int pageSize, int page, long forecons){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtEstado> listAll=fmtEstadoRepository.listAll(init, limit, forecons);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
}

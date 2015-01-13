package com.confianza.webapp.service.framework.frmconsulta;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmconsulta.FrmConsultaRepository;

@Service
public class FrmConsultaServiceImpl implements FrmConsultaService{
	
	@Autowired
	private FrmConsultaRepository frmConsultaRepository;
	
	/**
	 * @return the frmconsultaRepository
	 */
	public FrmConsultaRepository getFrmConsultaRepository() {
		return frmConsultaRepository;
	}

	/**
	 * @param frmconsultaRepository the frmconsultaRepository to set
	 */
	public void setFrmConsultaRepository(FrmConsultaRepository frmconsultaRepository) {
		this.frmConsultaRepository = frmconsultaRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMCONSULTA__ALL", "APP_FRMCONSULTA__READ"})
	public FrmConsulta list(Long id){
		return frmConsultaRepository.list(id);
	}
	
	@Override	
	public FrmConsulta listName(String id){
		return frmConsultaRepository.listName(id);
	}
	
	@Override
	public List<Object[]> loadData(FrmConsulta frmConsulta,Map<String, Object> parameters){
		
		if(frmConsulta.getConscaco().equals("dataSource")){
			return frmConsultaRepository.loadData(frmConsulta, parameters);
		}
		else if(frmConsulta.getConscaco().equals("dataSourceOsiris")){
			return frmConsultaRepository.loadDataOsiris(frmConsulta, parameters);
		}
		return null;
		
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMCONSULTA__ALL", "APP_FRMCONSULTA__READ"})
	public List<FrmConsulta> listAll(int pageSize, int page){
	
		int limit=pageSize*page;
		int init=limit-pageSize;
		
		return frmConsultaRepository.listAll(init, limit);
	}	
	
	@Override
	public int getCount(){
				
		return frmConsultaRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMCONSULTA__ALL", "APP_FRMCONSULTA__UPDATE"})
	public FrmConsulta update(FrmConsulta frmconsulta){
		return frmConsultaRepository.update(frmconsulta);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMCONSULTA__ALL", "APP_FRMCONSULTA__DELETE"})
	public void delete(FrmConsulta frmconsulta){
		frmConsultaRepository.delete(frmconsulta);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMCONSULTA__ALL", "APP_FRMCONSULTA__CREATE"})
	public FrmConsulta insert(FrmConsulta frmconsulta){
		return frmConsultaRepository.insert(frmconsulta);
	}
	
}

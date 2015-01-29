package com.confianza.webapp.service.framework.frmlogext;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.framework.frmlogext.FrmLogext;
import com.confianza.webapp.repository.framework.frmlogext.FrmLogextRepository;

@Service
public class FrmLogextServiceImpl implements FrmLogextService{
	
	@Autowired
	private FrmLogextRepository frmlogextRepository;
	
	/**
	 * @return the frmlogextRepository
	 */
	public FrmLogextRepository getFrmLogextRepository() {
		return frmlogextRepository;
	}

	/**
	 * @param frmlogextRepository the frmlogextRepository to set
	 */
	public void setFrmLogextRepository(FrmLogextRepository frmlogextRepository) {
		this.frmlogextRepository = frmlogextRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMLOGEXT__ALL", "APP_FRMLOGEXT__READ"})
	public FrmLogext list(Long id){
		return frmlogextRepository.list(id);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMLOGEXT__ALL", "APP_FRMLOGEXT__READ"})
	public List<FrmLogext> listAll(int pageSize, int page){
	
		int limit=pageSize*page;
		int init=limit-pageSize;
		
		return frmlogextRepository.listAll(init, limit);
	}	
	
	@Override
	public int getCount(){
				
		return frmlogextRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMLOGEXT__ALL", "APP_FRMLOGEXT__UPDATE"})
	public FrmLogext update(FrmLogext frmlogext){
		return frmlogextRepository.update(frmlogext);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMLOGEXT__ALL", "APP_FRMLOGEXT__DELETE"})
	public void delete(FrmLogext frmlogext){
		frmlogextRepository.delete(frmlogext);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMLOGEXT__ALL", "APP_FRMLOGEXT__CREATE"})
	public FrmLogext insert(FrmLogext frmlogext){
		return frmlogextRepository.insert(frmlogext);
	}					
}

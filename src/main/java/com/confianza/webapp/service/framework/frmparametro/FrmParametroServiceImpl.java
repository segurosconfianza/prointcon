package com.confianza.webapp.service.framework.frmparametro;

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

import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametroRepository;

@Service
public class FrmParametroServiceImpl implements FrmParametroService{
	
	@Autowired
	private FrmParametroRepository frmParametroRepository;
	
	/**
	 * @return the frmparametroRepository
	 */
	public FrmParametroRepository getFrmParametroRepository() {
		return frmParametroRepository;
	}

	/**
	 * @param frmparametroRepository the frmparametroRepository to set
	 */
	public void setFrmParametroRepository(FrmParametroRepository frmparametroRepository) {
		this.frmParametroRepository = frmparametroRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMPARAMETRO__ALL", "APP_FRMPARAMETRO__READ"})
	public FrmParametro list(Long id){
		return frmParametroRepository.list(id);
	}
	
	@Override
	public List<FrmParametro> listParamsCosu(Long id){
	
		return frmParametroRepository.listParamsCosu(id);
	}	
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMPARAMETRO__ALL", "APP_FRMPARAMETRO__READ"})
	public List<FrmParametro> listAll(int pageSize, int page){
	
		int limit=pageSize*page;
		int init=limit-pageSize;
		
		return frmParametroRepository.listAll(init, limit);
	}	
	
	@Override
	public int getCount(){
				
		return frmParametroRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMPARAMETRO__ALL", "APP_FRMPARAMETRO__UPDATE"})
	public FrmParametro update(FrmParametro frmparametro){
		return frmParametroRepository.update(frmparametro);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMPARAMETRO__ALL", "APP_FRMPARAMETRO__DELETE"})
	public void delete(FrmParametro frmparametro){
		frmParametroRepository.delete(frmparametro);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMPARAMETRO__ALL", "APP_FRMPARAMETRO__CREATE"})
	public FrmParametro insert(FrmParametro frmparametro){
		return frmParametroRepository.insert(frmparametro);
	}
	
}

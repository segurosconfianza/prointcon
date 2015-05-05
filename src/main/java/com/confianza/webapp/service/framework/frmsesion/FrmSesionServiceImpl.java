package com.confianza.webapp.service.framework.frmsesion;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.framework.frmsesion.FrmSesion;
import com.confianza.webapp.repository.framework.frmsesion.FrmSesionRepository;

@Service
public class FrmSesionServiceImpl implements FrmSesionService{
	
	@Autowired
	private FrmSesionRepository frmSesionRepository;
	
	/**
	 * @return the frmsesionRepository
	 */
	public FrmSesionRepository getFrmSesionRepository() {
		return frmSesionRepository;
	}

	/**
	 * @param frmsesionRepository the frmsesionRepository to set
	 */
	public void setFrmSesionRepository(FrmSesionRepository frmsesionRepository) {
		this.frmSesionRepository = frmsesionRepository;
	}
	
	@Override
	public FrmSesion list(Long id){
		return frmSesionRepository.list(id);
	}
	
	@Override
	public List<FrmSesion> listAll(){
		return frmSesionRepository.listAll();
	}	
	
	@Override
	public FrmSesion update(Long id){
		return frmSesionRepository.update(id);
	}
	
	@Override
	public void delete(Long id){
		frmSesionRepository.delete(id);
	}
	
	@Override
	public FrmSesion insert(String usuario, String sesion){
		
		FrmSesion frmsesion=new FrmSesion();
		frmsesion.setSesiusua(usuario);
		frmsesion.setSesisesi(sesion);
		frmsesion.setSesifecr(new Date());
		frmSesionRepository.insert(frmsesion);
		return frmsesion;
	}
	
}

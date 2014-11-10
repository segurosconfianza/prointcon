package com.confianza.webapp.service.framework.frmperfil;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.framework.frmperfil.FrmPerfil;
import com.confianza.webapp.repository.framework.frmperfil.FrmPerfilRepository;

@Service
public class FrmPerfilServiceImpl implements FrmPerfilService{
	
	@Autowired
	private FrmPerfilRepository frmPerfilRepository;
	
	/**
	 * @return the frmperfilRepository
	 */
	public FrmPerfilRepository getFrmPerfilRepository() {
		return frmPerfilRepository;
	}

	/**
	 * @param frmperfilRepository the frmperfilRepository to set
	 */
	public void setFrmPerfilRepository(FrmPerfilRepository frmperfilRepository) {
		this.frmPerfilRepository = frmperfilRepository;
	}
	
	@Override
	public FrmPerfil list(Long id){
		return frmPerfilRepository.list(id);
	}
	
	@Override
	public List<FrmPerfil> listAll(){
		return frmPerfilRepository.listAll();
	}	
	
	@Override
	public FrmPerfil update(Long id){
		return frmPerfilRepository.update(id);
	}
	
	@Override
	public void delete(Long id){
		frmPerfilRepository.delete(id);
	}
	
	@Override
	public FrmPerfil insert(FrmPerfil frmperfil){
		return frmPerfilRepository.insert(frmperfil);
	}
	
}
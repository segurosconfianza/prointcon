package com.confianza.webapp.service.framework.frmauditoria;

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

import com.confianza.webapp.repository.framework.frmauditoria.FrmAuditoria;
import com.confianza.webapp.repository.framework.frmauditoria.FrmAuditoriaRepository;

@Service
public class FrmAuditoriaServiceImpl implements FrmAuditoriaService{
	
	@Autowired
	private FrmAuditoriaRepository frmAuditoriaRepository;
	
	/**
	 * @return the frmauditoriaRepository
	 */
	public FrmAuditoriaRepository getFrmAuditoriaRepository() {
		return frmAuditoriaRepository;
	}

	/**
	 * @param frmauditoriaRepository the frmauditoriaRepository to set
	 */
	public void setFrmAuditoriaRepository(FrmAuditoriaRepository frmauditoriaRepository) {
		this.frmAuditoriaRepository = frmauditoriaRepository;
	}
	
	@Override
	public FrmAuditoria list(Long id){
		return frmAuditoriaRepository.list(id);
	}
	
	@Override
	public List<FrmAuditoria> listAll(){
		return frmAuditoriaRepository.listAll();
	}	
	
	@Override
	public FrmAuditoria update(FrmAuditoria frmAuditoria){
		return frmAuditoriaRepository.update(frmAuditoria); 
	}
	
	@Override
	public void delete(Long id){
		frmAuditoriaRepository.delete(id);
	}
	
	@Override
	public FrmAuditoria insert(FrmAuditoria frmauditoria){
		return frmAuditoriaRepository.insert(frmauditoria);
	}
	
}

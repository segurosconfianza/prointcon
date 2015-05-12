package com.confianza.webapp.service.framework.frmi18n;

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

import com.confianza.webapp.repository.framework.frmi18n.FrmI18n;
import com.confianza.webapp.repository.framework.frmi18n.FrmI18nRepository;

@Service
public class FrmI18nServiceImpl implements FrmI18nService{
	
	@Autowired
	private FrmI18nRepository frmI18nRepository;
	
	/**
	 * @return the frmi18nRepository
	 */
	public FrmI18nRepository getFrmI18nRepository() {
		return frmI18nRepository;
	}

	/**
	 * @param frmi18nRepository the frmi18nRepository to set
	 */
	public void setFrmI18nRepository(FrmI18nRepository frmi18nRepository) {
		this.frmI18nRepository = frmi18nRepository;
	}
	
	@Override
	public FrmI18n list(Long id){
		return frmI18nRepository.list(id);
	}
	
	@Override
	public List<Object[]> listModulo(String modulo){
		String[] modulos= modulo.split(",");
		return frmI18nRepository.listModulo(modulos);
	}
	
	@Override
	public List<FrmI18n> listAll(int pageSize, int page){
	
		int limit=pageSize*page;
		int init=limit-pageSize;
		
		return frmI18nRepository.listAll(init, limit);
	}	
	
	@Override
	public int getCount(){
				
		return frmI18nRepository.getCount();
	}
	
	@Override
	public FrmI18n update(Long id){
		return frmI18nRepository.update(id);
	}
	
	@Override
	public void delete(Long id){
		frmI18nRepository.delete(id);
	}
	
	@Override
	public FrmI18n insert(FrmI18n frmi18n){
		
		frmI18nRepository.insert(frmi18n);
		return frmi18n;
	}
	
}

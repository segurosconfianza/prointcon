package com.confianza.webapp.service.framework.frmtransaccion;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.framework.frmperfil.FrmPerfil;
import com.confianza.webapp.repository.framework.frmsesion.FrmSesion;
import com.confianza.webapp.repository.framework.frmtransaccion.FrmTransaccion;
import com.confianza.webapp.repository.framework.frmtransaccion.FrmTransaccionRepository;
import com.google.gson.Gson;

@Service
public class FrmTransaccionServiceImpl implements FrmTransaccionService{
	
	@Autowired
	Gson gson;
	
	@Autowired
	private FrmTransaccionRepository frmTransaccionRepository;
		
	/**
	 * @return the frmtransaccionRepository
	 */
	public FrmTransaccionRepository getFrmTransaccionRepository() {
		return frmTransaccionRepository;
	}

	/**
	 * @param frmtransaccionRepository the frmtransaccionRepository to set
	 */
	public void setFrmTransaccionRepository(FrmTransaccionRepository frmtransaccionRepository) {
		this.frmTransaccionRepository = frmtransaccionRepository;
	}
	
	@Override
	public FrmTransaccion list(Long id){
		return frmTransaccionRepository.list(id);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_TRANSACCION_ALL", "FRM_TRANSACCION_READ"})
	public String listAll(int pageSize, int page){
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FrmTransaccion> listAll=this.frmTransaccionRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);
	}	
	
	@Override
	public int getCount(){
				
		return frmTransaccionRepository.getCount();
	}
	
	@Override
	public FrmTransaccion update(Long id){
		return frmTransaccionRepository.update(id);
	}
	
	@Override
	public void delete(Long id){
		frmTransaccionRepository.delete(id);
	}
	
	@Override
	public FrmTransaccion insert(FrmTransaccion frmtransaccion){
		return frmTransaccionRepository.insert(frmtransaccion);
	}
	
	@Override
	public FrmTransaccion insert(Long frmSesion){
		
		FrmTransaccion frmtransaccion=new FrmTransaccion();
		frmtransaccion.setTransesi(frmSesion);
		frmtransaccion.setTranfecr(new Date());
				
	    return frmTransaccionRepository.insert(frmtransaccion);
	}
}

package com.confianza.webapp.service.framework.frmperfmodu;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confianza.webapp.repository.framework.frmaplicaciones.FrmAplicaciones;
import com.confianza.webapp.repository.framework.frmmodulo.FrmModulo;
import com.confianza.webapp.repository.framework.frmmodurope.FrmModurope;
import com.confianza.webapp.repository.framework.frmperfmodu.FrmPerfmodu;
import com.confianza.webapp.repository.framework.frmperfmodu.FrmPerfmoduRepository;
import com.confianza.webapp.repository.framework.frmroleperm.FrmRoleperm;
import com.confianza.webapp.utils.JSONUtil;
import com.google.gson.Gson;

@Service
public class FrmPerfmoduServiceImpl implements FrmPerfmoduService, getColumnsMaps{
	
	@Autowired
	private FrmPerfmoduRepository frmperfmoduRepository;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the frmperfmoduRepository
	 */
	public FrmPerfmoduRepository getFrmPerfmoduRepository() {
		return frmperfmoduRepository;
	}

	/**
	 * @param frmperfmoduRepository the frmperfmoduRepository to set
	 */
	public void setFrmPerfmoduRepository(FrmPerfmoduRepository frmperfmoduRepository) {
		this.frmperfmoduRepository = frmperfmoduRepository;
	}
	
	@Override
	public String list(Long id){
		return gson.toJson(frmperfmoduRepository.list(id));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFMODU_ALL", "FRM_PERFMODU_READ"})
	public String listAll(int pageSize, int page, Long pemopefi){
		
		int limit=pageSize*page;
		int init=limit-pageSize;
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Object[]> listAll=frmperfmoduRepository.listAll(init, limit, pemopefi);
		
		//cast de los menu a ser mapeados por cada campo
		List<Map<String, Object>> rolAll = JSONUtil.toNameList(getColumMap(),listAll);
		
		result.put("data", rolAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);
	}

	private String[] getColumMap() {
		String maestroDetalle[] = new String[FrmPerfmodu.getNames().length+FrmModurope.getNames().length+FrmModulo.getNames().length+FrmRoleperm.getNames().length+FrmAplicaciones.getNames().length+1];
		
		System.arraycopy(FrmPerfmodu.getNames(), 0, maestroDetalle, 0, FrmPerfmodu.getNames().length);
		System.arraycopy(FrmModurope.getNames(), 0, maestroDetalle, FrmPerfmodu.getNames().length, FrmModurope.getNames().length);
		System.arraycopy(FrmModulo.getNames(), 0, maestroDetalle, FrmPerfmodu.getNames().length+FrmModurope.getNames().length, FrmModulo.getNames().length);
		System.arraycopy(FrmRoleperm.getNames(), 0, maestroDetalle, FrmPerfmodu.getNames().length+FrmModurope.getNames().length+FrmModulo.getNames().length, FrmRoleperm.getNames().length);
		System.arraycopy(FrmAplicaciones.getNames(), 0, maestroDetalle, FrmPerfmodu.getNames().length+FrmModurope.getNames().length+FrmModulo.getNames().length+FrmRoleperm.getNames().length, FrmAplicaciones.getNames().length);
		System.arraycopy(new String[]{"tablvast"}, 0, maestroDetalle, FrmPerfmodu.getNames().length+FrmModurope.getNames().length+FrmModulo.getNames().length+FrmRoleperm.getNames().length+FrmAplicaciones.getNames().length, 1);
		
		return maestroDetalle;
	}	
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFMODU_ALL", "FRM_PERFMODU_UPDATE"})
	public String update(FrmPerfmodu frmperfmodu){
		return gson.toJson(frmperfmoduRepository.update(frmperfmodu));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFMODU_ALL", "FRM_PERFMODU_DELETE"})
	public String delete(FrmPerfmodu frmperfmodu){
		return gson.toJson(frmperfmoduRepository.delete(frmperfmodu));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFMODU_ALL", "FRM_PERFMODU_CREATE"})
	public String insert(FrmPerfmodu frmperfmodu){
		return gson.toJson(frmperfmoduRepository.insert(frmperfmodu));
	}

	@Override
	public int getCount() {
		return frmperfmoduRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_PERFMODU_ALL", "FRM_PERFMODU_READ"})
	public String listComboMoro(){
		
		List<Object[]> listAll=frmperfmoduRepository.listComboMoro();
		
		//cast de los menu a ser mapeados por cada campo
		List<Map<String, Object>> rolAll = JSONUtil.toNameList(
				new String[]{"value", "label"},listAll
		);
		
		return gson.toJson(rolAll);
	}
	
}

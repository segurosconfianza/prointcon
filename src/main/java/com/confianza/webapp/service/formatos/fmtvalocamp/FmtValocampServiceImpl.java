package com.confianza.webapp.service.formatos.fmtvalocamp;

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
import com.confianza.webapp.repository.formatos.fmtcampo.FmtCampo;
import com.confianza.webapp.repository.formatos.fmtcampo.FmtCampoRepository;
import com.confianza.webapp.repository.formatos.fmtvalocamp.FmtValocamp;
import com.confianza.webapp.repository.formatos.fmtvalocamp.FmtValocampRepository;

@Service
public class FmtValocampServiceImpl implements FmtValocampService{
	
	@Autowired
	private FmtValocampRepository fmtvalocampRepository;
	
	@Autowired
	private FmtCampoRepository fmtcampoRepository;
	
	private enum typesData { S, CS, CI, D, I, L, T, O, B, F, TA, TL};
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the fmtvalocampRepository
	 */
	public FmtValocampRepository getFmtValocampRepository() {
		return fmtvalocampRepository;
	}

	/**
	 * @param fmtvalocampRepository the fmtvalocampRepository to set
	 */
	public void setFmtValocampRepository(FmtValocampRepository fmtvalocampRepository) {
		this.fmtvalocampRepository = fmtvalocampRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VALOCAMP_ALL", "FMT_VALOCAMP_READ"})
	public String list(Long id){
		FmtValocamp listAll=fmtvalocampRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VALOCAMP_ALL", "FMT_VALOCAMP_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		
		List<FmtValocamp> listAll=fmtvalocampRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return fmtvalocampRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VALOCAMP_ALL", "FMT_VALOCAMP_UPDATE"})
	public String update(FmtValocamp fmtvalocamp){
		return gson.toJson(fmtvalocampRepository.update(fmtvalocamp));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VALOCAMP_ALL", "FMT_VALOCAMP_DELETE"})
	public void delete(FmtValocamp fmtvalocamp){
		fmtvalocampRepository.delete(fmtvalocamp);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FMT_VALOCAMP_ALL", "FMT_VALOCAMP_CREATE"})
	public String insert(FmtValocamp fmtvalocamp){
		return gson.toJson(fmtvalocampRepository.insert(fmtvalocamp));
	}
	
	@Override  
	public boolean insertValuesIntermediario(Long vefocons, Long forecons, Map<String, Object> parametersData){
		
		try{
			List<FmtCampo> listAllCampos=fmtcampoRepository.listCamposCosu(vefocons);	
			
			for(FmtCampo campo:listAllCampos){
				FmtValocamp fmtvalocamp=new FmtValocamp();
				fmtvalocamp.setVacacamp(campo.getCampcons());
				fmtvalocamp.setVacafore(forecons);
				fmtvalocamp.setVacavalo(this.getValue(campo.getCamptipo(), parametersData.get(campo.getCampnomb()).toString()));
				System.out.println(fmtvalocamp);
				fmtvalocampRepository.insert(fmtvalocamp);
				
				
			}	
			return true;
		}catch (Exception e){ 
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<FmtValocamp> listAll(int init, int limit, Long vacafore, String user){
	
		return fmtvalocampRepository.listAll(init, limit, vacafore, user);
	}
	
	private String getValue(String tipo, String value){
		typesData typeData=typesData.valueOf(tipo);
		double aux;
		String result="";
		switch(typeData){
				case S: result = value;
						break;
				case CS: result = value;
						break;
				case TA: result = value;
						break;	
				case TL: result = value;
						break;				
				case D: result = value;
						break;
				case I: aux=Float.parseFloat( value);
						result = (int)aux+"";
						break;
				case CI:aux=Float.parseFloat( value);
						result = (int)aux+"";
						break;		
				case L: aux=Float.parseFloat( value);
						result = (long)aux+"";
						break;
				case T: result = value;
						break;
				case O: aux = Double.parseDouble(value);
						result = aux+"";
						break;
				case B: result = value;
						break;
				case F: aux = Double.parseDouble(value);
						result = aux+"";
						break;
				default:break;
		}
		
		return result;
	}
	
	@Override  
	public boolean updateValuesIntermediario(Long vefocons, Long vacafore, Map<String, Object> parametersData, String user){
		
		try{
			List<FmtCampo> listAllCampos=fmtcampoRepository.listCamposCosu(vefocons);	
			List<FmtValocamp> listAllValocamp=fmtvalocampRepository.listAll(vacafore);
			
			for(FmtCampo campo:listAllCampos){
				for(FmtValocamp fmtValocamp:listAllValocamp){
					if(campo.getCampcons().equals(fmtValocamp.getVacacamp())){
						if(!fmtValocamp.getVacavalo().equals(parametersData.get(campo.getCampnomb()).toString())){
							updateFmtValocamp(parametersData, campo, fmtValocamp, user);
						}
						break;
					}
				}
			}
			return true;
		}catch (Exception e){ 
			e.printStackTrace();
			return false;
		}
	}

	@Override  
	public  FmtValocamp updateFmtValocamp(Map<String, Object> parametersData, FmtCampo campo, FmtValocamp fmtValocamp, String user) {
		fmtValocamp.setVacavalo(this.getValue(campo.getCamptipo(), parametersData.get(campo.getCampnomb()).toString()));
		return fmtvalocampRepository.update(fmtValocamp);
	}
}

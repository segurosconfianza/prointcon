package com.confianza.webapp.service.pila.pilusua;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.confianza.webapp.repository.pila.pilusua.PilUsua;
import com.confianza.webapp.repository.pila.pilusua.PilUsuaRepository;
import com.confianza.webapp.service.framework.frmtransaccion.FrmTransaccionService;
import com.confianza.webapp.service.pila.pilauditoria.PilAuditoriaService;
import com.confianza.webapp.service.security.AutenticateImpl;
import com.confianza.webapp.utils.Filter;

@Service
public class PilUsuaServiceImpl implements PilUsuaService{
	
	@Autowired
	private PilUsuaRepository pilUsuaRepository;
	
	@Autowired
	private	AutenticateImpl autenticateImpl;
	
	@Autowired
	private FrmTransaccionService frmTransaccionService;
	
	@Autowired
	private PilAuditoriaService pilAuditoriaService;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the pilusuaRepository
	 */
	public PilUsuaRepository getPilUsuaRepository() {
		return pilUsuaRepository;
	}

	/**
	 * @param pilusuaRepository the pilusuaRepository to set
	 */
	public void setPilUsuaRepository(PilUsuaRepository pilusuaRepository) {
		this.pilUsuaRepository = pilusuaRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_READ"})
	public String list(Long id){
		PilUsua listAll=pilUsuaRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		//result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_READ"})
	public String listAll(int pageSize, int page, String order, String stringFilters){
	
		int limit=pageSize;
		int init=(pageSize*page)-(pageSize);
		Type listOfTestObject = new TypeToken<List<Filter>>(){}.getType();
		List<Filter> filters = gson.fromJson("["+stringFilters+"]", listOfTestObject);
		
		List<PilUsua> listAll=pilUsuaRepository.listAll(init, limit, order, filters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount(filters));
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(List<Filter> filters){
				
		return pilUsuaRepository.getCount(filters);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_UPDATE"})
	public String update(PilUsua pilusua){
		
		pilusua=validateChanges(pilUsuaRepository.list(pilusua.getUsuacons()), pilusua);
		return gson.toJson(pilUsuaRepository.update(pilusua));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_DELETE"})
	public void delete(PilUsua pilusua){
		pilUsuaRepository.delete(pilusua);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "PIL_USUA_ALL", "PIL_USUA_CREATE"})
	public String insert(PilUsua pilusua){
		return gson.toJson(pilUsuaRepository.insert(pilusua));
	}
	
	@Override
	public String validateUsua(String user, String password){
		
		PilUsua usuario=pilUsuaRepository.validateUsua(user,password);
		if(usuario!=null)
			return gson.toJson("true");
		else
			return gson.toJson("false");
	}
	
	@Override
	public List<PilUsua> listAllFormregi(List<Long> codigosFormRegi){
		return pilUsuaRepository.listAllFormregi(codigosFormRegi);
	}
	
	@Override
	public String validateUser(String username){
		
		return gson.toJson(autenticateImpl.validateUser(username));
	}
	
	private PilUsua validateChanges(PilUsua pilusuaOld, PilUsua pilusuaNew){ 
		Long transcons=frmTransaccionService.generateTransaction("");
		
		if((pilusuaOld.getUsuaapel()==null && pilusuaNew.getUsuaapel()!=null) || (pilusuaOld.getUsuaapel()!=null && !pilusuaOld.getUsuaapel().equals(pilusuaNew.getUsuaapel())) )
			generateAudit("usuaapel",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuaapel(),pilusuaNew.getUsuaapel(), transcons);
		if((pilusuaOld.getUsuadive()==null && pilusuaNew.getUsuadive()!=null) || (pilusuaOld.getUsuadive()!=null && !pilusuaOld.getUsuadive().equals(pilusuaNew.getUsuadive())) )
			generateAudit("usuadive",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuadive(),pilusuaNew.getUsuadive(), transcons);		
		if((pilusuaOld.getUsuaemai()==null && pilusuaNew.getUsuaemai()!=null) || (pilusuaOld.getUsuaemai()!=null && !pilusuaOld.getUsuaemai().equals(pilusuaNew.getUsuaemai())) )
			generateAudit("usuaemai",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuaemai(),pilusuaNew.getUsuaemai(), transcons);
		if((pilusuaOld.getUsuaesta()==null && pilusuaNew.getUsuaesta()!=null) || (pilusuaOld.getUsuaesta()!=null && !pilusuaOld.getUsuaesta().equals(pilusuaNew.getUsuaesta())) )
			generateAudit("usuaesta",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuaesta(),pilusuaNew.getUsuaesta(), transcons);
		if((pilusuaOld.getUsuanomb()==null && pilusuaNew.getUsuanomb()!=null) || (pilusuaOld.getUsuanomb()!=null && !pilusuaOld.getUsuanomb().equals(pilusuaNew.getUsuanomb())) )
			generateAudit("usuanomb",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuanomb(),pilusuaNew.getUsuanomb(), transcons);
		if((pilusuaOld.getUsuapeco()==null && pilusuaNew.getUsuapeco()!=null) || (pilusuaOld.getUsuapeco()!=null && !pilusuaOld.getUsuapeco().equals(pilusuaNew.getUsuapeco())) )
			generateAudit("usuapeco",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuapeco(),pilusuaNew.getUsuapeco(), transcons);
		if((pilusuaOld.getUsuarazo()==null && pilusuaNew.getUsuarazo()!=null) || (pilusuaOld.getUsuarazo()!=null && !pilusuaOld.getUsuarazo().equals(pilusuaNew.getUsuarazo())) )
			generateAudit("usuarazo",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuarazo(),pilusuaNew.getUsuarazo(), transcons);
		if((pilusuaOld.getUsuasucu()==null && pilusuaNew.getUsuasucu()!=null) || (pilusuaOld.getUsuasucu()!=null && !pilusuaOld.getUsuasucu().equals(pilusuaNew.getUsuasucu())) )
			generateAudit("usuasucu",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuasucu().toString(),pilusuaNew.getUsuasucu().toString(), transcons);
		if((pilusuaOld.getUsuatele()==null && pilusuaNew.getUsuatele()!=null) || (pilusuaOld.getUsuatele()!=null && !pilusuaOld.getUsuatele().equals(pilusuaNew.getUsuatele())) )
			generateAudit("usuatele",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuatele(),pilusuaNew.getUsuatele(), transcons);
		if((pilusuaOld.getUsuatiin()==null && pilusuaNew.getUsuatiin()!=null) || (pilusuaOld.getUsuatiin()!=null && !pilusuaOld.getUsuatiin().equals(pilusuaNew.getUsuatiin())) )
			generateAudit("usuatiin",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuatiin(),pilusuaNew.getUsuatiin(), transcons);
		if((pilusuaOld.getUsuatipo()==null && pilusuaNew.getUsuatipo()!=null) || (pilusuaOld.getUsuatipo()!=null && !pilusuaOld.getUsuatipo().equals(pilusuaNew.getUsuatipo())) )
			generateAudit("usuatipo",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuatipo().toString(),pilusuaNew.getUsuatipo().toString(), transcons);
		if((pilusuaOld.getUsuaunit()==null && pilusuaNew.getUsuaunit()!=null) || (pilusuaOld.getUsuaunit()!=null && !pilusuaOld.getUsuaunit().equals(pilusuaNew.getUsuaunit())) )
			generateAudit("usuaunit",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuaunit(),pilusuaNew.getUsuaunit(), transcons);
		if((pilusuaOld.getUsuausua()==null && pilusuaNew.getUsuausua()!=null) || (pilusuaOld.getUsuausua()!=null && !pilusuaOld.getUsuausua().equals(pilusuaNew.getUsuausua())) )
			generateAudit("usuausua",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuausua(),pilusuaNew.getUsuausua(), transcons);	
		
		if(pilusuaNew.getUsuapass()==null || pilusuaNew.getUsuapass().isEmpty())
			pilusuaNew.setUsuapass(pilusuaOld.getUsuapass());
		else if(!pilusuaOld.getUsuapass().equals(pilusuaNew.getUsuapass()))
			generateAudit("usuapass",pilusuaOld.getUsuacons(),"PilUsua", pilusuaOld.getUsuapass(),pilusuaNew.getUsuapass(), transcons);
		
		return pilusuaNew;
	}
	
	private void generateAudit(String audicamp, Long audicopk, String tabla, String audivaan, String audivanu, Long trancons) {
		pilAuditoriaService.generateAudit(audicamp, audicopk, tabla, audivaan, audivanu, trancons);
	}
}

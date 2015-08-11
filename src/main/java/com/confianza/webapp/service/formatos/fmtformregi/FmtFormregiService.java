package com.confianza.webapp.service.formatos.fmtformregi;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregi;
import com.confianza.webapp.utils.Filter;

public interface FmtFormregiService{
	
	public String list(Long id);
	
	public String listAll(int pageSize, int page);	
	
	public String insert(FmtFormregi fmtformregi);
	
	public String update(FmtFormregi fmtformregi);
	
	public void delete(FmtFormregi fmtformregi);	
	
	public String insertRecordIntermediario(Long vefocons, String user, String paramsData, ArrayList<MultipartFile> file);

	public String updateRecordIntermediario(Long vefocons, Long forecons, String user, String paramsData, ArrayList<MultipartFile> file);

	public FmtFormregi listEntity(Long id);

	public String loadFormRegiIntermediario(Long vefocons, int pageSize, int page, String order, String stringFilters);

	public int getCount(List<Filter> filters);

	public String loadFormRegiAdmin(Long vefocons, int pageSize, int page, String order, String stringFilters);

	public int getCountAdmin(List<Filter> filters);

	public String aprobarRecord(Long forecons);

	public String devolverRecord(Long forecons);

	public String cancelarRecord(Long forecons);
}

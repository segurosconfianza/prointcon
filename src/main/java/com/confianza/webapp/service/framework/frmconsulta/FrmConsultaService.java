package com.confianza.webapp.service.framework.frmconsulta;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;

public interface FrmConsultaService{
	
	public String list(Long id);
		
	public String insert(FrmConsulta frmconsulta);
	
	public String update(FrmConsulta frmconsulta);
	
	public void delete(FrmConsulta frmconsulta);	
	
	public int getCount();

	public FrmConsulta listName(String conscons);

	public FrmConsulta listProcedureChild(String id);

	public Map<String, Object> loadProcedure(FrmConsulta frmConsulta,	List<FrmParametro> parametros, Map<String, Object> parameters, Map<String, Object> parametersData);

	public String loadRecord(String conscons, String params);

	public String updateRecord(String conscons, String params, String paramsData, ArrayList<MultipartFile> file);

	public String listCombo(String conscons);

	public String listComboDynamic(String conscons);

	public String loadData(String conscons);

	public FrmConsulta listChild(String id);

	public String loadConsChield(String conscons);

	public void uploadFiles(String motidesc, ArrayList<MultipartFile> file,	String result);

	public List<Object[]> loadListData(FrmConsulta frmConsulta, Map<String, Object> parameters, List<FrmParametro> parametros);

	public FrmConsulta listId(String id);

	public String loadDataName(String conscons);

	public List<FrmConsulta> listQueryChilds(String conscons);

	public List<FrmConsulta> listProcedureChildren(String id);


}

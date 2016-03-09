package com.confianza.webapp.repository.framework.frmconsulta;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;

public interface FrmConsultaRepository {
	
	public FrmConsulta list(Long id);
	
	public List<FrmConsulta> listAll(int id);	
	
	public FrmConsulta update(FrmConsulta frmconsulta);
	
	public void delete(FrmConsulta frmconsulta);
	
	public FrmConsulta insert(FrmConsulta frmconsulta);
	
	public int getCount();

	public FrmConsulta listName(String id);

	public List<Object[]> loadData(FrmConsulta frmConsulta,	Map<String, Object> parameters);

	public FrmConsulta listProcedureChild(String id);

	public Map<String, Object> loadProcedure(FrmConsulta frmConsulta, List<FrmParametro> parametros, Map<String, Object> parameters, Map<String, Object> parametersData);

	public Map<String, Object> loadProcedureOsiris(FrmConsulta frmConsulta, List<FrmParametro> parametros, Map<String, Object> parameters, Map<String, Object> parametersData);

	public Map<String, Object> getParametersOutput(List<FrmParametro> fp, CallableStatement cst) throws SQLException;

	public CallableStatement putParameterOutput(List<FrmParametro> fp, CallableStatement cst) throws SQLException;

	public CallableStatement putParametersInput(Map<String, Object> p, List<FrmParametro> fp, CallableStatement cst, String tipo) throws SQLException;

	public FrmConsulta listChild(String id);

	public List<Object[]> loadDataOsiris(FrmConsulta frmConsulta, Map<String, Object> parameters, List<FrmParametro> parametros);

	public List<FrmConsulta> listProcedureChildren(String id);
	
}

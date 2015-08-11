package com.confianza.webapp.repository.formatos.fmtauditoria;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregi;
import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.pila.pilusua.PilUsua;
import com.confianza.webapp.service.framework.frmconsulta.FrmConsultaService;
import com.confianza.webapp.utils.Filter;
import com.confianza.webapp.utils.JSONUtil;
import com.confianza.webapp.utils.SqlFunctions;

@Repository
public class FmtAuditoriaRepositoryImpl implements FmtAuditoriaRepository{
	
	@Autowired
	private SessionFactory sessionFactory;  	
	
	@Autowired
	private SqlFunctions sqlFunctions;
	
	@Autowired
	private FrmConsultaService frmConsultaService;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtAuditoria por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtAuditoria = objeto de la case FmtAuditoria que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtAuditoria list(Long id){
		try{
			String sql = "select "+FmtAuditoria.getColumnNames()
					   + "from FMT_AUDITORIA "
					   + "where audicons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtAuditoria.class)					
					     .setParameter("id", id);
			return (FmtAuditoria)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtAuditoria
	 * @return FmtAuditoria = coleccion de objetos de la case FmtAuditoria que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FmtAuditoria> listAll(int init, int limit){
		try{
			String sql = "select "+FmtAuditoria.getColumnNames()
					   + "from FMT_AUDITORIA ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtAuditoria.class);
						 
			if(limit!=0){
				query.setFirstResult(init);			
				query.setMaxResults(limit);
			}
					     
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}	
	
	/**
	 * Metodo de consulta para el conteo de los registros de la tabla FmtAuditoria
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FmtAuditoria ";
						
			Query query = getSession().createQuery(sql);
	        
			Iterator it = query.list().iterator();
	        Long ret = new Long(0);
	        
	        if (it != null)
		        if (it.hasNext()){
		        	ret = (Long) it.next();
		        }
	        
			return ret.intValue();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Metodo para actualizar los datos de un registro de la tabla FmtAuditoria por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtAuditoria = objeto de la case FmtAuditoria que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtAuditoria update(FmtAuditoria fmtauditoria){
		getSession().update(fmtauditoria);
		return fmtauditoria;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FmtAuditoria por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtAuditoria = objeto de la case FmtAuditoria que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FmtAuditoria fmtauditoria){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FmtAuditoria
	 * @value audicons
	 * @value auditran
	 * @value auditabl
	 * @value audicopk
	 * @value audicamp
	 * @value audivaan
	 * @value audivanu
	 * @return FmtAuditoria = objeto de la case FmtAuditoria que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtAuditoria insert(FmtAuditoria fmtauditoria){
		getSession().save(fmtauditoria);	
		return fmtauditoria;
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtAuditoria
	 * @throws Exception
	 */
	@Override
	@Transactional
	public Map<String, Object> listAllFrmFormregi(int init, int limit, String order, List<Filter> filters, long forecons){
		try{
			FrmConsulta consulta=frmConsultaService.listName("FMT_AUDITORIA_FMT_FORMREGI");
			
			String sql = consulta.getConslsql();
						
			sql = sqlFunctions.completeSQL(order, filters, sql, consulta.getConscolu());
			
			Query query = getSession().createSQLQuery(sql);
			
			query.setParameter("forecons", forecons);
			query=sqlFunctions.setParameters(filters, query);
			
			if(limit!=0){
				query.setFirstResult(init);			
				query.setMaxResults(limit);
			}
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("data", JSONUtil.toNameList(consulta.getConscolu().split(","),query.list()));
			
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}

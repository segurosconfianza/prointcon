package com.confianza.webapp.repository.framework.frmconsulta;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;

@Repository
public class FrmConsultaRepositoryImpl implements FrmConsultaRepository{
	
	@Autowired
	private SessionFactory sessionFactory;  	
	
	@Autowired
	private SessionFactory sessionFactoryOsiris;
	
	public SessionFactory getSessionFactoryOsiris() {
		return sessionFactoryOsiris;
	}

	public void setSessionFactoryOsiris(SessionFactory sessionFactoryOsiris) {
		this.sessionFactoryOsiris = sessionFactoryOsiris;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Session getSessionOsiris() {
		return sessionFactoryOsiris.getCurrentSession();
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmConsulta por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmConsulta list(Long id){
		try{
			String sql = "select "+FrmConsulta.getColumnNames()
					   + "from FrmConsulta "
					   + "where conscons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmConsulta.class)					
					     .setParameter("id", id);
			return (FrmConsulta)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmConsulta por el nombre de la consulta
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmConsulta listName(String id){
		try{
			String sql = "select "+FrmConsulta.getColumnNames()
					   + "from Frm_Consulta "
					   + "where conscons = :id ";
						
			Query query = getSession().createSQLQuery(sql)	
						 .addEntity(FrmConsulta.class)	
					     .setParameter("id", id);
			return (FrmConsulta) query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para ejecutar la consulta guardad en el sql
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<Object[]> loadData(FrmConsulta frmConsulta,Map<String, Object> parameters){
		try{
			Query query =  getSession().createSQLQuery(frmConsulta.getConslsql());
					
			Iterator it = parameters.entrySet().iterator();			
		    while (it.hasNext()) {
		        Map.Entry e = (Map.Entry)it.next();
		        query.setParameter(e.getKey().toString(), e.getValue());
		   }
			
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para ejecutar la consulta guardad en el sql
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional("transactionManagerOsiris")
	public List<Object[]> loadDataOsiris(FrmConsulta frmConsulta,Map<String, Object> parameters){
		try{
			Query query = getSessionOsiris().createSQLQuery(frmConsulta.getConslsql());
			
			
			Iterator it = parameters.entrySet().iterator();			
		    while (it.hasNext()) {
		        Map.Entry e = (Map.Entry)it.next();
		        query.setParameter(e.getKey().toString(), e.getValue());
		   }
			
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmConsulta
	 * @return FrmConsulta = coleccion de objetos de la case FrmConsulta que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FrmConsulta> listAll(int init, int limit){
		try{
			String sql = "select "+FrmConsulta.getColumnNames()
					   + "from FrmConsulta ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmConsulta.class);
						 
			if(init==0 && limit!=0){
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
	 * Metodo de consulta para el conteo de los registros de la tabla FrmConsulta
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FrmConsulta ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FrmConsulta por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmConsulta update(FrmConsulta frmconsulta){
		getSession().update(frmconsulta);
		return frmconsulta;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FrmConsulta por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FrmConsulta frmconsulta){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FrmConsulta
	 * @value conscons
	 * @value consmodu
	 * @value consnomb
	 * @value consdesc
	 * @value conscaco
	 * @value constipo
	 * @value conslsql
	 * @value conscolu
	 * @value constico
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmConsulta insert(FrmConsulta frmconsulta){
		getSession().save(frmconsulta);	
		return frmconsulta;
	}
}

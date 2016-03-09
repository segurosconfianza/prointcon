package com.confianza.webapp.repository.cierre.cieestaproc;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		cierre  
  */                          

import java.util.List;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.confianza.webapp.utils.SqlFunctions;
import com.confianza.webapp.utils.Filter;

@Repository
public class CieEstaprocRepositoryImpl implements CieEstaprocRepository{
	
	@Autowired
	private SessionFactory sessionFactory;  	
	
	@Autowired
	private SqlFunctions sqlFunctions;
	
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
	 * Metodo de consulta para los registros de la tabla CieEstaproc por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return CieEstaproc = objeto de la case CieEstaproc que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public CieEstaproc list(Long id){
		try{
			String sql = "select "+CieEstaproc.getColumnNames()
					   + "from CIE_ESTAPROC "
					   + "where esprcons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(CieEstaproc.class)					
					     .setParameter("id", id);
			return (CieEstaproc)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla CieEstaproc
	 * @return CieEstaproc = coleccion de objetos de la case CieEstaproc que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<CieEstaproc> listAll(int init, int limit, String order, List<Filter> filters){
		try{
			String sql = "select "+CieEstaproc.getColumnNames()
					   + "from CIE_ESTAPROC ";
				
			sql = sqlFunctions.completeSQL(order, filters, sql, CieEstaproc.getColumnNames());
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(CieEstaproc.class);
				
			query=sqlFunctions.setParameters(filters, query);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla CieEstaproc
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(List<Filter> filters){
		try{
			String sql = "select count(*) "
					   + "from CieEstaproc ";
				
			sql = sqlFunctions.completeSQL(null, filters, sql, CieEstaproc.getColumnNames());
						
			Query query = getSession().createQuery(sql);
	        
	        query=sqlFunctions.setParameters(filters, query);
	        
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
	 * Metodo para actualizar los datos de un registro de la tabla CieEstaproc por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return CieEstaproc = objeto de la case CieEstaproc que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public CieEstaproc update(CieEstaproc cieestaproc){
		getSession().update(cieestaproc);
		return cieestaproc;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla CieEstaproc por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return CieEstaproc = objeto de la case CieEstaproc que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(CieEstaproc cieestaproc){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla CieEstaproc
	 * @value esprcons
	 * @value esprnomb
	 * @value esprporc
	 * @value esprdesc
	 * @value espreror
	 * @value espruser
	 * @value espresta
	 * @value esprduho
	 * @value esprfein
	 * @value esprfefi
	 * @return CieEstaproc = objeto de la case CieEstaproc que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public CieEstaproc insert(CieEstaproc cieestaproc){
		getSession().save(cieestaproc);	
		return cieestaproc;
	}
}

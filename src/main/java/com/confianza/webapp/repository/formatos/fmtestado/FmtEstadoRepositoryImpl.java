package com.confianza.webapp.repository.formatos.fmtestado;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.util.List;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.confianza.webapp.utils.Filter;
import com.confianza.webapp.utils.SqlFunctions;

@Repository
public class FmtEstadoRepositoryImpl implements FmtEstadoRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FmtEstado por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtEstado = objeto de la case FmtEstado que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtEstado list(Long id){
		try{
			String sql = "select "+FmtEstado.getColumnNames()
					   + "from FMT_ESTADO "
					   + "where estacons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtEstado.class)					
					     .setParameter("id", id);
			return (FmtEstado)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtEstado
	 * @return FmtEstado = coleccion de objetos de la case FmtEstado que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FmtEstado> listAll(int init, int limit){
		try{
			String sql = "select "+FmtEstado.getColumnNames()
					   + "from FMT_ESTADO ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtEstado.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla FmtEstado
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(List<Filter> filters){
		try{
			String sql = "select count(*) "
					   + "from FmtEstado ";
			
			sql = sqlFunctions.completeSQL(null, filters, sql, FmtEstado.getColumnNames());
			
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
	 * Metodo para actualizar los datos de un registro de la tabla FmtEstado por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtEstado = objeto de la case FmtEstado que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtEstado update(FmtEstado fmtestado){
		getSession().update(fmtestado);
		return fmtestado;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FmtEstado por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtEstado = objeto de la case FmtEstado que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FmtEstado fmtestado){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FmtEstado
	 * @value estacons
	 * @value estafore
	 * @value estafech
	 * @value estauser
	 * @value estaesta
	 * @return FmtEstado = objeto de la case FmtEstado que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtEstado insert(FmtEstado fmtestado){
		getSession().save(fmtestado);	
		return fmtestado;
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtEstado
	 * @return FmtEstado = coleccion de objetos de la case FmtEstado que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FmtEstado> listAll(int init, int limit, String order, List<Filter> filters){
		try{
			String sql = "select "+FmtEstado.getColumnNames()
					   + "from FMT_ESTADO ";
				
			sql = sqlFunctions.completeSQL(order, filters, sql, FmtEstado.getColumnNames());
			
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtEstado.class);
				
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
}

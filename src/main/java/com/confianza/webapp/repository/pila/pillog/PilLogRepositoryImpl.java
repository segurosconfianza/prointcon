package com.confianza.webapp.repository.pila.pillog;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.util.List;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PilLogRepositoryImpl implements PilLogRepository{
	
	@Autowired
	private SessionFactory sessionFactory;  	
	
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
	 * Metodo de consulta para los registros de la tabla PilLog por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilLog = objeto de la case PilLog que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilLog list(Long id){
		try{
			String sql = "select "+PilLog.getColumnNames()
					   + "from PIL_LOG "
					   + "where slogcons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(PilLog.class)					
					     .setParameter("id", id);
			return (PilLog)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla PilLog
	 * @return PilLog = coleccion de objetos de la case PilLog que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<PilLog> listAll(int init, int limit){
		try{
			String sql = "select "+PilLog.getColumnNames()
					   + "from PIL_LOG ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(PilLog.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla PilLog
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from PilLog ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla PilLog por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilLog = objeto de la case PilLog que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilLog update(PilLog pillog){
		getSession().update(pillog);
		return pillog;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla PilLog por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilLog = objeto de la case PilLog que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(PilLog pillog){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla PilLog
	 * @value slogcons
	 * @value slogtran
	 * @value slogtabl
	 * @value slogacci
	 * @value slogregi
	 * @return PilLog = objeto de la case PilLog que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilLog insert(PilLog pillog){
		getSession().save(pillog);	
		return pillog;
	}
}

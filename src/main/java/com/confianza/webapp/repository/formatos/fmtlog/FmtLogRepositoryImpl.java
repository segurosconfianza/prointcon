package com.confianza.webapp.repository.formatos.fmtlog;

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

@Repository
public class FmtLogRepositoryImpl implements FmtLogRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FmtLog por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtLog = objeto de la case FmtLog que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtLog list(Long id){
		try{
			String sql = "select "+FmtLog.getColumnNames()
					   + "from FMT_LOG "
					   + "where slogcons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtLog.class)					
					     .setParameter("id", id);
			return (FmtLog)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtLog
	 * @return FmtLog = coleccion de objetos de la case FmtLog que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FmtLog> listAll(int init, int limit){
		try{
			String sql = "select "+FmtLog.getColumnNames()
					   + "from FMT_LOG ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtLog.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla FmtLog
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FmtLog ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FmtLog por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtLog = objeto de la case FmtLog que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtLog update(FmtLog fmtlog){
		getSession().update(fmtlog);
		return fmtlog;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FmtLog por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtLog = objeto de la case FmtLog que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FmtLog fmtlog){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FmtLog
	 * @value slogcons
	 * @value slogtran
	 * @value slogtabl
	 * @value slogacci
	 * @value slogregi
	 * @return FmtLog = objeto de la case FmtLog que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtLog insert(FmtLog fmtlog){
		getSession().save(fmtlog);	
		return fmtlog;
	}
}

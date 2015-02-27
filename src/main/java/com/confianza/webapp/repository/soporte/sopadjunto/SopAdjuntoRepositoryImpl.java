package com.confianza.webapp.repository.soporte.sopadjunto;

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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SopAdjuntoRepositoryImpl implements SopAdjuntoRepository{
	
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
	 * Metodo de consulta para los registros de la tabla SopAdjunto por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return SopAdjunto = objeto de la case SopAdjunto que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public SopAdjunto list(Long id){
		try{
			String sql = "select "+SopAdjunto.getColumnNames()
					   + "from SopAdjunto "
					   + "where adjucodi = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(SopAdjunto.class)					
					     .setParameter("id", id);
			return (SopAdjunto)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla SopAdjunto
	 * @return SopAdjunto = coleccion de objetos de la case SopAdjunto que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<SopAdjunto> listAll(int init, int limit){
		try{
			String sql = "select "+SopAdjunto.getColumnNames()
					   + "from SopAdjunto ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(SopAdjunto.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla SopAdjunto
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from SopAdjunto ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla SopAdjunto por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return SopAdjunto = objeto de la case SopAdjunto que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public SopAdjunto update(SopAdjunto sopadjunto){
		getSession().update(sopadjunto);
		return sopadjunto;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla SopAdjunto por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return SopAdjunto = objeto de la case SopAdjunto que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(SopAdjunto sopadjunto){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla SopAdjunto
	 * @value adjucodi
	 * @value adjuarch
	 * @value adjunomb
	 * @value adjuuser
	 * @value adjufech
	 * @value adjuesta
	 * @value adjumoti
	 * @return SopAdjunto = objeto de la case SopAdjunto que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public SopAdjunto insert(SopAdjunto sopadjunto){
		getSession().save(sopadjunto);	
		return sopadjunto;
	}
}

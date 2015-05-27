package com.confianza.webapp.repository.formatos.fmtadjunto;

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
public class FmtAdjuntoRepositoryImpl implements FmtAdjuntoRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FmtAdjunto por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtAdjunto = objeto de la case FmtAdjunto que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtAdjunto list(Long id){
		try{
			String sql = "select "+FmtAdjunto.getColumnNames()
					   + "from FMT_ADJUNTO "
					   + "where adjucons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtAdjunto.class)					
					     .setParameter("id", id);
			return (FmtAdjunto)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtAdjunto
	 * @return FmtAdjunto = coleccion de objetos de la case FmtAdjunto que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FmtAdjunto> listAll(int init, int limit){
		try{
			String sql = "select "+FmtAdjunto.getColumnNames()
					   + "from FMT_ADJUNTO ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtAdjunto.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla FmtAdjunto
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FmtAdjunto ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FmtAdjunto por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtAdjunto = objeto de la case FmtAdjunto que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtAdjunto update(FmtAdjunto fmtadjunto){
		getSession().update(fmtadjunto);
		return fmtadjunto;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FmtAdjunto por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtAdjunto = objeto de la case FmtAdjunto que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FmtAdjunto fmtadjunto){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FmtAdjunto
	 * @value adjucons
	 * @value adjuarch
	 * @value adjufore
	 * @value adjunomb
	 * @value adjuuser
	 * @value adjufech
	 * @value adjuesta
	 * @return FmtAdjunto = objeto de la case FmtAdjunto que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtAdjunto insert(FmtAdjunto fmtadjunto){
		getSession().save(fmtadjunto);	
		return fmtadjunto;
	}
}

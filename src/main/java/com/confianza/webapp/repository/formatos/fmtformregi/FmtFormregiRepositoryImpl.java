package com.confianza.webapp.repository.formatos.fmtformregi;

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
public class FmtFormregiRepositoryImpl implements FmtFormregiRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FmtFormregi por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtFormregi = objeto de la case FmtFormregi que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtFormregi list(Long id){
		try{
			String sql = "select "+FmtFormregi.getColumnNames()
					   + "from FMT_FORMREGI "
					   + "where forecons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtFormregi.class)					
					     .setParameter("id", id);
			return (FmtFormregi)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtFormregi
	 * @return FmtFormregi = coleccion de objetos de la case FmtFormregi que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FmtFormregi> listAll(int init, int limit){
		try{
			String sql = "select "+FmtFormregi.getColumnNames()
					   + "from FMT_FORMREGI ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtFormregi.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla FmtFormregi
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FmtFormregi ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FmtFormregi por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtFormregi = objeto de la case FmtFormregi que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtFormregi update(FmtFormregi fmtformregi){
		getSession().update(fmtformregi);
		return fmtformregi;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FmtFormregi por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtFormregi = objeto de la case FmtFormregi que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FmtFormregi fmtformregi){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FmtFormregi
	 * @value forecons
	 * @value forevefo
	 * @value forefech
	 * @value foreuser
	 * @value foreesta
	 * @return FmtFormregi = objeto de la case FmtFormregi que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtFormregi insert(FmtFormregi fmtformregi){
		getSession().save(fmtformregi);	
		return fmtformregi;
	}
}

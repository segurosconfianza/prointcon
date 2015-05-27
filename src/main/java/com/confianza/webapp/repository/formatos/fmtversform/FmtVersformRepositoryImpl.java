package com.confianza.webapp.repository.formatos.fmtversform;

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
public class FmtVersformRepositoryImpl implements FmtVersformRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FmtVersform por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtVersform = objeto de la case FmtVersform que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtVersform list(Long id){
		try{
			String sql = "select "+FmtVersform.getColumnNames()
					   + "from FMT_VERSFORM "
					   + "where vefocons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtVersform.class)					
					     .setParameter("id", id);
			return (FmtVersform)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtVersform
	 * @return FmtVersform = coleccion de objetos de la case FmtVersform que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FmtVersform> listAll(int init, int limit){
		try{
			String sql = "select "+FmtVersform.getColumnNames()
					   + "from FMT_VERSFORM ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtVersform.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla FmtVersform
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FmtVersform ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FmtVersform por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtVersform = objeto de la case FmtVersform que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtVersform update(FmtVersform fmtversform){
		getSession().update(fmtversform);
		return fmtversform;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FmtVersform por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtVersform = objeto de la case FmtVersform que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FmtVersform fmtversform){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FmtVersform
	 * @value vefocons
	 * @value vefoform
	 * @value vefonomb
	 * @value vefofein
	 * @value vefofefi
	 * @value vefoesta
	 * @return FmtVersform = objeto de la case FmtVersform que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtVersform insert(FmtVersform fmtversform){
		getSession().save(fmtversform);	
		return fmtversform;
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtVersform por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtVersform = objeto de la case FmtVersform que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtVersform lastVersion(Long id){
		try{
			String sql = "select "+FmtVersform.getColumnNames()
					   + "from FMT_VERSFORM "
					   + "where vefoform = :id and vefoesta='A' ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtVersform.class)					
					     .setParameter("id", id);
			return (FmtVersform)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}

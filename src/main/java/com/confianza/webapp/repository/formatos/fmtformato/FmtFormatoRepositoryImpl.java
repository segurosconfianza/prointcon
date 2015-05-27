package com.confianza.webapp.repository.formatos.fmtformato;

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
public class FmtFormatoRepositoryImpl implements FmtFormatoRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FmtFormato por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtFormato = objeto de la case FmtFormato que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtFormato list(Long id){
		try{
			String sql = "select "+FmtFormato.getColumnNames()
					   + "from FMT_FORMATO "
					   + "where formcons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtFormato.class)					
					     .setParameter("id", id);
			return (FmtFormato)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtFormato
	 * @return FmtFormato = coleccion de objetos de la case FmtFormato que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FmtFormato> listAll(int init, int limit){
		try{
			String sql = "select "+FmtFormato.getColumnNames()
					   + "from FMT_FORMATO ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtFormato.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla FmtFormato
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FmtFormato ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FmtFormato por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtFormato = objeto de la case FmtFormato que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtFormato update(FmtFormato fmtformato){
		getSession().update(fmtformato);
		return fmtformato;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FmtFormato por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtFormato = objeto de la case FmtFormato que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FmtFormato fmtformato){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FmtFormato
	 * @value formcons
	 * @value formnomb
	 * @value formdesc
	 * @value formesta
	 * @return FmtFormato = objeto de la case FmtFormato que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtFormato insert(FmtFormato fmtformato){
		getSession().save(fmtformato);	
		return fmtformato;
	}
}

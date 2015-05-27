package com.confianza.webapp.repository.pila.pilmotiform;

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
public class PilMotiformRepositoryImpl implements PilMotiformRepository{
	
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
	 * Metodo de consulta para los registros de la tabla PilMotiform por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilMotiform = objeto de la case PilMotiform que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilMotiform list(Long id){
		try{
			String sql = "select "+PilMotiform.getColumnNames()
					   + "from PIL_MOTIFORM "
					   + "where mofocons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(PilMotiform.class)					
					     .setParameter("id", id);
			return (PilMotiform)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla PilMotiform
	 * @return PilMotiform = coleccion de objetos de la case PilMotiform que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<PilMotiform> listAll(int init, int limit){
		try{
			String sql = "select "+PilMotiform.getColumnNames()
					   + "from PIL_MOTIFORM ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(PilMotiform.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla PilMotiform
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from PilMotiform ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla PilMotiform por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilMotiform = objeto de la case PilMotiform que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilMotiform update(PilMotiform pilmotiform){
		getSession().update(pilmotiform);
		return pilmotiform;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla PilMotiform por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilMotiform = objeto de la case PilMotiform que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(PilMotiform pilmotiform){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla PilMotiform
	 * @value mofocons
	 * @value mofofore
	 * @value mofodevo
	 * @value mofodesc
	 * @value mofofech
	 * @value mofouser
	 * @return PilMotiform = objeto de la case PilMotiform que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilMotiform insert(PilMotiform pilmotiform){
		getSession().save(pilmotiform);	
		return pilmotiform;
	}
}

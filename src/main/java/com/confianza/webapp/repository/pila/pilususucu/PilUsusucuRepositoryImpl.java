package com.confianza.webapp.repository.pila.pilususucu;

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
public class PilUsusucuRepositoryImpl implements PilUsusucuRepository{
	
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
	 * Metodo de consulta para los registros de la tabla PilUsusucu por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilUsusucu = objeto de la case PilUsusucu que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilUsusucu list(Long id){
		try{
			String sql = "select "+PilUsusucu.getColumnNames()
					   + "from PIL_USUSUCU "
					   + "where ussucons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(PilUsusucu.class)					
					     .setParameter("id", id);
			return (PilUsusucu)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla PilUsusucu
	 * @return PilUsusucu = coleccion de objetos de la case PilUsusucu que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<PilUsusucu> listAll(int init, int limit){
		try{
			String sql = "select "+PilUsusucu.getColumnNames()
					   + "from PIL_USUSUCU ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(PilUsusucu.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla PilUsusucu
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from PilUsusucu ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla PilUsusucu por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilUsusucu = objeto de la case PilUsusucu que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilUsusucu update(PilUsusucu pilususucu){
		getSession().update(pilususucu);
		return pilususucu;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla PilUsusucu por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilUsusucu = objeto de la case PilUsusucu que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(PilUsusucu pilususucu){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla PilUsusucu
	 * @value ussucons
	 * @value ussuusua
	 * @value ussusucu
	 * @value ussuesta
	 * @return PilUsusucu = objeto de la case PilUsusucu que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilUsusucu insert(PilUsusucu pilususucu){
		getSession().save(pilususucu);	
		return pilususucu;
	}
}

package com.confianza.webapp.repository.pila.pilmotivo;

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
public class PilMotivoRepositoryImpl implements PilMotivoRepository{
	
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
	 * Metodo de consulta para los registros de la tabla PilMotivo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilMotivo = objeto de la case PilMotivo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilMotivo list(Long id){
		try{
			String sql = "select "+PilMotivo.getColumnNames()
					   + "from PIL_MOTIVO "
					   + "where devocons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(PilMotivo.class)					
					     .setParameter("id", id);
			return (PilMotivo)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla PilMotivo
	 * @return PilMotivo = coleccion de objetos de la case PilMotivo que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<PilMotivo> listAll(int init, int limit){
		try{
			String sql = "select "+PilMotivo.getColumnNames()
					   + "from PIL_MOTIVO order by devocons ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(PilMotivo.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla PilMotivo
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from PilMotivo ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla PilMotivo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilMotivo = objeto de la case PilMotivo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilMotivo update(PilMotivo pilmotivo){
		getSession().update(pilmotivo);
		return pilmotivo;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla PilMotivo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilMotivo = objeto de la case PilMotivo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(PilMotivo pilmotivo){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla PilMotivo
	 * @value devocons
	 * @value devonomb
	 * @value devodesc
	 * @return PilMotivo = objeto de la case PilMotivo que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilMotivo insert(PilMotivo pilmotivo){
		getSession().save(pilmotivo);	
		return pilmotivo;
	}
}

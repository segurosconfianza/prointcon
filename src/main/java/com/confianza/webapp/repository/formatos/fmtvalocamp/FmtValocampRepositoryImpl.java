package com.confianza.webapp.repository.formatos.fmtvalocamp;

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

import com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregi;

@Repository
public class FmtValocampRepositoryImpl implements FmtValocampRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FmtValocamp por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtValocamp = objeto de la case FmtValocamp que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtValocamp list(Long id){
		try{
			String sql = "select "+FmtValocamp.getColumnNames()
					   + "from FMT_VALOCAMP "
					   + "where vacacons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtValocamp.class)					
					     .setParameter("id", id);
			return (FmtValocamp)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtValocamp
	 * @return FmtValocamp = coleccion de objetos de la case FmtValocamp que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FmtValocamp> listAll(int init, int limit){
		try{
			String sql = "select "+FmtValocamp.getColumnNames()
					   + "from FMT_VALOCAMP ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtValocamp.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla FmtValocamp
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FmtValocamp ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FmtValocamp por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtValocamp = objeto de la case FmtValocamp que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtValocamp update(FmtValocamp fmtvalocamp){
		getSession().update(fmtvalocamp);
		return fmtvalocamp;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FmtValocamp por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtValocamp = objeto de la case FmtValocamp que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FmtValocamp fmtvalocamp){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FmtValocamp
	 * @value vacacons
	 * @value vacacamp
	 * @value vacafore
	 * @value vacavalo
	 * @return FmtValocamp = objeto de la case FmtValocamp que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtValocamp insert(FmtValocamp fmtvalocamp){
		getSession().save(fmtvalocamp);	
		return fmtvalocamp;
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtValocamp
	 * @return FmtValocamp = coleccion de objetos de la case FmtValocamp que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FmtValocamp> listAll(int init, int limit, Long vefocons, String user){ 
		try{
			String sql = "select "+FmtValocamp.getColumnNames()
					   + "from FMT_VALOCAMP "
					   + "join FMT_CAMPO ON (CAMPCONS = VACACAMP AND CAMPVEFO = :vefocons) "
					   + "join FMT_FORMREGI ON (FORECONS = VACAFORE AND FOREVEFO = :vefocons AND FOREUSER = :user) "
					   + "order by vacafore,camporde ";
						
			Query query = getSession().createSQLQuery(sql)
					     .addEntity(FmtValocamp.class)
						 .setParameter("vefocons", vefocons)
						 .setParameter("user", user);
						 
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
	 * Metodo de consulta para los registros de la tabla FmtValocamp
	 * @return FmtValocamp = coleccion de objetos de la case FmtValocamp que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FmtValocamp> listAll(Long vacafore){ 
		try{
			String sql = "select "+FmtValocamp.getColumnNames()
					   + "from FMT_VALOCAMP "
					   + "where  vacafore = :vacafore";
						
			Query query = getSession().createSQLQuery(sql)
					     .addEntity(FmtValocamp.class)
						 .setParameter("vacafore", vacafore);						 			
					     
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}

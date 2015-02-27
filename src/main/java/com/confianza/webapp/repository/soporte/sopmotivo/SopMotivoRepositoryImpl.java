package com.confianza.webapp.repository.soporte.sopmotivo;

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
public class SopMotivoRepositoryImpl implements SopMotivoRepository{
	
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
	 * Metodo de consulta para los registros de la tabla SopMotivo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return SopMotivo = objeto de la case SopMotivo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public SopMotivo list(Long id){
		try{
			String sql = "select "+SopMotivo.getColumnNames()
					   + "from SopMotivo "
					   + "where moticons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(SopMotivo.class)					
					     .setParameter("id", id);
			return (SopMotivo)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla SopMotivo
	 * @return SopMotivo = coleccion de objetos de la case SopMotivo que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<SopMotivo> listAll(int init, int limit){
		try{
			String sql = "select "+SopMotivo.getColumnNames()
					   + "from SopMotivo ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(SopMotivo.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla SopMotivo
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from SopMotivo ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla SopMotivo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return SopMotivo = objeto de la case SopMotivo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public SopMotivo update(SopMotivo sopmotivo){
		getSession().update(sopmotivo);
		return sopmotivo;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla SopMotivo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return SopMotivo = objeto de la case SopMotivo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(SopMotivo sopmotivo){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla SopMotivo
	 * @value moticons
	 * @value motitran
	 * @value motidesc
	 * @return SopMotivo = objeto de la case SopMotivo que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public SopMotivo insert(SopMotivo sopmotivo){
		getSession().save(sopmotivo);	
		return sopmotivo;
	}
}

package com.confianza.webapp.repository.framework.frmtransaccion;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FrmTransaccionRepositoryImpl implements FrmTransaccionRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FrmTransaccion por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmTransaccion = objeto de la case FrmTransaccion que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmTransaccion list(Long id){
		try{
			String sql = "select trancons ,transesi ,tranfecr "
					   + "from FrmTransaccion "
					   + "where trancons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmTransaccion.class)					
					     .setParameter("id", id);
			return (FrmTransaccion)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmTransaccion
	 * @return FrmTransaccion = coleccion de objetos de la case FrmTransaccion que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FrmTransaccion> listAll(int init, int limit){
		try{
			String sql = "select trancons ,transesi ,tranfecr "
					   + "from FrmTransaccion ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmTransaccion.class);
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
	 * Metodo de consulta para el conteo de los registros de la tabla FrmPerfil
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FrmTransaccion ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FrmTransaccion por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmTransaccion = objeto de la case FrmTransaccion que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmTransaccion update(Long id){
		FrmTransaccion frmtransaccion = this.list(id);
		getSession().update(frmtransaccion);
		return frmtransaccion;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FrmTransaccion por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmTransaccion = objeto de la case FrmTransaccion que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(Long id){
		FrmTransaccion frmtransaccion = this.list(id);
		//FrmTransaccion.setEstado="B";
		getSession().update(frmtransaccion);
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FrmTransaccion
	 * @value trancons
	 * @value transesi
	 * @value tranfecr
	 * @return FrmTransaccion = objeto de la case FrmTransaccion que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmTransaccion insert(FrmTransaccion frmtransaccion){
		getSession().save(frmtransaccion);
		return frmtransaccion;
	}
}

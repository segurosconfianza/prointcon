package com.confianza.webapp.repository.framework.frmlog;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FrmLogRepositoryImpl implements FrmLogRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FrmLog por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmLog = objeto de la case FrmLog que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmLog list(Long id){
		try{
			String sql = "select slogcons ,slogtran ,slogtabl ,slogacci ,slogregi "
					   + "from FrmLog "
					   + "where slogcons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmLog.class)					
					     .setParameter("id", id);
			return (FrmLog)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmLog
	 * @return FrmLog = coleccion de objetos de la case FrmLog que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FrmLog> listAll(){
		try{
			String sql = "select slogcons ,slogtran ,slogtabl ,slogacci ,slogregi "
					   + "from FrmLog ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmLog.class);
					     
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}	
	
	/**
	 * Metodo para actualizar los datos de un registro de la tabla FrmLog por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmLog = objeto de la case FrmLog que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmLog update(Long id){
		FrmLog frmlog = this.list(id);
		getSession().update(frmlog);
		return frmlog;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FrmLog por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmLog = objeto de la case FrmLog que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(Long id){
		FrmLog frmlog = this.list(id);
		//FrmLog.setEstado="B";
		getSession().update(frmlog);
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FrmLog
	 * @value slogcons
	 * @value slogtran
	 * @value slogtabl
	 * @value slogacci
	 * @value slogregi
	 * @return FrmLog = objeto de la case FrmLog que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmLog insert(FrmLog frmlog){
		getSession().save(frmlog);
		return frmlog;
	}
}

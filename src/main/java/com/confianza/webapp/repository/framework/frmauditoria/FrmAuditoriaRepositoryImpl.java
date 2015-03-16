package com.confianza.webapp.repository.framework.frmauditoria;

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
public class FrmAuditoriaRepositoryImpl implements FrmAuditoriaRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FrmAuditoria por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmAuditoria = objeto de la case FrmAuditoria que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmAuditoria list(Long id){
		try{
			String sql = "select audicons ,auditran ,auditabl ,audicopk ,audicamp ,audivaan ,audivanu "
					   + "from FrmAuditoria "
					   + "where audicons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmAuditoria.class)					
					     .setParameter("id", id);
			return (FrmAuditoria)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmAuditoria
	 * @return FrmAuditoria = coleccion de objetos de la case FrmAuditoria que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FrmAuditoria> listAll(){
		try{
			String sql = "select audicons ,auditran ,auditabl ,audicopk ,audicamp ,audivaan ,audivanu "
					   + "from FrmAuditoria ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmAuditoria.class);
					     
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}	
	
	/**
	 * Metodo para actualizar los datos de un registro de la tabla FrmAuditoria por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmAuditoria = objeto de la case FrmAuditoria que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmAuditoria update(FrmAuditoria frmauditoria){
		getSession().update(frmauditoria);
		return frmauditoria;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FrmAuditoria por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmAuditoria = objeto de la case FrmAuditoria que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(Long id){
		FrmAuditoria frmauditoria = this.list(id);
		//FrmAuditoria.setEstado="B";
		getSession().update(frmauditoria);
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FrmAuditoria
	 * @value audicons
	 * @value auditran
	 * @value auditabl
	 * @value audicopk
	 * @value audicamp
	 * @value audivaan
	 * @value audivanu
	 * @return FrmAuditoria = objeto de la case FrmAuditoria que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmAuditoria insert(FrmAuditoria frmauditoria){
		getSession().save(frmauditoria);
		return frmauditoria;
	}
}

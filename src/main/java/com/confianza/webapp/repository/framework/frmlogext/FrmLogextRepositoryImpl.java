package com.confianza.webapp.repository.framework.frmlogext;

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
public class FrmLogextRepositoryImpl implements FrmLogextRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FrmLogext por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmLogext = objeto de la case FrmLogext que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmLogext list(Long id){
		try{
			String sql = "select "+FrmLogext.getColumnNames()
					   + "from FrmLogext "
					   + "where loexcons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmLogext.class)					
					     .setParameter("id", id);
			return (FrmLogext)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmLogext
	 * @return FrmLogext = coleccion de objetos de la case FrmLogext que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FrmLogext> listAll(int init, int limit){
		try{
			String sql = "select "+FrmLogext.getColumnNames()
					   + "from FrmLogext ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmLogext.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla FrmLogext
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FrmLogext ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FrmLogext por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmLogext = objeto de la case FrmLogext que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmLogext update(FrmLogext frmlogext){
		getSession().update(frmlogext);
		return frmlogext;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FrmLogext por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmLogext = objeto de la case FrmLogext que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FrmLogext frmlogext){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FrmLogext
	 * @value loexcons
	 * @value loextabl
	 * @value loexacci
	 * @value loexdaco
	 * @value loexdata
	 * @value loexfecr
	 * @return FrmLogext = objeto de la case FrmLogext que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmLogext insert(FrmLogext frmlogext){
		getSession().save(frmlogext);	
		return frmlogext;
	}
}

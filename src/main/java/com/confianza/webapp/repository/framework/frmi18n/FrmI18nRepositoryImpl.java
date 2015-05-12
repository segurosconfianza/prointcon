package com.confianza.webapp.repository.framework.frmi18n;

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
public class FrmI18nRepositoryImpl implements FrmI18nRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FrmI18n por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmI18n = objeto de la case FrmI18n que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmI18n list(Long id){
		try{
			String sql = "select etincons ,etinmodu ,etincamp ,etinetiq "
					   + "from FrmI18n "
					   + "where etincons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmI18n.class)					
					     .setParameter("id", id);
			return (FrmI18n)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmI18n por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmI18n = objeto de la case FrmI18n que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<Object[]> listModulo(String[] modulos){
		try{
			String sql = "select etincons ,etinmodu ,etincamp ,etinetiq, modunomb "
					   + "from Frm_I18n "
					   + "join Frm_Modulo on (moducons=etinmodu and modunomb in (:modulos)) "
					   + "order by etincons";
						
			Query query = getSession().createSQLQuery(sql)						 		
					     .setParameterList("modulos", modulos);			
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmI18n
	 * @return FrmI18n = coleccion de objetos de la case FrmI18n que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FrmI18n> listAll(int init, int limit){
		try{
			String sql = "select etincons ,etinmodu ,etincamp ,etinetiq "
					   + "from FrmI18n ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmI18n.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla FrmI18n
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FrmI18n ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FrmI18n por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmI18n = objeto de la case FrmI18n que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmI18n update(Long id){
		FrmI18n frmi18n = this.list(id);
		getSession().update(frmi18n);
		return frmi18n;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FrmI18n por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmI18n = objeto de la case FrmI18n que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(Long id){
		FrmI18n frmi18n = this.list(id);
		//FrmI18n.setEstado="B";
		getSession().update(frmi18n);
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FrmI18n
	 * @value etincons
	 * @value etinmodu
	 * @value etincamp
	 * @value etinetiq
	 * @return FrmI18n = objeto de la case FrmI18n que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void insert(FrmI18n frmi18n){
		getSession().save(frmi18n);		
	}
}

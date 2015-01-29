package com.confianza.webapp.repository.framework.frmparametro;

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
public class FrmParametroRepositoryImpl implements FrmParametroRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FrmParametro por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmParametro = objeto de la case FrmParametro que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmParametro list(Long id){
		try{
			String sql = "select "+FrmParametro.getColumnNames()
					   + "from FrmParametro "
					   + "where paracons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmParametro.class)					
					     .setParameter("id", id);
			return (FrmParametro)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmParametro de acuerdo a la consulta que pertenezcan
	 * @value id = id de la llave foranea a consultar el registro
	 * @return FrmParametro = objeto de la case FrmParametro que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FrmParametro> listParamsCosu(Long id){
		try{
			String sql = "select "+FrmParametro.getColumnNames()
					   + "from Frm_Parametro "
					   + "where paracosu = :id order by paracons";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmParametro.class)					
					     .setParameter("id", id);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmParametro
	 * @return FrmParametro = coleccion de objetos de la case FrmParametro que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FrmParametro> listAll(int init, int limit){
		try{
			String sql = "select "+FrmParametro.getColumnNames()
					   + "from Frm_Parametro ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmParametro.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla FrmParametro
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FrmParametro ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FrmParametro por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmParametro = objeto de la case FrmParametro que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmParametro update(FrmParametro frmparametro){
		getSession().update(frmparametro);
		return frmparametro;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FrmParametro por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmParametro = objeto de la case FrmParametro que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FrmParametro frmparametro){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FrmParametro
	 * @value paracons
	 * @value paracosu
	 * @value paranomb
	 * @value paratipo
	 * @value paratida
	 * @value paracomb
	 * @return FrmParametro = objeto de la case FrmParametro que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmParametro insert(FrmParametro frmparametro){
		getSession().save(frmparametro);	
		return frmparametro;
	}
}

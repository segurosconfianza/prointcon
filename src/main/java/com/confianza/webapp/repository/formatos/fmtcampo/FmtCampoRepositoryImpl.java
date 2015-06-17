package com.confianza.webapp.repository.formatos.fmtcampo;

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

@Repository
public class FmtCampoRepositoryImpl implements FmtCampoRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FmtCampo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtCampo = objeto de la case FmtCampo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtCampo list(Long id){
		try{
			String sql = "select "+FmtCampo.getColumnNames()
					   + "from FMT_CAMPO "
					   + "where campcons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtCampo.class)					
					     .setParameter("id", id);
			return (FmtCampo)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtCampo
	 * @return FmtCampo = coleccion de objetos de la case FmtCampo que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FmtCampo> listAll(int init, int limit){
		try{
			String sql = "select "+FmtCampo.getColumnNames()
					   + "from FMT_CAMPO ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtCampo.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla FmtCampo
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FmtCampo ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FmtCampo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtCampo = objeto de la case FmtCampo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtCampo update(FmtCampo fmtcampo){
		getSession().update(fmtcampo);
		return fmtcampo;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FmtCampo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtCampo = objeto de la case FmtCampo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FmtCampo fmtcampo){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FmtCampo
	 * @value campcons
	 * @value campvefo
	 * @value campauto
	 * @value campcomb
	 * @value campnomb
	 * @value camporden
	 * @value camptipo
	 * @value camprequ
	 * @value campvali
	 * @value campesta
	 * @return FmtCampo = objeto de la case FmtCampo que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtCampo insert(FmtCampo fmtcampo){
		getSession().save(fmtcampo);	
		return fmtcampo;
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtCampo de acuerdo al formato que pertenezcan
	 * @value id = id de la llave foranea a consultar el registro
	 * @return FrmParametro = objeto de la case FrmParametro que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FmtCampo> listCamposCosu(Long id){
		try{
			String sql = "select "+FmtCampo.getColumnNames()
					   + "from Fmt_Campo "
					   + "where campvefo = :id order by camporde";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtCampo.class)					
					     .setParameter("id", id);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}

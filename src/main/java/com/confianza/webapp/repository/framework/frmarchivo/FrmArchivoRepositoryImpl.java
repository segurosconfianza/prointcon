package com.confianza.webapp.repository.framework.frmarchivo;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FrmArchivoRepositoryImpl implements FrmArchivoRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FrmArchivo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmArchivo = objeto de la case FrmArchivo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmArchivo list(Long id){
		try{
			String sql = "select "+FrmArchivo.getColumnNames()
					   + "from Frm_Archivo "
					   + "where archcodi = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmArchivo.class)					
					     .setParameter("id", id);
			return (FrmArchivo)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmArchivo
	 * @return FrmArchivo = coleccion de objetos de la case FrmArchivo que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FrmArchivo> listAll(int init, int limit){
		try{
			String sql = "select "+FrmArchivo.getColumnNames()
					   + "from Frm_Archivo ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmArchivo.class);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla FrmArchivo
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FrmArchivo ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FrmArchivo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmArchivo = objeto de la case FrmArchivo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmArchivo update(FrmArchivo frmarchivo){
		getSession().update(frmarchivo);
		return frmarchivo;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FrmArchivo por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmArchivo = objeto de la case FrmArchivo que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FrmArchivo frmarchivo){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FrmArchivo
	 * @value archcodi
	 * @value archruta
	 * @value archnomb
	 * @value archmd5
	 * @value archfech
	 * @value archmime
	 * @value archsize
	 * @return FrmArchivo = objeto de la case FrmArchivo que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmArchivo insert(FrmArchivo frmarchivo){
		getSession().save(frmarchivo);	
		return frmarchivo;
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmArchivo
	 * @return FrmArchivo = coleccion de objetos de la case FrmArchivo que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FrmArchivo> listAllMd5(ArrayList<String> mime){
		try{
			String sql = "select "+FrmArchivo.getColumnNames()
					   + "from Frm_Archivo "
					   + "where archmd5 in (:lista)";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmArchivo.class);
						 
			query.setParameterList("lista", mime);
					     
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}

package com.confianza.webapp.repository.osiris.osisucursal;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		osiris  
  */                          

import java.util.List;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.confianza.webapp.utils.SqlFunctions;
import com.confianza.webapp.utils.Filter;

@Repository
public class OsiSucursalRepositoryImpl implements OsiSucursalRepository{
	
	@Autowired
	private SessionFactory sessionFactory;  	
	
	@Autowired
	private SqlFunctions sqlFunctions;
	
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
	 * Metodo de consulta para los registros de la tabla OsiSucursal por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return OsiSucursal = objeto de la case OsiSucursal que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public OsiSucursal list(Long id){
		try{
			String sql = "select "+OsiSucursal.getColumnNames()
					   + "from OSI_SUCURSAL "
					   + "where  = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(OsiSucursal.class)					
					     .setParameter("id", id);
			return (OsiSucursal)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla OsiSucursal
	 * @return OsiSucursal = coleccion de objetos de la case OsiSucursal que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<OsiSucursal> listAll(int init, int limit, String order, List<Filter> filters){
		try{
			String sql = "select "+OsiSucursal.getColumnNames()
					   + "from OSI_SUCURSAL ";
				
			sql = sqlFunctions.completeSQL(order, filters, sql, OsiSucursal.getColumnNames());
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(OsiSucursal.class);
				
			query=sqlFunctions.setParameters(filters, query);
						 
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
	 * Metodo de consulta para el conteo de los registros de la tabla OsiSucursal
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(List<Filter> filters){
		try{
			String sql = "select count(*) "
					   + "from OsiSucursal ";
				
			sql = sqlFunctions.completeSQL(null, filters, sql, OsiSucursal.getColumnNames());
						
			Query query = getSession().createQuery(sql);
	        
	        query=sqlFunctions.setParameters(filters, query);
	        
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
	 * Metodo para actualizar los datos de un registro de la tabla OsiSucursal por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return OsiSucursal = objeto de la case OsiSucursal que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public OsiSucursal update(OsiSucursal osisucursal){
		getSession().update(osisucursal);
		return osisucursal;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla OsiSucursal por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return OsiSucursal = objeto de la case OsiSucursal que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(OsiSucursal osisucursal){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla OsiSucursal
	 * @value sucucons
	 * @value sucunomb
	 * @return OsiSucursal = objeto de la case OsiSucursal que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public OsiSucursal insert(OsiSucursal osisucursal){
		getSession().save(osisucursal);	
		return osisucursal;
	}
}

package com.confianza.webapp.repository.pila.pilusua;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		pila  
  */                          

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregi;
import com.confianza.webapp.utils.Filter;
import com.confianza.webapp.utils.SqlFunctions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Repository
public class PilUsuaRepositoryImpl implements PilUsuaRepository{
	
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
	 * Metodo de consulta para los registros de la tabla PilUsua por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilUsua = objeto de la case PilUsua que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilUsua list(Long id){
		try{
			String sql = "select "+PilUsua.getColumnNames()
					   + "from PIL_USUA "
					   + "where usuacons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(PilUsua.class)					
					     .setParameter("id", id);
			return (PilUsua)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla PilUsua
	 * @return PilUsua = coleccion de objetos de la case PilUsua que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<PilUsua> listAll(int init, int limit, String order, List<Filter> filters){
		try{
			String sql = "select "+PilUsua.getColumnNames()
					   + "from PIL_USUA ";
				
			sql = sqlFunctions.completeSQL(order, filters, sql, PilUsua.getColumnNames());
			
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(PilUsua.class);
					
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
	 * Metodo de consulta para el conteo de los registros de la tabla PilUsua
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(List<Filter> filters){
		try{
			String sql = "select count(*) "
					   + "from PilUsua ";
					
			sql = sqlFunctions.completeSQL(null, filters, sql, PilUsua.getColumnNames());
			
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
	 * Metodo para actualizar los datos de un registro de la tabla PilUsua por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilUsua = objeto de la case PilUsua que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilUsua update(PilUsua pilusua){
		getSession().update(pilusua);
		return pilusua;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla PilUsua por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return PilUsua = objeto de la case PilUsua que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(PilUsua pilusua){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla PilUsua
	 * @value usuacons
	 * @value usuaunit
	 * @value usuadive
	 * @value usuatiin
	 * @value usuarazo
	 * @value usuanomb
	 * @value usuaapel
	 * @value usuaemai
	 * @value usuatele
	 * @value usuapeco
	 * @value usuausua
	 * @value usuapass
	 * @value usuatipo
	 * @value usuasucu
	 * @value usuaesta
	 * @return PilUsua = objeto de la case PilUsua que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilUsua insert(PilUsua pilusua){
		getSession().save(pilusua);	
		return pilusua;
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla PilUsua
	 * @return PilUsua = coleccion de objetos de la case PilUsua que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public PilUsua validateUsua(String user, String password){
		try{
			String sql = "select "+PilUsua.getColumnNames()
					   + "from PIL_USUA where usuausua = :user and usuapass = :password";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(PilUsua.class);
			query.setParameter("user", user);
			query.setParameter("password", password);
						 
			return (PilUsua) query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla PilUsua
	 * @return PilUsua = coleccion de objetos de la case PilUsua que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<PilUsua> listAllFormregi(List<Long> codigosFormRegi){
		try{
			String sql = "select "+PilUsua.getColumnNames()
					   + "from PIL_USUA "
					   + "join FMT_FORMREGI ON (FOREUSER=USUAUSUA AND FORECONS IN (:lista)) "
					   + "where  USUATIPO=1 ";				
			
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(PilUsua.class)
						 .setParameterList("lista", codigosFormRegi);														
					     
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}

package com.confianza.webapp.repository.formatos.fmtformregi;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.confianza.webapp.utils.Filter;

@Repository
public class FmtFormregiRepositoryImpl implements FmtFormregiRepository{
	
	@Autowired
	private SessionFactory sessionFactory;  	
	
	@Autowired
	private FmtFormregiInterceptor FmtFormregiInterceptor;		
	
	public Session getSession() {
		
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtFormregi por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtFormregi = objeto de la case FmtFormregi que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public FmtFormregi list(Long id){
		try{
			String sql = "select "+FmtFormregi.getColumnNames()
					   + "from FMT_FORMREGI "
					   + "where forecons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtFormregi.class)					
					     .setParameter("id", id);
			return (FmtFormregi)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtFormregi
	 * @return FmtFormregi = coleccion de objetos de la case FmtFormregi que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public List<FmtFormregi> listAll(int init, int limit){
		try{
			String sql = "select "+FmtFormregi.getColumnNames()
					   + "from FMT_FORMREGI ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtFormregi.class);
						 
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
	 * Metodo para actualizar los datos de un registro de la tabla FmtFormregi por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtFormregi = objeto de la case FmtFormregi que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtFormregi update(FmtFormregi fmtformregi){
		getSession().update(fmtformregi);
		return fmtformregi;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FmtFormregi por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FmtFormregi = objeto de la case FmtFormregi que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(FmtFormregi fmtformregi){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FmtFormregi
	 * @value forecons
	 * @value forevefo
	 * @value forefech
	 * @value foreuser
	 * @value foreesta
	 * @return FmtFormregi = objeto de la case FmtFormregi que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FmtFormregi insert(FmtFormregi fmtformregi){
		getSession().save(fmtformregi);	
		return fmtformregi;
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtFormregi
	 * @return FmtFormregi = coleccion de objetos de la case FmtFormregi que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public List<FmtFormregi> listAll(int init, int limit, Long vefocons, String user, String order, List<Filter> filters){
		try{
			String sql = "select "+FmtFormregi.getColumnNames()
					   + "from FMT_FORMREGI "
					   + "join FRM_TABLAS ON (TABLCODI='foreesta' AND TABLCLAV=FOREESTA)  ";
						
			sql = completeSQL(order, filters, sql);
			
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FmtFormregi.class);
			
			query=setParameters(filters, query);
			
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

	private Query setParameters(List<Filter> filters, Query query) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
		for(Filter filter:filters){
			if((FmtFormregi.getColumnNames()+", tablvast").matches("(.*)"+filter.getCampo()+"(.*)"))
				if(filter.getTipodato().equals("Date")){
					Date parsed=null;
					try {
						parsed = format.parse(filter.getVal1());
						query.setDate(filter.getCampo(), parsed);
					} catch (ParseException e) {
						query.setDate(filter.getCampo(), parsed);
					}
					if(filter.getVal2()!=null){
						try {
							parsed = format.parse(filter.getVal2());
							query.setDate(filter.getCampo()+"2", parsed);
						} catch (ParseException e) {
							query.setDate(filter.getCampo()+"2", parsed);
						}
					}
				}
				else if(filter.getTipodato().equals("Number")){
					query.setDouble(filter.getCampo(), new Double(filter.getVal1()));
					System.out.println(filter.getCampo()+" "+new Double(filter.getVal1()));
					if(filter.getVal2()!=null)	{
						query.setDouble(filter.getCampo()+"2", new Double(filter.getVal2()));
						System.out.println(filter.getCampo()+"2 "+new Double(filter.getVal2()));
					}
				}
				else{
					query.setParameter(filter.getCampo(), filter.getVal1());
					System.out.println("String: "+filter.getCampo()+" "+filter.getVal1());
				}
		}
		
		return query;
	}

	private String completeSQL(String order, List<Filter> filters, String sql) {
		String where = "";
		where = generateWhere(filters, where);
		
		sql+= where;
		
		if(order!=null && (FmtFormregi.getColumnNames()+", tablvast").matches("(.*)"+order.split(" orber by ")[0]+"(.*)") )
			sql+=order;
		
		System.out.println(sql);
		return sql;
	}

	private String generateWhere(List<Filter> filters, String where) {
		for(Filter filter:filters){
			if((FmtFormregi.getColumnNames()+", tablvast").matches("(.*)"+filter.getCampo()+"(.*)"))
				if(where.isEmpty()){
					where+=" WHERE ";
					where+= generateCondition(filter);
				}
				else{
					where+=" AND ";
					where+= generateCondition(filter);
				}
		}
		return where;
	}

	private String generateCondition(Filter filter) {
		if(filter.getTipo().equals("BETWEEN"))
			return filter.getCampo()+" "+filter.getTipo()+" :"+filter.getCampo()+" AND :"+filter.getCampo()+"2";
		else
			return filter.getCampo()+" "+filter.getTipo()+" :"+filter.getCampo();
	}
	
	/**
	 * Metodo de consulta para el conteo de los registros de la tabla FmtFormregi
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public int getCount(List<Filter> filters){ 
		try{
			String sql = "select count(*) "
					   + "from Fmt_Formregi "
					   + "join Frm_Tablas ON (TABLCODI='foreesta' AND TABLCLAV=FOREESTA) ";
			
			sql = completeSQL(null, filters, sql);
			
			Query query = getSession().createSQLQuery(sql);
			
			query=setParameters(filters, query);
			
			Iterator it = query.list().iterator();
	        Long ret = new Long(0);
	        
	        if (it != null)
		        if (it.hasNext()){
		        	ret = new Long(it.next().toString());
		        }
	        
			return ret.intValue();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}

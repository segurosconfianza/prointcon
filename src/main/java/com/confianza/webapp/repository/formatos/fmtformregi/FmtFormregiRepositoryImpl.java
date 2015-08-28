package com.confianza.webapp.repository.formatos.fmtformregi;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
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
import org.hibernate.metamodel.SessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.confianza.webapp.utils.Filter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Repository
public class FmtFormregiRepositoryImpl implements FmtFormregiRepository{
	
	@Autowired
	private SessionFactory sessionFactory;  	
	
	@Autowired
	private FmtFormregiInterceptor FmtFormregiInterceptor;		
	
	@Autowired  
	Gson gson;
	
	public Session getSession() {
		
		FmtFormregiInterceptor.setSessionFactory(sessionFactory);
		sessionFactory.getCurrentSession().sessionWithOptions().interceptor(FmtFormregiInterceptor);
		
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
			
		getSession().saveOrUpdate(fmtformregi);
		return fmtformregi;
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FmtFormregi
	 * @return FmtFormregi = coleccion de objetos de la case FmtFormregi que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public List<FmtFormregi> listAll(int init, int limit, Long vefocons, String order, List<Filter> filters){
		try{
			String sql = "select "+FmtFormregi.getColumnNames().replace("forecons", " distinct(forecons) forecons")
					   + "from FMT_FORMREGI "
					   + "join FRM_TABLAS   ON (TABLCODI='foreesta' AND TABLCLAV=FOREESTA) "
					   + "left join FMT_VALOCAMP ON (VACAFORE=FORECONS) "
					   + "left join FMT_CAMPO    ON (CAMPCONS=VACACAMP) "
					   + "join PIL_USUA     ON (USUAUSUA=FOREUSER) ";
						
			sql = completeSQL(order, filters, sql);
			
			System.out.println(sql);
			
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

	/**
	 * Metodo de consulta para los registros de la tabla FmtFormregi para los analistas
	 * @return FmtFormregi = coleccion de objetos de la case FmtFormregi que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public List<FmtFormregi> listAllAdmin(int init, int limit, Long vefocons, String order, List<Filter> filters){
		try{
			String sql = "select "+FmtFormregi.getColumnNames().replace("forecons", " distinct(forecons) forecons")
					   + "from FMT_FORMREGI "
					   + "join FRM_TABLAS   ON (TABLCODI='foreesta' AND TABLCLAV=FOREESTA) "
					   + "left join FMT_VALOCAMP ON (VACAFORE=FORECONS) "
					   + "left join FMT_CAMPO    ON (CAMPCONS=VACACAMP) "
					   + "join PIL_USUA     ON (USUAUSUA=FOREUSER) ";
						
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
			    if(filter.getTipo().equals("IN")){
			    	if(filter.getTipodato().equals("Number")){
				    	Type listOfTestObject = new TypeToken<List<Long>>(){}.getType();
						List<Long> listin = gson.fromJson("["+filter.getVal1()+"]", listOfTestObject);
				    	query.setParameterList(filter.getCampo(), listin);
			    	}
			    	else if(filter.getTipodato().equals("Stirng")){
				    	Type listOfTestObject = new TypeToken<List<String>>(){}.getType();
						List<String> listin = gson.fromJson("["+filter.getVal1()+"]", listOfTestObject);
				    	query.setParameterList(filter.getCampo(), listin);
			    	}
			    }
			    else if(filter.getTipodato().equals("Date")){
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
					
					if(filter.getCampo().equals("usuasucu") && !filter.getTipo().equals("IN")){
						query.setDouble(filter.getCampo()+"o", new Double(filter.getVal1()));
						if(filter.getVal2()!=null)	{
							query.setDouble(filter.getCampo()+"o2", new Double(filter.getVal2()));
						}
					}
					else{
						query.setDouble(filter.getCampo(), new Double(filter.getVal1()));
						if(filter.getVal2()!=null)	{
							query.setDouble(filter.getCampo()+"2", new Double(filter.getVal2()));
						}
					}
				}
				else{
					query.setParameter(filter.getCampo(), filter.getVal1());
				}
		}
		
		return query;
	}

	private String completeSQL(String order, List<Filter> filters, String sql) {
		String where = "";
		where = generateWhere(filters, where);
		
		sql+= where;
		
		if(order!=null){
			String campoorde=order.replace("order by", "").replace("desc", "").replace("asc", "").replace(" ", "");
			
			sql = evaluateField(order, sql, where, campoorde);
		}
		return sql;
	}

	private String evaluateField(String order, String sql, String where, String campoorde) {
		if((FmtFormregi.getColumnNames()).matches("(.*)"+campoorde+"(.*)")) 
			sql+=" "+order;
		else if("tablvast".matches("(.*)"+campoorde+"(.*)")){
			sql+=order;
			//para el order by se debe agregar el campo al select porque sino no hacer el ornamiento pero si no se ordena por ese campo al dejar el campo en el select me repite los registros
			sql=sql.replace("distinct(forecons) forecons," , "distinct(forecons) forecons, tablvast, ");
		}
		else if("usuarazo".matches("(.*)"+campoorde+"(.*)")){
			sql+=order;
			//para el order by se debe agregar el campo al select porque sino no hacer el ornamiento pero si no se ordena por ese campo al dejar el campo en el select me repite los registros
			sql=sql.replace("distinct(forecons) forecons," , "distinct(forecons) forecons, usuarazo, ");
		}			
		else if("usuaunit".matches("(.*)"+campoorde+"(.*)")){
			sql+=order;
			//para el order by se debe agregar el campo al select porque sino no hacer el ornamiento pero si no se ordena por ese campo al dejar el campo en el select me repite los registros
			sql=sql.replace("distinct(forecons) forecons," , "distinct(forecons) forecons, usuaunit, ");
		}
		else if("usuasucu".matches("(.*)"+campoorde+"(.*)")){
			sql+=order;
			//para el order by se debe agregar el campo al select porque sino no hacer el ornamiento pero si no se ordena por ese campo al dejar el campo en el select me repite los registros
			sql=sql.replace("distinct(forecons) forecons," , "distinct(forecons) forecons, usuasucu, ");
		}
		else{
			if(where.isEmpty())
				sql+=" WHERE campnomb='"+campoorde+"' ";
			else
				sql+=" AND campnomb='"+campoorde+"' ";				
			sql+=" order by vacavalo ";
			sql=sql.replace("distinct(forecons) forecons,", "distinct(forecons) forecons, vacavalo, ");
		}
		return sql;
	}

	private String generateWhere(List<Filter> filters, String where) {
		for(Filter filter:filters){
			if((FmtFormregi.getColumnNames()+", tablvast, usuasucu, usuarazo, usuaunit").matches("(.*)"+filter.getCampo()+"(.*)") ){
				if(where.isEmpty())
					where+=" WHERE ";
				else
					where+=" AND ";
				where+= generateCondition(filter);
			}
			else{
				if(where.isEmpty())
					where+=" WHERE ";
				else
					where+=" AND ";
								
				where+= "campnomb='"+filter.getCampo()+"' AND "+generateConditionValocamp(filter);
			}
		}
		return where;
	}

	private String generateCondition(Filter filter) {
		if(filter.getTipo().equals("BETWEEN")){
			if(filter.getCampo().equals("usuasucu") && !filter.getTipo().equals("IN"))
				return filter.getCampo()+" "+filter.getTipo()+" :"+filter.getCampo()+"o AND :"+filter.getCampo()+"2";
			else
				return filter.getCampo()+" "+filter.getTipo()+" :"+filter.getCampo()+" AND :"+filter.getCampo()+"2";
		}
		if(filter.getTipo().equals("IN"))
			return filter.getCampo()+" IN(:"+filter.getCampo()+")";
		else
			if(filter.getCampo().equals("usuasucu") && !filter.getTipo().equals("IN"))
				return filter.getCampo()+" "+filter.getTipo()+" :"+filter.getCampo()+"o";
			else
				return filter.getCampo()+" "+filter.getTipo()+" :"+filter.getCampo();
	}
	
	private String generateConditionValocamp(Filter filter) {
		if(filter.getTipo().equals("BETWEEN"))
			if(filter.getTipodato().equals("Number"))
				return " COALESCE(TO_NUMBER(REGEXP_SUBSTR(vacavalo, '^\\d+')), 0) "+filter.getTipo()+" :"+filter.getCampo()+" AND :"+filter.getCampo()+"2";
			else
				return " vacavalo "+filter.getTipo()+" :"+filter.getCampo()+" AND :"+filter.getCampo()+"2";
		else
			if(filter.getTipodato().equals("Number"))
				return " COALESCE(TO_NUMBER(REGEXP_SUBSTR(vacavalo, '^\\d+')), 0) "+filter.getTipo()+" :"+filter.getCampo();
			else
				return " vacavalo "+filter.getTipo()+" :"+filter.getCampo();
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
			String sql = "select count(distinct(forecons)) "
					   + "from Fmt_Formregi "
					   + "join FRM_TABLAS   ON (TABLCODI='foreesta' AND TABLCLAV=FOREESTA) "
					   + "join FMT_VALOCAMP ON (VACAFORE=FORECONS) "
					   + "join FMT_CAMPO    ON (CAMPCONS=VACACAMP) ";
			
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
	
	/**
	 * Metodo de consulta para el conteo de los registros de la tabla FmtFormregi
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public int getCountAdmin(List<Filter> filters){  
		try{
			String sql = "select count(distinct(forecons)) "
					   + "from Fmt_Formregi "
					   + "join FRM_TABLAS   ON (TABLCODI='foreesta' AND TABLCLAV=FOREESTA) "
					   + "join FMT_VALOCAMP ON (VACAFORE=FORECONS) "
					   + "join FMT_CAMPO    ON (CAMPCONS=VACACAMP) "
					   + "join PIL_USUA     ON (USUAUSUA=FOREUSER) ";
			
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

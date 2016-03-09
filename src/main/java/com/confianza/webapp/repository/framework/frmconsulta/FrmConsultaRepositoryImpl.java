package com.confianza.webapp.repository.framework.frmconsulta;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.ObjectType;
import org.hibernate.type.StringType;
import org.hibernate.type.TextType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;

@Repository
@Transactional
public class FrmConsultaRepositoryImpl implements FrmConsultaRepository{
	
	@Autowired
	private SessionFactory sessionFactory;  	
	
	@Autowired
	private SessionFactory sessionFactoryOsiris;
	
	private enum typesData { S, CS, CI, D, I, L, T, O, B, F, TA, TL};	
	
	public SessionFactory getSessionFactoryOsiris() {
		return sessionFactoryOsiris;
	}

	public void setSessionFactoryOsiris(SessionFactory sessionFactoryOsiris) {
		this.sessionFactoryOsiris = sessionFactoryOsiris;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Session getSessionOsiris() {
		return sessionFactoryOsiris.getCurrentSession();
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmConsulta por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public FrmConsulta list(Long id){
		try{
			String sql = "select "+FrmConsulta.getColumnNames()
					   + "from Frm_Consulta "  
					   + "where conscons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmConsulta.class)					
					     .setParameter("id", id);
			return (FrmConsulta)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmConsulta por el nombre de la consulta
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public FrmConsulta listName(String id){
		try{
			String sql = "select "+FrmConsulta.getColumnNames()
					   + "from Frm_Consulta "
					   + "where consnomb = :id ";
						
			Query query = getSession().createSQLQuery(sql)	
						 .addEntity(FrmConsulta.class)	
					     .setParameter("id", id);
			return (FrmConsulta) query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmConsulta por el padre de la consulta
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public FrmConsulta listChild(String id){
		try{
			String sql = "select "+FrmConsulta.getColumnNames()
					   + "from Frm_Consulta "
					   + "where conspadr = :id and constipo=1";
						
			Query query = getSession().createSQLQuery(sql)	
						 .addEntity(FrmConsulta.class)	
					     .setParameter("id", id);
			return (FrmConsulta) query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmConsulta por el nombre de la consulta
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public FrmConsulta listProcedureChild(String id){
		try{
			String sql = "select "+FrmConsulta.getColumnNames()
					   + "from Frm_Consulta "
					   + "where conspadr = :id and constipo=2";
						
			Query query = getSession().createSQLQuery(sql)	
						 .addEntity(FrmConsulta.class)	
					     .setParameter("id", id);
			return (FrmConsulta) query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmConsulta por el nombre de la consulta
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public List<FrmConsulta> listProcedureChildren(String id){
		try{
			String sql = "select "+FrmConsulta.getColumnNames()
					   + "from Frm_Consulta "
					   + "where conspadr = :id and constipo=2";
						
			Query query = getSession().createSQLQuery(sql)	
						 .addEntity(FrmConsulta.class)	
					     .setParameter("id", id);
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para ejecutar la consulta guardad en el sql
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Object[]> loadData(FrmConsulta frmConsulta,Map<String, Object> parameters){
		try{
			Query query =  getSession().createSQLQuery(frmConsulta.getConslsql());
					
			if(parameters!=null){
				Iterator it = parameters.entrySet().iterator();			
			    while (it.hasNext()) {
			        Map.Entry e = (Map.Entry)it.next();
			        query.setParameter(e.getKey().toString(), e.getValue());
			   }
			}
			
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para ejecutar la consulta guardad en el sql
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional(value="transactionManagerOsiris", readOnly=true)
	public List<Object[]> loadDataOsiris(FrmConsulta frmConsulta, Map<String, Object> parameters, List<FrmParametro> parametros){
		try{
			SQLQuery query = getSessionOsiris().createSQLQuery(frmConsulta.getConslsql());
			
			if(parameters!=null){
				Iterator it = parameters.entrySet().iterator();			
			    while (it.hasNext()) {
			        Map.Entry e = (Map.Entry)it.next();
			        query.setParameter(e.getKey().toString(), e.getValue());
			    }
		    }
			
			/*if(parametros!=null){
				for(FrmParametro frmParametro:parametros){
					if(frmParametro.getParatipo().equals("S")){
						if(frmParametro.getParatida().equals("D"))
							query.addScalar(frmParametro.getParanomb(), DateType.INSTANCE);
						else if(frmParametro.getParatida().equals("CI") || frmParametro.getParatida().equals("CI"))
							query.addScalar(frmParametro.getParanomb(), IntegerType.INSTANCE);
						else if(frmParametro.getParatida().equals("C") || frmParametro.getParatida().equals("CS"))
							query.addScalar(frmParametro.getParanomb(), StringType.INSTANCE);
						else if(frmParametro.getParatida().equals("L"))
							query.addScalar(frmParametro.getParanomb(), StringType.INSTANCE);
						else 
							query.addScalar(frmParametro.getParanomb(), StringType.INSTANCE);
					}
				}
			}*/
			
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmConsulta
	 * @return FrmConsulta = coleccion de objetos de la case FrmConsulta que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public List<FrmConsulta> listAll(int id){
		try{
			String sql = "select "+FrmConsulta.getColumnNames()
					   + "from Frm_Consulta where conspadr = :padre and constipo=1 ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmConsulta.class)
						 .setParameter("padre", id);						 			
					     
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}	
	
	/**
	 * Metodo de consulta para el conteo de los registros de la tabla FrmConsulta
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly=true)
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FrmConsulta ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FrmConsulta por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	public FrmConsulta update(FrmConsulta frmconsulta){
		getSession().update(frmconsulta);
		return frmconsulta;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FrmConsulta por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	public void delete(FrmConsulta frmconsulta){
		
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FrmConsulta
	 * @value conscons
	 * @value consmodu
	 * @value consnomb
	 * @value consdesc
	 * @value conscaco
	 * @value constipo
	 * @value conslsql
	 * @value conscolu
	 * @value constico
	 * @return FrmConsulta = objeto de la case FrmConsulta que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmConsulta insert(FrmConsulta frmconsulta){
		getSession().save(frmconsulta);	
		return frmconsulta;
	}
	
	/**
	 * Metodo de consulta para ejecutar la consulta guardad en el sql
	 * @value FrmConsulta = procedimiento almacenado a ejecutarse
	 * @value parametros = listado de los parametros que son usados del procedimiento
	 * @value parameters = mapa de los parametros de busqueda que son usados del procedimiento con sus respectivos valores
	 * @value parametersData = mapa de los parametros a modificar que son usados del procedimiento con sus respectivos valores
	 * @return Map<String, Object> = mapa clave, valor con el resultado del procedimiento ejecutado
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> loadProcedure(FrmConsulta frmConsulta, List<FrmParametro> parametros, Map<String, Object> parameters, Map<String, Object> parametersData){
		try{
			
			final String procedure="begin " +frmConsulta.getConslsql()+" end; ";
			final Map<String, Object> p= parameters;
			final Map<String, Object> pd= parametersData;
			final List<FrmParametro> fp = parametros;
			
			final Map<String, Object> output= new HashMap<String, Object>();
			
			getSession().doWork(new Work() {
								
				@Override
				public void execute(Connection connection) throws SQLException {
					// TODO Auto-generated method stub
					CallableStatement cst = connection.prepareCall(procedure);
															
					cst=putParametersInput(p, fp, cst, "E");
					cst=putParametersInput(pd, fp, cst, "S");
					
					cst=putParameterOutput(fp, cst);
					
				    cst.execute();

				    output.putAll(getParametersOutput(fp, cst));
				}								
												
			});						
			
			return output;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para ejecutar la consulta guardad en el sql
	 * @value FrmConsulta = procedimiento almacenado a ejecutarse
	 * @value parametros = listado de los parametros que son usados del procedimiento
	 * @value parameters = mapa de los parametros de busqueda que son usados del procedimiento con sus respectivos valores
	 * @value parametersData = mapa de los parametros a modificar que son usados del procedimiento con sus respectivos valores
	 * @return Map<String, Object> = mapa clave, valor con el resultado del procedimiento ejecutado
	 * @throws Exception
	 */
	@Override
	@Transactional("transactionManagerOsiris")
	public Map<String, Object> loadProcedureOsiris(FrmConsulta frmConsulta, List<FrmParametro> parametros, Map<String, Object> parameters, Map<String, Object> parametersData){
		try{
			
			final String procedure="begin " +frmConsulta.getConslsql()+" end; ";
			final Map<String, Object> p= parameters;
			final Map<String, Object> pd= parametersData;
			final List<FrmParametro> fp = parametros;
			
			final Map<String, Object> output= new HashMap<String, Object>();
						
			getSessionOsiris().doWork(new Work() {
								
				@Override
				public void execute(Connection connection) throws SQLException {
					// TODO Auto-generated method stub
					CallableStatement cst = connection.prepareCall(procedure);
												
					cst=putParametersInput(p, fp, cst, "E");
					if(pd!=null)
						cst=putParametersInput(pd, fp, cst, "S");
					
					cst=putParameterOutput(fp, cst);
					
				    cst.execute();

				    output.putAll(getParametersOutput(fp, cst));
				}								
												
			});						
			
			return output;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo para fijar las variables de entrada del procedimiento almacenado
	 * @value p = mapa de los parametros de entrada que son usados del procedimiento con sus respectivos valores
	 * @value fp = listado de los parametros que son usados del procedimiento
	 * @value cst= callablestatement, para fijar los valores de salida del procedimiento ejecutado
	 * @return CallableStatement = el procedimiento almacenado configurado con los parametros de entrada y sus valores en el respectivo orden
	 * @throws Exception
	 */
	@Override
	public CallableStatement putParametersInput(final Map<String, Object> p, final List<FrmParametro> fp, CallableStatement cst, String tipo) throws SQLException {
		//fijar todos los parametros de entrada	
		float aux;
		
		for(FrmParametro objP:fp){
			
			if(objP.getParatipo().equals(tipo))
			{
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
			        
				typesData typeData=typesData.valueOf(objP.getParatida());
				System.out.println(objP.getParanomb());
				switch(typeData){
					case S: if(p.get(objP.getParanomb())!=null)
						    	cst.setString(objP.getParanomb(),p.get(objP.getParanomb()).toString());
							else
								cst.setNull(objP.getParanomb(), java.sql.Types.VARCHAR);
							break;
					case CS:if(p.get(objP.getParanomb())!=null) 
								cst.setString(objP.getParanomb(), p.get(objP.getParanomb()).toString());
							else
								cst.setNull(objP.getParanomb(), java.sql.Types.VARCHAR);
							break;
					case TA: if(p.get(objP.getParanomb())!=null) 
								cst.setString(objP.getParanomb(), p.get(objP.getParanomb()).toString());
							else
								cst.setNull(objP.getParanomb(), java.sql.Types.VARCHAR);
							break;	
					case TL: if(p.get(objP.getParanomb())!=null) 
								cst.setNString(objP.getParanomb(), p.get(objP.getParanomb()).toString());
							else
								cst.setNull(objP.getParanomb(), java.sql.Types.VARCHAR);
							break;
					case D: if(p.get(objP.getParanomb())!=null && !p.get(objP.getParanomb()).toString().trim().isEmpty()){
								Date parsed=null;
								try {
									parsed = format.parse(p.get(objP.getParanomb()).toString());									
									cst.setDate(objP.getParanomb(),new java.sql.Date(parsed.getTime()));
								} catch (ParseException e) {
									cst.setNull(objP.getParanomb(), java.sql.Types.DATE);
								}
							}
							else
								cst.setNull(objP.getParanomb(), java.sql.Types.DATE);
							break;
					case I: if(p.get(objP.getParanomb())!=null){
								aux=Float.parseFloat( p.get(objP.getParanomb()).toString());
								cst.setInt(objP.getParanomb(), (int)aux );
							}
							else
								cst.setNull(objP.getParanomb(), java.sql.Types.NUMERIC);
							break;
					case CI:if(p.get(objP.getParanomb())!=null){
								aux=Float.parseFloat( p.get(objP.getParanomb()).toString());							
								cst.setInt(objP.getParanomb(), (int)aux );
							}
							else
								cst.setNull(objP.getParanomb(), java.sql.Types.NUMERIC);
		 					break;
					case L: if(p.get(objP.getParanomb())!=null)							
								cst.setLong(objP.getParanomb(), Long.parseLong(p.get(objP.getParanomb()).toString()) );
							else
								cst.setNull(objP.getParanomb(), java.sql.Types.NUMERIC);
							break;
					case T: if(p.get(objP.getParanomb())!=null && !p.get(objP.getParanomb()).toString().trim().isEmpty())
								cst.setTimestamp(objP.getParanomb(), new java.sql.Timestamp(new Date(p.get(objP.getParanomb()).toString()).getTime()));
							else
								cst.setNull(objP.getParanomb(), java.sql.Types.TIMESTAMP);
							break;
					case O: if(p.get(objP.getParanomb())!=null)
								cst.setDouble(objP.getParanomb(), Double.parseDouble(p.get(objP.getParanomb()).toString()) );
							else
								cst.setNull(objP.getParanomb(), java.sql.Types.NUMERIC);
							break;
					case B: if(p.get(objP.getParanomb())!=null)
								cst.setBoolean(objP.getParanomb(), Boolean.parseBoolean(p.get(objP.getParanomb()).toString()) );
							else
								cst.setNull(objP.getParanomb(), java.sql.Types.NUMERIC);
							break;
					case F: if(p.get(objP.getParanomb())!=null)
								cst.setDouble(objP.getParanomb(), Double.parseDouble(p.get(objP.getParanomb()).toString()) );
							else
								cst.setNull(objP.getParanomb(), java.sql.Types.NUMERIC);
							break;
					default:break;		
				}					
			}
	    }
	    return cst;
	}
	
	/**
	 * Metodo para fijar las variables de salida del procedimiento almacenado
	 * @value fp = listado de los parametros que son usados del procedimiento
	 * @value cst= callablestatement, para fijar los valores de salida del procedimiento ejecutado
	 * @return CallableStatement = el procedimiento almacenado configurado con los parametros de salida y sus tipos de dato en el respectivo orden
	 * @throws Exception
	 */
	@Override
	public CallableStatement putParameterOutput(final List<FrmParametro> fp, CallableStatement cst) throws SQLException {
		//fijar todos los parametros de salida
        for(FrmParametro objP:fp){
			if(objP.getParatipo().equals("O")){
				typesData typeData=typesData.valueOf(objP.getParatida());
				switch(typeData){
				case S: cst.registerOutParameter(objP.getParanomb(), java.sql.Types.VARCHAR);
						break;
				case CS: cst.registerOutParameter(objP.getParanomb(), java.sql.Types.VARCHAR);
						break;
				case TA: cst.registerOutParameter(objP.getParanomb(), java.sql.Types.VARCHAR);
						break;	
				case TL: cst.registerOutParameter(objP.getParanomb(), java.sql.Types.LONGVARCHAR);
						break;				
				case D: cst.registerOutParameter(objP.getParanomb(), java.sql.Types.DATE);
						break;
				case I: cst.registerOutParameter(objP.getParanomb(), java.sql.Types.NUMERIC);
						break;
				case CI:cst.registerOutParameter(objP.getParanomb(), java.sql.Types.NUMERIC);
						break;		
				case L: cst.registerOutParameter(objP.getParanomb(), java.sql.Types.LONGVARCHAR);
						break;
				case T: cst.registerOutParameter(objP.getParanomb(), java.sql.Types.TIMESTAMP);
						break;
				case O: cst.registerOutParameter(objP.getParanomb(), java.sql.Types.NUMERIC);
						break;
				case B: cst.registerOutParameter(objP.getParanomb(), java.sql.Types.BOOLEAN);
						break;
				case F: cst.registerOutParameter(objP.getParanomb(), java.sql.Types.NUMERIC);
						break;
				default:break;
				}
			}
		}				        				        
		
		return cst;
	}
	
	/**
	 * Metodo para obtener el resultado de cada variable de salida del procedimiento almacenado
	 * @value fp = listado de los parametros que son usados del procedimiento
	 * @value cst= callablestatement, para obtener los valores del procedimiento ejecutado
	 * @return Map<String, Object> = mapa clave, valor con los resultados de las variables de salida del procedimiento almaceado ejecutado
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getParametersOutput(final List<FrmParametro> fp, CallableStatement cst) throws SQLException {

		Map<String, Object> o= new HashMap<String, Object>();
		
		for(FrmParametro objP:fp){			
			if(objP.getParatipo().equals("O")){				
				typesData typeData=typesData.valueOf(objP.getParatida());
				switch(typeData){
					case S: o.put(objP.getParanomb(), cst.getString(objP.getParanomb()));
							break;
					case CS: o.put(objP.getParanomb(), cst.getString(objP.getParanomb()));
							break;
					case TA: o.put(objP.getParanomb(), cst.getString(objP.getParanomb()));
							break;
					case TL: o.put(objP.getParanomb(), cst.getString(objP.getParanomb()));
							break;						
					case D: o.put(objP.getParanomb(), cst.getDate(objP.getParanomb()));
							break;
					case I: o.put(objP.getParanomb(), cst.getInt(objP.getParanomb()));
							break;
					case CI: o.put(objP.getParanomb(), cst.getInt(objP.getParanomb()));
							break;		
					case L: o.put(objP.getParanomb(), cst.getLong(objP.getParanomb()));
							break;
					case T: o.put(objP.getParanomb(), cst.getTimestamp(objP.getParanomb()));
							break;
					case O: o.put(objP.getParanomb(), cst.getDouble(objP.getParanomb()));
							break;
					case B: o.put(objP.getParanomb(), cst.getBoolean(objP.getParanomb()));
							break;
					case F: o.put(objP.getParanomb(), cst.getFloat(objP.getParanomb()));
							break;
					default:break;
				}
			 }
		}	
		
	    return o;
	}
}

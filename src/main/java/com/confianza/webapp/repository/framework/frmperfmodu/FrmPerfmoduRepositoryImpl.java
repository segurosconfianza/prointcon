package com.confianza.webapp.repository.framework.frmperfmodu;

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
public class FrmPerfmoduRepositoryImpl implements FrmPerfmoduRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FrmPerfmodu por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmPerfmodu = objeto de la case FrmPerfmodu que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmPerfmodu list(Long id){
		try{
			String sql = "select pemocons ,pemopefi ,pemomoro "
					   + "from FrmPerfmodu "
					   + "where pemocons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmPerfmodu.class)					
					     .setParameter("id", id);
			return (FrmPerfmodu)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmPerfmodu
	 * @return FrmPerfmodu = coleccion de objetos de la case FrmPerfmodu que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<Object[]> listAll(int init, int limit, Long pemopefi){
		try{
			String sql = "select pemocons, pemopefi ,pemomoro, morocons, moromodu, mororope, moducons, moduapli, modunomb, modunemo, modudurl, ropecons, ropenomb, ropedesc, ropetipo, aplicons, aplinomb, aplidesc, apliesta, aplifecr, tablvast "
					   + "from Frm_Perfmodu "
					   + "join Frm_modurope     on (morocons=pemomoro) "
					   + "join Frm_Modulo       on (moducons=moromodu) "
					   + "join Frm_Aplicaciones on (aplicons=moduapli) "
					   + "join Frm_Roleperm     on (ropecons=mororope) "
					   + "join Frm_Tablas       on (tablmodu=10 and tablcodi='ropetipo' and tablclav=ropetipo) "
					   + "where pemopefi = :pemopefi order by moducons";
						
			Query query = getSession().createSQLQuery(sql)						 
						 .setParameter("pemopefi", pemopefi);
			
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
	 * Metodo de consulta para el conteo de los registros de la tabla FrmPerfil
	 * @return int = cantidad de registros encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int getCount(){
		try{
			String sql = "select count(*) "
					   + "from FrmPerfmodu ";
						
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
	 * Metodo para actualizar los datos de un registro de la tabla FrmPerfmodu por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmPerfmodu = objeto de la case FrmPerfmodu que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmPerfmodu update(FrmPerfmodu frmperfmodu){
		getSession().update(frmperfmodu);
		return frmperfmodu;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FrmPerfmodu por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmPerfmodu = objeto de la case FrmPerfmodu que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmPerfmodu delete(FrmPerfmodu frmperfmodu){
		getSession().delete(frmperfmodu);
		return frmperfmodu;
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FrmPerfmodu
	 * @value pemocons
	 * @value pemopefi
	 * @value pemomoro
	 * @return FrmPerfmodu = objeto de la case FrmPerfmodu que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmPerfmodu insert(FrmPerfmodu frmperfmodu){
		getSession().save(frmperfmodu);
		return frmperfmodu;
	}
	
	/**
	 * Metodo de consulta para modulos rope en combo
	 * @return FrmPerfmodu = coleccion de objetos de la case FrmPerfmodu que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<Object[]> listComboMoro(){
		try{
			String sql = "select morocons, MODUNOMB || '-' ||ROPENOMB "
					   + "from Frm_modurope "
					   + "join Frm_Modulo       on (moducons=moromodu) "
					   + "join Frm_Roleperm     on (ropecons=mororope) "
					   + "order by moducons";
						
			Query query = getSession().createSQLQuery(sql);						
					     
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}	
}

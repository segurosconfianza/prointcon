package com.confianza.webapp.repository.framework.frmmenu;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FrmMenuRepositoryImpl implements FrmMenuRepository{
	
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
	 * Metodo de consulta para los registros de la tabla FrmMenu por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmMenu = objeto de la case FrmMenu que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmMenu list(Long id){
		try{
			String sql = "select menucons ,menupadr ,menumodu ,menuicon ,menutitu ,menuorde ,menuesta "
					   + "from FrmMenu "
					   + "where menucons = :id ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmMenu.class)					
					     .setParameter("id", id);
			return (FrmMenu)query.uniqueResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Metodo de consulta para los registros de la tabla FrmMenu
	 * @return FrmMenu = coleccion de objetos de la case FrmMenu que contiene los datos encontrados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<FrmMenu> listAll(){
		try{
			String sql = "select menucons ,menupadr ,menumodu ,menuicon ,menutitu ,menuorde ,menuesta "
					   + "from FrmMenu ";
						
			Query query = getSession().createSQLQuery(sql)
						 .addEntity(FrmMenu.class);
					     
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}	
	
	/**
	 * Metodo para actualizar los datos de un registro de la tabla FrmMenu por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmMenu = objeto de la case FrmMenu que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmMenu update(Long id){
		FrmMenu frmmenu = this.list(id);
		getSession().update(frmmenu);
		return frmmenu;
	}
	
	/**
	 * Metodo para borrar un registro de la tabla FrmMenu por id
	 * @value id = id de la llave primaria a consultar el registro
	 * @return FrmMenu = objeto de la case FrmMenu que contiene los datos encontrados dado el id
	 * @throws Exception
	 */
	@Override
	@Transactional
	public void delete(Long id){
		FrmMenu frmmenu = this.list(id);
		//FrmMenu.setEstado="B";
		getSession().update(frmmenu);
	}
	
	/**
	 * Metodo para ingresar un registro de la tabla FrmMenu
	 * @value menucons
	 * @value menupadr
	 * @value menumodu
	 * @value menuicon
	 * @value menutitu
	 * @value menuorde
	 * @value menuesta
	 * @return FrmMenu = objeto de la case FrmMenu que contiene los datos ingresados
	 * @throws Exception
	 */
	@Override
	@Transactional
	public FrmMenu insert(FrmMenu frmmenu){
		//getSession().insert(frmmenu);
		return frmmenu;
	}
	
	@Override
	@Transactional
	public List<Object[]> loadMenu(List<String> roles, Long id) {
		try{
			String sql;
			Query query;
			
			if(id==null){
			    sql= "select menucons, menuicon, menutitu, nvl(modudurl,' '), ' ' hijos "
				   + "from frm_modulo "
				   + "join frm_menu  on (menumodu=moducons) "
				   + "where modunomb in (:roles) and menuesta='A' and menupadr is null "
				   + "order by menuorde,menupadr ";
				    
				query = getSession().createSQLQuery(sql)					
				        .setParameterList("roles", roles);
			}
			else{
				sql= "select menucons, menuicon, menutitu, nvl(modudurl,' '), ' ' hijos "
				   + "from frm_modulo "
				   + "join frm_menu  on (menumodu=moducons) "
				   + "where modunomb in (:roles) and menuesta='A' and menupadr=:id "
				   + "order by menuorde,menupadr ";
				query = getSession().createSQLQuery(sql)					
					    .setParameterList("roles", roles)
					    .setParameter("id", id);	
			}
					
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}

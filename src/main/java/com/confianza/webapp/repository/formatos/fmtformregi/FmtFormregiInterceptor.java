package com.confianza.webapp.repository.formatos.fmtformregi;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		formatos  
  */                          


import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.confianza.webapp.repository.formatos.fmtauditoria.FmtAuditoria;

@Repository
public class FmtFormregiInterceptor extends EmptyInterceptor{
	
	private SessionFactory sessionFactory;  	
	
	private Set inserts = new HashSet();
	private Set updates = new HashSet();
	private Set deletes = new HashSet();
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		
		return sessionFactory.getCurrentSession();
	}
	
	public boolean onSave(Object entity,Serializable id, Object[] state,String[] propertyNames,Type[] types) throws CallbackException {
	 
		System.out.println("onSave");
		inserts.add(entity);
 		
		return true;
 
	}
	
	public boolean onFlushDirty(Object entity,Serializable id, Object[] currentState,Object[] previousState, String[] propertyNames,Type[] types) throws CallbackException {
 
		System.out.println("onFlushDirty");
		updates.add(entity);
		
		return true;
 
	}
	 
	public void onDelete(Object entity, Serializable id,  Object[] state, String[] propertyNames, Type[] types) {
 
		System.out.println("onDelete");
		deletes.add(entity);
		
	}
	 
	//called before commit into database
	public void preFlush(Iterator iterator) {
		System.out.println("preFlush");
	}	
	 
	//called after committed into database
	public void postFlush(Iterator iterator) {
		System.out.println("postFlush");
 
		try{
	 
			for (Iterator it = inserts.iterator(); it.hasNext();) {
				FmtAuditoria entity = (FmtAuditoria) it.next();
			    System.out.println("postFlush - insert");		
			    //AuditLogUtil.LogIt("Saved",entity, getSession().connection());
			}	
	 
			for (Iterator it = updates.iterator(); it.hasNext();) {
				FmtAuditoria entity = (FmtAuditoria) it.next();
			    System.out.println("postFlush - update");
			    //AuditLogUtil.LogIt("Updated",entity, session.connection());
			}	
	 
			for (Iterator it = deletes.iterator(); it.hasNext();) {
				FmtAuditoria entity = (FmtAuditoria) it.next();
			    System.out.println("postFlush - delete");
			    //AuditLogUtil.LogIt("Deleted",entity, session.connection());
			}	
	 
		} finally {
			inserts.clear();
			updates.clear();
			deletes.clear();
		}
	}		
}

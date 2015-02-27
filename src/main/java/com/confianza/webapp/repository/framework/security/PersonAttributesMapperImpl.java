package com.confianza.webapp.repository.framework.security;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.apache.poi.util.SystemOutLogger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonAttributesMapperImpl implements PersonAttributesMapper,AttributesMapper<Person>{
	
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
	
	@Override
	public Person mapFromAttributes(Attributes attrs) throws NamingException {	    	     	
   	 try{
   		 Person person = new Person();
	   		 try{
		       	 person.setsAMAccountName((String)attrs.get("sAMAccountName").get());
	         }
		        catch(Exception e){
		   		 System.out.println("Error en el param sAMAccountName");
		   		 e.printStackTrace();
		   	 }
   		 	 try{
	        	 person.setFullName((String)attrs.get("cn").get());
	         }
	         catch(Exception e){
	    		 System.out.println("Error en el param cn");
	    		 e.printStackTrace();
	    	 }
	         try{
	        	 person.setLastName((String)attrs.get("sn").get());
	         }
	         catch(Exception e){
	    		 System.out.println("Error en el param sn");
	    		 e.printStackTrace();
	    	 }
	         try{
	        	 person.setDescription((String)attrs.get("description").get());
	         }
	         catch(Exception e){
	    		 System.out.println("Error en el param description");
	    		 e.printStackTrace();
	    	 }	
	         try{
	        	 //inicializo la lista de perfiles
	        	 List<String> memberof = new ArrayList();
	        	 String grupo;
	        	 //tomo el atributo memberOf y lo agrego uno a uno a mi lista de perfiles
	             for (Enumeration vals = attrs.get("memberOf").getAll(); vals.hasMoreElements();){
	            	 //tomo solo el valor del groupo de toda la cadena que contiene memberOf
	            	 grupo=vals.nextElement().toString().toUpperCase();
	                 memberof.add(new DistinguishedName(grupo).removeLast().getValue());
	             }
	             //asigno los perfiles a la persona
	             person.setGroups(memberof);
	         }
	         catch(Exception e){
	    		 System.out.println("Error en el param memberOf");
	    		 e.printStackTrace();
	    	 }	
	         
	         return person;
        }catch(Exception e){
   		 e.printStackTrace();
   		 return null;
   	    }
     }
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object[]> loadRoles(List<String> perfiles) {
				
		try{
			String sql = "select modunomb, ropenomb "
					   + "from frm_perfil "
					   + "join frm_perfmodu on (pemopefi=peficons) "
					   + "join frm_modurope on (morocons=pemomoro) "
					   + "join frm_roleperm on (ropecons=mororope) "
					   + "join frm_modulo   on (moducons=moromodu) "
					   + "where pefinomb in (:perfiles)";
						
			Query query = getSession().createSQLQuery(sql)					
					     .setParameterList("perfiles", perfiles);
			
			return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}	
	
	@Override
	@Transactional
	public boolean validateRoles(String[] roles) {
				
		try{
			String sql = "select modunomb, ropenomb "
					   + "from frm_perfil "
					   + "join frm_perfmodu on (pemopefi=peficons) "
					   + "join frm_modurope on (morocons=pemomoro) "
					   + "join frm_roleperm on (ropecons=mororope) "
					   + "join frm_modulo   on (moducons=moromodu) "
					   + "where modunomb ||'_'|| ropenomb in (:roles)";
						
			Query query = getSession().createSQLQuery(sql)					
					     .setParameterList("roles", roles);
			
			if(query.list().size()>0)
				return true;
			else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}	
	
}

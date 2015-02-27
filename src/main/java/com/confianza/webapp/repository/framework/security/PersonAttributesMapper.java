package com.confianza.webapp.repository.framework.security;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

public interface PersonAttributesMapper{

	public List<Object[]> loadRoles(List<String> perfiles);
	public Person mapFromAttributes(Attributes attrs) throws NamingException;
	public boolean validateRoles(String[] roles);

	
}

package com.confianza.webapp.service.security;

import java.util.List;

public interface RolService {

	public List<Object[]> loadRoles(List<String> perfiles);

	public boolean validateRoles(String perfiles);
	
}

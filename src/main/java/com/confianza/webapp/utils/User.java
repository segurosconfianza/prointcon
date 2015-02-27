package com.confianza.webapp.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class User {

	private Authentication auth;
	
	public User(){
		auth = SecurityContextHolder.getContext().getAuthentication();
	}
	
	public String getUser(){
		return auth.getName();
	}
}

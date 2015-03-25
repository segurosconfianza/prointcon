package com.confianza.webapp.service.security;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.confianza.webapp.repository.framework.security.Person;
import com.confianza.webapp.repository.framework.security.PersonAttributesMapperImpl;

public class userDetailsService implements UserDetailsService, AuthenticationUserDetailsService{

	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Autowired
	private RolService rolService;
	
	private String userName;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("username is:"+username);
		return null;
	}

	@Override
	public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		userName=token.getName();
		
		Person persona=loadPersonLdap();
		
		List<Object[]> roles=rolService.loadRoles(persona.getGroups());
		
		UserDetails user = new User(userName, token.getCredentials().toString(), true, true, true, true, assignRolesForGrups(roles));
		
		return user;
	}
	
	private Person loadPersonLdap(){
		List<Person> lista =  ldapTemplate.search(query().	  
       		 filter("(&(objectClass=person)(sAMAccountName="+userName+"))"),
       		 new PersonAttributesMapperImpl());
		
		return lista.get(0);
	}
	
	private List<GrantedAuthority> assignRolesForGrups(List<Object[]> roles){
		
		List<GrantedAuthority> autorities = new ArrayList<GrantedAuthority>();		
		
		for(Object[] obj:roles)
			autorities.add(new SimpleGrantedAuthority(obj[0].toString()+"_"+obj[1].toString()));
				
		return autorities;
	}

}

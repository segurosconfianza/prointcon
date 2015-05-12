package com.confianza.webapp.service.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.confianza.webapp.repository.framework.frmsesion.FrmSesion;
import com.confianza.webapp.service.framework.frmsesion.FrmSesionService;

@Service
public class userDetails {	
	
	@Autowired
	private FrmSesionService frmSesionService;		
	
	public void onAuthenticationSuccess(){       
	    
    	HttpSession session=this.getSession();
    	
    	FrmSesion frmSesion = (FrmSesion) session.getAttribute("frmSesion");
    	
    	if(frmSesion==null){
	        
	    	String sesion = session.getId();
	    	
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	String user = authentication.getName();
	    	
	    	FrmSesion frmSesionNew=frmSesionService.insert(user, sesion);
	        //asigno la session del usuario al HttpSession para luego ser tomada
	        
	        session.setAttribute("frmSesion", frmSesionNew);
		}        
		
    }
	
	public static HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(); // true == allow create
	}
	
	public List<String> getRoles() {
		
		List<String> roles = new ArrayList<String>();
		List<GrantedAuthority> autorities = new ArrayList<GrantedAuthority>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {			 
			autorities=(List<GrantedAuthority>) auth.getAuthorities();
			for(GrantedAuthority obj:autorities){
				String[] auxRol= obj.toString().split("_");
				String rol="";
				for(int i=0;i<auxRol.length-1;i++){				
					if(rol.isEmpty())
						rol+=auxRol[i];
					else
						rol+="_"+auxRol[i];
				}
				roles.add(rol);	
			}
	    }
		
		return roles;
	}
	
	public String getUser() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {			 
			return auth.getName();
	    }
		
		return null;
	}

}

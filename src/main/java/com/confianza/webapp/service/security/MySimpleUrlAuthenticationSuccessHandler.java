package com.confianza.webapp.service.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.confianza.webapp.repository.framework.frmsesion.FrmSesion;
import com.confianza.webapp.service.framework.frmsesion.FrmSesionService;

public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 
    @Autowired
	private FrmSesionService frmSesionService;
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
    	System.out.println("onAuthenticationSuccess");
    	
    	String sesion = request.getRequestedSessionId();
        FrmSesion frmSesion=frmSesionService.insert(authentication.getName(), sesion);
		
        //asigno la session del usuario al HttpSession para luego ser tomada
        HttpSession session = request.getSession();        
        session.setAttribute("frmSesion", frmSesion);
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
    }	        
}
package com.confianza.webapp.aop.controller;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.confianza.webapp.repository.framework.frmconsulta.FrmConsultaRepository;
import com.confianza.webapp.repository.framework.frmsesion.FrmSesion;
import com.confianza.webapp.service.framework.frmsesion.FrmSesionService;
import com.google.gson.Gson;

@Service
@Aspect
public class ControllerAOPImpl{
	
	@Autowired
	Gson gson;
		
	@Autowired
	private FrmSesionService frmSesionService;	
	
	public ControllerAOPImpl(){
		
	}
	
	@Pointcut("execution(* com.confianza.webapp.controller..*(..))")
	public void pointIntercepController(){		
	}
	
	@Around("pointIntercepController()")
	public String interceptController(ProceedingJoinPoint point) throws Throwable{
		try{
			this.onAuthenticationSuccess();
			return (String) point.proceed();
		}catch(AccessDeniedException e){
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tituloError", "Acceso denegado");
			result.put("error", "No posee los permisos para esta accion");
			return gson.toJson(result);
		}
	}
	
	private void onAuthenticationSuccess(){       
	    
		try{
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
    	}catch(Exception e){}
		
    }
	
	private static HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(); // true == allow create
	}
}

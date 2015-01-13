package com.confianza.webapp.service.framework.frmlogext;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmlogext.FrmLogext;
import com.confianza.webapp.repository.framework.frmlogext.FrmLogextRepository;

@Service
@Aspect
public class FrmLogextServiceImpl implements FrmLogextService{
	
	@Autowired
	private FrmLogextRepository frmlogextRepository;
	
	/**
	 * @return the frmlogextRepository
	 */
	public FrmLogextRepository getFrmLogextRepository() {
		return frmlogextRepository;
	}

	/**
	 * @param frmlogextRepository the frmlogextRepository to set
	 */
	public void setFrmLogextRepository(FrmLogextRepository frmlogextRepository) {
		this.frmlogextRepository = frmlogextRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMLOGEXT__ALL", "APP_FRMLOGEXT__READ"})
	public FrmLogext list(Long id){
		return frmlogextRepository.list(id);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMLOGEXT__ALL", "APP_FRMLOGEXT__READ"})
	public List<FrmLogext> listAll(int pageSize, int page){
	
		int limit=pageSize*page;
		int init=limit-pageSize;
		
		return frmlogextRepository.listAll(init, limit);
	}	
	
	@Override
	public int getCount(){
				
		return frmlogextRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMLOGEXT__ALL", "APP_FRMLOGEXT__UPDATE"})
	public FrmLogext update(FrmLogext frmlogext){
		return frmlogextRepository.update(frmlogext);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMLOGEXT__ALL", "APP_FRMLOGEXT__DELETE"})
	public void delete(FrmLogext frmlogext){
		frmlogextRepository.delete(frmlogext);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "APP_FRMLOGEXT__ALL", "APP_FRMLOGEXT__CREATE"})
	public FrmLogext insert(FrmLogext frmlogext){
		return frmlogextRepository.insert(frmlogext);
	}
					
	@Pointcut("execution(* com.confianza.webapp.controller.framework.frmconsulta.CFrmConsulta.loadRecord(..))")
	public void pointLogext(){		
	}
	
	@Before("pointLogext()")
	public void interceptarLoadRecord(JoinPoint point) throws Throwable{
		/*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		String remoteAddress = request.getRemoteAddr();	
		InetAddress IP=InetAddress.getByName(remoteAddress);
		System.out.println("InetAddress.getHostAddress(): "+IP.getHostAddress());
		System.out.println("InetAddress.getHostName(): "+IP.getHostName());
		System.out.println("InetAddress.getLoopbackAddress(): "+IP.getLoopbackAddress());
		System.out.println("InetAddress.getCanonicalHostName(): "+IP.getCanonicalHostName());						
		System.out.println("InetAddress.toString(): "+IP.toString());
		System.out.println("InetAddress.getAddress(): "+IP.getAddress());		
		
		String[] HEADERS_TO_TRY = { 
		    "X-Forwarded-For",
		    "Proxy-Client-IP",
		    "WL-Proxy-Client-IP",
		    "HTTP_X_FORWARDED_FOR",
		    "HTTP_X_FORWARDED",
		    "HTTP_X_CLUSTER_CLIENT_IP",
		    "HTTP_CLIENT_IP",
		    "HTTP_FORWARDED_FOR",
		    "HTTP_FORWARDED",
		    "HTTP_VIA",
		    "REMOTE_ADDR" };
		
		String info="";
		for (String header : HEADERS_TO_TRY) {
	        info += header + ": " + request.getHeader(header) + ". ";	        
	    }
		System.out.println("info: ");
		
		//Se llama al método de negocio (método objetivo)
		joinpoint.proceed();*/
		System.out.println("info: //////////////////////////*********************************************///////////////////////////////////////");
	}
	
	@Override
	public void insert(HttpServletRequest request, FrmConsulta frmConsulta, String data) throws Throwable{
		FrmLogext frmlogext=new FrmLogext();
		
		String remoteAddress = request.getRemoteAddr();	
		InetAddress IP=InetAddress.getByName(remoteAddress);
		
		String InetAddress = "HostAddress(): "+IP.getHostAddress()
							+"\nHostName(): "+IP.getHostName()
							+"\nLoopbackAddress(): "+IP.getLoopbackAddress()
							+"\nCanonicalHostName(): "+IP.getCanonicalHostName()
							+"\nInetAddress: "+IP.toString()
							+"\nAddress(): "+IP.getAddress();
		
		String[] HEADERS_TO_TRY = { 
		    "X-Forwarded-For",
		    "Proxy-Client-IP",
		    "WL-Proxy-Client-IP",
		    "HTTP_X_FORWARDED_FOR",
		    "HTTP_X_FORWARDED",
		    "HTTP_X_CLUSTER_CLIENT_IP",
		    "HTTP_CLIENT_IP",
		    "HTTP_FORWARDED_FOR",
		    "HTTP_FORWARDED",
		    "HTTP_VIA",
		    "REMOTE_ADDR" };
		
		String info="\n";
		for (String header : HEADERS_TO_TRY) {
	        info += "\n"+header + ": " + request.getHeader(header) + ". ";	        
	    }
		
		frmlogext.setLoexacci(frmConsulta.getConstipo());
		frmlogext.setLoexdaco(InetAddress+info);
		frmlogext.setLoexdata(data);
		frmlogext.setLoexfecr(new Date());
		frmlogext.setLoextabl("Consulta id: "+frmConsulta.getConscons());
		frmlogextRepository.insert(frmlogext);
	}
}

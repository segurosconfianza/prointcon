package com.confianza.webapp.aop.pil.pillog;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.confianza.webapp.repository.framework.frmsesion.FrmSesion;
import com.confianza.webapp.repository.framework.frmtransaccion.FrmTransaccion;
import com.confianza.webapp.repository.pila.pillog.PilLog;
import com.confianza.webapp.repository.pila.pilususucu.PilUsusucu;
import com.confianza.webapp.service.framework.frmsesion.FrmSesionService;
import com.confianza.webapp.service.framework.frmtransaccion.FrmTransaccionService;
import com.confianza.webapp.service.pila.pillog.PilLogService;
import com.confianza.webapp.service.security.userDetails;
import com.google.gson.Gson;

@Service
@Aspect
public class PilLogAOPImpl{
	
	@Autowired
	private Gson gson;		
	
	@Autowired
	private PilLogService pilLogService;
		
	@Autowired
	private FrmTransaccionService frmTransaccionService;
	
	@Autowired
	private FrmSesionService frmSesionService;
	
	@Autowired
	userDetails userDetails;  		

	@Pointcut("execution(* com.confianza.webapp.repository.pila.pilususucu.PilUsusucuRepositoryImpl.insert(..))")	
	public void pointInsertRecordPilUsusucu(){		
	}
	
	@Around("pointInsertRecordPilUsusucu()")
	public PilUsusucu interceptInsertRecordPilUsusucu(ProceedingJoinPoint point) throws Throwable{
		PilUsusucu pilUsusucu=(PilUsusucu) point.proceed();		
		
		CreateLog(pilUsusucu.toString(), "PilUsusucu", userDetails.getUser());
		return pilUsusucu;
	}
	
	private void CreateLog(String registro, String tabla, String user) {
		
		FrmTransaccion frmtransaccion;
		FrmSesion frmSesion=(FrmSesion) getSession().getAttribute("frmSesion");
		if(frmSesion==null)
			frmtransaccion = createTransactionIntermediario(user);
		else
			frmtransaccion=frmTransaccionService.insert(frmSesion.getSesicons());
		
		PilLog pilLog=new PilLog();
		pilLog.setSlogacci("INSERT");
		pilLog.setSlogregi(registro);
		pilLog.setSlogtabl(tabla);
		pilLog.setSlogtran(frmtransaccion.getTrancons());
		
		pilLogService.insert(pilLog);
	}

	//si es una peticion de intermediario no tendra la sesion iniciada en este backbone por lo tanto es caso especial
	private FrmTransaccion createTransactionIntermediario(String user) {
		FrmSesion frmSesion=frmSesionService.insert(user, "INTERMEDIARIO");		
		FrmTransaccion frmtransaccion=frmTransaccionService.insert(frmSesion.getSesicons());
		return frmtransaccion;
	}			
	
	private static HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(); // true == allow create
	}
}

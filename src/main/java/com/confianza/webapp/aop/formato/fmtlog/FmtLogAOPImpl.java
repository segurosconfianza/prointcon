package com.confianza.webapp.aop.formato.fmtlog;

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

import com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregi;
import com.confianza.webapp.repository.formatos.fmtlog.FmtLog;
import com.confianza.webapp.repository.formatos.fmtvalocamp.FmtValocamp;
import com.confianza.webapp.repository.framework.frmsesion.FrmSesion;
import com.confianza.webapp.repository.framework.frmtransaccion.FrmTransaccion;
import com.confianza.webapp.service.formatos.fmtformregi.FmtFormregiService;
import com.confianza.webapp.service.formatos.fmtlog.FmtLogService;
import com.confianza.webapp.service.formatos.fmtvalocamp.FmtValocampService;
import com.confianza.webapp.service.framework.frmsesion.FrmSesionService;
import com.confianza.webapp.service.framework.frmtransaccion.FrmTransaccionService;
import com.google.gson.Gson;

@Service
@Aspect
public class FmtLogAOPImpl{
	
	@Autowired
	private Gson gson;		
	
	@Autowired
	private FmtLogService fmtLogService;
	
	@Autowired
	private FmtFormregiService fmtFormregiService;
	
	@Autowired
	private FrmTransaccionService frmTransaccionService;
	
	@Autowired
	private FrmSesionService frmSesionService;
	
	@Pointcut("execution(* com.confianza.webapp.repository.formatos.fmtformregi.FmtFormregiRepositoryImpl.insert(..))")	
	public void pointInsertRecordFmtFormregi(){		
	}
	
	@Around("pointInsertRecordFmtFormregi()")
	public FmtFormregi interceptInsertRecordFmtFormregi(ProceedingJoinPoint point) throws Throwable{
		FmtFormregi fmtformregi=(FmtFormregi) point.proceed();
				
		CreateLog(fmtformregi.toString(), "FmtFormregi", fmtformregi.getForeuser());
		return fmtformregi;
	} 
	
	@Pointcut("execution(* com.confianza.webapp.repository.formatos.fmtvalocamp.FmtValocampRepositoryImpl.insert(..))")	
	public void pointInsertRecordFmtValocamp(){		
	}
	
	@Around("pointInsertRecordFmtValocamp()")
	public FmtValocamp interceptInsertRecordFmtValocamp(ProceedingJoinPoint point) throws Throwable{
		FmtValocamp fmtValocamp=(FmtValocamp) point.proceed();
		FmtFormregi fmtFormregi=fmtFormregiService.listEntity(fmtValocamp.getVacafore());
		
		CreateLog(fmtValocamp.toString(), "FmtValocamp", fmtFormregi.getForeuser());
		return fmtValocamp;
	}

	private void CreateLog(String registro, String tabla, String user) {
		
		FrmTransaccion frmtransaccion;
		FrmSesion frmSesion=(FrmSesion) getSession().getAttribute("frmSesion");
		if(frmSesion==null)
			frmtransaccion = createTransactionIntermediario(user);
		else
			frmtransaccion=frmTransaccionService.insert(frmSesion.getSesicons());
		
		FmtLog fmtLog=new FmtLog();
		fmtLog.setSlogacci("INSERT");
		fmtLog.setSlogregi(registro);
		fmtLog.setSlogtabl(tabla);
		fmtLog.setSlogtran(frmtransaccion.getTrancons());
		
		fmtLogService.insertIntermediario(fmtLog);
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

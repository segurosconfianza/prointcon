package com.confianza.webapp.aop.framework.frmauditoria;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;



import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.confianza.webapp.repository.framework.frmauditoria.FrmAuditoria;
import com.confianza.webapp.repository.framework.frmlog.FrmLog;
import com.confianza.webapp.repository.framework.frmsesion.FrmSesion;
import com.confianza.webapp.repository.framework.frmtransaccion.FrmTransaccion;
import com.confianza.webapp.service.framework.frmarchivo.FrmArchivoService;
import com.confianza.webapp.service.framework.frmauditoria.FrmAuditoriaService;
import com.confianza.webapp.service.framework.frmlog.FrmLogService;
import com.confianza.webapp.service.framework.frmtransaccion.FrmTransaccionService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
@Aspect
public class FrmAuditoriaAOPImpl{
	
	@Autowired
	private Gson gson;		
	
	@Autowired
	private FrmLogService frmLogService;
	
	@Autowired
	private FrmAuditoriaService frmAuditoriaService;	
	
	@Autowired
	private FrmTransaccionService frmTransaccionService;
	
	@Autowired
	private FrmArchivoService frmArchivoService;
	
	@Pointcut("execution(* com.confianza.webapp.service.framework.frmconsulta.FrmConsultaServiceImpl.updateRecord(..))")
	public void pointUpdateRecord(){		
	}
		
	@Around("pointUpdateRecord()")
	public String interceptUpdateRecord(ProceedingJoinPoint point) throws Throwable{
		
		try{
			String result=(String)point.proceed();
			
			Type type = new TypeToken<Map<String, Object>>(){}.getType();
			Map<String, Object> resultData=gson.fromJson(result, type);
			
			if(resultData.get("AUDITORIA")!=null){
				String []auditoria=resultData.get("AUDITORIA").toString().split("--//--");
				String campos[];
				//recupero la sesion del usuario
				
				FrmSesion frmSesion = (FrmSesion) getSession().getAttribute("frmSesion");
				//creo la transaccion de este proceso
				FrmTransaccion frmtransaccion=frmTransaccionService.insert(frmSesion.getSesicons());
				FrmAuditoria frmAuditoria;
				FrmLog frmLog;
				
				//creo la auditoria por cada campo actualizado
				generarAuditoriaPorRegistro(auditoria, frmtransaccion);
				
				resultData.remove("AUDITORIA");
				resultData.put("TRANSACCION", frmtransaccion.getTrancons().toString());
			}
			
			return gson.toJson(resultData);
		}catch(Exception e){
			e.printStackTrace();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tituloError", "Error");
			result.put("error", e.getMessage());
			return gson.toJson(result);
		}
	}

	private void generarAuditoriaPorRegistro(String[] auditoria, FrmTransaccion frmtransaccion) {
		String[] campos;
		
		for(String aux:auditoria){
			campos=aux.split("--,--");
			
			if(campos[0].equals("UPDATE") ){
				crearAuditoria(campos, frmtransaccion);
			}
			else {				
				crearLog(campos, frmtransaccion);
			}				
		}
	}

	private void crearAuditoria(String[] campos, FrmTransaccion frmtransaccion) {
		FrmAuditoria frmAuditoria;
		frmAuditoria=new FrmAuditoria();
		frmAuditoria.setAuditran(frmtransaccion.getTrancons());
		frmAuditoria.setAuditabl(campos[1]);
		frmAuditoria.setAudicopk(campos[2]);
		frmAuditoria.setAudicamp(campos[3]);
		if(campos[4].length()<=4000)
			frmAuditoria.setAudivaan(campos[4]);
		else
			frmAuditoria.setAudivaan("creando archivo");
		if(campos[5].length()<=4000)
			frmAuditoria.setAudivanu(campos[5]);
		else 
			frmAuditoria.setAudivanu("creando archivo");
		frmAuditoriaService.insert(frmAuditoria);
		
		try {
			if(campos[4].length()>4000){
				frmAuditoria.setAudivaan(frmArchivoService.ingresarArchivoSoporte(frmAuditoria.getAudicons()+"-Audivaan", (campos[4])));
			}
			if(campos[5].length()>4000){
				frmAuditoria.setAudivanu(frmArchivoService.ingresarArchivoSoporte(frmAuditoria.getAudicons()+"-Audivanu", (campos[5])));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frmAuditoriaService.update(frmAuditoria);
	}

	private void crearLog(String[] campos, FrmTransaccion frmtransaccion) {
		FrmLog frmLog;
		frmLog=new FrmLog();
		frmLog.setSlogtran(frmtransaccion.getTrancons());
		frmLog.setSlogtabl(campos[1]);
		frmLog.setSlogacci(campos[0]);
		frmLog.setSlogregi(campos[2]);
		frmLogService.insert(frmLog);
	}
	
	public static HttpSession getSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(); // true == allow create
	}
}

package com.confianza.webapp.aop.controller.frmarchivo;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.confianza.webapp.repository.framework.frmarchivo.FrmArchivo;
import com.confianza.webapp.repository.framework.frmconsulta.FrmConsultaRepository;
import com.confianza.webapp.utils.CFile;
import com.confianza.webapp.utils.FileImpl;
import com.confianza.webapp.utils.UploadFile;
import com.google.gson.Gson;

@Service
@Aspect
public class FrmArchivoAOPImpl{
	
	@Autowired
	Gson gson;
	
	public FrmArchivoAOPImpl(){
		
	}
	
	/*@Pointcut("execution(* com.confianza.webapp.service.framework.frmconsulta.FrmConsultaServiceImpl.updateRecord(..))")
	public void pointIntercepUpdateRecord(){		
	}
	
	@Around("pointIntercepUpdateRecord()")
	public String interceptUpdateRecord(ProceedingJoinPoint point) throws Throwable{
		try{
			String result=(String)point.proceed();
			
			Object[] arguments = point.getArgs();
			
			for(Object obj:arguments)
				System.out.println(obj.toString());		
			
			/*ArrayList<MultipartFile> file= (ArrayList<MultipartFile>) arguments[2];
			
			ArrayList<CFile> files=new FileImpl(file).getFiles();
			
			for(CFile obj:files){
				System.out.println(obj.toString());			
			}
						   
			//List<FrmArchivo> listAll						
			
			return  result;
		}catch(AccessDeniedException e){
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("tituloError", "Acceso denegado");
			result.put("error", "No posee los permisos para esta accion");
			return "";
		}
	}*/
		
}

package com.confianza.webapp.service.gobiernodatos.carga;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.confianza.webapp.repository.framework.frmconsulta.FrmConsulta;
import com.confianza.webapp.repository.framework.frmconsulta.FrmConsultaRepository;
import com.confianza.webapp.repository.framework.frmparametro.FrmParametro;
import com.confianza.webapp.service.framework.frmparametro.FrmParametroService;
import com.confianza.webapp.utils.CFile;
import com.confianza.webapp.utils.FileImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class GodConsultaServiceImpl implements GodConsultaService{
	
	@Autowired
	Gson gson;
	
	@Autowired
	private FrmConsultaRepository frmConsultaRepository;	
	
	@Autowired
	private FrmParametroService frmParametroService;
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "GOBIERNODATOS_ALL", "GOBIERNODATOS_UPDATE"})
	public String loadFiles(String conscons, String params, ArrayList<MultipartFile> file){
		
		try{
			
			//FrmConsulta frmConsulta=frmConsultaRepository.listProcedureChild(conscons);
			
			Type type = new TypeToken<Map<String, Object>>(){}.getType();
			Map<String, Object> parameters=gson.fromJson(params, type);//charsetString.convertUTF8ToISO88591(params)   
			
			FileImpl fileImpl=new FileImpl(file);
			ArrayList<CFile> files=fileImpl.getFiles();
			//Se toma cada archivo
			for(CFile obj: files){
				//por el archivo obtenido se toma cada linea
				Map<String, Object> p=getContentFileV(getContentFile(obj), conscons, parameters);
		        return gson.toJson(p);
			}
			
		}catch(Exception e){
			
		}
		return "";
	}

	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "SOPORTE_ALL", "SOPORTE_UPDATE"})
	public Map<String, Object> loadProcedure(FrmConsulta frmConsulta, List<FrmParametro> parametros, Map<String, Object> parameters, Map<String, Object> parametersData){
		
		if(frmConsulta.getConscaco().equals("dataSource")){
			return frmConsultaRepository.loadProcedure(frmConsulta, parametros, parameters, parametersData);
		}
		else if(frmConsulta.getConscaco().equals("dataSourceOsiris")){
			return frmConsultaRepository.loadProcedureOsiris(frmConsulta, parametros, parameters, parametersData);
		}
		return null;
		
	}
	
	private String getContentFile(CFile obj) {
		String contentFile="";
		for (int i = 0; i < obj.getBytes().length; i++) 
			contentFile+=(char)obj.getBytes()[i];
		return contentFile;
	} 
	
	private Map<String, Object> getContentFileV(String texto, String conscons, Map<String, Object> parameters){
		InputStream is = new ByteArrayInputStream(texto.getBytes());
		// read it with BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		Map<String, Object> p=null;
		FrmConsulta frmConsulta=null;
		FrmConsulta frmConsultaChild;
		try {
			
			while ((line = br.readLine()) != null) {
				//carga la consulta dinamica					
	    		frmConsulta=frmConsultaRepository.listProcedureChild(conscons);
	    		List<FrmParametro> parametros=frmParametroService.listParamsCosuType(frmConsulta.getConscons());
	    		parameters.put("DATOS", line);
	    		p=this.loadProcedure(frmConsulta, parametros, parameters, null);	
	    		if(!p.get("SUCCESS").equals("true"))
	    			return p;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ejecuta el procedimiento de validacion de datos
		frmConsultaChild=frmConsultaRepository.listProcedureChild(frmConsulta.getConscons().toString());
		System.out.println("consulta" +frmConsultaChild);
		if(frmConsultaChild!=null){
			List<FrmParametro> parametros=frmParametroService.listParamsCosuType(frmConsultaChild.getConscons());
			System.out.println("parametros" +frmConsulta);
			System.out.println("parametros" +parametros);
			p=this.loadProcedure(frmConsultaChild, parametros, null, null);
		}
		
		return p;
	}
	
	
}

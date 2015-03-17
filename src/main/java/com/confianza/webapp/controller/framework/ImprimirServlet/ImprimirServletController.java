package com.confianza.webapp.controller.framework.ImprimirServlet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptchaImpl;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import sun.net.www.http.HttpClient;

import com.google.gson.Gson;

@Controller
public class ImprimirServletController {

	
	@RequestMapping(value = "/ImprimirServlet", method = RequestMethod.POST)
	@ResponseBody
    public String validateCaptcha( ServletRequest servletRequest, @RequestParam("clave") String clave, @RequestParam("codpla") String codpla, @RequestParam("tipodoc") String tipodoc, @RequestParam("usuario") String usuario, @RequestParam("xml") String xml) throws Exception
    {		
		System.out.println("iniciando");  
		
		String remoteAddress = servletRequest.getRemoteAddr();
		String urlu = "http://192.168.100.202:8080/prueba/ImprimirServlet";		
		String urlParameters = "&clave="+clave+"&codpla="+codpla+"&tipodoc="+tipodoc+"&usuario="+usuario+"&xml="+xml;
		System.out.println("urlParameters: "+urlParameters);
		URL url = new URL(urlu);
		
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setDoInput(true);
		httpCon.setRequestProperty("Content-Type", "text/html;charset=ISO-8859-1");
		httpCon.setRequestMethod("POST");

		DataOutputStream wr = new DataOutputStream(httpCon.getOutputStream());		
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		//display what returns the POST request

		StringBuilder sb = new StringBuilder();  

		int HttpResult =httpCon.getResponseCode(); 
		BufferedReader br=null;
		
		if(HttpResult ==HttpURLConnection.HTTP_OK){

		    br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));  

		    String line = null;  

		    while ((line = br.readLine()) != null) {  
		    	sb.append(line.trim());  
		    }  

		    br.close();  

		}else{
		    System.out.println(httpCon.getResponseMessage());  
		}  
			
		
		System.out.println(sb.toString());
        return sb.toString();       
    }
		
}

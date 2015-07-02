package com.confianza.webapp.service.framework.frmarchivo;

 /**                          
  *                           
  * @modifico	CONFIANZA
  * @version	1.0 
  * @Fecha		30/10/2014 
  * @since		1.0            
  * @app		framework  
  */                          

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.confianza.webapp.repository.framework.frmarchivo.FrmArchivo;
import com.confianza.webapp.repository.framework.frmarchivo.FrmArchivoRepository;
import com.confianza.webapp.service.framework.frmtablas.FrmTablasService;
import com.confianza.webapp.utils.CFile;
import com.confianza.webapp.utils.FileImpl;
import com.google.gson.Gson;

@Service
public class FrmArchivoServiceImpl implements FrmArchivoService{ 
	
	@Autowired
	private FrmArchivoRepository frmArchivoRepository;
	
	@Autowired
	private FrmTablasService frmTablasService;
	
	@Autowired
	Gson gson;
	
	/**
	 * @return the frmarchivoRepository
	 */
	public FrmArchivoRepository getFrmArchivoRepository() {
		return frmArchivoRepository;
	}

	/**
	 * @param frmarchivoRepository the frmarchivoRepository to set
	 */
	public void setFrmArchivoRepository(FrmArchivoRepository frmarchivoRepository) {
		this.frmArchivoRepository = frmarchivoRepository;
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_ARCHIVO_ALL", "FRM_ARCHIVO_READ"})
	public String list(Long id){
		FrmArchivo listAll=frmArchivoRepository.list(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_ARCHIVO_ALL", "FRM_ARCHIVO_READ"})
	public String listAll(int pageSize, int page){
	
		int limit=pageSize*page;
		int init=limit-pageSize;
		
		List<FrmArchivo> listAll=frmArchivoRepository.listAll(init, limit);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", listAll);
		result.put("count", this.getCount());
		
		return gson.toJson(result);	
	}	
	
	@Override
	public int getCount(){
				
		return frmArchivoRepository.getCount();
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_ARCHIVO_ALL", "FRM_ARCHIVO_UPDATE"})
	public String update(FrmArchivo frmarchivo){
		return gson.toJson(frmArchivoRepository.update(frmarchivo));
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_ARCHIVO_ALL", "FRM_ARCHIVO_DELETE"})
	public void delete(FrmArchivo frmarchivo){
		frmArchivoRepository.delete(frmarchivo);
	}
	
	@Override
	@RolesAllowed({"ADMINISTRATOR_ADMINISTRATOR", "FRM_ARCHIVO_ALL", "FRM_ARCHIVO_CREATE"})
	public String insert(FrmArchivo frmarchivo){
		return gson.toJson(frmArchivoRepository.insert(frmarchivo));
	}
	
	@Override
	public List<FrmArchivo> ingresarArchivos(ArrayList<MultipartFile> file, String ruta) throws Exception{ 
		
		FileImpl fileImpl=new FileImpl(file);
		ArrayList<CFile> files=fileImpl.getFiles();		
		
		List<FrmArchivo> listAll=new ArrayList<FrmArchivo>();
		List<FrmArchivo> listAllMd5=frmArchivoRepository.listAllMd5(fileImpl.getMd5());
		
		if(listAllMd5!=null)
			listAll=listAllMd5;
		
		String rutaCompleta=frmTablasService.listByTablcodi(ruta).getTablvast();
		File dir = new File(rutaCompleta+(new Date().getYear()+1900)+"\\"+(new Date().getMonth()+1));
		if (!dir.exists())
            dir.mkdirs();
		
		for(CFile obj: files)
			if(!verificarArchivo(obj.getMd5(), listAllMd5)){
				FrmArchivo archivo = createArchivo(dir.getPath(), obj);
				
				archivo=frmArchivoRepository.insert(archivo);
				
				listAll.add(archivo);
												
				uploadFileServer(dir, obj);									
			}
		
		return listAll;
		
	}

	private FrmArchivo createArchivo(String dir, CFile obj) {
		FrmArchivo archivo=new FrmArchivo();
		archivo.setArchfech(new Date());
		archivo.setArchmd5(obj.getMd5());
		archivo.setArchmime(obj.getType());
		archivo.setArchnomb(obj.getName());
		archivo.setArchruta(dir);
		archivo.setArchsize(obj.getSize());
		return archivo;
	}

	private void uploadFileServer(File dir, CFile obj) throws Exception {
		// Create the file on server
		File serverFile = new File(dir.getAbsolutePath() + File.separator + obj.getName());
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(obj.getBytes());
		stream.close();
	}
	
	public boolean verificarArchivo(String md5, List<FrmArchivo> listAllMd5){
		
		for(FrmArchivo obj: listAllMd5)
			if(obj.getArchmd5().equals(md5))
				return true;
		return false;
	}
	
	@Override
	public String ingresarArchivoSoporte(String nombreArchivo, String valor) throws Exception{
		
		String ruta=frmTablasService.listByTablcodi("archsopo").getTablvast();
		File dir = new File(ruta+(new Date().getYear()+1900)+"\\"+(new Date().getMonth()+1));
		if (!dir.exists())
            dir.mkdirs();
		
		CFile obj=new CFile(nombreArchivo+".rtf", "application/msword", valor.getBytes());
		
		uploadFileServer(dir, obj);									
		
		return (new Date().getYear()+1900)+"\\\\"+(new Date().getMonth()+1)+"\\\\"+nombreArchivo;
	}		
	
	@Override
	public void getfrmArchivo(Long id, String adjunomb, HttpServletRequest request, HttpServletResponse response){
		try {
			  FrmArchivo frmArchivo=frmArchivoRepository.list(id);
			  // get absolute path of the application
			  ServletContext context = request.getSession().getServletContext();
		 
 	          // construct the complete absolute path of the file
		      String fullPath = frmArchivo.getArchruta()+"\\"+frmArchivo.getArchnomb();      
		      File downloadFile = new File(fullPath);
		      FileInputStream inputStream = new FileInputStream(downloadFile);
		         
		      // get MIME type of the file
		      String mimeType = setMime(context, fullPath);
		 		      		 
		      // set headers for the response
		      response=setHeaders(response, downloadFile, mimeType, adjunomb);
		 
		      downloadFile(response, frmArchivo, inputStream);
		      
		 } catch (IOException ex) {
		      System.out.println("Error writing file to output stream. Filename was '{}'"+ adjunomb + ex);
		      throw new RuntimeException("IOError writing file to output stream");
		 }
	}

	private void downloadFile(HttpServletResponse response, FrmArchivo frmArchivo, FileInputStream inputStream) throws IOException {
		// get output stream of the response
		OutputStream outStream = response.getOutputStream();
 
		byte[] buffer = new byte[Integer.parseInt(frmArchivo.getArchsize().toString())];
		int bytesRead = -1;
 
		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1)
		     outStream.write(buffer, 0, bytesRead);
 
		inputStream.close();
		outStream.close();
	}

	private HttpServletResponse setHeaders(HttpServletResponse response, File downloadFile, String mimeType, String adjunomb) {
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",adjunomb);
		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		response.setHeader(headerKey, headerValue);
		
		return response;
	}

	private String setMime(ServletContext context, String fullPath) {
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
		   // set to binary type if MIME mapping not found
		   mimeType = "application/octet-stream";
		}
		return mimeType;
	}
	
}

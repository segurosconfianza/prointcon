package com.confianza.webapp.utils;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

public class FileImpl {

	ArrayList<CFile> files;
	
	public FileImpl(ArrayList<MultipartFile> file) throws Exception {
		super();
		
		files=new ArrayList<CFile>();
		for(MultipartFile obj:file){
			CFile fileobj=new CFile(obj);
			
			files.add(fileobj);
		}
	}
	
	public ArrayList<CFile> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<CFile> files) {
		this.files = files;
	}

	public ArrayList<String> getMd5(){
		ArrayList<String> listAll=new ArrayList<String>();
		for(CFile obj:files){
			listAll.add(obj.getMd5());
		}
		return listAll;
	}

	
}

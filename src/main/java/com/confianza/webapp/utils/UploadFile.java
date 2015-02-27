package com.confianza.webapp.utils;

import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class UploadFile {

	public UploadFile(ArrayList<CFile> files) throws Exception {
		super();
		File dir = new File("D:\\Desktop\\pruebas\\UPLOAD\\"+ (new Date().getYear()+1900)+"\\"+(new Date().getMonth()+1));
		if (!dir.exists())
            dir.mkdirs();
		
		for(CFile obj:files){
			// Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + obj.getName());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(obj.getBytes());
            stream.close();
			
		}
	}

	
}

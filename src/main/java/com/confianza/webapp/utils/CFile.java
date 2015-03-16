package com.confianza.webapp.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class CFile {
	
	String name;
	String type;
	String md5;
	Long size;
	byte[] bytes;
	
	public CFile(MultipartFile multipartFile) throws Exception {
		super();
		this.name = multipartFile.getOriginalFilename();
		this.type = multipartFile.getContentType();
		this.md5 = castMd5(multipartFile);
		this.size = multipartFile.getSize();
		this.bytes = multipartFile.getBytes();
	}

	public CFile(String name, String type, byte[] bytes) throws Exception {
		super();
		this.name = name;
		this.type = type;
		this.bytes = bytes;
	}
	
	private String castMd5(MultipartFile multipartFile) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(multipartFile.getBytes());
		byte[] mdbytes = md.digest();
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) 
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        
        return sb.toString();
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getMd5() {
		return md5;
	}
	
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	public Long getSize() {
		return size;
	}
	
	public void setSize(Long size) {
		this.size = size;
	}
	
	public byte[] getBytes() {
		return bytes;
	}
	
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public String toString() {
		return "File [name=" + name + ", type=" + type + ", md5=" + md5 + ", size=" + size + "]";
	}	
	
}

package com.confianza.webapp.repository.framework.fileupload;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	private MultipartFile file;
	
	private String name;
	
	private String type;
	
	private double size;
	
	private byte[] bytes;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
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

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public FileUpload() {
		super();		
	}

	@Override
	public String toString() {
		return "FileUpload [file=" + file + ", name=" + name + ", type=" + type
				+ ", size=" + size + ", bytes=" + Arrays.toString(bytes) + "]";
	}
	
	
}

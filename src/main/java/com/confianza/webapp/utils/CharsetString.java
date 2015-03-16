package com.confianza.webapp.utils;

import java.io.UnsupportedEncodingException;

public class CharsetString {

	public CharsetString(){
	}
	
	public String convertISO88591ToUTF8(String stringConvert){
		try {
			return new String (stringConvert.getBytes ("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {		
			e.printStackTrace();
			return stringConvert;
		}
	}
	
	public String convertUTF8ToISO88591(String stringConvert){
		try {
			return new String (stringConvert.getBytes ("UTF-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {		
			e.printStackTrace();
			return stringConvert;
		}
	}
	
}

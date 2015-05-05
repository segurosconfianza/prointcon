package com.confianza.webapp.utils;

import java.io.IOException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import sun.misc.BASE64Decoder;

public class EncryptedDriverManagerDataSource extends DriverManagerDataSource{

	private static byte[] sharedvector = {
	    0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11
	    };
	
	@Override
    public String getPassword() {
        String password = super.getPassword();
        return decode(password);
    }
	
	@Override
	public String getUsername(){
		String username = super.getUsername();
        return decode(username);
	}
	
	@Override
	public String getUrl(){
		String url = super.getUrl();
        return decode(url);
	}
	
    /**
     * You can implement you own decoding method.
     */
    private String decode(String decode) {
    	BASE64Decoder decoder = new BASE64Decoder();
    	
		try {
			decode = new String(decoder.decodeBuffer(decode));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return decode;
    }
   
}

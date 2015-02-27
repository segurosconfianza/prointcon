package com.confianza.webapp.utils;

import java.io.IOException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sun.misc.BASE64Decoder;

import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.swing.JOptionPane;

import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

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

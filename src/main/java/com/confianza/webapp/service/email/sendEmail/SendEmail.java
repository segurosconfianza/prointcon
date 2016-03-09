package com.confianza.webapp.service.email.sendEmail;

import javax.servlet.http.HttpServletRequest;

public interface SendEmail {

	public boolean sendMessage(String app, String subject, String text, String to, String[] cc, HttpServletRequest request);

	boolean sendMessage(String app, String subject, String text, String to, String[] cc, String urlFirma, HttpServletRequest request);
	
}

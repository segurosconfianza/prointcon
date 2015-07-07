package com.confianza.webapp.service.email.sendEmail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendEmailImpl implements SendEmail { 

	@Autowired
	private MailSender mailSender;
	
	@Autowired
    private SimpleMailMessage templateMessage;
	
	@Autowired
    private JavaMailSenderImpl sender;
		
	private static final String fuente="<font style='font-size: (11 pt); font-family: tahoma; text-align:left'>";
	private static final String th="<th style='text-align:left'>";
	
	@Override
	public void sendMessage(String app, String subject,  String text, String to, String[] cc, HttpServletRequest request) {
		 
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper;
		
        if(cc!=null)
        	templateMessage.setCc(cc);
        
        try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setFrom("webappconfianza@confianza.com.co");
    		helper.setSubject(subject);
    		helper.setText(getBody(app, subject, text), true);
    		helper.addInline("firmaWebApp", new FileSystemResource(request.getSession().getServletContext().getRealPath("/Imagenes/Confianza/FirmaAplicativoWeb.png")));
    		
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try{
        	
        	sender.send(message);
        }
        catch(MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

	private String getBody(String app, String subject, String text) {
		
		String textoHtml="<table>"
				        +"<tr>"+th+fuente+"Aplicacion:</font></th><td>"+fuente+app+"</font></td></tr>"
				        +"<tr>"+th+fuente+"Asunto:</font></th><td>"+fuente+subject+"</font></td></tr>"
				        +"<tr>"+th+fuente+"Descripcion:</font></th><td>"+fuente+text+"</font></td></tr>"
				        +"<tr><td colspan='2'>&nbsp;</td></tr>"
				        +"<tr><td colspan='2'><img src='cid:firmaWebApp' width='450' height='200'></td></tr>"
				        +"</table>";
		return textoHtml;
	}
}

package com.confianza.webapp.controller.email.sendEmail;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.confianza.webapp.service.email.sendEmail.SendEmail;

@Controller
@RequestMapping("/CSendEmail")
public class CSendEmail {

	@Autowired
	private SendEmail sendEmail;
	
	
	@RequestMapping("/sendMessage")
	public void sendMessage(@RequestParam("app")String app, @RequestParam("subject")String subject, @RequestParam("text")String text, @RequestParam("from")String from, @RequestParam("cc")String[] cc, HttpServletRequest request) throws Exception {
		 
		sendEmail.sendMessage(app, subject, text, from, cc, request);
    }
}

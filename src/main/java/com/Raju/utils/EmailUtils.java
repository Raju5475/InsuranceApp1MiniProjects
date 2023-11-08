package com.Raju.utils;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component
public class EmailUtils {
	
	@Autowired
private JavaMailSender mailsender;
	
	public boolean sendmail(String subject,String body,String to,File f) {
		
		try {
			MimeMessage msg=mailsender.createMimeMessage();
			
			MimeMessageHelper helper=new MimeMessageHelper(msg,true);
			helper.setText(to, true);
			helper.setSubject(subject);
			helper.setTo(to);
			helper.addAttachment("users",f);
			mailsender.send(msg);		
		} catch (Exception e) {
			
		}
		
		return true;
	}
	
	
	
	
}

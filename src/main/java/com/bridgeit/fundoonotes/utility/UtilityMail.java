package com.bridgeit.fundoonotes.utility;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.bridgeit.fundoonotes.dto.Dtologin;
import com.bridgeit.fundoonotes.model.Email;

@Component
public class UtilityMail {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private TokenUtility TokenUtility; 

//	@Autowired
//	public UtilityMail(JavaMailSender javaMailSender) {
//	
//		this.javaMailSender = javaMailSender;
//	}
	
	public void send(Email email)
	{
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setTo(email.getTo());
		mailMessage.setSubject(email.getSubject());
		mailMessage.setText(email.getBody());
		javaMailSender.send(mailMessage);
		
		System.out.println("email sent successfull");
	}
	
	public String getlink(String link,String id) throws UnsupportedEncodingException
	{
		return link+TokenUtility.generateToken(id);
	}
}

package com.utils;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.eclipse.jdt.internal.compiler.batch.Main;

public class EmailTest {
	private String username ="cga101g1@gmail.com";
	private String password ="Tibamecga101g1";
	private String customer ="the38245691@gmail.com";
	private String subject ="hello";
	private String txt ="yoyoyoyoyoyoyo";
	
	public void SendMail() {
		Properties prop=new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.socketFactory.port", "465");
		
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setSender(new InternetAddress(username));
			message.setRecipient(RecipientType.TO, new InternetAddress(customer));
			message.setSubject(subject);
			message.setContent(txt, "text/html; charset=UTF-8");
			
			Transport transport = session.getTransport();
			transport.send(message);
			transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		EmailTest tm =new EmailTest();
		tm.SendMail();
		System.out.println("成功");
	}
}

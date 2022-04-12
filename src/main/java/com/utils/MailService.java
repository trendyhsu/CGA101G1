package com.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
@WebServlet("/MailService")
public class MailService {

	public void sendMail(String targetName, String subject, String messageText) {

		try {
			// 放置 SMTP/SMTPS 相關參數到 java.util.Properties 物件
			// G-mail SMTPS Server
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			
			// 設定帳號密碼
			final String gmailAccount = "cga101g1@gmail.com";
			final String gmailPassword = "Tibamecga101g1";
			
			// 將帳號密碼放入Session中
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(gmailAccount, gmailPassword);
				}
			});
			
			// 建立javax.mail.Message 物件
			// javax.mail.Message 為抽象類別;
			// 使用 javax.mail.internet.MimeMessage 實作類別建立 Message 物件。
			// 電子郵件地址使用 javax.mail.internet.InternetAddress 物件表示。
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(gmailAccount));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetName));

			// 設定主旨
			message.setSubject(subject);
			// 設定內容
			message.setText(messageText);
			
			// 送出郵件
			Transport.send(message);
			System.out.println("成功");
		} catch (MessagingException e) {
			System.out.println("失敗");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		String to = "cathyl1u123425@gmail.com";

		String subject = "星巴克今天有夠多人";

		String targetName = "大屌萌妹";
		
		String messageText = "Hello! " + targetName + "\n" + "今天星巴克說要等一小時，櫃台身後有六十杯空杯";

		MailService mailService = new MailService();
		
		mailService.sendMail(to, subject, messageText);

	}

}
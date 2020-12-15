package com.lti.bean;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	public static void sendEmail(String recipient, String m) throws MessagingException
	{
		System.out.println("Preparing to send mail");
		
		Properties properties=new Properties();
		
		properties.put("mail.smtp.auth", "true");
		
		properties.put("mail.smtp.starttls.enable", "true");
		
		properties.put("mail.smtp.host", "smtp.gmail.com");
		
		properties.put("mail.smtp.port", "587");
		

		
		String myEmail="safartravels584@gmail.com";
		String password="Safar1234";
		
		Session session=Session.getInstance(properties, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(myEmail, password);
			}
			
		});
		
		Message message=prepareMessage(session,myEmail,recipient,m);
		
		Transport.send(message);
		
		
	}

	private static Message prepareMessage(Session session, String myEmail, String recipient, String m)
	{
		System.out.println("Sending mail");
		Message message =new MimeMessage(session);
		
		
			try {
				message.setFrom(new InternetAddress(myEmail));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
				message.setSubject("Reset Password Confirmation Link");
				message.setText("Click Here To Reset You Password: "+m);
				return message;
			} 
			catch (Exception e) {
				e.printStackTrace();
//				Logger.getLogger(NewAccountMail.class.getName()).log(Level.SEVERE,null,e);
			}
			return null;
		
	}

}

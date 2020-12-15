package com.lti.bean;

import javax.mail.MessagingException;

import com.lti.exception.HrException;

public class SendEmail {
	public static void resetpwd(String useremail) throws HrException {
		try {
			MailSender.sendEmail(useremail, "http://localhost:4200/changePwd");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
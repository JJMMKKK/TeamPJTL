package com.mail;

import javax.mail.PasswordAuthentication;

public class Send_Email extends javax.mail.Authenticator {
	public PasswordAuthentication getPasswordAuthentication() {
		
		System.out.println("PasswordAuthentication");
	
		return new PasswordAuthentication("cjstkrhdfk", "V6MP7NH9X92W");
//		return new PasswordAuthentication("아이디", "2차_인증_패스워드");
	
	}
}
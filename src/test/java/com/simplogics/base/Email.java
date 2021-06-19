package com.simplogics.base;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.EmailException;

public class Email {

	public static  void email()throws EmailException {
		
		final String username = "testingt919@gmail.com"; //change to your Gmail username
	    final String password ="Power123!";//change to your Gmail password
	    final String from ="testingt919@gmail.com"; //change to from email address
	    final String to ="testingt919@gmail.com"; //change to to email address
	   // final String cc = "test.cc.email@helloselenium.com"; //change to cc email address
	    //final String bcc = "test.bcc.email@helloselenium.com"; //change to bcc email address
	    final String subject = "Test Report and Logs of API Automation"; //change to your subject
	     String msg = "please find the attached document after automation"; //change to your message

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", true);
	    props.put("mail.smtp.starttls.enable", true);
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(username, password);
	                }
	            });

	    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(to));
	        //below code only requires if your want cc email address
	       // message.setRecipients(Message.RecipientType.CC,
	                //InternetAddress.parse(cc));
	        //below code only requires if your want bcc email address
	        //message.setRecipients(Message.RecipientType.BCC,
	                //InternetAddress.parse(bcc));
	        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
	        Date date = new Date();
	      String date2= dateFormat.format(date);
	      
	        
	        message.setSubject(subject);
	        message.setText(msg);

	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        Multipart multipart = new MimeMultipart();
	        messageBodyPart = new MimeBodyPart();
	        String file1 = System.getProperty("user.dir")+"/target/surefire-reports/html/API_Test_Result.html";	        		
	        String fileName1 = "API_Test_Result"+date2+".html"; //change to your attachment filename
	        DataSource source1 = new FileDataSource(file1);
	        messageBodyPart.setDataHandler(new DataHandler(source1));
	        messageBodyPart.setFileName(fileName1);
	        multipart.addBodyPart(messageBodyPart);
	        message.setContent(multipart);
	        
	        String file2 = System.getProperty("user.dir")+"/target/surefire-reports/html/API_Test_Log.log";	        		
	        String fileName2= "API_Test_Log"+date2+".log"; //change to your attachment filename
	        DataSource source2 = new FileDataSource(file2);
	        BodyPart messageBodyPart2 = new MimeBodyPart();
	        messageBodyPart2.setDataHandler(new DataHandler(source2));
	        messageBodyPart2.setFileName(fileName2);
	        multipart.addBodyPart(messageBodyPart2);
	        message.setContent(multipart);
	        
	        
	      
	        	        
	        
	        
	       // System.out.println(message);
	        System.out.println("Sending Email.....");

	        try {
				Transport.send(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        System.out.println("Sent Email");

	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	  }
	
			  
			  
			 

}


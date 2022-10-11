package edu.paulo.app.core.smtp;

import edu.paulo.app.core.connection.SimpleToolsDB;
import edu.paulo.app.util.ConfigProperties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;


public class SMTPCore {

	private String[] exceptionString = new String[2];
	private ConfigProperties cProp;
	private SimpleToolsDB stdb;
	private Properties prop;
	private Message message ;
	private Session session;
	private String strDestination;
	private StringBuilder sBuild ;
	private MimeBodyPart messageBodyPart;
	private Multipart multipart;
	
	public SMTPCore()
	{
		sBuild = new StringBuilder();
		exceptionString[0] = "ExcelWriter";
	    cProp = new ConfigProperties();
	    stdb = new SimpleToolsDB();
	}
	
	private Properties getTLSProp()
	{
		prop = new Properties();
		prop.put("mail.smtp.host", cProp.getEmHost());
        prop.put("mail.smtp.port", cProp.getEmPortTLS());
        prop.put("mail.smtp.auth", cProp.getEmAuth());
        prop.put("mail.smtp.starttls.enable", cProp.getEmStartTls());
        
        return prop;
	}
	
	private Properties getSSLProp()
	{
		prop = new Properties();
		prop.put("mail.smtp.host", cProp.getEmHost());
        prop.put("mail.smtp.port", cProp.getEmPortSSL());
        prop.put("mail.smtp.auth", cProp.getEmAuth());
        prop.put("mail.smtp.socketFactory.port", cProp.getEmPortSSL());
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        return prop;
	}
	
	public boolean sendSimpleMail(String[] strMailTo, String strSubject, String strContentMessage, String strLayer)
	{   
		Properties execProp ;
		try {
			
			if(strLayer.equals("SSL")) {
				execProp = getSSLProp();
			}
			else
			{
				execProp = getTLSProp();
			}
		
			exceptionString[1] = "sendSimpleMail(String[] strMailTo, String strSubject, String strContentMessage, String strLayer)";
			sBuild.setLength(0);
			for(int i=0;i<strMailTo.length;i++)
			{
				sBuild.setLength(0);
				strDestination = sBuild.append(strMailTo[i]).toString();
				
				if(i != strMailTo.length-1)
				{
					sBuild.setLength(0);
					strDestination = sBuild.append(strDestination).append(",").toString();
				}
					
			}
		
	        session = Session.getInstance(execProp,
	                new Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(cProp.getEmUsrName(), cProp.getEmPassword());
	                    }
	                });
	        
	            message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(cProp.getEmUsrName()));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse(strDestination)
	            );
	            message.setSentDate(new Date());
	            
	            /*BODY OF MAIL*/
	            message.setSubject(strSubject);
	            message.setText(strContentMessage);
	
	            Transport.send(message);
	            System.out.println("Done");

        } catch (Exception e) {
        	e.printStackTrace();
        	exceptionString[1] = "sendSimpleMail(String[] strMailTo, String strSubject, String strContentMessage, String strLayer) -- EXCEPTION LINE 116";
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			return false;
        }
        
        return true;
	}
	
	public boolean sendMailWithAttachment(String[] strMailTo, String strSubject, String strContentMessage, 
			String strLayer, String[] attachFiles)
	{		
		Properties execProp ;


		if(strLayer.equals("SSL")) {
			execProp = getSSLProp();
		}
		else
		{
			execProp = getTLSProp();
		}
		
		sBuild.setLength(0);
		for(int i=0;i<strMailTo.length;i++)
		{
			sBuild.setLength(0);
			strDestination = sBuild.append(strMailTo[i]).toString();
			
			if(i != strMailTo.length-1)
			{
				sBuild.setLength(0);
				strDestination = sBuild.append(strDestination).append(",").toString();
			}
				
		}
		session = Session.getInstance(execProp,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(cProp.getEmUsrName(), cProp.getEmPassword());
                    }
                });
		
		
        try {
        	message = new MimeMessage(session);
            message.setFrom(new InternetAddress(cProp.getEmUsrName()));
			message.setRecipients(
			        Message.RecipientType.TO,
			        InternetAddress.parse(strDestination)
			);
			message.setSentDate(new Date());
			message.setSubject(strSubject);
			
			// creates message part
	        messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(strContentMessage, "text/html");
	        
	     // creates multi-part
	        multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	        
	     // adds attachments
	        if (attachFiles != null && attachFiles.length > 0) {
	            for (String filePath : attachFiles) {
	                MimeBodyPart attachPart = new MimeBodyPart();
	 
	                try {
	                    attachPart.attachFile(filePath);
	                } catch (Exception ex) {
	                	ex.printStackTrace();
	        			stdb.exceptionStringz(exceptionString, ex, cProp.getfException());
	        			return false;
	                }	 
	                multipart.addBodyPart(attachPart);
	            }
	        }
	        
	     // sets the multi-part as e-mail's content
	        message.setContent(multipart);
	 
	        // sends the e-mail
	        Transport.send(message);
	        
		} catch (Exception e) {
			e.printStackTrace();
			exceptionString[1] = "endMailWithAttachment(String[] strMailTo, String strSubject, String strContentMessage, String strLayer, String[] attachFile) -- EXCEPTION LINE 201";
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			return false;
		}
        
		return true;
	}
}
package edu.paulo.app.example.mailsmtp;

import edu.paulo.app.core.io.file.ReadTextFile;
import edu.paulo.app.core.smtp.SMTPCore;

public class SendMailSMTP {

	public static void main(String[] args) {
		
		ReadTextFile rtf = new ReadTextFile(System.getProperty("user.dir")+"\\data\\MailHTMLExample.txt");
		SMTPCore sc = new SMTPCore();
		System.out.println(sc.sendMailWithAttachment(new String[] {"delviserly354@gmail.com"},"INI HANYA TEST",rtf.getContentFile(),"SSL",
				new String[] {System.getProperty("user.dir")+"\\data\\MailHTMLExample.txt"}));
	}
}
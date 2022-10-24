package edu.paulo.app;

import edu.paulo.app.core.connectivity.JDBCInsertFromExcelPS;
import edu.paulo.app.core.io.file.ReadTextFile;
import edu.paulo.app.util.ConfigProperties;

public class GoLive {

	public static void main(String[] args) {
//		JDBCInsertFromExcelPS jife = new JDBCInsertFromExcelPS("./materials/data/DataDriven.xlsx","JDBCDemoInsert", "insert_demo" );
		System.out.println("DEFAULT DIRECTORY ==> "+System.getProperty("user.dir")+"/materials/data/MailHTMLExample.txt");
		System.out.println("DEFAULT DIRECTORY ==> "+System.getProperty("user.dir") + "/materials\\data\\MailHTMLExample.txt");
		ReadTextFile rtf = new ReadTextFile(System.getProperty("user.dir") + "/materials\\data\\MailHTMLExample.txt");
		System.out.println(rtf.getContentFile());
		ConfigProperties sTP = new ConfigProperties();
		System.out.println(sTP.getDbConnString());
		System.out.println(sTP.getDbPassword());
		System.out.println(sTP.getDbUserName());
		System.out.println(sTP.getEmAuth());
		System.out.println(sTP.getEmPassword());
		System.out.println(sTP.getEmPort());
		System.out.println(sTP.getEmStartTls());
		System.out.println(sTP.getEmUsrName());
		System.out.println(sTP.getfDelay());
		System.out.println(sTP.getfException());

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		sTP = new ConfigProperties();
		System.out.println(sTP.getDbConnString());
		System.out.println(sTP.getDbPassword());
		System.out.println(sTP.getDbUserName());
		System.out.println(sTP.getEmAuth());
		System.out.println(sTP.getEmPassword());
		System.out.println(sTP.getEmPort());
		System.out.println(sTP.getEmStartTls());
		System.out.println(sTP.getEmUsrName());
		System.out.println(sTP.getfDelay());
		System.out.println(sTP.getfException());
		
	}
}
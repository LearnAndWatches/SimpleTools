package edu.paulo.app;

import edu.paulo.app.util.ConfigProperties;

public class GoLive {

	public static void main(String[] args) {
		ConfigProperties sTP = new ConfigProperties();
		System.out.println(sTP.getDbConnString());
		System.out.println(sTP.getDbDriver());
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
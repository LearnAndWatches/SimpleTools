package edu.paulo.app.core.io.file;

import edu.paulo.app.core.connection.SimpleToolsDB;
import edu.paulo.app.util.ConfigProperties;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

public class ReadTextFile {

	private FileInputStream fInput;
	private BufferedInputStream bInput;
	private String[] exceptionString = new String[2];
	private ConfigProperties cProp;
	private SimpleToolsDB stdb;
	private String contentFile; 
	private byte[] contentOfFile;
	
	public ReadTextFile(String pathFile)
	{
		exceptionString[0] = "ReadTextFile";
	    cProp = new ConfigProperties();
	    stdb = new SimpleToolsDB();
	    setContentFile(pathFile);
	}
	
	public void setContentFile(String pathFile)
	{
		
		try {
			fInput = new FileInputStream(new File(pathFile));
			bInput = new BufferedInputStream(fInput);
			this.contentOfFile = bInput.readAllBytes();
			this.contentFile = new String(contentOfFile, StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionString[1] = "setContentFile(String pathFile) -- EXCEPTION LINE  40";
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());
		}
		finally {
			try {
				fInput.close();
				bInput.close();
			} catch (Exception e) {
				e.printStackTrace();
				exceptionString[1] = "setContentFile(String pathFile) -- EXCEPTION LINE  49";
				stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			}
		}
	}
	
	public String getContentFile()
	{
		return contentFile;
	}
	
	public byte[] getByteOfFile()
	{
		return contentOfFile;
	}
//	public static void main(String[] args) {
//		ReadTextFile rtf = new ReadTextFile(System.getProperty("user.dir")+"\\data\\MailHTMLExample.txt");
//		System.out.println(rtf.getContentFile());
//	}
}
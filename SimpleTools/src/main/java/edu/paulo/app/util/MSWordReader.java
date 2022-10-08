package edu.paulo.app.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import edu.paulo.app.core.connection.SimpleToolsDB;

public class MSWordReader {

	private FileInputStream fis;
	private BufferedInputStream inputBuff;
	private XWPFDocument document ;
	private String[] exceptionString = new String[2];
	private ConfigProperties cProp ;
	private SimpleToolsDB stdb;
	private List<XWPFParagraph> paragraphs;
	private StringBuilder sBuild ;
	
	private String strA;
	
	public MSWordReader()
	{
		exceptionString[0] = "MSWordReader";
		cProp = new ConfigProperties();
	    stdb = new SimpleToolsDB();
	    sBuild = new StringBuilder();
	}
	
	public String getData(String pathSource)
	{
		sBuild.setLength(0);
		exceptionString[1] = "getData(String pathSource)";
		strA = "";
		try {
			fis = new FileInputStream(pathSource);
			inputBuff = new BufferedInputStream(fis);
			
			document = new XWPFDocument(inputBuff);
			paragraphs = document.getParagraphs();
			sBuild.setLength(0);
            for (XWPFParagraph para : paragraphs) {
            	sBuild.setLength(0);
            	strA = sBuild.append(strA).append(para.getText()).append("\n").toString();
            }            
            
		} catch (Exception e) {
			e.printStackTrace();
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());
		}finally {

			try {
				document.close();
	            fis.close();
	            inputBuff.close();
	            paragraphs =null;//set this object to be destroyed by GC
			
			}catch (Exception e) {
							e.printStackTrace();
							stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			}

		}
		return strA;
	}
}
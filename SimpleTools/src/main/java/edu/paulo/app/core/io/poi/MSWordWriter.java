package edu.paulo.app.core.io.poi;
import edu.paulo.app.core.connection.SimpleToolsDB;
import edu.paulo.app.util.ConfigProperties;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class MSWordWriter {

	private XWPFDocument document ;
	private FileOutputStream fOut ;
	private BufferedOutputStream bOut;
	private XWPFParagraph paragraph ;
	private XWPFRun run ;
	private StringBuilder sBuild ;
	private String[] exceptionString = new String[2];
	private ConfigProperties cProp ;
	private SimpleToolsDB stdb;
	
	public MSWordWriter(String pathDestination,String text)
	{
		document = new XWPFDocument();
		exceptionString[0] = "MSWordWriter";
		cProp = new ConfigProperties();
	    stdb = new SimpleToolsDB();
	    sBuild = new StringBuilder();
	    generateWordFile(pathDestination, text);
	}
	
	public void generateWordFile(String pathDestination,String textMSWord)
	{
		sBuild.setLength(0);
		try {
			fOut = new FileOutputStream(new File(pathDestination));
			bOut = new BufferedOutputStream(fOut);
			paragraph = document.createParagraph();
			run = paragraph.createRun();
			run.setText(textMSWord);
			document.write(bOut);
			
		} catch (Exception e) {
			e.printStackTrace();
			exceptionString[1] = "generateWordFile(String pathDestination,String textMSWord)  -- EXCEPTION LINE 50";
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());
		}finally {

			try {
				fOut.flush();
				bOut.flush();			
				fOut.close();
				bOut.close();
			}catch (Exception e) {
				e.printStackTrace();
				exceptionString[1] = "generateWordFile(String pathDestination,String textMSWord)  -- EXCEPTION LINE 60";
				stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			}

		}
	}
}

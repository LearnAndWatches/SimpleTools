package edu.paulo.app.util;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVWriter;

import edu.paulo.app.core.connection.SimpleToolsDB;

public class CSVWriting {

	private FileWriter fW;
	private String[] exceptionString = new String[2];
	private ConfigProperties cProp;
	private SimpleToolsDB stdb;
	private CSVWriter writer;
	private List<String[]> listConvert ;
	private String[] strArr;
	
	public CSVWriting(List<HashMap<String,Object>> datas, String pathDestination,String[] header,char separator)
	{
		exceptionString[0] = "CSVWriting";
		cProp = new ConfigProperties();
	    stdb = new SimpleToolsDB();
	    listConvert = new ArrayList<String[]>();
	    writeToCsv(datas, pathDestination, header,separator);
	}
	
	public CSVWriting(List<String[]> datas, String pathDestination,char separator)
	{
		exceptionString[0] = "CSVWriting";
		cProp = new ConfigProperties();
	    stdb = new SimpleToolsDB();
	    writeToCsv(datas, pathDestination,separator);
	}
	
	/*THE DATA SET FROM SQL STATEMENT DATABASE */
	public void writeToCsv(List<HashMap<String,Object>> datas, String pathDestination,String[] header,char separator)
	{
		listConvert.clear();
		exceptionString[1] = "writeToCsv(List<HashMap<String,Object>> datas, String pathDestination,String[] header)";
		try
		{
			fW = new FileWriter(new File(pathDestination));
			writer = new CSVWriter(fW,separator, 
                    CSVWriter.NO_QUOTE_CHARACTER, 
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
                    CSVWriter.DEFAULT_LINE_END);
			
			writer.writeNext(header);
			
			for(int i=0;i<datas.size();i++)
			{
				strArr = new String[header.length];
				
				for(int j=0;j<header.length;j++)
				{
					strArr[j] = datas.get(i).get(header[j]).toString();
				}
				listConvert.add(strArr);
			}
	        writer.writeAll(listConvert);
			
	        
			
		}catch(Exception e)
		{
			e.printStackTrace();
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());
		}finally {

			try {
				fW.flush();
		        writer.flush();
				writer.close();
				fW.close();
			}catch (Exception e) {
							e.printStackTrace();
							stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			}

		}
	}
	
	/*THE DATA SET MANUAL*/
	public void writeToCsv(List<String[]> datas, String pathDestination,char separator)
	{
		exceptionString[1] = "writeToCsv(List<HashMap<String,Object>> datas, String pathDestination)";
		try
		{
			fW = new FileWriter(new File(pathDestination));
			writer = new CSVWriter(fW, separator, 
                    CSVWriter.NO_QUOTE_CHARACTER, 
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
                    CSVWriter.DEFAULT_LINE_END);
	        writer.writeAll(datas); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());
		}finally {

			try {
		        fW.flush();
		        writer.flush();	        	
				writer.close();					
				fW.close();
			}catch (Exception e) {
							e.printStackTrace();
							stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			}

		}
	}
}
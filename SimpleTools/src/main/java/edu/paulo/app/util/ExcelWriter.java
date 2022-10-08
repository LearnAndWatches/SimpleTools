package edu.paulo.app.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.paulo.app.core.connection.SimpleToolsDB;

public class ExcelWriter {

	private XSSFWorkbook workbook ; 
	private XSSFRow row;
	private XSSFSheet spreadsheet ;
	private Map<Integer, String[]> mapData  ;
	private FileOutputStream fOutS ;
	private BufferedOutputStream out;
	private String[] exceptionString = new String[2];
	private ConfigProperties cProp ;
	private SimpleToolsDB stdb;
	private String[] strDatas = null;
	
	/*SELECT LIST FROM RESULT SQL STATEMENT SELECT FROM DATABASE*/
	public ExcelWriter(List<HashMap<String,Object>> datas, String pathDestination, String sheetName , String[] header )
	{
		workbook = new XSSFWorkbook(); 
	    spreadsheet = workbook.createSheet(sheetName);
	    mapData  = new TreeMap<Integer, String[]>();
	    exceptionString[0] = "ExcelWriter";
	    cProp = new ConfigProperties();
	    stdb = new SimpleToolsDB();
	    writeToExcel(datas, pathDestination, header);
	}
	
	/*SELECT LIST FROM DATA THAT SET MANUAL */
	public ExcelWriter(List<String[]> datas , String pathDestination, String sheetName)
	{
		workbook = new XSSFWorkbook(); 
	    spreadsheet = workbook.createSheet(sheetName);
	    mapData  = new TreeMap<Integer, String[]>();
	    exceptionString[0] = "ExcelWriter";
	    cProp = new ConfigProperties();
	    stdb = new SimpleToolsDB();
	    writeToExcelManual(datas, pathDestination);
	}
	
	public void writeToExcel(List<HashMap<String,Object>> datas, String pathDestination, String[] header )
	{
		mapData.clear();
		exceptionString[1] = "writeToExcel(List<HashMap<String,Object>> datas, String pathDestination, String[] header )";
		
		try {
			int k=1;/*initiate for rows data in excel*/
			
			mapData.put(1,header);
			
			for(int i=0;i<datas.size();i++)
			{
				k++;/*start from 2*/
				
				strDatas = new String[header.length];
				for(int j=0;j<header.length;j++)
				{
					strDatas[j] = datas.get(i).get(header[j]).toString();
				}
				mapData.put(k,strDatas);
			}
			
			Set<Integer> keyid = mapData.keySet();
			int rowid = 0;
			for (int key : keyid) {
				  
	            row = spreadsheet.createRow(rowid++); 
	            Object[] objectArr = mapData.get(key); 
	            int cellid = 0; 
	  
	            for (Object obj : objectArr) { 
	                Cell cell = row.createCell(cellid++); 
	                cell.setCellValue((String)obj); 
	            } 
	        }
			
			fOutS =  new FileOutputStream(new File(pathDestination));
			out = new BufferedOutputStream(fOutS);
		} catch (Exception e) {
			e.printStackTrace();
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());
		}
		finally {
			try {
				workbook.write(out);
				workbook.close();
				out.flush();
		        fOutS.close();
		        out.close();
			} catch (IOException e) {
				e.printStackTrace();
				stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			}			
		}
	}
	
	public void writeToExcelManual(List<String[]> datas, String pathDestination)
	{
		mapData.clear();
		exceptionString[1] = "writeToExcelManual(List<String[]> datas, String pathDestination)";
		
		try {
			int k=1;/*initiate for rows data in excel*/
			int intColumn = datas.get(0).length;
			
			for(int i=0;i<datas.size();i++)
			{
				k++;/*start from 2*/
				
				strDatas = new String[intColumn];
				for(int j=0;j<intColumn;j++)
				{
					strDatas[j] = datas.get(i)[j].toString();
				}
				mapData.put(k,strDatas);
			}
			
			Set<Integer> keyid = mapData.keySet();
			int rowid = 0;
			for (int key : keyid) {
				  
	            row = spreadsheet.createRow(rowid++); 
	            Object[] objectArr = mapData.get(key); 
	            int cellid = 0; 
	  
	            for (Object obj : objectArr) { 
	                Cell cell = row.createCell(cellid++); 
	                cell.setCellValue((String)obj); 
	            }
	        }
			
			fOutS =  new FileOutputStream(new File(pathDestination));
			out = new BufferedOutputStream(fOutS);
			
		} catch (Exception e) {
			e.printStackTrace();
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());
		}
		finally {
			try {
				workbook.write(out);
				workbook.close();
				out.flush();
		        fOutS.close();
		        out.close();
			} catch (IOException e) {
				e.printStackTrace();
				stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			}			
		}
	}
}
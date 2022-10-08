package edu.paulo.app.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.paulo.app.core.connection.SimpleToolsDB;

public class ExcelReader {
	
	private XSSFWorkbook wBook ;
	private XSSFSheet sheet ;	
	private String values ;
	private DataFormatter dFormatter ;
	private int intRowCount;
	private int intColCount;
	private String[][] strAllData;
	private String[][] arrWithoutHeader;
	private int loopRows;
	private FileInputStream excelFile;
	private BufferedInputStream inputBuff;
	private ConfigProperties cProp ;
	private SimpleToolsDB stdb;
	private String[] exceptionString = new String[2];
	
	public ExcelReader(String excelPath, String sheetName) {
		exceptionString[0] = "ExcelReader";
		exceptionString[1] = "ExcelReader(String excelPath, String sheetName)";
		try {
			cProp = new ConfigProperties();
		    stdb = new SimpleToolsDB();
			excelFile = new FileInputStream(new File(excelPath));
			inputBuff = new BufferedInputStream(excelFile);
			wBook = new XSSFWorkbook(inputBuff);/**/
			sheet = wBook.getSheet(sheetName);			
			getRowCount();/*Must Generated first*/
			getColCount();/*Must Generated first*/
			setData();/*SET ALL DATA*/
			
		} catch (Exception e) {
			e.printStackTrace();
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());		
		}
		finally {
			try {
				excelFile.close();
				inputBuff.close();
				wBook.close();
			} catch (IOException e) {
				e.printStackTrace();
				stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			}
		}
	}
	
	/*
	 * if you want to handle manual the loops of all data in another method , you can use this method
	 */
	public Iterator<Row> getIter()
	{
		Iterator<Row> r = sheet.iterator();
		return r;
	}
	
	
	public void setData()
	{
		try
		{
			exceptionString[1] = "setData()";
			strAllData = new String[intRowCount][intColCount];		
			arrWithoutHeader = new String[intRowCount-1][intColCount];/*BECAUSE OF remove a Header so Row for this object must be minus 1 */
			loopRows =0;
			Iterator<Row> rX = sheet.iterator();
			
			while(rX.hasNext())
			{
				Row rows = rX.next();
				for(int j=0;j<intColCount;j++)
				{
					if(loopRows!=0)
					{
						/*BECAUSE OF remove a Header so Row for this object must be minus 1 */
						arrWithoutHeader [loopRows-1][j] = getCellData(loopRows,j).toString();
					}
					strAllData[loopRows][j] = getCellData(loopRows,j).toString();
				}
				loopRows++;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());
		}
		this.strAllData = strAllData;
		this.arrWithoutHeader = arrWithoutHeader;		
	}
	
	/*
	 * if you want to get all data and proceed it from two dimension String array object, using this method
	 */
	public String[][] getAllData()
	{
		return strAllData;
	}
	
	public String[][] getDataWithoutHeader()
	{
		return arrWithoutHeader;
	}
	
	/*GET SPECIFIC DATA USING ROW NUMBER AND COLUMN NUMBER*/
	public Object getCellData(int rowNum, int colNum)
	{
		dFormatter = new DataFormatter();
		values = dFormatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));

		return values;
	}
	
	public int getRowCount()
	{		
		intRowCount = sheet.getPhysicalNumberOfRows();
		return intRowCount;
	}
	public int getColCount()
	{		
		intColCount = sheet.getRow(0).getPhysicalNumberOfCells();

		return intColCount;
	}
}
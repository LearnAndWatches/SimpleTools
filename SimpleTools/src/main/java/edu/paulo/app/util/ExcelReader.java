package edu.paulo.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	private XSSFWorkbook wBook ;
	private XSSFSheet sheet ;	
	private String values ;
	private DataFormatter dFormatter ;
	private int intRowCount;
	private int intColCount;
	private String[][] strAllData;
	private int loopRows;
	private FileInputStream excelFile;
	
	public ExcelReader(String excelPath, String sheetName) {
		try {
			excelFile = new FileInputStream(new File(excelPath));
			wBook = new XSSFWorkbook(excelPath);/**/
			sheet = wBook.getSheet(sheetName);
			getRowCount();/*Must Generated first*/
			getColCount();/*Must Generated first*/
			
		} catch (IOException e) {
			System.out.println(""+e.getMessage());			
		}
		finally {
			try {
				excelFile.close();
			} catch (IOException e) {
				System.out.println(""+e.getCause());
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
	
	/*
	 * if you want to get all data and proceed it from two dimension String array object, using this method
	 */
	public String[][] getAllData()
	{
		strAllData = new String[intRowCount][intColCount];
		loopRows =0;
		Iterator<Row> rX = sheet.iterator();
		
		while(rX.hasNext())
		{
			Row rows = rX.next();
			for(int j=0;j<intColCount;j++)
			{
				strAllData[loopRows][j] = getCellData(loopRows,j).toString();
			}
			loopRows++;
		}
		
		return strAllData;
	}
	
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
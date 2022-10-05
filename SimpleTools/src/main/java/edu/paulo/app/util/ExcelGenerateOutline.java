package edu.paulo.app.util;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;

public class ExcelGenerateOutline {

	public static void main(String[] args) {
		StringBuilder sBuild = new StringBuilder();
		String excelPath = "./data/DataDriven.xlsx";
		String sheetName = "Sheet1";
		ExcelReader excelReader = new ExcelReader(excelPath, sheetName);

		Iterator<Row> rX = excelReader.getIter();
		int intRows = excelReader.getRowCount();
		int intCol  = excelReader.getColCount();
		String[][] dDriven = new String[intRows][intCol];
		int a=0;
		while(rX.hasNext())
		{
			Row rows = rX.next();
			for(int j=0;j<intCol;j++)
			{
				dDriven[a][j] = excelReader.getCellData(a,j).toString();
			}
			a++;
		}
		
		String generateOutline = "";
		for(int i=0;i<dDriven.length;i++)
		{
			for(int j=0;j<intCol;j++)
			{
				sBuild.setLength(0);
				if(j==0) {
					generateOutline = sBuild.append(generateOutline).append("|").append(dDriven[i][j]).
							append("|").toString();
				}else {
					generateOutline = sBuild.append(generateOutline).append(dDriven[i][j]).
							append("|").toString();
				}
			}
			sBuild.setLength(0);
			generateOutline = sBuild.append(generateOutline).append("\n").toString();
		}
		System.out.println(generateOutline);
	}
}
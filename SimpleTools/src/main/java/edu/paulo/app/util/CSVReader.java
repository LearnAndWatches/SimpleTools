package edu.paulo.app.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

	private String line;
	private int intRow=0;
	private BufferedReader br =null;
	private String[][] arrIOBr;
	String[] split = line.split(",");
	
	public CSVReader(String pathCsv)
	{
		try {
			br = new BufferedReader(new FileReader(pathCsv));
			getBR();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String[][] getBR() throws IOException
	{
		while ((line = br.readLine()) != null) {
			split = line.split(",");
			for(int j=0;j<split.length;j++)
			{
				arrIOBr[intRow][j] = split[j];
			}
			intRow++;
		}		
		return arrIOBr;
	}
	
	public int getCol()
	{
		return arrIOBr[0][1].length();
	}
	
	public int getRow()
	{
		return intRow;/*MINUS 1 BECAUSE INDEX 0 IS THE TITLE OF COLUMN*/
	}
}
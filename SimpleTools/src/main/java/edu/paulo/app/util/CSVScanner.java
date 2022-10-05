package edu.paulo.app.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVScanner {

	private String line;
	private int intRow=0;
	private int intCol=0;
	private BufferedReader br =null;
	private String[][] arrIOBr;
	private String[][] arrWithoutHeader;
	private String[] split ;
	private List<String[]> listArr ;
	
	public CSVScanner(String pathCsv)
	{
		try {
			br = new BufferedReader(new FileReader(pathCsv));
			setBR();/*INITIATE ALL ELEMENTS WHEN CLASS CREATED*/
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
	
	public void setBR() throws IOException
	{
		listArr = new ArrayList<String[]>();
		while ((line = br.readLine())!=null) {
			split = line.split(",");
			listArr.add(split);
		}
		intCol = split.length;/**/
		arrIOBr = new String[listArr.size()][intCol];/*set String Array Data With Header / Title */
		/*arrWithoutHeader -> BECAUSE OF remove a Header so Row for this object must be minus 1 */
		arrWithoutHeader = new String[listArr.size()-1][intCol];/*set String Array Data Without Header / Title */
		for(int i=0;i<listArr.size();i++)
		{
			for(int j=0;j<intCol;j++)
			{
				if((i!=0))
				{
					/*BECAUSE OF remove a Header so Row for this object must be minus 1 */
					arrWithoutHeader[i-1][j] = listArr.get(i)[j];
				}
				arrIOBr[i][j] = listArr.get(i)[j];
			}
		}
		this.arrIOBr = arrIOBr;
		this.arrWithoutHeader = arrWithoutHeader;
	}
	
	public String[][] getDataWithoutHeader()
	{
		return arrWithoutHeader;
	}
	
	public String[][] getBR()
	{
		return arrIOBr;
	}
	
	public int getCol()
	{
		return intCol;
	}
	
	public int getRow()
	{
		return intRow;
	}
}
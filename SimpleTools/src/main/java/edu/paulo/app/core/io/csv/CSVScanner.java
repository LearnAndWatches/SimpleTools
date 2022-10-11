package edu.paulo.app.core.io.csv;

import edu.paulo.app.core.connection.SimpleToolsDB;
import edu.paulo.app.util.ConfigProperties;

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
	private ConfigProperties cProp ;
	private SimpleToolsDB stdb;
	private String[] exceptionString = new String[2];
	
	public CSVScanner(String pathCSV)
	{
		exceptionString[0] = "CSVScanner";
		try {
			br = new BufferedReader(new FileReader(pathCSV));
			setBR();/*INITIATE ALL ELEMENTS WHEN CLASS CREATED*/
		} catch (Exception e) {
			exceptionString[1] = "CSVScanner(String pathCSV)  -- EXCEPTION LINE 35";
			e.printStackTrace();
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
				exceptionString[1] = "CSVScanner(String pathCSV)  -- LINE 40";
				stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			}
		}
	}	
	
	public void setBR() throws IOException
	{
		exceptionString[1] = "setBR()  -- EXCEPTION LINE 35";
		try {
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
		}catch(Exception e)
		{
			e.printStackTrace();
			exceptionString[1] = "setBR() -- LINE 73";
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());
		}
		this.arrIOBr = arrIOBr;
		this.arrWithoutHeader = arrWithoutHeader;
	}
	
	/*PURE DATA ONLY WITHOUT COLUMN NAME*/
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
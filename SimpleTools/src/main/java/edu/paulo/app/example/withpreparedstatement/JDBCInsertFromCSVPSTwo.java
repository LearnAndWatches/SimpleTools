package edu.paulo.app.example.withpreparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.paulo.app.core.connection.SimpleToolsDB;
import edu.paulo.app.util.CSVScanner;
import edu.paulo.app.util.ConfigProperties;

/*
 * THIS CLASS USING COMPLEX DATA TO THE complex_data table
 */
public class JDBCInsertFromCSVPSTwo {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private CSVScanner csvScanner ;
	private StringBuilder sBuild = new StringBuilder();
	private String[][] dDriven ;
	private int intCol  = 0; /*accomodate count of column in csv file*/
	private String queryz = "";
	private ConfigProperties cProp ;
	private String[] exceptionString = new String[2];
	private SimpleToolsDB stdb;
	
	public JDBCInsertFromCSVPSTwo(String strPathCSV, String tableName)
	{
		stdb = new SimpleToolsDB();
		exceptionString[0] = "JDBCInsertFromCSVPSTwo";
		cProp = new ConfigProperties();
		setData(strPathCSV, tableName);
	}
    
	public static void main(String[] args) {
		/*Parameter order , path csv file ---- table name*/
		JDBCInsertFromCSVPSTwo jife = new JDBCInsertFromCSVPSTwo("./data/DataComplex.csv","complex_data");
	}
	
	public void setData(String strPathCSV, String tableName)
	{
		exceptionString[1] = "setData(String strPathCSV, String tableName)";
		csvScanner = new CSVScanner(strPathCSV);
		/*SET DRIVER BY PARAMETER*/
		try {
            conn = stdb.getDatabaseConnection();
		    System.out.println("Connection is created successfully!!");
		    
//		    conn.setAutoCommit(false);/*DEFAULT IS AUTOCOMMIT TRUE , IF THIS METHOD REMOVED , ENTRY DATA WILL NOT BE CLEAN !!*/		    
		    dDriven = csvScanner.getBR();
  	        intCol  = csvScanner.getCol();
  	        
  	        queryz = generateQueryInsert(dDriven, tableName, intCol);
  	        
  	        ps = conn.prepareStatement(queryz) ;
  	        
  	        dDriven = csvScanner.getDataWithoutHeader();
  	        
  	        for(int i=0;i<dDriven.length;i++)
  	        {
  	        	for(int j=0;j<intCol;j++)
  	        	{
  	        		stdb.setPStatement(j, dDriven[i][j], ps);
  	        	}
  	        	ps.executeUpdate();
  	        }
  	        conn.commit();
  	        System.out.println("Record is inserted in the table successfully..................");
         } catch (Exception e) {
        	 stdb.exceptionStringz(exceptionString, e, cProp.getfException());
            try {
				conn.rollback();
			} catch (SQLException e1) {
			 stdb.exceptionStringz(exceptionString, e1, cProp.getfException());
			}
         }
		finally {                                                                                                                                                                                                                                                                                                            
			try {
				stdb.closeResource(ps, conn);
			} catch (SQLException e) {
				stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			}
		}
		System.out.println("Please check it in the MariaDB/MySQL Table......... ……..");
	}
	
	public String generateQueryInsert(String[][] header, String tableName, int colCountz)
	{
		exceptionString[1] = "generateQueryInsert(String[][] header, String tableName, int colCountz)";
		try {
			sBuild.setLength(0);
			queryz = sBuild.append("INSERT INTO ").append(tableName).append(" (").toString();
			
			for(int i=0;i<colCountz;i++)
			{
				sBuild.setLength(0);
				queryz = sBuild.append(queryz).append(header[0][i]).toString();
				if(i != colCountz-1)
				{
					sBuild.setLength(0);
					queryz = sBuild.append(queryz).append(",").toString();
				}
			}
			sBuild.setLength(0);
			queryz = sBuild.append(queryz).append(") VALUES (").toString();
			
			for(int i=0;i<colCountz;i++)
			{
				sBuild.setLength(0);
				queryz = sBuild.append(queryz).append("?").toString();
				if(i != colCountz-1)
				{
					sBuild.setLength(0);
					queryz = sBuild.append(queryz).append(",").toString();
				}
			}
			sBuild.setLength(0);
			queryz = sBuild.append(queryz).append(");").toString();
		}catch(Exception e)
		{
			stdb.exceptionStringz(exceptionString, e, cProp.getfException());
		}		
		
		return queryz;
	}
}
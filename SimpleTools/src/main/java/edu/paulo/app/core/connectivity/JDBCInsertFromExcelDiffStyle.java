package edu.paulo.app.core.connectivity;

import edu.paulo.app.core.connection.SimpleToolsDB;
import edu.paulo.app.core.io.poi.ExcelReader;
import edu.paulo.app.util.ConfigProperties;

import java.sql.Connection;
import java.sql.Statement;

public class JDBCInsertFromExcelDiffStyle {

	
	private Connection conn = null;
	private Statement stmt = null;
	private ExcelReader excelReader ;
	private StringBuilder sBuild = new StringBuilder();
	private String[][] dDriven ;
	private int intCol  = 0; /*accomodate count of column in excel file*/
	private String queryz = "";
	private String strQueries = "";
	private ConfigProperties cProp ;
	private String[] exceptionString = new String[2];
	private SimpleToolsDB stdb;

	public JDBCInsertFromExcelDiffStyle(String strPathExcel, String strSheetName, String tableName)
	{
		stdb = new SimpleToolsDB();
		exceptionString[0] = "JDBCInsertFromExcelDiffStyle";
		cProp = new ConfigProperties();
		setData(strPathExcel,strSheetName,tableName);
	}

	public void setData(String strPathExcel, String strSheetName, String tableName)
	{
		
		excelReader = new ExcelReader(strPathExcel, strSheetName);
		/*SET DRIVER BY PARAMETER*/
		try {
            conn = stdb.getDatabaseConnection();
		    System.out.println("Connection is created successfully!!");
		    stmt = conn.createStatement();
			
		    execData(stmt, conn, excelReader,tableName);
         } catch (Exception e) {
        	exceptionString[1] = "setData(String strPathExcel, String strSheetName, String tableName) -- EXCEPTION LINE 47";
        	stdb.exceptionStringz(exceptionString, e, cProp.getfException());
         }
	}
	
	public void execData(Statement stment, Connection connects, ExcelReader eR,String tabName)
	{
		  
	      try {
	    	  dDriven = eR.getAllData();
	  	      intCol  = eR.getColCount();/*need this variable to help data looping for excel datas*/
	  	      connects.setAutoCommit(false);/*DEFAULT IS AUTOCOMMIT TRUE , IF THIS METHOD REMOVED , ENTRY DATA WILL NOT BE CLEAN !!*/
		  	    /*GENERATE QUERY HEADER*/
		  		sBuild.setLength(0);
		  		queryz = sBuild.append("INSERT INTO ").append(tabName).append(" ( ").toString();
	  			for(int i=0;i<dDriven.length;i++)
	  			{
	  				for(int j=0;j<intCol;j++)
	  				{
	  					if(i==0)
	  					{/*GENERATE QUERY HEADER*/
	  						sBuild.setLength(0);
	  						queryz = sBuild.append(queryz).append(dDriven[0][j]).toString();				
	  			  			if(j != intCol-1)
	  			  			{
	  			  				sBuild.setLength(0);
	  			  				queryz = sBuild.append(queryz).append(",").toString();
	  			  			}
	  					}
	  					else
	  					{
	  						/*APPEND ONE BY ONE DATA FROM EXCEL START FROM ROW 2nd*/
	  						sBuild.setLength(0);/*Replace Quotation marks aims to prevent error in SQL Statement*/
		  					queryz = sBuild.append(queryz).append("'").append(dDriven[i][j].replace("'","")).append("'").
		  							append(",").toString();
	  					}	  					
	  				}
	  				
	  				if(i==0)
	  				{
	  					sBuild.setLength(0);/*FINISHING HEADER*/
	  					queryz = sBuild.append(queryz).append(") VALUES ( ").toString();
	  				}
	  				else
	  				{
	  					sBuild.setLength(0);
		  				queryz = sBuild.append(queryz.substring(0, queryz.length()-1)).append("),").append("\n").append("(").toString();
	  				}
	  			}
	  			
	  			sBuild.setLength(0);
  				queryz = sBuild.append(queryz.substring(0, queryz.length()-3)).append(";").toString();
  				this.strQueries = queryz;/*SET STRING QUERIES*/
  				System.out.println(queryz); /*if you just want to take the string from generate query, uncomment this*/
  				stment.executeUpdate(queryz);/*if you want to execute sql statement from jdbc uncomment this*/
  				connects.commit();
  				System.out.println("Record is inserted in the table successfully..................");
	      } catch (Exception e) {
	    	  exceptionString[1] = "execData(Statement stment, Connection connects, ExcelReader eR,String tabName)  -- EXCEPTION LINE 105";
	    	  stdb.exceptionStringz(exceptionString, e, cProp.getfException());
           try {
				connects.rollback();
			} catch (Exception e1) {
				exceptionString[1] = "execData(Statement stment, Connection connects, ExcelReader eR,String tabName)  -- EXCEPTION LINE 110";
				stdb.exceptionStringz(exceptionString, e1, cProp.getfException());
			}
	      } finally {
	         try {
	        	stdb.closeResource(stment, connects);
			} catch (Exception e) {
				exceptionString[1] = "execData(Statement stment, Connection connects, ExcelReader eR,String tabName)  -- EXCEPTION LINE 117";
				stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			}
	      }
	      System.out.println("Please check it in the MariaDB/MySQL Table......... ??????..");
	}
	
	/*TO GET QUERIES , RESULT FROM GENERATE*/
	public String getStrQueries()
	{
		return strQueries;
	}
}
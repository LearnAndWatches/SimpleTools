package edu.paulo.app.core.connectivity;

import edu.paulo.app.core.connection.SimpleToolsDB;
import edu.paulo.app.core.io.csv.CSVScanner;
import edu.paulo.app.util.ConfigProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCInsertFromCSV {

	
	private Connection conn = null;
	private Statement stmt = null;
	private CSVScanner csvReader ;
	private StringBuilder sBuild = new StringBuilder();
	private String[][] dDriven ;
	private int intCol  = 0; /*accomodate count of column in csv file*/
	private String queryz = "";
	private String queryzHeader = "";
	private String strQueries = "";
	private SimpleToolsDB stdb;
	private String[] exceptionString = new String[2];
	private ConfigProperties cProp ;
	
	public JDBCInsertFromCSV(String strPathCSV, String tableName)
	{
	    exceptionString[0] = "JDBCInsertFromCSV";
		cProp = new ConfigProperties();
		stdb = new SimpleToolsDB();
		setData(strPathCSV, tableName);
	}
	
	public void setData(String strPathCSV, String tableName)
	{
		csvReader = new CSVScanner(strPathCSV);
		/*SET DRIVER BY PARAMETER*/
		try {
            conn = stdb.getDatabaseConnection();
		    System.out.println("Connection is created successfully!!");
		    stmt = conn.createStatement();			
		    execData(stmt, conn, csvReader,tableName);
         } catch (Exception e) {
        	exceptionString[1] = "setData(String strPathCSV, String tableName) --- EXCEPTION LINE 46";
            stdb.exceptionStringz(exceptionString, e, cProp.getfException());
         }
	}
	
	public void execData(Statement stment, Connection connects, CSVScanner csvR,String tabName)
	{
	      try {
	    	  dDriven = csvR.getBR();
	  	      intCol  = csvR.getCol();/*need this variable to help data looping for csv datas*/
	  	      connects.setAutoCommit(false);/*DEFAULT IS AUTOCOMMIT TRUE , IF THIS METHOD REMOVED , ENTRY DATA WILL NOT BE CLEAN !!*/
	  	    
	  		  /*GENERATE QUERY HEADER*/
	  		  sBuild.setLength(0);
	  		  queryzHeader = sBuild.append("INSERT INTO ").append(tabName).append(" ( ").toString();
	  		  
	  		  for(int j=0;j<intCol;j++)
	  		  {
	  		    sBuild.setLength(0);
	  			queryzHeader = sBuild.append(queryzHeader).append(dDriven[0][j]).toString();				
	  			if(j != intCol-1)
	  			{
	  				sBuild.setLength(0);
	  				queryzHeader = sBuild.append(queryzHeader).append(",").toString();
	  			}
	  		  }
	  		  sBuild.setLength(0);/*FINISHING HEADER*/
	  		  queryzHeader = sBuild.append(queryzHeader).append(") VALUES ( ").toString();
	  		  
	  			for(int i=1;i<dDriven.length;i++)
	  			{
	  				sBuild.setLength(0);
	  				queryz = sBuild.append(queryzHeader).toString();/*APPEND ONE BY ONE DATA FROM EXCEL START FROM ROW 2nd*/
	  				for(int j=0;j<intCol;j++)
	  				{
	  					sBuild.setLength(0);/*Replace Quotation marks aims to prevent error in SQL Statement*/
	  					queryz = sBuild.append(queryz).append("'").append(dDriven[i][j].replace("'","")).append("'").
	  							append(",").toString();
	  				}
	  				sBuild.setLength(0);
	  				queryz = sBuild.append(queryz.substring(0, queryz.length()-1)).append(");").toString();
	  				this.strQueries = queryz;
//	  				System.out.println(queryz); /*if you just want to take the string from generate query, uncomment this*/
	  				stment.executeUpdate(queryz);/*if you want to execute sql statement from jdbc uncomment this*/
	  			}
	  			 connects.commit();
  				System.out.println("Record is inserted in the table successfully..................");
		      } catch (Exception e) {
		    	  exceptionString[1] = "execData(Statement stment, Connection connects, CSVScanner csvR,String tabName) --- EXCEPTION LINE 94";
		    	  stdb.exceptionStringz(exceptionString, e, cProp.getfException());
		           try {
						connects.rollback();
					} catch (SQLException e1) {
						exceptionString[1] = "execData(Statement stment, Connection connects, CSVScanner csvR,String tabName) --- EXCEPTION LINE 99";
						stdb.exceptionStringz(exceptionString, e1, cProp.getfException());
					}
		      } finally {
		         try {
		        	 stdb.closeResource(stment, connects);
				} catch (SQLException e) {
					exceptionString[1] = "execData(Statement stment, Connection connects, CSVScanner csvR,String tabName) --- EXCEPTION LINE 106";
					stdb.exceptionStringz(exceptionString, e, cProp.getfException());
				}
		      }
	      System.out.println("Please check it in the MariaDB/MySQL Table......... ……..");
	}
	
	/*TO GET QUERIES , RESULT FROM GENERATE*/
	public String getStrQueries()
	{
		return strQueries;
	}	
}
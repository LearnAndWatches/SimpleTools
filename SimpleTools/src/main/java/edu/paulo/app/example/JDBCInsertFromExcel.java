package edu.paulo.app.example;

import java.sql.DriverManager;

import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

import edu.paulo.app.util.ExcelReader;

public class JDBCInsertFromExcel {

	
	private Connection conn = null;
	private Statement stmt = null;
	private ExcelReader excelReader ;
	private StringBuilder sBuild = new StringBuilder();
	private String[][] dDriven ;
	private int intCol  = 0; /*accomodate count of column in excel file*/
	private String queryz = "";
	private String queryzHeader = "";
    int intCheck = 0;
    
	public static void main(String[] args) {
		JDBCInsertFromExcel jife = new JDBCInsertFromExcel();
	    String [] strCon = new String[4];
	    strCon[0] = "org.mariadb.jdbc.Driver";/*Database Driver*/
		strCon[1] = "jdbc:mariadb://localhost:3309/z_acf";/*Connection String*/
		strCon[2] = "root";/*Database userName*/
		strCon[3] = "root";/*Database passwOrd*/
		
		/*Parameter order , path excel file --- sheet name ---- table name ---- driver & connection string*/
		jife.setData("./data/DataDriven.xlsx","JDBCDemoInsert", "insert_demo", strCon);
   }
	
	public void setData(String strPathExcel, String strSheetName, String tableName, String[] conString)
	{
		excelReader = new ExcelReader(strPathExcel, strSheetName);
		/*SET DRIVER BY PARAMETER*/
		try {
            Class.forName(conString[0]);
            conn = (Connection) DriverManager.getConnection(conString[1],conString[2],conString[3]);
		    System.out.println("Connection is created successfully!!");
		    stmt = conn.createStatement();
			
		    execData(stmt, conn, excelReader,tableName);
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }		
	}
	
	public void execData(Statement stment, Connection connects, ExcelReader eR,String tabName)
	{
		
	      try {
	    	  dDriven = eR.getAllData();
	  	      intCol  = eR.getColCount();/*need this variable to help data looping for excel datas*/
	  		  
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
//	  				System.out.println(queryz); /*if you just want to take the string from generate query, uncomment this*/
		  				stment.executeUpdate(queryz);/*if you want to execute sql statement from jdbc uncomment this*/
	  			}
	  	      System.out.println("Record is inserted in the table successfully..................");
	      } catch (Exception e) {
	         e.getMessage();
	      } finally {
	         try {
	            if (stment != null)
	            	connects.close();
	         } catch (Exception e) {
	        	 e.getMessage();
	         }
	         try {
	            if (connects != null)
	            	connects.close();
	         } catch (Exception e) {
	            e.getMessage();
	         }  
	      }
	      System.out.println("Please check it in the MariaDB/MySQL Table......... ……..");
	}
}
package edu.paulo.app.example.withstatement;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

import edu.paulo.app.util.CSVScanner;
import edu.paulo.app.util.ConfigProperties;

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
	
	public JDBCInsertFromCSV(String strPathCSV, String tableName, String[] conString)
	{
		setData(strPathCSV, tableName, conString);
	}
	
    public static void main(String[] args) {
		ConfigProperties cProp = new ConfigProperties();		
	    String [] strCon = new String[4];
	    strCon[0] = cProp.getDbDriver();/*Database Driver*/
		strCon[1] = cProp.getDbConnString();/*Connection String*/
		strCon[2] = cProp.getDbUserName();/*Database userName*/
		strCon[3] = cProp.getDbPassword();/*Database passwOrd*/
		
		JDBCInsertFromCSV jife = new JDBCInsertFromCSV("./data/DataDriven.csv", "insert_demo", strCon);
		/*Parameter order , path csv file --- table name ---- driver & connection string*/
   }
	
	public void setData(String strPathCSV, String tableName, String[] conString)
	{
		csvReader = new CSVScanner(strPathCSV);
		/*SET DRIVER BY PARAMETER*/
		try {
            Class.forName(conString[0]);
            conn = (Connection) DriverManager.getConnection(conString[1],conString[2],conString[3]);
		    System.out.println("Connection is created successfully!!");
		    stmt = conn.createStatement();
			
		    execData(stmt, conn, csvReader,tableName);
         } catch (Exception e) {
            System.out.println(e.getMessage());
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
	  				System.out.println(queryz); /*if you just want to take the string from generate query, uncomment this*/
		  				stment.executeUpdate(queryz);/*if you want to execute sql statement from jdbc uncomment this*/
	  			}
	  			 connects.commit();
  				System.out.println("Record is inserted in the table successfully..................");
		      } catch (Exception e) {
		         System.out.println(e.getMessage());
	           try {
					connects.rollback();
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
		      } finally {
		         try {
					closeResource(stment, connects);
				} catch (SQLException e) {
					System.out.println(e.getMessage()+" --- "+e.getCause());
				}  
		      }
	      System.out.println("Please check it in the MariaDB/MySQL Table......... ……..");
	}
	
	/*TO GET QUERIES , RESULT FROM GENERATE*/
	public String getStrQueries()
	{
		return strQueries;
	}
	
	public void closeResource(Statement stmt, Connection connect) throws SQLException{
        if(stmt != null) {
            if(!stmt.isClosed())
            	stmt.close();
            stmt = null;
        }

        if(connect != null) {
            if(!connect.isClosed())
                connect.close();
        }
    }
}
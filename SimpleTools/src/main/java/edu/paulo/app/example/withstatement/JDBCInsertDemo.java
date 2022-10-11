package edu.paulo.app.example.withstatement;

import edu.paulo.app.core.connection.SimpleToolsDB;
import edu.paulo.app.util.ConfigProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCInsertDemo {
	
	   public static void main(String[] args) {
		   	  Connection conn = null;
		      Statement stmt = null;
		      SimpleToolsDB stdb = new SimpleToolsDB();
		      ConfigProperties cProp = new ConfigProperties();
		      String[] exceptionString = new String[2];
		      exceptionString[0] = "JDBCInsertDemo";
		      exceptionString[1] = "main method";
		      
		      try {
			      conn = stdb.getDatabaseConnection();
			      System.out.println("Connection is created successfully:");
			      stmt = conn.createStatement();
			      String queryz = "INSERT INTO insert_demo VALUES (1, 'John', 'Smith')";
			      stmt.executeUpdate(queryz);
			      
			      queryz = "INSERT INTO insert_demo VALUES (2, 'Carol', 'Alexandria')";
			      stmt.executeUpdate(queryz);
			      
			      System.out.println("Record is inserted in the table successfully..................");
		      } catch (Exception excep) {
		    	  stdb.exceptionStringz(exceptionString, excep, cProp.getfException());
		      } finally {
		         try {
					stdb.closeResource(stmt, conn);
				} catch (SQLException e) {
					stdb.exceptionStringz(exceptionString, e, cProp.getfException());
				}
		      }
		      System.out.println("Please check it in the MySQL Table......... ……..");
	   }
}
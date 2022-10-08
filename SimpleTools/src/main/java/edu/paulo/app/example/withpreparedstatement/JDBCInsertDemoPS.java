package edu.paulo.app.example.withpreparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.paulo.app.core.connection.SimpleToolsDB;
import edu.paulo.app.util.ConfigProperties;


/*
 * PS = Prepared Statement
 */
public class JDBCInsertDemoPS {
	
	   public static void main(String[] args) {
		   
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ConfigProperties cProp = new ConfigProperties();		
	      String[] exceptionString = new String[2];
	      exceptionString[0] = "JDBCInsertDemoPS";
	      exceptionString[1] = "main(String[] args)";
	      SimpleToolsDB stdb = new SimpleToolsDB();

	      try {
	    	  
		      conn = stdb.getDatabaseConnection();
		      System.out.println("Connection is created successfully:");
		      conn.setAutoCommit(false);/*DEFAULT IS AUTOCOMMIT TRUE !!*/
		      String queryz = "INSERT INTO insert_demo ( id,first_name,last_name) VALUES (?,?,?)";
		      pstmt = conn.prepareStatement(queryz) ;
		      pstmt.setString(1, "1");
	          pstmt.setString(2, "Andhika");
	          pstmt.setString(3, "Bagaskara");
	
	          pstmt.addBatch();
		      int row = pstmt.executeUpdate();
		      conn.commit();
	      
		      System.out.println("Record is inserted in the table successfully..................");
	      }catch (Exception e) {
	    	   stdb.exceptionStringz(exceptionString, e, cProp.getfException());
	    	  try {
				conn.rollback();
			} catch (SQLException e1) {
				stdb.exceptionStringz(exceptionString, e1, cProp.getfException());
			}
	      } finally {
	    	  try {
				stdb.closeResource(pstmt, conn);
			} catch (SQLException e) {
				stdb.exceptionStringz(exceptionString, e, cProp.getfException());
			}
	      }
	      System.out.println("Please check it in the MariaDB/MySQL Table......... ……..");
	   }
}
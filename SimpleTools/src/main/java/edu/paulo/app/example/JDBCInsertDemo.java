package edu.paulo.app.example;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

public class JDBCInsertDemo {
	   public static void main(String[] args) {
	      Connection conn = null;
	      Statement stmt = null;
	      try {
	         try {
	            Class.forName("org.mariadb.jdbc.Driver");
	         } catch (Exception e) {
	            System.out.println(e);
	      }
	      conn = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3309/z_acf", "root", "root");
	      System.out.println("Connection is created successfully:");
	      stmt = (Statement) conn.createStatement();
	      String query1 = "INSERT INTO insert_demo " + "VALUES (1, 'John', 'Smith')";
	      stmt.executeUpdate(query1);
	      
	      query1 = "INSERT INTO insert_demo " + "VALUES (2, 'Carol', 'Alexandria')";
	      stmt.executeUpdate(query1);
	      
	      System.out.println("Record is inserted in the table successfully..................");
	      } catch (SQLException excep) {
	         excep.printStackTrace();
	      } catch (Exception excep) {
	         excep.printStackTrace();
	      } finally {
	         try {
	            if (stmt != null)
	               conn.close();
	         } catch (SQLException se) {}
	         try {
	            if (conn != null)
	               conn.close();
	         } catch (SQLException se) {
	            se.printStackTrace();
	         }  
	      }
	      System.out.println("Please check it in the MySQL Table......... ……..");
	   }
}
package edu.paulo.app.example.withpreparedstatement;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

/*
 * PS = Prepared Statement
 */
public class JDBCInsertDemoPS {
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
	      conn.setAutoCommit(false);/*DEFAULT IS AUTOCOMMIT TRUE !!*/
	      String queryz = "INSERT INTO insert_demo ( id,first_name,last_name) VALUES (?,?,?)";
	      PreparedStatement preparedStatement = conn.prepareStatement(queryz) ;
	      preparedStatement.setString(1, "1");
          preparedStatement.setString(2, "Andhika");
          preparedStatement.setString(3, "Bagaskara");

          preparedStatement.addBatch();
	      int row = preparedStatement.executeUpdate();
	      conn.commit();
	      
	      System.out.println("Record is inserted in the table successfully..................");
	      }catch (Exception e) {
	    	  System.out.println(e.getMessage()+" data error !! ");
	    	  try {
				conn.rollback();
				System.out.println("MAYDAY MAYDAY --- ROLLBACK!!");
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
	      } finally {
	         try {
	            if (stmt != null)
	               conn.close();
	         } catch (SQLException e) {System.out.println(e.getMessage());}
	         
	         try {
	            if (conn != null)
	               conn.close();
	         } catch (SQLException e) {
	        	 System.out.println(e.getMessage());
	         }
	      }
	      System.out.println("Please check it in the MariaDB/MySQL Table......... ……..");
	   }
}
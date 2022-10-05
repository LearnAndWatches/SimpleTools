//package edu.paulo.app.example;
//
//import java.sql.DriverManager;
//
//import org.mariadb.jdbc.Connection;
//import org.mariadb.jdbc.Statement;
//
//import edu.paulo.app.util.ExcelReader;
//
//public class JDBCInsertFromExcelTwo {
//
//	public static void main(String[] args) {
//	      Connection conn = null;
//	      Statement stmt = null;
//	      String excelPath = "./data/DataDriven.xlsx";
//	      String sheetName = "JDBCDemoInsert";
//	      ExcelReader excelReader = new ExcelReader(excelPath, sheetName);
//	      StringBuilder sBuild = new StringBuilder();
//	      String queryz = "";
//	      String queryzHeader = "";	      
//	      int intCheck = 0;
//	      String tableName = "insert_demo";
//	      
//	      try {
//	         try {
//	            Class.forName("org.mariadb.jdbc.Driver");
//	         } catch (Exception e) {
//	            System.out.println(e.getMessage());
//	         }
//	         
//		      conn = (Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3309/z_acf", "root", "root");
//		      System.out.println("Connection is created successfully:");
//		      stmt = conn.createStatement();
//			
//		      String[][] dDriven = excelReader.getAllData();
//			  int intCol  = excelReader.getColCount();/*need this variable to help data looping for excel datas*/
//			  
//			  /*GENERATE QUERY HEADER*/
//			  sBuild.setLength(0);
//			  queryzHeader = sBuild.append("INSERT INTO ").append(tableName).append(" ( ").toString();
//			  
//			  for(int j=0;j<intCol;j++)
//			  {
//			    sBuild.setLength(0);
//				queryzHeader = sBuild.append(queryzHeader).append(dDriven[0][j]).toString();				
//				if(j != intCol-1)
//				{
//					sBuild.setLength(0);
//					queryzHeader = sBuild.append(queryzHeader).append(",").toString();
//				}
//			  }
//			  sBuild.setLength(0);/*FINISHING HEADER*/
//			  queryzHeader = sBuild.append(queryzHeader).append(") VALUES ( ").toString();
//			  
//				for(int i=1;i<dDriven.length;i++)
//				{
//					sBuild.setLength(0);
//					queryz = sBuild.append(queryzHeader).toString();
//					for(int j=0;j<intCol;j++)
//					{
//						sBuild.setLength(0);
//						queryz = sBuild.append(queryz).append("'").append(dDriven[i][j].replace("'","")).append("'").
//								append(",").toString();
//					}
//					sBuild.setLength(0);
//					queryz = sBuild.append(queryz.substring(0, queryz.length()-1)).append(");").toString();
//					System.out.println(queryz); /*if you just want to take the string from generate query, uncomment this*/
////					stmt.executeUpdate(queryz);/*if you want to execute sql statement from jdbc uncomment this*/
//				}
//		      System.out.println("Record is inserted in the table successfully..................");
//		      
//	      } catch (Exception e) {
//	         e.getMessage();
//	      } finally {
//	         try {
//	            if (stmt != null)
//	               conn.close();
//	         } catch (Exception e) {
//	        	 e.getMessage();
//	         }
//	         try {
//	            if (conn != null)
//	               conn.close();
//	         } catch (Exception e) {
//	            e.getMessage();
//	         }  
//	      }
//	      System.out.println("Please check it in the MySQL Table......... ……..");
//	   }
//}
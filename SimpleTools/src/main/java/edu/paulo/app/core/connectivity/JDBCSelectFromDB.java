package edu.paulo.app.core.connectivity;

import edu.paulo.app.core.connection.SimpleToolsDB;
import edu.paulo.app.core.io.poi.ExcelWriter;
import edu.paulo.app.util.ConfigProperties;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

public class JDBCSelectFromDB {



	   public static void main(String[] args) {
		   String[] exceptionString = new String[2];
		   exceptionString[0] = "JDBCSelectFromDB";
		   exceptionString[1] = "execData(Statement stment, Connection connects, CSVScanner csvR,String tabName)";
		   Connection conn = null;
	       Statement stmt = null;
	       ResultSet rs = null;
	       ConfigProperties cProp = new ConfigProperties();
	       SimpleToolsDB stdb = new SimpleToolsDB();
//	       String [] k = {"id","jobtitle","emailaddress","firstnamelastname","randoms","kosmetik","ktp","kampus","mat_kul","institusi","skill","departemen","saham","no_cc","obat","tgl",};
	       
		   try {
		      conn = stdb.getDatabaseConnection();
		      System.out.println("Connection is created successfully:");
		      stmt = conn.createStatement();
		      
		      String queryz = "SELECT * FROM complex_data ";
		      rs = stmt.executeQuery(queryz);
		      String [] strArr = {"id","first_name","last_name","email","gender","my_date"};		      
		      List<HashMap<String,Object>> list = stdb.getListData(strArr, rs);
		      
//		      for(int i=0;i<list.size();i++)
//		      {
//		    	  for(int j=0;j<strArr.length;j++)
//		    	  {
//		    		  System.out.print(list.get(i).get(strArr[j])+"------");
//		    	  }
//		    	  System.out.println();
//		    	  
//		      }
		      String pathOutout = System.getProperty("user.dir")+"\\outputio\\Sample.xlsx";
		      new ExcelWriter(list, pathOutout, "Contoh_1", strArr);
//		      pathOutout = System.getProperty("user.dir")+"\\outputio\\Sample.csv";
//		      new CSVWriting(list, pathOutout, strArr,',');
		      
		      System.out.println("Record is inserted in the table successfully..................");
		      } catch (Exception e) {
		    	  stdb.exceptionStringz(exceptionString, e, cProp.getfException());
		      } finally {
		    	  try {
					stdb.closeResource(rs, stmt, conn);
				} catch (SQLException e) {
					stdb.exceptionStringz(exceptionString, e, cProp.getfException());
				}
		      }
		      System.out.println("Please check it in the MySQL Table......... ……..");
	   }
}
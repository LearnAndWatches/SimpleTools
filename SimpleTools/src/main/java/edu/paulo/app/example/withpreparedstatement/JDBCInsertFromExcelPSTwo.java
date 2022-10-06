package edu.paulo.app.example.withpreparedstatement;

import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mariadb.jdbc.Connection;

import edu.paulo.app.util.ExcelReader;

public class JDBCInsertFromExcelPSTwo {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ExcelReader excelReader ;
	private StringBuilder sBuild = new StringBuilder();
	private String[][] dDriven ;
	private int intCol  = 0; /*accomodate count of column in excel file*/
	private String queryz = "";
	int intCheck = 0;
    
	public static void main(String[] args) {

		JDBCInsertFromExcelPSTwo jife = new JDBCInsertFromExcelPSTwo();
	    String [] strCon = new String[4];
	    strCon[0] = "org.mariadb.jdbc.Driver";/*Database Driver*/
		strCon[1] = "jdbc:mariadb://localhost:3309/z_acf";/*Connection String*/
		strCon[2] = "root";/*Database userName*/
		strCon[3] = "root";/*Database passwOrd*/
		
		/*Parameter order , path excel file --- sheet name ---- table name ---- driver & connection string*/
		jife.setData("./data/DataDriven.xlsx","ComplexData", "complex_data", strCon);
	}
	
	public void setData(String strPathExcel, String strSheetName, String tableName, String[] conString)
	{
		excelReader = new ExcelReader(strPathExcel, strSheetName);
		/*SET DRIVER BY PARAMETER*/
		try {
            Class.forName(conString[0]);
            conn = (Connection) DriverManager.getConnection(conString[1],conString[2],conString[3]);
		    System.out.println("Connection is created successfully!!");
		    
		    conn.setAutoCommit(false);/*DEFAULT IS AUTOCOMMIT TRUE , IF THIS METHOD REMOVED , ENTRY DATA WILL NOT BE CLEAN !!*/
		    
		    dDriven = excelReader.getAllData();
  	        intCol  = excelReader.getColCount();
  	        queryz = generateQueryInsert(dDriven, tableName, intCol);
  	        ps = conn.prepareStatement(queryz) ;
  	        
  	        dDriven = excelReader.getDataWithoutHeader();
  	        
  	        for(int i=0;i<dDriven.length;i++)
  	        {
  	        	for(int j=0;j<intCol;j++)
  	        	{
  	        		setStatement(j, dDriven[i][j], ps);
  	        	}
  	        	ps.executeUpdate();
  	        }
//  	        int k = ps.executeUpdate();
  	        conn.commit();
  	      System.out.println("Record is inserted in the table successfully..................");
         } catch (Exception e) {
        	System.out.println("Failed to insert Record to the table ..................!!!!");
            System.out.println(e.getMessage());            
            try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}
         }
		finally {
			try {
				closeResource(ps, conn);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Please check it in the MariaDB/MySQL Table......... ……..");
	}
	
	public String generateQueryInsert(String[][] header, String tableName, int colCountz)
	{
		sBuild.setLength(0);
		queryz = sBuild.append("INSERT INTO ").append(tableName).append(" (").toString();
		
		for(int i=0;i<colCountz;i++)
		{
			sBuild.setLength(0);
			queryz = sBuild.append(queryz).append(header[0][i]).toString();
			if(i != colCountz-1)
			{
				sBuild.setLength(0);
				queryz = sBuild.append(queryz).append(",").toString();
			}
		}
		sBuild.setLength(0);
		queryz = sBuild.append(queryz).append(") VALUES (").toString();
		
		for(int i=0;i<colCountz;i++)
		{
			sBuild.setLength(0);
			queryz = sBuild.append(queryz).append("?").toString();
			if(i != colCountz-1)
			{
				sBuild.setLength(0);
				queryz = sBuild.append(queryz).append(",").toString();
			}
		}
		sBuild.setLength(0);
		queryz = sBuild.append(queryz).append(");").toString();
		
		return queryz;
	}
	
	public void setStatement(int i, Object obj, PreparedStatement preStmt) {        

        try{
            if (obj instanceof Long) {
                preStmt.setLong(i+1, (Long) obj);
            }else if (obj instanceof Integer) {
                preStmt.setInt(i+1, (Integer) obj);
            }else if(obj instanceof java.util.Date){
//                preStmt.setDate(i+1, new Date(((java.util.Date) obj).getTime()));
                preStmt.setTimestamp(i+1, new java.sql.Timestamp(((java.util.Date) obj).getTime()));
            }else if(obj instanceof Blob){
                preStmt.setBlob(i+1, (Blob)obj);
            }
            else {
                preStmt.setString(i+1, obj.toString());
            }
        }   catch (SQLException e) {
        	System.out.println("MAYDAY MAYDAY --- ERROR!!"+e.getMessage());
        }
    }
	
	public void closeResource(PreparedStatement pstmt, Connection connect) throws SQLException{
        if(pstmt != null) {
            if(!pstmt.isClosed())
                pstmt.close();
            pstmt = null;
        }

        if(connect != null) {
            if(!connect.isClosed())
                connect.close();
        }
    }
	
}
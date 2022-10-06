package edu.paulo.app.example.withpreparedstatement;

import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mariadb.jdbc.Connection;

import edu.paulo.app.util.CSVScanner;
import edu.paulo.app.util.ConfigProperties;

/*
 * THIS CLASS USING COMPLEX DATA TO THE complex_data table
 */
public class JDBCInsertFromCSVPSTwo {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private CSVScanner csvScanner ;
	private StringBuilder sBuild = new StringBuilder();
	private String[][] dDriven ;
	private int intCol  = 0; /*accomodate count of column in excel file*/
	private String queryz = "";
	private String queryzHeader = "";
    int intCheck = 0;
    
    public JDBCInsertFromCSVPSTwo(String strPathCSV, String tableName, String[] conString)
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
		
		/*Parameter order , path excel file --- sheet name ---- table name ---- driver & connection string*/
		JDBCInsertFromCSVPSTwo jife = new JDBCInsertFromCSVPSTwo("./data/DataComplex.csv","complex_data", strCon);
	}
	
	public void setData(String strPathCSV, String tableName, String[] conString)
	{
		csvScanner = new CSVScanner(strPathCSV);
		/*SET DRIVER BY PARAMETER*/
		try {
            Class.forName(conString[0]);
            conn = (Connection) DriverManager.getConnection(conString[1],conString[2],conString[3]);
		    System.out.println("Connection is created successfully!!");
		    
//		    conn.setAutoCommit(false);/*DEFAULT IS AUTOCOMMIT TRUE , IF THIS METHOD REMOVED , ENTRY DATA WILL NOT BE CLEAN !!*/
		    
		    dDriven = csvScanner.getBR();
  	        intCol  = csvScanner.getCol();
  	        
  	        queryz = generateQueryInsert(dDriven, tableName, intCol);
  	        
  	        ps = conn.prepareStatement(queryz) ;
  	        
  	        dDriven = csvScanner.getDataWithoutHeader();
  	        
  	        for(int i=0;i<dDriven.length;i++)
  	        {
  	        	for(int j=0;j<intCol;j++)
  	        	{
  	        		setStatement(j, dDriven[i][j], ps);
  	        	}
  	        	ps.executeUpdate();
  	        }
//  	        int k = ps.executeUpdate();
//  	        conn.commit();
  	      System.out.println("Record is inserted in the table successfully..................");
         } catch (Exception e) {
        	 System.out.println("Failed to insert Record to the table ..................!!!!");
            System.out.println(e.getMessage());            
//            try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				System.out.println(e1.getMessage());
//			}
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
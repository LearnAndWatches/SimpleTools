package edu.paulo.app.core.connection;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import edu.paulo.app.util.ConfigProperties;

public class SimpleToolsDB {

    protected int done = 0;
    protected String query = "";
    private final String thisClassNamez = "SimpleToolsDB";
    private String[] exceptionString;
    private StringBuilder sbuilds;
    private Logger logger = Logger.getLogger(SimpleToolsDB.class);
    private ConfigProperties cProp;
    private String sConString;
    private String sLogin;
    private String sPwd;
    private Connection con = null;
    private PreparedStatement ps = null;
    private Driver driver = null;
    private HashMap<String,Object> hMap ;
    private List<HashMap<String,Object>> list;
    
    public SimpleToolsDB()
    {
    	list = new ArrayList<HashMap<String,Object>>();
        sbuilds  = new StringBuilder();
        new StringBuilder();
        exceptionString = new String[3];
        exceptionString[0] = thisClassNamez;
        cProp = new ConfigProperties();
        sConString = cProp.getDbConnString();
        sLogin = cProp.getDbUserName();
        sPwd = cProp.getDbPassword();
        setDatabaseConnection();
    }
    
    public void setDatabaseConnection() {
        exceptionString[1] = "setDatabaseConnection()";
        try {
        	driver = new org.mariadb.jdbc.Driver();
        	DriverManager.registerDriver(driver);
        	this.con = DriverManager.getConnection(sConString, sLogin, sPwd);
        } catch(Exception e) {
        	exceptionStringz(exceptionString,e,cProp.getfException());
        	con = null;
        }
    }
    
    public Connection getDatabaseConnection() {        
        exceptionString[1] = "getDatabaseConnection()";
        try
        {
        	if(con==null)
            {
            	setDatabaseConnection();/*instantiate connection*/            	
            }
        }catch(Exception e)
        {
        	exceptionStringz(exceptionString,e,cProp.getfException());
        }
        return con;
    }
    
    public void destroyConnection()
    {
    	exceptionString[1] = "killConnection()";
        try {
            con = null;
            DriverManager.deregisterDriver(driver);
            driver =null;
        } catch (SQLException e) {
        	exceptionStringz(exceptionString,e,cProp.getfException());
        }
    }
    
    public List<HashMap<String,Object>> getListData(String[] strArr,ResultSet rs)
    {
    	exceptionString[1] = "getListData(String[] strArr,ResultSet rs)";
    	list.clear();
    	try {
			while(rs.next())
			  {
				  hMap = new HashMap<String,Object>();
				  for(int i =0;i<strArr.length;i++)
				  {
					  hMap.put(strArr[i],rs.getObject(strArr[i])==null?"":rs.getString(strArr[i]));
				  }
				  list.add(hMap);
			  }
		} catch (SQLException e) {
			exceptionStringz(exceptionString,e,cProp.getfException());
		}
    	
    	return list;
    }
    
    public void setPStatement(int i, Object obj, PreparedStatement preStmt) {
        exceptionString[1] = "setStatement()";

        try{
            if (obj instanceof Long) {
                preStmt.setLong(i+1, (Long) obj);
            }else if (obj instanceof Integer) {
                preStmt.setInt(i+1, (Integer) obj);
            }else if(obj instanceof java.util.Date){
                preStmt.setTimestamp(i+1, new java.sql.Timestamp(((java.util.Date) obj).getTime()));
            }else if(obj instanceof Blob){
                preStmt.setBlob(i+1, (Blob)obj);
            }
            else {
                preStmt.setString(i+1, obj.toString());
            }
        }   catch (SQLException e) {
        	exceptionStringz(exceptionString,e,cProp.getfException());
        }
    }
    
    /*USING THIS IF JUST WANT TO EXECUTE 1 SQL STATEMENT INSERT !!*/
    public int executeQuery(String query) {
        done = 0;
        exceptionString[1] = "executeQuery(String query)";        

        try
        {
            con = getDatabaseConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            con.commit();
            done = 1;

        }
        catch (Exception e) 
        {
            done = 0;
            exceptionString[1] = "executeQuery(String query)==> con.rollback()";
            
            exceptionStringz(exceptionString,e,cProp.getfException());
            try {
                con.rollback();
            } catch (SQLException ex) {
            	exceptionStringz(exceptionString,e,cProp.getfException());
            }
        }
        finally
        {
            try
            {
                closeResource(ps,con);
            }
            catch (SQLException e)
            {
                exceptionString[1] = "executeQuery(String query) ==> finally -> closeResource(ps,con)";                
                exceptionStringz(exceptionString,e,cProp.getfException());
            }
        }
        return done;
    }
    
    public void exceptionStringz(String[] datax,Exception e, String flag) {
    	if(flag.equals("y"))
    	{
    		sbuilds.setLength(0);
            logger.info(sbuilds.append(System.getProperty("line.separator")).
                    append("ERROR IN CLASS =>").append(datax[0]).append(System.getProperty("line.separator")).
                    append("METHOD   =>").append(datax[1]).append(System.getProperty("line.separator")).
                    append("ERROR IS       =>").append(e.getMessage()).
                    append(System.getProperty("line.separator")).toString());
            sbuilds.setLength(0);
    	}
    }
    
    public void closeResource(PreparedStatement pstmt, Connection connect) throws SQLException{
        if(pstmt != null) {
            if(!pstmt.isClosed())
            {
            	pstmt.close();
            }                
            pstmt = null;
        }

        if(connect != null) {
            if(!connect.isClosed()) {
            	connect.close();
            }                
            destroyConnection();
        }
    }
    public void closeResource(Statement stmt, Connection connect) throws SQLException{
        if(stmt != null) {
            if(!stmt.isClosed())
            {
            	stmt.close();
            }                
            stmt = null;
        }

        if(connect != null) {
            if(!connect.isClosed()) {
            	connect.close();
            }                
            destroyConnection();
        }
    }
    
    public void closeResource(ResultSet rst, Statement stmt, Connection connect) throws SQLException{
        if(stmt != null) {
            if(!stmt.isClosed())
            	stmt.close();
            stmt = null;
        }

        if(rst != null) {
            if(!rst.isClosed())
                rst.close();
            rst = null;
        }

        if(connect != null) {
            if(!connect.isClosed())
                connect.close();
            destroyConnection();
        }
    }
    public void closeResource(ResultSet rst, PreparedStatement pstmt, Connection connect) throws SQLException{
        if(pstmt != null) {
            if(!pstmt.isClosed())
                pstmt.close();
            pstmt = null;
        }

        if(rst != null) {
            if(!rst.isClosed())
                rst.close();
            rst = null;
        }

        if(connect != null) {
            if(!connect.isClosed())
                connect.close();
            destroyConnection();
        }
    }
    
    public void closeResource(CallableStatement cs, Connection connect) throws SQLException{
        if(cs != null) {
            if(!cs.isClosed())
                cs.close();
            cs = null;
        }

        if(connect != null) {
            if(!connect.isClosed())
                connect.close();
            destroyConnection();
        }
    }
}
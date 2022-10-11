package edu.paulo.app.core.connection;

import edu.paulo.app.util.ConfigProperties;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    	list = new ArrayList<>();
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
        
        try {
        	driver = new org.mariadb.jdbc.Driver();
        	DriverManager.registerDriver(driver);
        	this.con = DriverManager.getConnection(sConString, sLogin, sPwd);
        } catch(Exception e) {
        	exceptionString[1] = "setDatabaseConnection() -- EXCEPTION LINE 60";
        	exceptionStringz(exceptionString,e,cProp.getfException());
        	con = null;
        }
    }
    
    public Connection getDatabaseConnection() {        
        
        try
        {
        	if(con==null)
            {
            	setDatabaseConnection();/*instantiate connection*/            	
            }
        }catch(Exception e)
        {
        	exceptionString[1] = "getDatabaseConnection()  -- EXCEPTION LINE 75";
        	exceptionStringz(exceptionString,e,cProp.getfException());
        }
        return con;
    }
    
    public void destroyConnection()
    {
    	
        try {
            con = null;
            DriverManager.deregisterDriver(driver);
            driver =null;
        } catch (SQLException e) {
        	exceptionString[1] = "destroyConnection()---- -- EXCEPTION LINE 87";
        	exceptionStringz(exceptionString,e,cProp.getfException());
        }
    }
    
    public List<HashMap<String,Object>> getListData(String[] strArr,ResultSet rs)
    {
    	
    	list.clear();
    	try {
			while(rs.next())
			  {
				  hMap = new HashMap<>();
                  for (String s : strArr) {
                      hMap.put(s, rs.getObject(s) == null ? "" : rs.getString(s));
                  }
				  list.add(hMap);
			  }
		} catch (SQLException e) {
			exceptionString[1] = "List<HashMap<String,Object>> getListData(String[] strArr,ResultSet rs) -- EXCEPTION LINE 107";
			exceptionStringz(exceptionString,e,cProp.getfException());
		}
    	
    	return list;
    }
    
    public void setPStatement(int i, Object obj, PreparedStatement preStmt) {
        

        try{
            if (obj instanceof Long) {
                preStmt.setLong(i+1, (Long) obj);
            }else if (obj instanceof Integer) {
                preStmt.setInt(i+1, (Integer) obj);
            }else if(obj instanceof java.util.Date){
                preStmt.setTimestamp(i+1, new Timestamp(((java.util.Date) obj).getTime()));
            }else if(obj instanceof Blob){
                preStmt.setBlob(i+1, (Blob)obj);
            }
            else {
                preStmt.setString(i+1, obj.toString());
            }
        }   catch (SQLException e) {
        	exceptionString[1] = "setPStatement(int i, Object obj, PreparedStatement preStmt) ----  -- EXCEPTION LINE 132";
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
            exceptionString[1] = "executeQuery(String query) ------ EXCEPTION LINE 155";            
            exceptionStringz(exceptionString,e,cProp.getfException());
            try {
                con.rollback();
            } catch (SQLException ex) {
            	exceptionString[1] = "executeQuery(String query) ------ EXCEPTION LINE 160";
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
            	exceptionString[1] = "executeQuery(String query) ------ EXCEPTION LINE 171";                
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
            {
                stmt.close();
            }
        }

        if(rst != null) {
            if(!rst.isClosed())
            {
                rst.close();
            }
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
            {
                pstmt.close();
            }
        }

        if(rst != null) {
            if(!rst.isClosed())
            {
                rst.close();
            }
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
            {
                cs.close();
            }
        }

        if(connect != null) {
            if(!connect.isClosed())
            {
                connect.close();
            }
            destroyConnection();
        }
    }
}
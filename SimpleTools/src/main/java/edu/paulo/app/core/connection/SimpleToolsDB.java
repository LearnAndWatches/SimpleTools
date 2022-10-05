package edu.paulo.app.core.connection;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.mariadb.jdbc.Driver;

import edu.paulo.app.util.Crypto;
import edu.paulo.app.util.SimpleToolsProperties;

public class SimpleToolsDB {

	protected Driver driver =null;
    protected int done = 0;
    protected String query = "";
    private final String thisClassNamez = "SimpleToolsDB";
    private String[] exceptionString;
    private StringBuilder sbuilds;
    private StringBuilder sbuildz;
    private Logger logger = Logger.getLogger(SimpleToolsDB.class);
    private SimpleToolsProperties sToolsProp;
    private String sDrvClass ;
    private String sConString;
    private String sLogin;
    private String sPwd;
    
    public SimpleToolsDB()
    {
        sbuilds  = new StringBuilder();
        sbuildz = new StringBuilder();
        exceptionString = new String[3];
        exceptionString[0] = thisClassNamez;
        sToolsProp = new SimpleToolsProperties();
        sDrvClass = sToolsProp.getDbDriver();
        sConString = sToolsProp.getDbConnString();
        sLogin = sToolsProp.getDbUserName();
        sPwd = sToolsProp.getDbPassword();
    }
    
    public void SetDatabaseConnection(Connection conn) {
        
        exceptionString[1] = "setDatabaseConnection()";
        try {
            conn = null;
            DriverManager.deregisterDriver(driver);
            driver =null;
        } catch (SQLException e) {
        	exceptionStringz(exceptionString,e,sToolsProp.getfException());
        }
    }
    
    public Connection getDatabaseConnection() {
        Connection conz = null;
        exceptionString[1] = "getDatabaseConnection()";
        try {           

            
            sPwd = Crypto.performDecrypt(sPwd);
            driver = (Driver) Class.forName(sDrvClass).newInstance();
            DriverManager.registerDriver(driver);
            conz = DriverManager.getConnection(sConString, sLogin, sPwd);
        } catch(Exception e) {
        	exceptionStringz(exceptionString,e,sToolsProp.getfException());
        	conz = null;
        }
        return conz;
    }
    
    public void setStatement(int i, Object obj, PreparedStatement preStmt) {
        exceptionString[1] = "setStatement()";

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
        	exceptionStringz(exceptionString,e,sToolsProp.getfException());
        }
    }
    
    public int executeQuery(String query) {
        done = 0;
        exceptionString[1] = "executeQuery(String query)";
        Connection con = null;
        PreparedStatement ps = null;

        try{
            con = getDatabaseConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            con.commit();
            done = 1;

        }catch (Exception e) {
            done = 0;
            exceptionString[1] = "executeQuery(String query)==> con.rollback()";
            
            exceptionStringz(exceptionString,e,sToolsProp.getfException());
            try {
                con.rollback();
            } catch (SQLException ex) {
            	exceptionStringz(exceptionString,e,sToolsProp.getfException());
            }
        }finally {
            try{
                closeResource(ps,con);
            }   catch (SQLException e){
                exceptionString[1] = "executeQuery(String query) ==> finally -> closeResource(ps,con)";                
                exceptionStringz(exceptionString,e,sToolsProp.getfException());
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
                    append("ERROR IS       =>").append(e.getMessage()).append(System.getProperty("line.separator")).toString());
            sbuilds.setLength(0);
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
            this.SetDatabaseConnection(connect);
        }
    }
}
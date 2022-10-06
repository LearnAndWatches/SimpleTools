package edu.paulo.app.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

	private Properties properties = new Properties();
	private String dbDriver ;
    private String dbConnString ;
    private String dbUserName ;
    private String dbPassword ;
    private String emUsrName ;
    private String emPassword ;
    private String emPort ;
    private String emAuth ;
    private String emStartTls ;
    private String fDelay;
    private String fException;
    FileReader fReader;
    
    public ConfigProperties()
    {
    	try {
    		fReader = new FileReader("./config.properties");
    		properties.load(fReader);
			dbDriver = properties.getProperty("driver").toString();
            dbConnString = properties.getProperty("connection.string").toString();
            dbUserName = properties.getProperty("username").toString();
            dbPassword = Crypto.performDecrypt(properties.getProperty("password").toString());
            emUsrName = properties.getProperty("email.username").toString();
            emPassword = Crypto.performDecrypt(properties.getProperty("email.password").toString());
            emPort = properties.getProperty("email.port").toString();
            emAuth = properties.getProperty("email.auth").toString();
            emStartTls = properties.getProperty("email.starttls.enable").toString();
            fException = properties.getProperty("flag.exception").toString();
            fDelay = properties.getProperty("flag.delay").toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	finally {
    		try {
    			fReader.close();
			} catch (IOException e) {
			}
		}
    }

    
	public String getfDelay() {
		return fDelay;
	}


	public String getfException() {
		return fException;
	}


	public String getDbDriver() {
		return dbDriver;
	}

	public String getDbConnString() {
		return dbConnString;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public String getEmUsrName() {
		return emUsrName;
	}

	public String getEmPassword() {
		return emPassword;
	}

	public String getEmPort() {
		return emPort;
	}

	public String getEmAuth() {
		return emAuth;
	}

	public String getEmStartTls() {
		return emStartTls;
	}	
}
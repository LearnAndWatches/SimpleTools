package edu.paulo.app.util;

import edu.paulo.app.core.crypto.Crypto;

import java.io.FileReader;
import java.util.Properties;

public class ConfigProperties {

	private Properties properties = new Properties();
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
	private FileReader fReader;
	private String openCVPath;
	private String openCVDLL;
	private String emPortTLS;
	private String emPortSSL;
	private String emHost;
	private String emSocketFactory;

	public ConfigProperties()
	{
		try {
			fReader = new FileReader("./materials/config.properties");
			properties.load(fReader);
			openCVDLL =  properties.getProperty("opencv.dll").toString();
			openCVPath = properties.getProperty("opencv.path").toString();
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
			emPortSSL = properties.getProperty("email.port.ssl").toString();
			emPortTLS = properties.getProperty("email.port.tls").toString();
			emHost = properties.getProperty("email.host").toString();
			emSocketFactory = properties.getProperty("email.smtp.socket.factory.class").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				fReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}



	public String getEmSocketFactory() {
		return emSocketFactory;
	}

	public String getEmHost() {
		return emHost;
	}

	public String getEmPortTLS() {
		return emPortTLS;
	}

	public String getEmPortSSL() {
		return emPortSSL;
	}

	public String getOpenCVDLL() {
		return openCVDLL;
	}

	public String getOpenCVPath() {
		return openCVPath;
	}


	public String getfDelay() {
		return fDelay;
	}

	public String getfException() {
		return fException;
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
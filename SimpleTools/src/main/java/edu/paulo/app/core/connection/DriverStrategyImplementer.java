package edu.paulo.app.core.connection;


import edu.paulo.app.core.connection.driver.*;

public class DriverStrategyImplementer {

	public static DriverStrategy chooseStrategy(String strategy) {
		
		switch (strategy) {
		case Constants.CHROME:
			return new Chrome();
		case Constants.FIREFOX:
			return new Firefox();
		case Constants.EDGE:
			return new Edge();
		case Constants.SAFARI:
			return new Safari();
		case Constants.IE:
			return new InternetExplorer();
		default:
			return null;
		}
	}
}

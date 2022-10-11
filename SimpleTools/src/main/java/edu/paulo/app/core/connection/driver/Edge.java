package edu.paulo.app.core.connection.driver;

import edu.paulo.app.core.connection.DriverStrategy;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

	public class Edge implements DriverStrategy {

		public WebDriver setStrategy() {
			WebDriverManager.edgedriver().setup();
			WebDriver driver = new EdgeDriver();
			return driver;
		}
		
	}
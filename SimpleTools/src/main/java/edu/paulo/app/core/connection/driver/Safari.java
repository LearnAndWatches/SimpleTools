package edu.paulo.app.core.connection.driver;

import edu.paulo.app.core.connection.DriverStrategy;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Safari implements DriverStrategy {

	public WebDriver setStrategy() {
		WebDriverManager.safaridriver().setup();
		WebDriver driver = new SafariDriver();
		
		return driver;
	}
}

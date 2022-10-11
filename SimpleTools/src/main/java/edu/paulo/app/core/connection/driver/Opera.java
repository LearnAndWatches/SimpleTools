package edu.paulo.app.core.connection.driver;

import edu.paulo.app.core.connection.DriverStrategy;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

@SuppressWarnings("deprecation")
public class Opera implements DriverStrategy {

	@Override
	public WebDriver setStrategy() {
		WebDriverManager.operadriver().setup();
		WebDriver driver = new OperaDriver();
		return driver;
	}

	
}

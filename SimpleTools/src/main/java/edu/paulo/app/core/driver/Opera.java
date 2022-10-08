package edu.paulo.app.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("deprecation")
public class Opera implements DriverStrategy{

	@Override
	public WebDriver setStrategy() {
		WebDriverManager.operadriver().setup();
		WebDriver driver = new OperaDriver();
		return driver;
	}

	
}

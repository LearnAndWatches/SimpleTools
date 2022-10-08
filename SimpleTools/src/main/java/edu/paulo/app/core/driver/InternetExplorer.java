package edu.paulo.app.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InternetExplorer implements DriverStrategy{

	@Override
	public WebDriver setStrategy() {
		WebDriverManager.iedriver().setup();
		WebDriver driver = new InternetExplorerDriver();
		
		return driver;
	}

}

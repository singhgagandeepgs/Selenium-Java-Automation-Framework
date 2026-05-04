package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.exceptions.FrameworkExceptions;

public class DriverFactory {
	
	private WebDriver driver;
	private Properties prop;
	
	public WebDriver initDriver(Properties properties) {
		String browser = properties.getProperty("browser");
		System.out.println("Browser name is: " + browser);
		
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			System.out.println(browser + AppErrors.INVALID_BROWSER_MESSAGE);
			throw new FrameworkExceptions("===INVALID BROWSER===");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(properties.getProperty("url"));
		
		return driver;
	}
	
	public Properties initProperties() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
			try {
				prop.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}

}

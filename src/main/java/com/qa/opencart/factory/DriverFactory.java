package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); // Why static???
	
	public WebDriver initDriver(Properties properties) {
		String browser = properties.getProperty("browser");
		System.out.println("Browser name is: " + browser);
		
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver());
			break;
		case "firefox":
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
			break;
		case "edge":
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;
		default:
			System.out.println(browser + AppErrors.INVALID_BROWSER_MESSAGE);
			throw new FrameworkExceptions("===INVALID BROWSER===");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(properties.getProperty("url"));
		
		return getDriver();
	}
	
	private WebDriver getDriver() {
		return tlDriver.get();
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
	
	public File getScreenshotAsFile() {
		File file = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		return file;
	}

}

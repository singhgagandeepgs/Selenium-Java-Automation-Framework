package com.qa.opencart.factory;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private OptionsManager optionsManager;
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); // Why static???
	private static final Logger logger = LogManager.getLogger(DriverFactory.class);
	
	public WebDriver initDriver(Properties properties) {
		optionsManager = new OptionsManager(properties);
		
		String browser = properties.getProperty("browser");
		//System.out.println("Browser name is: " + browser);
		logger.info("Browser name is: " + browser);
		
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;
		default:
			//System.out.println(browser + AppErrors.INVALID_BROWSER_MESSAGE);
			logger.error(browser + AppErrors.INVALID_BROWSER_MESSAGE);
			throw new FrameworkExceptions("===INVALID BROWSER===");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(properties.getProperty("url"));
		
		return getDriver();
	}
	
	public static WebDriver getDriver() { // Try to make it private later
		return tlDriver.get();
	}
	
	public Properties initProperties() {
		prop = new Properties();
		FileInputStream fis = null;
		
		// mvn clean install -Denv="qa";
		
		String envName = System.getProperty("env");
		
		
		try {
			if(envName == null) {
				fis = new FileInputStream("src/test/resources/config/config.properties");
				logger.info("Test Cases are running in the default env as no env is passed as an argument (-D) in mvn CLI statement" + envName);
			}
			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					logger.info("Test Cases are running in the: **"+ envName + "** environment");
					fis = new FileInputStream("src/test/resources/config/config_qa.properties");
					break;
				case "dev":
					logger.info("Test Cases are running in the: **"+ envName + "** environment");
					fis = new FileInputStream("src/test/resources/config/config_dev.properties");
					break;
				case "stage":
					logger.info("Test Cases are running in the: **"+ envName + "** environment");
					fis = new FileInputStream("src/test/resources/config/config_stage.properties");
					break;
				default:
					logger.error("Wrong environment is passed");
					throw new FrameworkExceptions("===INVALID ENVIRONMENT PASSED===");
					//break;
				}
			}
			
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

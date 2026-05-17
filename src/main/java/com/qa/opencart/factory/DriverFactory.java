package com.qa.opencart.factory;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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
		
		boolean remoteFlag = Boolean.parseBoolean(properties.getProperty("remote"));
		
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver();
			if(remoteFlag) {
				initRemoteDriver(browser);
			} else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
				logger.info("Running Chrome in Local Mode");
			}
			
			break;
		case "firefox":
			//driver = new FirefoxDriver();
			if(remoteFlag) {
				initRemoteDriver(browser);
			} else {
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
				logger.info("Running Firefox in Local Mode");
			}
			break;
		case "edge":
			//driver = new EdgeDriver();
			if(remoteFlag) {
				initRemoteDriver(browser);
			} else {
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
				logger.info("Running Edge in Local Mode");
			}
			break;
		case "safari":
			//driver = new SafariDriver();
			if(remoteFlag) {
				initRemoteDriver(browser);
			} else {
				tlDriver.set(new SafariDriver(optionsManager.getSafariOptions()));
				logger.info("Running Safari in Local Mode");
			}
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
	
	private void initRemoteDriver(String browser) {
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")),optionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "firefox":
			//driver = new FirefoxDriver();
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")),optionsManager.getFirefoxOptions()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "edge":
			//driver = new EdgeDriver();
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")),optionsManager.getEdgeOptions()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "safari":
			//driver = new SafariDriver();
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")),optionsManager.getSafariOptions()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			//System.out.println(browser + AppErrors.INVALID_BROWSER_MESSAGE);
			logger.error(browser + AppErrors.INVALID_BROWSER_MESSAGE);
			throw new FrameworkExceptions("===INVALID BROWSER===");
		}
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
			if(envName == null || envName.trim().isEmpty()) {
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
				case "uat":
					logger.info("Test Cases are running in the: **"+ envName + "** environment");
					fis = new FileInputStream("src/test/resources/config/config_uat.properties");
					break;
				case "prod":
					logger.info("Test Cases are running in the: **"+ envName + "** environment");
					fis = new FileInputStream("src/test/resources/config/config_prod.properties");
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

package com.qa.opencart.factory;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	private SafariOptions so;
	private static final Logger logger = LogManager.getLogger(OptionsManager.class);
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions co = new ChromeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
			logger.info("I am in Headless Mode");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
			logger.info("I am in Incognito Mode");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))){
			co.setCapability("browserName", "chrome");
			logger.info("Running Chrome in Remote Mode");
		}
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
			logger.info("I am in Headless Mode");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("--incognito");
			logger.info("I am in Incognito Mode");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))){
			fo.setCapability("browserName", "firefox");
			logger.info("Running Firefox in Remote Mode");
		}
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			eo.addArguments("--headless");
			logger.info("I am in Headless Mode");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			eo.addArguments("--incognito");
			logger.info("I am in Incognito Mode");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote"))){
			eo.setCapability("browserName", "MicrosoftEdge");
			logger.info("Running Edge in Remote Mode");
		}
		return eo;
	}
	
	public SafariOptions getSafariOptions() {
		so = new SafariOptions();
		
		/*
		 * if(Boolean.parseBoolean(prop.getProperty("headless"))) {
		 * so.addArguments("--headless"); logger.info("I am in Headless Mode"); }
		 * if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
		 * so.addArguments("--incognito"); logger.info("I am in Incognito Mode"); }
		 */
		if(Boolean.parseBoolean(prop.getProperty("remote"))){
			so.setCapability("browserName", "safari");
			logger.info("Running Safari in Remote Mode");
		}
		return so;
	}
	
}

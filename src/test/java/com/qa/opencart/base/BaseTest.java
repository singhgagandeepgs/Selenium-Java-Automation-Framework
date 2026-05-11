package com.qa.opencart.base;

import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

//@Listeners({ChainTestListener.class, TestAllureListener.class, AnnotationTransformer.class}) //Another way to configure listener is in the TestNG.xml files. Please check out there.
public class BaseTest {
	
	private DriverFactory df;
	private WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected RegisterPage registerPage;
	protected AccountsPage accountsPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(@Optional ("chrome") String browserName) {
		df = new DriverFactory();
		prop = df.initProperties();
		
		if(browserName != null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) {
			ChainTestListener.embed(df.getScreenshotAsFile(), "image/png");
		}
	}
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}
}

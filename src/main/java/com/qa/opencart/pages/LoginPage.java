package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.WebElementUtils;

public class LoginPage {
	
	// a. private | By locators
	// b. public | constructor
	// c. public | action methods
	
	private WebDriver driver;
	private WebElementUtils elementUtils;
	
	// By locators:
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotPwdLink = By.xpath("(//a[text()='Forgotten Password'])[1]");
	private final By header = By.tagName("h2");
	
	// Constructor:
	public LoginPage(WebDriver driver) {
		this.driver  = driver;
		elementUtils = new WebElementUtils(driver);
	}
	
	// Action Methods:
	public String getLoginPageTitle() {
		String title = elementUtils.waitForExactTitle(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Title of the page is: " + title);
		return title;
	}
	
	public String getLoginPageUrl() {
		String url = elementUtils.waitForUrlContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("URL of the page is: " + url);
		return url;
	} 
	
	public boolean doesForgotPasswordLinkExist() {
		boolean flag = elementUtils.isElementDisplayed(forgotPwdLink);
		return flag;
	}
	
	public boolean doesHeaderExist() {
		System.out.println("Header of the Login Page is: " + elementUtils.getElementText(header));
		boolean flag = elementUtils.isElementDisplayed(header);
		return flag;
	}
	
	public AccountsPage doLogin(String uName, String pass) throws InterruptedException {
		System.out.println("App Credentials are: " + uName + " : " + pass);
		elementUtils.waitForElementVisibility(email, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(uName);
		elementUtils.waitForElementVisibility(password, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(pass);
		elementUtils.waitForElementVisibility(loginBtn, AppConstants.DEFAULT_SHORT_WAIT).click();
		
		Thread.sleep(1000);
		
		elementUtils.waitForExactTitle(AppConstants.ACC_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
		
		//return driver.getTitle();
		return new AccountsPage(driver);
	}

}

package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.WebElementUtils;

public class RegisterPage {
	
	private WebDriver driver;
	private WebElementUtils elementUtils;
	
	private final By firstName = By.id("input-firstname");
	private final By lastName = By.id("input-lastname");
	private final By email = By.id("input-email");
	private final By telephone = By.id("input-telephone");
	private final By password = By.id("input-password");
	private final By confirmPassword = By.id("input-confirm");
	
	private final By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private final By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
	
	private final By agreeCheckbox = By.name("agree");
	private final By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	
	private final By successMsg = By.cssSelector("div#content h1");
	
	private final By logoutLink = By.linkText("Logout");
	private final By registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver  = driver;
		elementUtils = new WebElementUtils(driver);
	}
	
	public boolean doUserRegistration(String fName, String lName, String email, String tel, String password, String subscribeYesOrNo) {
		elementUtils.waitForElementVisibility(firstName, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(fName);
		elementUtils.doSendKeys(lastName, lName);
		elementUtils.doSendKeys(this.email, email);
		elementUtils.doSendKeys(telephone, tel);
		elementUtils.doSendKeys(this.password, password);
		elementUtils.doSendKeys(confirmPassword, password);
		
		if(subscribeYesOrNo.equalsIgnoreCase("yes")) {
			elementUtils.doClick(subscribeYes);
		} else {
			elementUtils.doClick(subscribeNo);
		}
		
		elementUtils.doClick(agreeCheckbox);
		elementUtils.doClick(continueButton);
		
		WebElement successMsgEle = elementUtils.waitForElementVisibility(successMsg, AppConstants.DEFAULT_SHORT_WAIT);
		String actualSuccessMsg = elementUtils.getElementText(successMsgEle);
		System.out.println("Registration Success Message is: "+ actualSuccessMsg);
		
		elementUtils.doClick(logoutLink);
		elementUtils.doClick(registerLink);
		
		if(actualSuccessMsg.equals(AppConstants.USER_REGISTRATION_SUCCESS_MSG)) {
			return true;
		}
		else {
			return false;
		}
	}

}

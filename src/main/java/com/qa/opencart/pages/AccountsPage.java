package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.WebElementUtils;

public class AccountsPage {

	private WebDriver driver;
	private WebElementUtils elementUtils;
	private static final Logger logger = LogManager.getLogger(AccountsPage.class);

	private final By headers = By.tagName("h2");
	private final By logoutLink = By.linkText("Logout");
	private final By searchIcon = By.xpath("//div[@id='search']//button");
	private final By searchBar = By.name("search");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new WebElementUtils(driver);
	}
	
	public List<String> getAccountPageHeaders() {
		List<String> headertextsList = elementUtils.getElementsTextList(headers);
		return headertextsList;
	}
	
	public boolean doesLogoutLinkExist() {
		boolean flag = elementUtils.isElementDisplayed(logoutLink);
		return flag;
	}
	
	public SearchResultsPage doSearchProduct(String searchValue) {
		//System.out.println("Searched Product is: "+ searchValue);
		logger.info("Searched Product is: "+ searchValue);
		elementUtils.waitForElementVisibility(searchBar, AppConstants.DEFAULT_SHORT_WAIT);
		elementUtils.clearFieldAndDoSendKeys(searchBar, searchValue);
		elementUtils.waitForElementVisibility(searchIcon, AppConstants.DEFAULT_SHORT_WAIT).click();
		
		return new SearchResultsPage(driver);
	}
}

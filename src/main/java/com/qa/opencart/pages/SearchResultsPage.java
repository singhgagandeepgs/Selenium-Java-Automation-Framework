package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.WebElementUtils;

public class SearchResultsPage {

	private WebDriver driver;
	private WebElementUtils elementUtils;
	private static final Logger logger = LogManager.getLogger(SearchResultsPage.class);

	private final By searchResults = By.cssSelector("div.product-thumb");
	private final By resultHeader = By.tagName("h1");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new WebElementUtils(driver);
	}
	
	public int getSearchResultsCount() {
		int count = elementUtils.waitForElementsPresence(searchResults, AppConstants.DEFAULT_SHORT_WAIT).size();
		//System.out.println("Total count of search results is: "+count);
		logger.info("Total count of search results is: "+count);
		return count;
	}
	
	public String getResultsHeaderText() {
		String header = elementUtils.getElementText(resultHeader);
		//System.out.println("Header text of the Search Result page is: "+ header);
		logger.info("Header text of the Search Result page is: "+ header);
		return header;
	}
	
	public ProductInfoPage clickToSelectProduct(String productName) {
		elementUtils.doClick(By.linkText(productName));
		//System.out.println("Clicked Product is: "+productName);
		logger.info("Clicked Product is: "+productName);
		return new ProductInfoPage(driver);
	}
}

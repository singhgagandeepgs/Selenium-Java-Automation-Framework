package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.WebElementUtils;

public class ProductInfoPage {
	
	private WebDriver driver;
	private WebElementUtils elementUtils;

	private final By header = By.tagName("h1");
	private final By productImages = By.cssSelector("a.thumbnail"); 
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtils = new WebElementUtils(driver);
	}
	
	public String getProductHeader() {
		String prodHeader = elementUtils.waitForElementVisibility(header, AppConstants.DEFAULT_SHORT_WAIT).getText();
		System.out.println("Product Header is: "+ prodHeader);
		return prodHeader;
	}
	
	public int getProductImagesCount() {
		int imgCount = elementUtils.waitForElementsVisibility(productImages, AppConstants.DEFAULT_SHORT_WAIT).size();
		System.out.println("Total product images/thumbnails on the Product Info page are: "+ imgCount);
		return imgCount;
	}

}

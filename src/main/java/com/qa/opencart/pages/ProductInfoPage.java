package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.WebElementUtils;

public class ProductInfoPage {
	
	private WebDriver driver;
	private WebElementUtils elementUtils;
	private Map<String, String> productMap;

	private final By header = By.tagName("h1");
	private final By productImages = By.cssSelector("a.thumbnail"); 
	private final By productMetadata = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]/li");
	private final By productPrice = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][2]/li");
	
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
	
	private void getProductMetadata() {
		List<WebElement> metaList = elementUtils.waitForElementsVisibility(productMetadata, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Total size of product meta data is: "+ metaList.size());
		for (WebElement e : metaList) {
			String text = e.getText();
			String[] meta = text.split(":");
			
			String key = meta[0];
			String value = meta[1].trim();
			
			productMap.put(key, value);
		}
	}
	
	private void getProductPrice() {
		List<WebElement> priceList = elementUtils.waitForElementsVisibility(productPrice, AppConstants.DEFAULT_SHORT_WAIT);
		System.out.println("Total size of product price is: "+ priceList.size());
		
		String prodPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim(); // $2000.00
		
		productMap.put("Product Price", prodPrice);
		productMap.put("Ex Tax Price", exTaxPrice);
	}
	
	public Map<String, String> getCompleteProductMetaDataIncludingPrice() {
		productMap = new HashMap<String, String>();
		getProductMetadata();
		getProductPrice();
		
		System.out.println("Product Complete Details are: " + productMap);
		return productMap;
	}

}

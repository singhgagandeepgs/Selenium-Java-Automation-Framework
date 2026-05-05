package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{

	@BeforeClass
	public void productInfoPageSetup() throws InterruptedException {
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		accountsPage = loginPage.doLogin(username, password);
	}
	
	
	  @DataProvider public Object[][] getProductsData() { Object [][] obj = new
	  Object[3][2]; obj[0][0] = "macbook"; obj[0][1] = "MacBook Air";
	  
	  obj[1][0] = "samsung"; obj[1][1] = "Samsung Galaxy Tab 10.1";
	  
	  obj[2][0] = "canon"; obj[2][1] = "Canon EOS 5D";
	  
	  return obj; }
	  
	  @DataProvider public Object[][] getDataForProductImages() { Object [][] obj =
	  new Object[3][3]; obj[0][0] = "macbook"; obj[0][1] = "MacBook Air"; obj[0][2]
	  = 4;
	  
	  obj[1][0] = "samsung"; obj[1][1] = "Samsung Galaxy Tab 10.1"; obj[1][2] = 7;
	  
	  obj[2][0] = "canon"; obj[2][1] = "Canon EOS 5D"; obj[2][2] = 3;
	  
	  return obj; }
	  
	  @Test(dataProvider = "getProductsData") public void productHeaderTest(String
	  searchKey, String productName) { searchResultsPage =
	  accountsPage.doSearchProduct(searchKey); productInfoPage =
	  searchResultsPage.clickToSelectProduct(productName); String actualHeader =
	  productInfoPage.getProductHeader(); Assert.assertEquals(actualHeader,
	  productName); }
	  
	  @Test(dataProvider = "getDataForProductImages") public void
	  productImagesCountTest(String searchKey, String productName, int
	  thumbnailscount) { searchResultsPage =
	  accountsPage.doSearchProduct(searchKey); productInfoPage =
	  searchResultsPage.clickToSelectProduct(productName); int imagesCount =
	  productInfoPage.getProductImagesCount(); Assert.assertEquals(imagesCount,
	  thumbnailscount); }
	 
	
	@Test
	public void productMetaDataTest() {
		searchResultsPage = accountsPage.doSearchProduct("macbook");
		productInfoPage = searchResultsPage.clickToSelectProduct("MacBook Air");
		Map<String, String> completeProdDetails = productInfoPage.getCompleteProductMetaDataIncludingPrice();
		
		Assert.assertEquals(completeProdDetails.get("Brand"), "Apple");
		Assert.assertEquals(completeProdDetails.get("Availability"), "Out Of Stock");
		Assert.assertEquals(completeProdDetails.get("Product Code"), "Product 17");
		Assert.assertEquals(completeProdDetails.get("Product Price"), "$1,202.00");
		Assert.assertEquals(completeProdDetails.get("Reward Points"), "700");
		Assert.assertEquals(completeProdDetails.get("Ex Tax Price"), "$1000.00");
	}
}

package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultsPageTest extends BaseTest{
	
	@BeforeClass
	public void searchProductSetup() throws InterruptedException {
		accountsPage = loginPage.doLogin("admin@gmail.com", "admin@123");
	}
	
	@Test
	public void searchProductTest() {
		searchResultsPage = accountsPage.doSearchProduct("macbook");
		productInfoPage = searchResultsPage.clickToSelectProduct("MacBook Pro");
		String actualProductHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actualProductHeader, "MacBook Pro");
	}

}

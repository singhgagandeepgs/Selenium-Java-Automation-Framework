package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultsPageTest extends BaseTest{
	
	@BeforeClass
	public void searchProductSetup() throws InterruptedException {
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		accountsPage = loginPage.doLogin(username, password);
	}
	
	@Test
	public void searchProductTest() {
		searchResultsPage = accountsPage.doSearchProduct("macbook");
		productInfoPage = searchResultsPage.clickToSelectProduct("MacBook Pro");
		String actualProductHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actualProductHeader, "MacBook Pro");
	}

}

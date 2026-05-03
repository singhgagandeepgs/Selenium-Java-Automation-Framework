package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetup() throws InterruptedException {
		accountsPage = loginPage.doLogin("admin@gmail.com", "admin@123");
	}
	
	@Test
	public void doesLogoutLinkExistTest() {
		boolean flag = accountsPage.doesLogoutLinkExist();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void searchProductTest() {
		accountsPage.doSearchProduct("iMac");
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> headersTexts = accountsPage.getAccountPageHeaders();
		Assert.assertEquals(headersTexts.size(), AppConstants.ACC_PAGE_HEADERS_EXPECTED_COUNT);
	}
}

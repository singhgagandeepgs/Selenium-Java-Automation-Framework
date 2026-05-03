package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String expectedTitle = AppConstants.LOGIN_PAGE_TITLE;
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Test
	public void loginPageUrlTest() {
		String expectedUrlPath = AppConstants.LOGIN_PAGE_URL_FRACTION;
		String actualUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actualUrl.contains(expectedUrlPath));
	}

	@Test
	public void loginTest() throws InterruptedException {
		accountsPage = loginPage.doLogin("admin@gmail.com", "admin@123");
		Assert.assertTrue(accountsPage.doesLogoutLinkExist());
	}
}

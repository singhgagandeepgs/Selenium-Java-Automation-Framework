package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.utils.StringUtils;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageTestSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	@Test
	public void userRegistrationTest() {
		boolean isRegistrationSuccess = registerPage.doUserRegistration("Gagandeep", "Singh", StringUtils.generateEmail(), "9876543210", "Test@1234", true);
		Assert.assertTrue(isRegistrationSuccess);
	}
}

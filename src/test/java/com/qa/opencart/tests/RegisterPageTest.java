package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtils;
import com.qa.opencart.utils.StringUtils;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageTestSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getUserDataFromSheet(){
		Object[][] data = ExcelUtils.getTestData("user_registration");
		return data;
	}
	
	@Test (dataProvider="getUserDataFromSheet")
	public void userRegistrationTest(String fName, String lName, String tel, String password, String subscribeYesOrNo) {
		boolean isRegistrationSuccess = registerPage.doUserRegistration(fName, lName, StringUtils.generateEmail(), tel, password, subscribeYesOrNo);
		Assert.assertTrue(isRegistrationSuccess);
	}
}

package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageTestSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getUserDataFromExcelSheet(){
		Object[][] data = ExcelUtil.getTestData("user_registration");
		return data;
	}
	
	@DataProvider
	public Object[][] getUserDataFromCSV(){
		Object[][] data = CSVUtil.csvData("UserRegistrationTestData");
		return data;
	}
	
	/*
	 * @Test (dataProvider="getUserDataFromExcelSheet") public void
	 * userRegistrationTest(String fName, String lName, String tel, String password,
	 * String subscribeYesOrNo) { boolean isRegistrationSuccess =
	 * registerPage.doUserRegistration(fName, lName, StringUtils.generateEmail(),
	 * tel, password, subscribeYesOrNo); Assert.assertTrue(isRegistrationSuccess); }
	 */
	
	@Test (dataProvider="getUserDataFromCSV")
	public void userRegistrationTest(String fName, String lName, String tel, String password, String subscribeYesOrNo) {
		boolean isRegistrationSuccess = registerPage.doUserRegistration(fName, lName, StringUtils.generateEmail(), tel, password, subscribeYesOrNo);
		Assert.assertTrue(isRegistrationSuccess);
	}
}

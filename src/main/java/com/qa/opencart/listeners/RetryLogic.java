package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryLogic implements IRetryAnalyzer {

	private int count = 0;
	private static int maxTry = 2;

	@Override
	public boolean retry(ITestResult iTestResult) {

		if (!iTestResult.isSuccess()) { // Check if test didn't succeed
			if (count < maxTry) {
				count++;
				iTestResult.setStatus(ITestResult.FAILURE); // Mark test as Failed
				return true; // Tells TestNG to rerun the test
			} else {
				iTestResult.setStatus(ITestResult.FAILURE); // If maxCount reached, test marked as Failed
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS); // Test Succeeded, so marked as Passed
		}

		return false;
	}

}

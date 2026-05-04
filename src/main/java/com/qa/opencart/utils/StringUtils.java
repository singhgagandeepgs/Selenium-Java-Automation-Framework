package com.qa.opencart.utils;

public class StringUtils {

	public static String generateEmail() {
		String email = "UiAutomation"+Math.random()+"@test.com";
		return email;
	}
}

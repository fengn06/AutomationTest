package com.ning.walmart.page;

public final class LoginPage extends Page {
	private static final String sPasswordCSSSelector = "input[type=\"password\"]";
	private static final String sSignInButtonId = "COAC0WelAccntSignInBtn";
	private static final String sUserNameCSSSelector = "input[type=\"email\"]";

	public DeliveryPage loginAccount(String userName, String password) {
		mDriver.sendTextForCSSSelector(sUserNameCSSSelector, userName);
		mDriver.sendTextForCSSSelector(sPasswordCSSSelector, password);
		mDriver.clickId(sSignInButtonId);
		return getDeliveryPage();
	}
}

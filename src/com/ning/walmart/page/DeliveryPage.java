package com.ning.walmart.page;

public final class DeliveryPage extends Page {

	private static final String continueButtonId = "COAC1ShpOptContBtn";
	private static final String sAddressCSSSelector = ".address-col";

	public DeliveryPage chooseShippingMethod() {
		mDriver.clickId(continueButtonId);
		return getDeliveryPage();
	}

	public CartPage reviewCartInfo() {
		mDriver.clickCSSSelector(".wmicon-cart");
		return getCartPage();
	}

	// Test method
	public DeliveryPage verifyAddress(String address) {
		org.junit.Assert.assertEquals("there is no shipping address", address,
				mDriver.getTextByCSSSelector(sAddressCSSSelector));
		return this;
	}

}

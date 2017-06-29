package com.ning.walmart.page;

import com.ning.walmart.drive.DriverInterface;

public final class CartPage extends Page {
	private static final String sItemIdQuery = "data-us-item-id";
	private static final String sCartItemInfo = "CartItemInfo";
	private static final String sCartPresent = "//h3[contains(.,\"Your cart\")]/span";
	private static final String sCheckoutButton = "PACCheckoutBtn";

	public LoginPage clickCheckoutButton(){
		// Click 'Checkout' button.
		mDriver.clickId(sCheckoutButton);
		System.out.println("click checkout button");
		return getLoginPage();
	}

    // Test method
	public CartPage verifyProductNumber() {
		DriverInterface.ResultList list = mDriver.getIdResultList(sCartItemInfo);
		System.out.println(mDriver.getResultsCount(list));
		org.junit.Assert.assertEquals("Total # in cart is wrong", mDriver.getTextByXPath(sCartPresent), "1 item");
		return this;
	}

	// Test method
	public CartPage verifyProductId(String item) {
		String itemIdInCart = mDriver.getAttributeById(sCartItemInfo, sItemIdQuery);
		System.out.println("item id in cart is " + itemIdInCart);
		org.junit.Assert.assertEquals("Not same product", item, itemIdInCart);
		return this;
	}
}

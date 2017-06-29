package com.ning.walmart.page;

import com.ning.walmart.config.Configuration;

public final class Header extends Page {
	private static final String xpathToCartIcon = "//i[contains(@class,'wmicon-cart')]";
	private static final String sGoButtonSelector = ".searchbar-submit";
	private static final String sSearchKeywordSelector = "input[name=\"query\"]";
	private static final String sCartButtonCSSSelector = ".wmicon-cart";

	public ProductPage searchProductById(String itemId){
		mDriver.visitURL(Configuration.getsHomePage() + "ip/" + itemId);
		return getProductPage();
	}
	
	public ProductListPage searchProductByKeyword(String keyword){
		System.out.println("start search product by keyword " + keyword);
		// Place text in search field.
		mDriver.sendTextForCSSSelector(sSearchKeywordSelector, keyword);
		// Click 'Go' button.
		mDriver.clickCSSSelector(sGoButtonSelector);
		System.out.println("finish search product by keyword");
		return getProductListPage();
	}
	
	public CartPage clickCart() {
		// Click 'Cart' button.
		mDriver.clickCSSSelector(sCartButtonCSSSelector);
		return getCartPage();
	}
	
	public void verifyProduct() {
		// TODO: verify product in the cart in shipping page
		
	}
	
}

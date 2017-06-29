package com.ning.walmart.page;

public final class ProductPage extends Page {

	private static final String sAddToCartButtonId = "WMItemAddToCartBtn";
	private static final String sAddressAttributeKey = "//div[@class=\"js-product-questions\"]";
	private static final String sAddressAttribute = "data-us-item-id";

	// Add item to card. One can pass in a optional StringBuilder in. When the item is successfully added to cart, the
	// item number will be appended to the StringBuilder.
	public CartPage addProductToCart(StringBuilder sb) {
		// Click 'Add to cart' button.
		mDriver.clickId(sAddToCartButtonId);
		String itemId = mDriver.getAttributeByXPath(sAddressAttributeKey, sAddressAttribute);
		if (sb != null) {
			sb.append(itemId);
		}
		System.out.println("click add to cart button");
		return getCartPage();
	}

}

package com.ning.walmart.page;

import com.ning.walmart.drive.DriverInterface;

public final class ProductListPage extends Page {
	private static final String sRedirectLink = "//a[substring(@href, string-length(@href) - string-length('redirect=false') +1) = 'redirect=false']/span";
	private DriverInterface.ResultList mResult = null;
	private static final String sProductListXPath = "//div[@class='mobile-result-items']/div";
	private static final String sImageCSSSelector = ".image-and-swatches";

	public int getProductCount() {
		return mDriver.getResultsCount(mResult);
	}
	
	public ProductPage selectProduct(int index){
		System.out.println("start to select an item");
		mDriver.clickItemImageForResult(mResult, sImageCSSSelector, index);
		System.out.println("Select an item");
		return getProductPage();
	}

	protected void initializeProductListPage() {
		try {
			mDriver.clickXPath(sRedirectLink); // surpass redirect
		} catch (Exception e) {
			// do nothing;)
		}

		// Verify we are indeed in a product list page.
		System.out.println("start product list parsing ");
		mResult = mDriver.getXPathResultList(sProductListXPath);
		System.out.println("product list size: "+getProductCount());
	}

}

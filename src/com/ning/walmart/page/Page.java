package com.ning.walmart.page;

import com.ning.walmart.config.Configuration;
import com.ning.walmart.drive.DriverInterface;

import java.util.HashMap;
import java.util.Map;

public abstract class Page {
	protected static DriverInterface mDriver = null;

	public Page (){
		if (mDriver == null) {
			mDriver = Configuration.getDriver();
		}
	}
	
	private enum PageType {
			HEADER, PRODUCTLIST, PRODUCT, CART, LOGIN, DELIVERY
	}
	private static Map<PageType, Page> pageMap = null;

	private void initializePageMap() {
		if (pageMap != null) {
			return;
		}
		pageMap = new HashMap<PageType, Page>();
		pageMap.put(PageType.HEADER, new Header());
		pageMap.put(PageType.PRODUCTLIST, new ProductListPage());
		pageMap.put(PageType.PRODUCT, new ProductPage());
		pageMap.put(PageType.CART, new CartPage());
		pageMap.put(PageType.LOGIN, new LoginPage());
		pageMap.put(PageType.DELIVERY, new DeliveryPage());
	}
	
	private Page getPage(PageType t) {
		initializePageMap();
		return pageMap.get(t);
	}
	protected Header getHeader() {
		return (Header)getPage(PageType.HEADER);
	}
	
	protected ProductListPage getProductListPage() {
		ProductListPage page = (ProductListPage)getPage(PageType.PRODUCTLIST);
		page.initializeProductListPage();
		return page;
	}
	
	protected ProductPage getProductPage() {
		return (ProductPage)getPage(PageType.PRODUCT);
	}
	
	protected CartPage getCartPage() {
		return (CartPage)getPage(PageType.CART);
	}

	protected LoginPage getLoginPage() {
		return (LoginPage)getPage(PageType.LOGIN);
	}
	
	protected DeliveryPage getDeliveryPage() {
		return (DeliveryPage)getPage(PageType.DELIVERY);
	}

	public void closePage(){
		mDriver.shutdown();
	}
}

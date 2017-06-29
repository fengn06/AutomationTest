package com.ning.walmart.page;

import com.ning.walmart.config.Configuration;

public final class HomePage extends Page {
	public HomePage (){
		super();
		System.out.println("Starting new homepage");
		mDriver.visitURL(Configuration.getsHomePage());
	}
	
	public Header findHeader() {
		return getHeader();
	}
}

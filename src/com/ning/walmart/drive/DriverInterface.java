package com.ning.walmart.drive;

public interface DriverInterface {
	enum DRIVERTYPE {
		CHROME, SAFARI
	}

	// Visit a web page.
	void visitURL(String url);

	// Opaque type representing the result list got from a xpath/id/css query.
	class ResultList {
	}

	// Result list manipulation.
	ResultList getXPathResultList(String exp);
	ResultList getIdResultList(String id);
	int getResultsCount(ResultList result);

	// Click action.
	void clickCSSSelector(String selector);
	void clickId(String id);
	void clickItemImageForResult(ResultList result, String imageCSSSelector, int index);
	void clickXPath(String exp);

	// Attribute and text.
	String getAttributeById(String id, String attribute);
	String getAttributeByXPath(String exp, String attribute);
	String getTextByXPath(String exp);
	String getTextByCSSSelector(String selector);

	// Send text.
	void sendTextForId(String id, String text);
	void sendTextForCSSSelector(String selector, String text);

	// Clean up.
	void shutdown();
}

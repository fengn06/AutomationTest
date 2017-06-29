package com.ning.walmart.config;

import com.ning.walmart.drive.DriverInterface;
import com.ning.walmart.drive.SeleniumDriver;

public class Configuration {
	private static String sHomePage = "http://mobile.walmart.com/";
	private static String sDriverPath = "C:\\chromedriver.exe";
	private static Class sDriverClass = SeleniumDriver.class;
	private static DriverInterface.DRIVERTYPE sDriverType = DriverInterface.DRIVERTYPE.CHROME;

	public static void setHomePage(String homePage) {
		sHomePage = homePage;
	}
	public static void setDriverType(DriverInterface.DRIVERTYPE driverType) {
		sDriverType = driverType;
	}
	public static void setDriverPath(String driverPath) {
		sDriverPath = driverPath;
	}

	public static void setDriverClass(Class cls) {
		sDriverClass = cls;
	}

	// Driver factory.
	public static DriverInterface getDriver() {
		if (sDriverClass == SeleniumDriver.class) {
			return new SeleniumDriver(sDriverType);
		} else {
			// Once we have more supported drivers, add them to here.
			return null;
		}
	}

	public static String getsHomePage() {
		return sHomePage;
	}

	public static String getsDriverPath() {
		return sDriverPath;
	}
}

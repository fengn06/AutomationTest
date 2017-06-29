package com.ning.walmart.automationtest;

import com.ning.walmart.config.Configuration;
import com.ning.walmart.drive.DriverInterface;
import com.ning.walmart.page.HomePage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMain {

    private HomePage mPage = null;
    // please fill in your user name, password, and shipping address
    private static final String sUserName = "...";
    private static final String sPassword = "...";
    private static final String sAddress = "...";

    @Before
    public void setUp() throws Exception {
        Configuration.setDriverType(DriverInterface.DRIVERTYPE.CHROME);
        mPage = new HomePage();
    }

    @Test
    public void testPurchaseTV() throws Exception {
        StringBuilder item = new StringBuilder();
        mPage.findHeader()
                .searchProductByKeyword("tv")
                .selectProduct(0)
                .addProductToCart(item)
                .clickCheckoutButton()
                .loginAccount(sUserName, sPassword)
                .chooseShippingMethod()
                .verifyAddress(sAddress)
                .reviewCartInfo()
                .verifyProductNumber()
                .verifyProductId(item.toString());
    }

    @Test
    public void testPurchaseDVD() throws Exception {
        StringBuilder item = new StringBuilder();
        mPage.findHeader()
                .searchProductByKeyword("dvd")
                .selectProduct(2)
                .addProductToCart(item)
                .clickCheckoutButton()
                .loginAccount(sUserName, sPassword)
                .chooseShippingMethod()
                .verifyAddress(sAddress);
    }


    @Test
    public void testPurchaseSocks() throws Exception {
        StringBuilder item = new StringBuilder();
        mPage.findHeader()
                .searchProductByKeyword("socks")
                .selectProduct(5)
                .addProductToCart(item)
                .clickCheckoutButton()
                .loginAccount(sUserName, sPassword)
                .chooseShippingMethod()
                .verifyAddress(sAddress);
    }

    @Test
    public void testPurchaseToys() throws Exception {
        StringBuilder item = new StringBuilder();
        mPage.findHeader()
                .searchProductByKeyword("toys")
                .selectProduct(0)
                .addProductToCart(item)
                .clickCheckoutButton()
                .loginAccount(sUserName, sPassword)
                .chooseShippingMethod()
                .verifyAddress(sAddress);
    }

    @Test
    public void testPurchaseIPhone() throws Exception {
        StringBuilder item = new StringBuilder();
        mPage.findHeader()
                .searchProductByKeyword("iPhone")
                .selectProduct(7)
                .addProductToCart(item)
                .clickCheckoutButton()
                .loginAccount(sUserName, sPassword)
                .chooseShippingMethod()
                .verifyAddress(sAddress);
    }

    @After
    public void tearDown() throws Exception {
        mPage.closePage();
    }

}

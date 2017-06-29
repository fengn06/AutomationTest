package com.ning.walmart.drive;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ning.walmart.config.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumDriver implements DriverInterface {
	private WebDriver mWebDriver;

	public SeleniumDriver(DRIVERTYPE type){
		switch(type){
			case CHROME:{
				System.setProperty("webdriver.chrome.driver", Configuration.getsDriverPath());
				// Pretend we are using an iPhone to force the website load the mobile version.
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--user-agent=Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_2 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko)Version/5.0.2 Mobile/8H7 Safari/6533.18.5");
		        mWebDriver = new ChromeDriver(options);
		        break;
				}
			case SAFARI:{
				// TODO: add SAFARI browser setting
		        break;
				}
			default: {
				mWebDriver = null;
				break;
			}
		}
	}
	
	@Override
	public void visitURL(String url){
		mWebDriver.get(url);
	}

	@Override
	public ResultList getXPathResultList(String exp) {
		By by = By.xpath(exp);
		List<WebElement> list = getResults(by);
		WebElementList result = new WebElementList();
		result.list = list;
		return result;
	}

	@Override
	public ResultList getIdResultList(String id) {
		By by = By.id(id);
		List<WebElement> list = getResults(by);
		WebElementList result = new WebElementList();
		result.list = list;
		return result;
	}
	
	@Override
	public int getResultsCount(ResultList result) {
		WebElementList l = (WebElementList)result;
		return l.list.size();
	}

	@Override
	public void clickId(String id){
		System.out.println("Clicking button");
		WebElement element = getResultById(id);
		System.out.println(element.getText() + " " + element.isEnabled() );
		element.click();
	}

	@Override
	public void clickCSSSelector(String selector) {
		getResultForCSSSelector(selector).click();
	}

	@Override
	public void clickItemImageForResult(ResultList result, String imageCSSSelector, int index) {
		WebElementList l = (WebElementList)result;
		WebElement item = l.list.get(index);
		System.out.println(item.getText());
		By by = By.cssSelector(imageCSSSelector);
		WebElement elem = item.findElement(by);
		elem.click();
		System.out.println("item has been clicked");
	}

	@Override
	public void clickXPath(String exp) {
		WebElement element =  getXPathResult(exp);
		element.click();
	}


	@Override
	public String getAttributeById(String id, String attribute) {
		return getResultById(id).getAttribute(attribute);
	}

	@Override
	public String getAttributeByXPath(String exp, String attribute) {
		return getXPathResult(exp).getAttribute(attribute);
	}

	@Override
	public String getTextByXPath(String exp) {
		return getXPathResult(exp).getText();
	}

	@Override
	public String getTextByCSSSelector(String selector) {
		WebElement parent = getResultForCSSSelector(selector);
		By by = By.cssSelector(".address-details");
		WebElement elem = parent.findElement(by);
		return elem.getText();
	}

	@Override
	public void sendTextForId(String id, String text) {
		getResultById(id).sendKeys(text);
	}

	@Override
	public void sendTextForCSSSelector(String selector, String text) {
		getResultForCSSSelector(selector).sendKeys(text);
	}

	@Override
	public void shutdown(){
		mWebDriver.close();
	}

	// Private methods.

	private void waitBy(By by) {
		waitFiveSeconds();
		new WebDriverWait(mWebDriver, 2).until(ExpectedConditions.presenceOfElementLocated(by));
		new WebDriverWait(mWebDriver, 2).until(ExpectedConditions.elementToBeClickable(by));
		mWebDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	}

	private WebElement getResult(By by) {
		waitBy(by);
		return mWebDriver.findElement(by);
	}

	private List<WebElement> getResults(By by) {
		waitBy(by);
		return mWebDriver.findElements(by);
	}

	// query and manipulate by css
	private WebElement getResultForCSSSelector(String selector) {
		By by = By.cssSelector(selector);
		return getResult(by);
	}

	private WebElement getXPathResult(String exp) {
		return getResult(By.xpath(exp));
	}

	// query and manipulate elements by xpath
	private class WebElementList extends ResultList {
		private List<WebElement> list = null;
	}

	// query and manipulate element by Id
	private WebElement getResultById(String id) {
		By by = By.id(id);
		return getResult(by);
	}

	private void waitFiveSeconds() {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

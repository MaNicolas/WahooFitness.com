package com.wahooFitness.eu.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class CheckoutPageObject extends BasePageObject {

	// Locators
	private String url = "https://eu.wahoofitness.com/checkout/";

	private By placeOrderLocator = By.xpath("//button[@class='action primary checkout amasty']");
	private By errorMessage = By.id("stripe-payments-card-errors");
	private By expressShippingLocator = By.id("s_method_amstrates_amstrates22");
	private By shippingPriceLocator = By.xpath("//span[@data-th='Shipping']");
	private By loadingIconLocator1 = By.xpath("//img[@style='position: absolute;']");
	private By loadingIconLocator = By.xpath("(//img[@title='Loading...'])");

	private By emailLocator = By.xpath("//input[@class='input-text mage-error']");
	private By firstNameLocator = By.xpath("//input[@aria-invalid='true' and @name='firstname']");
	private By lastNameLocator = By.xpath("//input[@aria-invalid='true' and @name='lastname']");
	private By adressLocator = By.xpath("//input[@aria-invalid='true' and @name='street[0]']");
	private By cityLocator = By.xpath("(//input[@name='city'])[1]");
	private By phoneLocator = By.xpath("(//input[@name='telephone'])[1]");

	private By iframeCardNumber = By.xpath("//iframe[@title='Secure card number input frame']");
	private By iframeExpiricy = By.xpath("//iframe[@title='Secure expiration date input frame']");
	private By iframeCvc = By.xpath("//iframe[@title='Secure CVC input frame']");

	private By creditCardLocator = By.xpath("//input[@name='cardnumber']");
	private By expiryDateLocator = By.xpath("//input[@name='exp-date']");
	private By cvcLocator = By.xpath("//input[@name='cvc']");

	// Constructor
	public CheckoutPageObject(WebDriver driver) {
		super(driver);
	}

	// Methods
	public String getUrl() {
		return url;
	}

	public String getActualUrl() {
		return getCurrentUrl();
	}

	public void placeOrder() {
		waitForElementToBeClickable(placeOrderLocator, 5);
		click(placeOrderLocator, 5);
	}

	public boolean checkErrorMessage() {
		try {
			waitForVisibilityOf(errorMessage, 10);
			return true;
		} catch (Exception e) {
			Reporter.log("Error message is not displayed in the checkout page.");
		}
		return false;
	}

	public void waitForPageToLoad() {
		List<WebElement> loadingList = findAll(loadingIconLocator);
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.invisibilityOfAllElements(loadingList));
	}

	public float getShippingCost(String text) {
		return convertStringToFloat(shippingPriceLocator);
	}

	public void selectExpressShipping() {
		click(expressShippingLocator, 5);
//		waitForVisibilityOf(loadingIconLocator, 5);
//		waitForElementToDisappear(loadingIconLocator, 5);
	}

	public void fillShippingAdress() {
		type("ma.nicolas@hotmail.fr", emailLocator, 5);
		type("Nicolas", firstNameLocator, 5);
		type("MA", lastNameLocator, 5);
		type("Carrer Peu de la Creu", adressLocator, 5);
		type("Barcelona", cityLocator, 5);
		type("123456", phoneLocator, 5);
		switchToIframe(iframeCardNumber);
		type("1234567891234567", creditCardLocator, 5);

//		switchToIframe(iframeExpiricy);
//		driver.findElement(expiryDateLocator).sendKeys("0122");
//		switchToIframe(iframeCvc);
//		driver.findElement(cvcLocator).sendKeys("111");
	}
}
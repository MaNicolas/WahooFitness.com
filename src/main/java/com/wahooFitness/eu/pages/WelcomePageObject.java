package com.wahooFitness.eu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class WelcomePageObject extends BasePageObject {

	// Variables
	private String pageUrl = "https://eu.wahoofitness.com/";
	private By acceptCookieButton = By.id("onetrust-accept-btn-handler");
	private By allProducts = By.xpath("(//a[text()='All Products'])[2]");

	// Constructor
	public WelcomePageObject(WebDriver driver) {
		super(driver);
	}

	// Methods
	public void openPage() {
		Reporter.log("Opening page: " + pageUrl);
		openUrl(pageUrl);
		Reporter.log("Page opened!");
	}
	
	public void acceptCookie() {
		click(acceptCookieButton, 5);
	}
	
	public AllProductPageObject openAllProductPage() {
		click(allProducts, 3);
		return new AllProductPageObject(driver);
	}
}
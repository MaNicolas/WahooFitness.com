package com.wahooFitness.eu.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MiniCartPageObject extends BasePageObject {

	// Locators
	private By sideBarLocator = By.xpath("//div[@data-block='minicart']");
	private By productItemName = By.xpath("//strong[@class='product-item-name']");
	private By backArrowLocator = By.id("btn-minicart-close");
	private By removeIconLocator = By.xpath("//a[@title='Remove item']");
	private By productsLocator = By.xpath("//li[@class='item product product-item']");

	// Constructor
	public MiniCartPageObject(WebDriver driver) {
		super(driver);
	}

	// Methods
	public String getProductNameInCart() {
		waitForVisibilityOf(sideBarLocator, 5);
		String itemInCartName = find(productItemName).getText().toLowerCase();
		return itemInCartName;
	}

	public int countItemInCart() {
		List<WebElement> productsInCart = findAll(productsLocator);
		return productsInCart.size();
	}

	public AllProductPageObject goBackToProduct() {
		click(backArrowLocator, 3);
		waitForElementToDisappear(sideBarLocator, 5);
		return new AllProductPageObject(driver);
	}
	
	public ConfirmationPopupPageObject removeItem() {
		click(removeIconLocator, 3);
		return new ConfirmationPopupPageObject(driver);
	}
}

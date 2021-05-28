package com.wahooFitness.eu.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MiniCartPageObject extends BasePageObject {

	// Locators
	private By sideBarLocator = By.xpath("//div[@data-block='minicart']");
	private By productItemName = By.xpath("//strong[@class='product-item-name']");
	private By backArrowLocator = By.id("btn-minicart-close");
	private By removeIconLocator = By.xpath("//a[@title='Remove item']");
	private By removedItemNameLocator = By
			.xpath("./ancestor::div[@class='product-item-details']/child::strong[@class='product-item-name']");
	private By viewAndEditCartLocator = By.xpath("//a[@class='action viewcart']");

	private String removedItemName;

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

	public AllProductPageObject goBackToProduct() {
		click(backArrowLocator, 3);
		waitForElementToDisappear(sideBarLocator, 5);
		return new AllProductPageObject(driver);
	}

	public ConfirmationPopupPageObject removeItem() {
		// Get list of all remove buttons
		List<WebElement> removeButtons = findAll(removeIconLocator);

		// Pick one at random
		Random random = new Random();
		int randomNumber = random.nextInt(removeButtons.size());

		// Get product's name
		removedItemName = removeButtons.get(randomNumber).findElement(removedItemNameLocator).getText().toLowerCase();
		
		// Remove product
		removeButtons.get(randomNumber).click();
		return new ConfirmationPopupPageObject(driver);
	}

	public String getItemName() {
		return removedItemName;
	}

	public boolean checkProductName() {
		waitForNumberOfElementToBe(productItemName, 1);
		// Get list of all products element
		List<WebElement> productsInCart = findAll(productItemName);

		// Iterate over each element in the list
		for (WebElement product : productsInCart) {
			// Get product's name
			String name = product.getText().toLowerCase();
			if (name.matches(removedItemName)) {
				System.out.println("An error has occured. Item '" + removedItemName + "' is still present and should not!");
				return false;
			}
		}
		System.out.println("Item '" + removedItemName + "' has been removed.");
		return true;
	}

	public CartPageObject goToCart() {
		click(viewAndEditCartLocator, 3);
		return new CartPageObject(driver);
	}
}

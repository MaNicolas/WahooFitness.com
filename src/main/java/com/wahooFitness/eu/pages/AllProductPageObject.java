package com.wahooFitness.eu.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class AllProductPageObject extends BasePageObject {

	// Variables
	private By availableProducts = By.xpath("//button[@title='Add to Cart']");
	private By colorDropDown = By.id("attribute92");
	private By AddToCartButton = By.id("product-addtocart-button");
	private By ItemNameLocator = By.xpath("./ancestor::div[@class='text']/child::p[@class='product-name']");
	
	private int randomNumber;
	private int usedProductIndex;

	String itemName;

	// Constructor
	public AllProductPageObject(WebDriver driver) {
		super(driver);
	}

	// Methods
	public MiniCartPageObject addRandomProduct() {		
		// Get all available items from the list
		List<WebElement> productList = findAll(availableProducts);

		// Select any item from the list
		Random random = new Random();
		randomNumber = 0;
		do {
			randomNumber = random.nextInt(productList.size());
		} while (randomNumber == usedProductIndex);
		usedProductIndex = randomNumber;

		// Get item name for future assertion
		itemName = productList.get(randomNumber).findElement(ItemNameLocator).getText().toLowerCase();

		// Hover over element
		hoverOverElement(productList.get(randomNumber));
		
		// Click "Add to cart" button
		productList.get(randomNumber).click();

		// Check if color is required
		try {
			waitForVisibilityOf(colorDropDown, 3);
			Select colorDropdown = new Select(find(colorDropDown));
			colorDropdown.selectByIndex(1);
			click(AddToCartButton, 3);
		} catch (Exception e) {
			Reporter.log("User does not need to select a color.");
		}
		return new MiniCartPageObject(driver);
	}

	public String getItemName() {
		return itemName;
	}
}

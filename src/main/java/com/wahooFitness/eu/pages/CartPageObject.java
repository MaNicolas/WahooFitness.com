package com.wahooFitness.eu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPageObject extends BasePageObject{

	//Locators
	private String url = "https://eu.wahoofitness.com/checkout/cart/";
	private By quantityLocator = By.xpath("//input[@title='Quantity']");
	private By updateCartLocator = By.xpath("//button[@name='update_cart_action']");
	private By unitPriceLocator = By.xpath("(//span[@class='cart-price'])[1]");
	private By subTotalPriceLocator = By.xpath("(//span[@class='cart-price'])[2]");
	private By orderTotalLocator = By.xpath("(//td[@class='amount'])[3]");
	private By checkOutLocator = By.xpath("//button[@title='Proceed to Checkout']");
	
	//Constructor
	public CartPageObject(WebDriver driver) {
		super(driver);
	}
	
	//Methods
	public String getUrl() {
		return url;
	}
	
	public String getActualUrl() {
		return getCurrentUrl();
	}
	
	public void changeQuantity() {
		String quantityText = find(quantityLocator).getAttribute("value");
		clear(quantityLocator);		
		quantityText = incrementString(quantityText);		
		type(quantityText, quantityLocator, 3);
		click(updateCartLocator, 3);
	}
	
	private String incrementString(String s) {
		int quantity = Integer.valueOf(s);
		quantity += 1;
		return s = String.valueOf(quantity);
	}
	
	public float getUnitPrice() {
		return convertStringToFloat(unitPriceLocator);
	}
	
	public float getSubtotalPrice() {
		return convertStringToFloat(subTotalPriceLocator);
	}
	
	public float getOrderTotalPrice() {
		return convertStringToFloat(orderTotalLocator);
	}	
	
	public CheckoutPageObject proceedToCheckOut() {
		waitForElementToBeClickable(checkOutLocator, 5);
		click(checkOutLocator, 5);
		return new CheckoutPageObject(driver);
	}
}
package com.wahooFitness.eu.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPopupPageObject extends BasePageObject {

	// Locators
	private By okButton = By.xpath("//button[@class='action-primary action-accept']");
	
	// Constructor
	public ConfirmationPopupPageObject(WebDriver driver) {
		super(driver);
	}

	// Methods
	public MiniCartPageObject confirm() {
		click(okButton, 3);
		return new MiniCartPageObject(driver);
	}
}

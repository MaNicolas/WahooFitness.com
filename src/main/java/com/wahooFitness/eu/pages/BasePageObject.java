package com.wahooFitness.eu.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {

	// Variables
	protected WebDriver driver;

	// Constructor
	public BasePageObject(WebDriver driver) {
		this.driver = driver;
	}

	// Methods
	/** Open page with given URL **/
	protected void openUrl(String url) {
		driver.get(url);
	}
	
	/** Get current URL **/
	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	/** Clear element locator **/
	protected void clear(By locator) {
		find(locator).clear();
	}

	/** Find element using given locator **/
	protected WebElement find(By locator) {
		waitForVisibilityOf(locator, 3);
		return driver.findElement(locator);
	}

	/** Find all element using given Locator **/
	protected List<WebElement> findAll(By Locator) {
		return driver.findElements(Locator);
	}

	/** Click on WebElement **/
	protected void click(By locator, int seconds) {
		waitForVisibilityOf(locator, seconds);
		find(locator).click();
	}

	/** Type some text in WebElement **/
	protected void type(String text, By locator, int seconds) {
		waitForVisibilityOf(locator, seconds);
		find(locator).sendKeys(text);
	}

	/** Removes special characters from a string **/
	protected String removeSpecialCharacters(String s) {
		String newString = s.replaceAll(",", ".");
		newString = newString.replaceAll("[^0-9.]", "");
		return newString;
	}
	
	/** Perform mouse hover over element **/
	protected void hoverOverElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	/** Perform scroll to the bottom */
	public void scrollToElement(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
	}

	/** Wait for a WebElement to be visible **/
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}

	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}

	/** Wait for a WebElement to be clickable **/
	protected void waitForElementToBeClickable(By locator, Integer timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	protected void waitForElementToDisappear(By locator, Integer timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	protected void waitForNumberOfElementToBe(By locator, Integer number) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.numberOfElementsToBe(locator, number));
	}
}




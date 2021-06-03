package com.wahooFitness.eu.automationTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.wahooFitness.eu.base.TestUtilities;
import com.wahooFitness.eu.pages.AllProductPageObject;
import com.wahooFitness.eu.pages.CartPageObject;
import com.wahooFitness.eu.pages.CheckoutPageObject;
import com.wahooFitness.eu.pages.ConfirmationPopupPageObject;
import com.wahooFitness.eu.pages.MiniCartPageObject;
import com.wahooFitness.eu.pages.WelcomePageObject;

public class AutomationTest extends TestUtilities {

	@Test(invocationCount = 5)
	public void method() {

		// Go to https://eu.wahoofitness.com/
		WelcomePageObject welcomePage = new WelcomePageObject(driver);
		welcomePage.openPage();
		welcomePage.acceptCookie();

		// Select random product and add it to the cart
		AllProductPageObject allProductPage = welcomePage.openAllProductPage();
		MiniCartPageObject miniCartPage = allProductPage.addRandomProduct();

		// Verify that side-bar cart appears with added product
		String expectedName = allProductPage.getItemName();
		String actualName = miniCartPage.getProductNameInCart();
		Assert.assertEquals(actualName, expectedName);

		// Go back to product category and select another random product and add it to
		// the cart, too.
		miniCartPage.goBackToProduct();
		allProductPage.addRandomProduct();

		// Click the removal button under one of the items, then confirm with
		// the following pop-up.
		ConfirmationPopupPageObject confirmationPopupPage = miniCartPage.removeItem();
		confirmationPopupPage.confirm();

		// The item should be successfully removed from the cart.
		Assert.assertTrue(miniCartPage.checkProductName());

		// At the bottom of the cart side-bar, click on the edit cart link
		CartPageObject cartPage = miniCartPage.goToCart();

		// Should be taken to cart page.
		String expectedUrl = cartPage.getUrl();
		String actualUrl = cartPage.getActualUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

		// Change the quantity of the item in the cart and click the update cart button.
		cartPage.changeQuantity();

		// Prices should update to reflect the change.
		float expectedPrice = cartPage.getUnitPrice() * 2;
		float actualPrice = cartPage.getSubtotalPrice();
		float orderTotalPrice = cartPage.getOrderTotalPrice();
		Assert.assertEquals(actualPrice, expectedPrice);
		Assert.assertEquals(actualPrice, orderTotalPrice);

		// Click the blue "Proceed to Checkout" button. Should be taken to the checkout
		// page.
		CheckoutPageObject checkoutPage = cartPage.proceedToCheckOut();
		expectedUrl = checkoutPage.getUrl();
		actualUrl = checkoutPage.getActualUrl();
		Assert.assertEquals(actualUrl, expectedUrl);

		// Click the blue "Place Order" button without filling in any info. Error
		// messages should appear.
		checkoutPage.waitForPageToLoad();
		checkoutPage.placeOrder();
		Assert.assertTrue(checkoutPage.checkErrorMessage());

		// Switch the shipping method to "Express" shipping.
		float economyShippingCost = checkoutPage.getShippingCost("Economy");
		System.out.println("economyShippingCost = " + economyShippingCost);
		Reporter.log("economyShippingCost = " + economyShippingCost);

		checkoutPage.selectExpressShipping();
		checkoutPage.waitForPageToLoad();
		
		sleep(3000);

		float expressShippingCost = checkoutPage.getShippingCost("Express");
		System.out.println("expressShippingCost = " + expressShippingCost);
		Reporter.log("expressShippingCost = " + expressShippingCost);

		// Shipping method price should update.
		Assert.assertNotEquals(economyShippingCost, expressShippingCost);

		// Enter any email, name, address, phone, credit card
		checkoutPage.fillShippingAdress();

		// Click the blue place order button. Expected result: payment is declined.

	}
}
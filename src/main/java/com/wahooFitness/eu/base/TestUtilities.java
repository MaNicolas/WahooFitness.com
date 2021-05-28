package com.wahooFitness.eu.base;

import java.text.DecimalFormat;

public class TestUtilities extends BaseTest {

	// Variable
	private DecimalFormat decimalFormat = new DecimalFormat("#.##");

	// Methods
	/** Allow user to stop the driver for m milliseconds. **/
	protected void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/** Clean number so it has only 2 decimals **/
	public float roundDecimalNumber(float number) {
		String newString = decimalFormat.format(number);
		newString = newString.replaceAll(",", ".");
		return Float.valueOf(newString);
	}
}

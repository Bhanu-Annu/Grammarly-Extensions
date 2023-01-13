package com.webapptestproject.testcases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.webapptestproject.base.BaseClass;
import com.webapptestproject.pageobject.ChromeWebStorePage;
import com.webapptestproject.pageobject.ExtensionPage;
import com.webapptestproject.pageobject.GoogleTranslatePage;
import com.webapptestproject.utility.Log;

public class PositiveTestCase extends BaseClass{
	
	private ChromeWebStorePage chromeWebStorePage;
	private GoogleTranslatePage googleTranslatePage;
	private ExtensionPage extensionPage;
	
	@Parameters("browser")
	@BeforeMethod()
	public void setup(String browser) {
		loadBrowser(browser);
	}
	
	@AfterMethod()
	public void tearDown() {
		getDriver().quit();
	}
	
	@Parameters("extensionName")
	@Test
	public void checkForErrorReplacement(String extensionName) throws InterruptedException, AWTException {
		Log.testCasestarts("checkForErrorReplacement");
	
		chromeWebStorePage = new ChromeWebStorePage();
		chromeWebStorePage.searchExtension(extensionName);
		extensionPage = chromeWebStorePage.openExtensionPage(extensionName);
		extensionPage.installExtension();
		Log.info("Installed extension: " +extensionName);
		googleTranslatePage = extensionPage.openGoogleTranslatePage();
		Log.info("adding text input");
		String inputText = "googgl!";
		googleTranslatePage.getTextArea().sendKeys(inputText);
		Thread.sleep(3000);
		String errorCount = googleTranslatePage.getErrorIconValue();
		String actualError = "1";
		Assert.assertEquals(actualError, errorCount, "Errors found:" + errorCount);
		Log.info("Fixing Error ");
		googleTranslatePage.replaceMistake();
		Log.info("checking if the error exists");
		boolean result = googleTranslatePage.getTextArea().getText().contains(inputText);
		Assert.assertFalse(result);
		Log.testCaseEnds("checkForErrorReplacement");
	}
	
	
}

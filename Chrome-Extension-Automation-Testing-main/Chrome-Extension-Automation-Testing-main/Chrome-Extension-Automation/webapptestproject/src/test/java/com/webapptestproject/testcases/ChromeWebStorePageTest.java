package com.webapptestproject.testcases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.webapptestproject.base.BaseClass;
import com.webapptestproject.pageobject.ChromeWebStorePage;
import com.webapptestproject.utility.Log;

public class ChromeWebStorePageTest extends BaseClass{
	
	private ChromeWebStorePage chromeWebStorePage;
	
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
	public void validateIfExtensionExist(String extensionName) throws InterruptedException, AWTException {
		// install extension
		chromeWebStorePage = new ChromeWebStorePage();
		chromeWebStorePage.searchExtension(extensionName);
		boolean result = chromeWebStorePage.validateIfExtensionExist(extensionName);
		Assert.assertTrue(result, " Extension Exists !");
		Log.testCaseEnds("Extension found: " +  extensionName );
	}
}

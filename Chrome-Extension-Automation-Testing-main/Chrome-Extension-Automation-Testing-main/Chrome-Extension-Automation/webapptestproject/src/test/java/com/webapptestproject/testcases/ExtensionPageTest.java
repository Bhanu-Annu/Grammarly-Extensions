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
import com.webapptestproject.utility.Log;

// to test if install and enabled
public class ExtensionPageTest extends BaseClass{

	private ChromeWebStorePage chromeWebStorePage;
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
	public void validateIfExtensionInstalled(String extensionName) throws InterruptedException, AWTException {
		// install extension
		chromeWebStorePage = new ChromeWebStorePage();
		chromeWebStorePage.searchExtension(extensionName);
		extensionPage = chromeWebStorePage.openExtensionPage(extensionName);
		boolean result = extensionPage.checkIfInstalled();
		Assert.assertTrue(result, " Installed !");
		System.out.println("Ended");
	}
		
}

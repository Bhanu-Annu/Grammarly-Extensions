package com.webapptestproject.pageobject;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.webapptestproject.base.BaseClass;

public class ChromeWebStorePage extends BaseClass{
	// https://chrome.google.com/webstore/category/extensions
	
	@FindBy(id="searchbox-input")
	private WebElement searchBoxField;
	
	@FindBy(xpath="/html/body/div[3]/div[4]/main/div/div[1]/div/div[1]/div[2]/div/div[1]/a")
	private WebElement link;
	
	@FindBy(xpath="//*[@class='a-d-na a-d webstore-test-wall-tile a-d-zc Xd dd']")
	private List<WebElement> addressList;
	
	
	public WebElement getLink() {
		return link;
	}

	public ChromeWebStorePage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	public String getPageTitle() {
		return getDriver().getTitle();
	}
	
	public void searchExtension(String extensionName) throws InterruptedException {
		getSearchBoxField().sendKeys(extensionName);
	//	Thread.sleep(2000);
		getSearchBoxField().sendKeys(Keys.ENTER);
	}
	
	// only check if extension exist in chrome web store
	public boolean validateIfExtensionExist(String extensionName) {
		boolean ifExist = false;
		if(getAddressList().isEmpty()) {
			return false;
		}
		for(WebElement e : getAddressList()) {
			if(e.getText().startsWith(extensionName)) {
				return true;
			}
		}
		getLink().click();
		
		return ifExist;
	}
	
	public ExtensionPage openExtensionPage(String extensionName) {
		// select extension from List
		for(WebElement e : getAddressList()) {
			if(e.getAttribute("textContent").contains(extensionName)) {
				e.click();
			}
		}
		return new ExtensionPage();
	}

	public List<WebElement> getAddressList() {
		return addressList;
	}

	public WebElement getSearchBoxField() {
		return searchBoxField;
	}

}

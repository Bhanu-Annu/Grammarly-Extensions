package com.webapptestproject.pageobject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webapptestproject.base.BaseClass;
import com.webapptestproject.utility.Log;

public class ExtensionPage extends BaseClass{
	
	// https://chrome.google.com/webstore/category/extensions
	
	@FindBy(css="div[class='g-c-R  webstore-test-button-label']")
	private WebElement actionBtn;
	
	
	public ExtensionPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	// to add / remove extension
	public void installExtension() throws InterruptedException, AWTException {
		Thread.sleep(2000);
		getActionBtn().click();
		// perform js pop actions
		Thread.sleep(2000);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_LEFT);
	    robot.keyRelease(KeyEvent.VK_LEFT);  
	    Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    Thread.sleep(35000);                   // extra weight for installation
	}
	
	public boolean checkIfInstalled() throws InterruptedException, AWTException {
		Log.info("installing");
		installExtension();
		switchToChromeWebStore();
		String actionBtnText = getActionBtn().getText();
		boolean verifyText = false;
		
			// if not installed
			if (actionBtnText.contains("Add To Chrome")) {
				getActionBtn().click();
				return false;
			}
			// if installed
			if (actionBtnText.contains("Remove from Chrome")) {
				return true;
			}
			// but not available for specified browser
			if(actionBtnText.contains("Available on Chrome")) {
				return false;
			}
			System.out.println(verifyText + " " + getActionBtn().getText());
		return verifyText;
	}

	public void switchToChromeWebStore() {
		Set<String> windows = BaseClass.getDriver().getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentWindowId = it.next();
		BaseClass.getDriver().switchTo().window(parentWindowId);
	}
	
	public GoogleTranslatePage openGoogleTranslatePage() throws InterruptedException {
		BaseClass.getDriver().switchTo().newWindow(WindowType.WINDOW);                
		BaseClass.getDriver().get("https://translate.google.ca/?sl=en&tl=es&op=translate");
		return new GoogleTranslatePage();
	}
	
	public WebElement getActionBtn() {
		return actionBtn;
	}
	
	// get extension id from url 
	public String getExtensionId() {
		String getId = BaseClass.getDriver().getCurrentUrl();
	    int trimIndex = getId.trim().lastIndexOf("/");
	    String id = getId.substring(trimIndex);
	    return id;
	}
	
	// open chrome-extensions page list  | filter by id 
	public boolean checkIfEnabled(String id) {
		
		BaseClass.getDriver().switchTo().newWindow(WindowType.WINDOW);
	    BaseClass.getDriver().get("chrome://extensions/?id=" + id);       // id make api call through URL 
		String script = "return document.querySelector('body > extensions-manager').shadowRoot.querySelector(\"#items-list\").shadowRoot.querySelector(\"#content-wrapper\").querySelector(\"div.items-container\").querySelector(\"extensions-item\").shadowRoot.querySelector(\"#card > #button-strip\").querySelector(\"#enableToggle\")";
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver; 
		WebElement toogleBtn = (WebElement) jsExecutor.executeScript(script);
		String ifEnabled = toogleBtn.getAttribute("aria-pressed");        // boolean attribute for <cr-toggle> tag
		
		return Boolean.parseBoolean(ifEnabled);
	}
}

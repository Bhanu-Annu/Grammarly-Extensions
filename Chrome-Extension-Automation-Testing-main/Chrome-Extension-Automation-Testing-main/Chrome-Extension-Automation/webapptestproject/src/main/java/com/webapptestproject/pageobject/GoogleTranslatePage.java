package com.webapptestproject.pageobject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webapptestproject.base.BaseClass;

public class GoogleTranslatePage extends BaseClass {
	// https://translate.google.ca/
	
	@FindBy(xpath="//textarea[@aria-label='Source text']")
	private WebElement textArea;
	
	@FindBy(xpath="return document.querySelector(\"html > grammarly-mirror\")")
	private WebElement grammarlyShadomDOMTag;
	
	public JavascriptExecutor jsExecutor;
	
	public GoogleTranslatePage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	public void inputToTextArea(String text) {
		getTextArea().sendKeys(text);
	}
	
	// get value for number of detected errors
	public String getErrorIconValue() {
		jsExecutor = (JavascriptExecutor) BaseClass.getDriver(); 
		String jsError = " return document.querySelector('#yDmH0d > c-wiz > div > div.WFnNle > c-wiz > div.OlSOob > c-wiz > div.ccvoYb.EjH7wc > div.AxqVh > div.OPPzxe > c-wiz.rm1UF.UnxENd.dHeVVb > span > span > div > grammarly-extension:nth-child(2)').shadowRoot.querySelector('div > div > div > div > div > div:nth-child(2) > div > div > div > div > div._1iP8W > div').textContent";
		String errorIconValue = (String) jsExecutor.executeScript(jsError);
		return errorIconValue;
	}
	
	// return values shown as replacements
	public String getReplacementCard() {
		jsExecutor = (JavascriptExecutor) BaseClass.getDriver(); 
		String jsScript = "return document.querySelector('html > grammarly-mirror').shadowRoot.querySelector('div:nth-child(3) > div._1rJ5M > div > div:nth-child(1) > div > div > div.he7r0 > span').textContent";
		String spanText = (String) jsExecutor.executeScript(jsScript);
		return spanText;
	}
	
	// replace the mistake with first match
	public void replaceMistake() throws AWTException, InterruptedException {
		hoverOverText();
		Thread.sleep(2000);
		jsExecutor = (JavascriptExecutor) BaseClass.getDriver(); 
		String replaceItemScript = "return document.querySelector(\"html > grammarly-mirror\").shadowRoot.querySelector(\"div._1rJ5M > div._1rJ5M > div > div:nth-child(1)\")";
		WebElement replacementItem = (WebElement) jsExecutor.executeScript(replaceItemScript);
		replacementItem.click();
	}
	
	public WebElement getTextArea() {
		return textArea;
	}
	
	public void hoverOverText() throws AWTException, InterruptedException {
		Thread.sleep(2000);
		Point topLeft = textArea.getLocation();
		Actions actions = new Actions(BaseClass.getDriver());
		actions.moveToElement(getTextArea()).build().perform();
		Robot robot = new Robot();
		robot.mouseMove(topLeft.getX() + 15, topLeft.getY() + 135);
		Thread.sleep(3000);
		actions.click().perform();
		Thread.sleep(3000);
		actions.click().build().perform();
		actions.moveByOffset(10, 0).perform();
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_LEFT);
	    robot.keyRelease(KeyEvent.VK_LEFT);
	    Thread.sleep(3000);
	    actions.sendKeys(Keys.ARROW_LEFT);
	    Thread.sleep(3000);
		robot.mouseMove(topLeft.getX() + 20, topLeft.getY() + 135);
	}

	public WebElement getGrammarlyShadomDOMTag() {
		return grammarlyShadomDOMTag;
	}

	public JavascriptExecutor getJsExecutor() {
		return jsExecutor;
	}
	
}

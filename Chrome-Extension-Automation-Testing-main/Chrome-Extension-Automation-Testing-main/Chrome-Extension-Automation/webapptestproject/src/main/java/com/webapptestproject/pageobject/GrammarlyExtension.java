package com.webapptestproject.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.webapptestproject.base.BaseClass;

public class GrammarlyExtension {
	
	public JavascriptExecutor jsExecutor;
	
	// get value for number of dettected errors
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
	public void replaceMistake() {
		jsExecutor = (JavascriptExecutor) BaseClass.getDriver(); 
		String replaceItemScript = "return document.querySelector(\"html > grammarly-mirror\").shadowRoot.querySelector(\"div._1rJ5M > div._1rJ5M > div > div:nth-child(1)\")";
		WebElement replacementItem = (WebElement) jsExecutor.executeScript(replaceItemScript);
		replacementItem.click();
	}
	
	public boolean enabled() {
		jsExecutor = (JavascriptExecutor) BaseClass.getDriver(); 
		String shadowDOMTag = "return document.querySelector(\"html > grammarly-mirror\")";
		if(shadowDOMTag.equals("null")) {
			return false;
		}
		return true;
	}
	
}

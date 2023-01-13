package com.webapptestproject.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.WebDriverListener;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.webapptestproject.utility.ExtentManager;

// initialize webdriver | run before and after tests | read properties
public class BaseClass {
	
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	public static Properties prop;
	
	// load prop file | initiate extent reports
	@BeforeSuite
	public void loadConfig() throws IOException {
		ExtentManager.setExtent();
		DOMConfigurator.configure("Configuration/log4j.xml");
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") +"\\Configuration\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public void afterSuite() {
		ExtentManager.endReport();
	}
	
	// run browser
	@Parameters("browser")
	public static void loadBrowser(String browser) {
		if( browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		} else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else {
			System.out.println("Browser Name Invalid !");
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().get(prop.getProperty("url"));
	}
	
	public static RemoteWebDriver getDriver() {
		return driver.get();
	}

	public static RemoteWebDriver getThreadDriver() {
		return driver.get();
	}
	
	
}

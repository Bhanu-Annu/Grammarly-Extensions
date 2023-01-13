package com.webapptestproject.utility;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public static void setExtent() throws IOException {
		String path = System.getProperty("user.dir")+"/test-output/ExtentReport/report.html";
		sparkReporter= new ExtentSparkReporter(path);
		sparkReporter.config().setReportName("Extension Test Results");
		sparkReporter.loadXMLConfig(System.getProperty("user.dir")+"/Configuration/extent-config.xml");
		
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);	
		
		extent.setSystemInfo("QA Tester", "Bhanu");
		extent.setSystemInfo("Project", "Chrome Extenstion Automation");
	}
	public static void endReport() {
		extent.flush();
	}
}

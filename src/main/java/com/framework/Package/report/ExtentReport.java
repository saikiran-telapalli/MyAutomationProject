package com.framework.Package.report;

import java.io.File;

import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport {

	public static ExtentReports extent;
	public static ExtentTest extentTest;
	
	@BeforeSuite
	public void ExtentReporting(){
		String filePath=System.getProperty("user.dir")+"/Reports/MyOwnReports.html";
		File configFile=new File(System.getProperty("user.dir")+"/src/main/resources/configFiles/extent-config.xml");
		boolean replaceExisting=true;
		extent=new ExtentReports(filePath, replaceExisting);
		/*extent=new ExtentReports(filePath);*/
		extent.loadConfig(configFile);
		
	}
	
}

package com.framework.Package.core;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.framework.Package.helper.LoggerHelper;
import com.framework.Package.reader.ExcelClass;
import com.framework.Package.reader.PropertyFileReader;
import com.framework.Package.report.ExtentReport;
import com.framework.Package.utilities.ZIPFileUtility;
//import com.framework.Package.reader.PropertyFileReader;

public class BaseClass extends ExtentReport {

	public static WebDriver driver;
	public static ExcelClass data;
	private Logger log=LoggerHelper.getLogger(BaseClass.class);
	ZIPFileUtility zipFileUtility;

	@BeforeClass
	@Parameters({"browserType","environment"})
	public  void openBrowser(String browserType,String environment) throws Exception{
		
		data=new ExcelClass(System.getProperty("user.dir")+"/src/main/resources/excelFiles/login.xlsx");

		if(browserType.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
			driver=new ChromeDriver();			
		}else if(browserType.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/Drivers/geckodriver.exe");
			driver=new FirefoxDriver();
		}else if(browserType.equalsIgnoreCase("ie")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/Drivers/IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}else{
			browserType="Chrome";
			System.out.println("As invalid Browser Name was provided. Hence Opening Chrome Browser");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
			driver=new ChromeDriver();
		}		
		
		log.info("Opening "+browserType+" Browser");
		
		extent.addSystemInfo("BrowserName", browserType);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String url=getEnvironmentURL(environment);
		System.out.println("URL="+url);
		extent.addSystemInfo("Environment", environment);
		
		driver.get(url);
		log.info("Opening Url "+url);


	}
	
	public static String getEnvironmentURL(String key) throws Exception{
		
		PropertyFileReader reader=new PropertyFileReader(System.getProperty("user.dir")+"/src/main/resources/configFiles/Environments.properties");
		return reader.getPropData(key);
		
	}
	
	/*@AfterMethod
	public void forReportsEnd(){
		extent.endTest(extentTest);
		extent.flush();
	}*/
	
	@AfterClass
	public void end(){
		
		extent.endTest(extentTest);
		extent.flush();
		//extent.close();
		
	}
	
	@AfterSuite
	public void tearDown(){
		zipFileUtility=new ZIPFileUtility();
		File dir=new File(System.getProperty("user.dir")+"/ScreenShots");
		String zipDirName="ScreenShots.zip";
		zipFileUtility.zipDirectory(dir, zipDirName);
		log.info("Zipping "+zipDirName+" completed successfully");
	}

}

package com.framework.Package.driver;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.framework.Package.core.BaseClass;
import com.framework.Package.helper.LoggerHelper;
import com.relevantcodes.extentreports.LogStatus;

public class CommonMethods extends BaseClass {
	
	private static Logger log=LoggerHelper.getLogger(CommonMethods.class);
	public static String failedLoggerMessage=" Failed. Element is not visible or disabled on the webpage. ";
	
	public static By popUpNotification=By.xpath(".//*[@id='toast-container']/div/div");
	//static String stepDescription="Parameterize this variable";
	
	public static String captureScreenshot() throws Exception{
		return extentTest.addScreenCapture(takeScreenShot(driver));
	}
	
	public static String takeScreenShot(WebDriver driver) throws Exception{
		
		Date date=new Date();
		Timestamp time=new Timestamp(date.getTime());
		
		String timestamp=time.toString();
		timestamp=timestamp.replaceAll(" ", "T");
		timestamp=timestamp.replaceAll("-", "_");
		timestamp=timestamp.replaceAll(":", "_");
		timestamp=timestamp.substring(0, 19);
		
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//String path=System.getProperty("user.dir")+"/ScreenShots/"+timestamp+".jpg";
		String path="./ScreenShots/"+timestamp+".jpg"; //This is not working
		File destFile=new File(path);
		FileUtils.copyFile(srcFile, destFile);
		
		return path;
	}

	public static WebElement findElement(By element){
		
		 return driver.findElement(element);
	}
	
	public static List<WebElement> findElements(By element){
		
		 return driver.findElements(element);
	}
	
	public static void sendKeys(By element,String sendKeysValue,String stepDescription) throws Exception{
		
		if(isElementPresent(element)){
			findElement(element).sendKeys(sendKeysValue);
			log.info("Entering value...."+sendKeysValue+" into the textbox field...."+element);
			extentTest.log(LogStatus.PASS, stepDescription+" Successfully",captureScreenshot());			
		}
		else{
			extentTest.log(LogStatus.FAIL, stepDescription+failedLoggerMessage+element,captureScreenshot());
			throw new RuntimeException(stepDescription+failedLoggerMessage+element);
		}
	}
	
	public static void clear(By element,String stepDescription) throws Exception{
		
		if(isElementPresent(element)){
			findElement(element).clear();
			log.info("Clearing the textbox field....."+element);
			extentTest.log(LogStatus.PASS, stepDescription + " Successfully",captureScreenshot());			
		}
		else{
			extentTest.log(LogStatus.FAIL, stepDescription+failedLoggerMessage+element,captureScreenshot());
			throw new RuntimeException(stepDescription+failedLoggerMessage+element);
		}
	}
	
	public static void click(By element,String stepDescription) throws Exception{
		
		if(isElementPresent(element)){
			findElement(element).click();
			log.info("Clicking on element ..... " +element);
			extentTest.log(LogStatus.PASS, stepDescription + " Successfully",captureScreenshot());			
		}
		else{
			extentTest.log(LogStatus.FAIL, stepDescription+failedLoggerMessage+element,captureScreenshot());
			throw new RuntimeException(stepDescription+failedLoggerMessage+element);
		}
	}
	
	public static By xpath(String xpathExpression){
		return By.xpath(xpathExpression);
	}
	
	public static WebElement findElementByText(String textElement){
		return CommonMethods.findElement(By.xpath(".//*[text()='"+textElement+"']"));
	}
	
	public static void findElementByTextAndClick(String textElement,String stepDescription) throws Exception{
		 CommonMethods.findElement(By.xpath(".//*[text()='"+textElement+"']")).click();
		 log.info("Clicking on element with text....."+textElement);
		 extentTest.log(LogStatus.PASS, stepDescription, captureScreenshot());
	}
	
	public static WebElement findElementByPlaceHolder(String placeholderTextElement){
		 return CommonMethods.findElement(By.xpath(".//*[@placeholder='"+placeholderTextElement+"']"));
	}
	
	public static void findElementByPlaceHolderAndSendKeys(String placeholderTextElement,String sendKeysValue,String stepDescription) throws Exception{
		 CommonMethods.findElement(By.xpath(".//*[@placeholder='"+placeholderTextElement+"']")).sendKeys(sendKeysValue);
		 log.info("Entering value ....."+sendKeysValue+" into the text box field...."+placeholderTextElement);
		 extentTest.log(LogStatus.PASS, stepDescription, captureScreenshot());
	}
	
	public static String getElementText(By element){
		log.info("getting text from the element  "+element);
		return CommonMethods.findElement(element).getText();
	}
	
	public static String getElementAttribute(By element){
		log.info("getting text from the element  "+element);
		return CommonMethods.findElement(element).getAttribute("value");
	}
	

	public static String getPopUpNotificationMessage(){
		log.info("getting text from the element  "+popUpNotification);
		return CommonMethods.getElementText(popUpNotification);
	}
	
	public static void explicitWaitTillElementInvisible(By element){
		log.info("Waiting maximum of 30 secs for element "+element+" to be InVisible");
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
		
	}
	
	public static boolean isElementPresent(final By element){
		
		if(findElements(element).size()>0){
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;
		}
		else{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;
		}
	}
	
	public static boolean compareWithExpectedElement(By expectedLocator,String stepDescription) throws Exception{
		
		try{

			if(findElements(expectedLocator).size()>0){
				Assert.assertTrue(findElement(expectedLocator).isDisplayed());
				extentTest.log(LogStatus.PASS, stepDescription + " Successfully",captureScreenshot());
				return true;
			}
			else{
				Assert.assertTrue(findElement(expectedLocator).isDisplayed());
				return false;
			}
			
		}
		catch(Throwable t){
			log.info(t.getLocalizedMessage());
			//System.out.println("t.getMessage()="+t.getMessage());
			//System.out.println("t.getLocalizedMessage()="+t.getLocalizedMessage());
			Error e=new Error(t.getMessage());
			log.error("Expected value not present in the webpage"+t.getLocalizedMessage());
		    log.error(t.getMessage());
		    extentTest.log(LogStatus.FAIL, stepDescription+failedLoggerMessage+expectedLocator,captureScreenshot());
			throw e;
			

			//return false;
			
		}
		
		
	}
	
	
}

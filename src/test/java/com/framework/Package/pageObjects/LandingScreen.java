package com.framework.Package.pageObjects;

import org.openqa.selenium.By;

import com.framework.Package.driver.CommonMethods;

public class LandingScreen {
	
	By chaptersText=By.xpath(".//a[text()='Chapters']");
	
	public void verifyLandingScreen() throws Exception{
		CommonMethods.compareWithExpectedElement(chaptersText,"Comparing text="+chaptersText);
	}
	

}

package com.framework.Package.pageObjects;

import org.openqa.selenium.By;

import com.framework.Package.driver.CommonMethods;

public class ForgotPassword {
	
	//public By email=
	
	String emailPlaceHolder="Email";
	String submitButtonText="Submit";
	By loginLinkText=By.linkText("Login");
	By registerLinkText=By.linkText("Register");
	//By popUpNotification=By.xpath(".//*[@id='toast-container']/div/div");
	By verifyForgotPasswordText=By.xpath(".//*[text()='Forgot Password']");
	
	
	public void passDataToUserNameField(String userEmail) throws Exception{
		
		CommonMethods.findElementByPlaceHolder(emailPlaceHolder).clear();
		CommonMethods.findElementByPlaceHolderAndSendKeys(emailPlaceHolder, userEmail,"Sending data="+userEmail+" to the field "+emailPlaceHolder);
		
	}
	
	public void clickSubmitButton() throws Exception{
		CommonMethods.findElementByTextAndClick(submitButtonText,"Clicking on "+submitButtonText+" element");
	}
	
	public void verifyForgotPasswordPage() throws Exception{
		CommonMethods.compareWithExpectedElement(verifyForgotPasswordText," comparing with expected element "+verifyForgotPasswordText);
	}
	
	public void clickLoginHyperlink() throws Exception{
		CommonMethods.click(loginLinkText,"Clicking on login hyperlink "+loginLinkText);
	}
	
	public void clickRegisterHyperlink() throws Exception{
		CommonMethods.click(registerLinkText,"Clicking on register hyperlink "+registerLinkText);
		
	}
	

}

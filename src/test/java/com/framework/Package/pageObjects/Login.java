package com.framework.Package.pageObjects;

import org.openqa.selenium.By;

import com.framework.Package.driver.CommonMethods;

public class Login {

	By username=By.name("userName");
	By password=By.name("Password");
	String loginButtonText="Sign in";
	By rememberMeCheckBox=xpath(".//*[@class='checkbox_wrap']/input");
	By rememberMeElementName=xpath(".//*[@class='checkbox_wrap']/span");
	By forgotPasswordLink=By.linkText("Forgot Password");
	By registerLink=By.linkText("Register");
	By verifyLoginText=By.xpath(".//*[text()='Login']");
	
	
	public static By xpath(String xpathExpression){
		return By.xpath(xpathExpression);
	}

	public void passDataToUsernameField(String userEmail) throws Exception{

		CommonMethods.clear(username,"Clearing username field");
		CommonMethods.sendKeys(username, userEmail,"Passing data="+userEmail+" to the username field");

	}

	public void passDataToPasswordField(String userPassword) throws Exception{

		CommonMethods.clear(password,"Clearing password field");
		CommonMethods.sendKeys(password, userPassword,"Passing data="+userPassword+" to the password field");

	}
	
	public void clickOnLoginButton() throws Exception{
		
		CommonMethods.findElementByTextAndClick(loginButtonText,"clicking login element "+loginButtonText);
	}
	
	public void clickOnRememberMeCheckBox() throws Exception{
		CommonMethods.click(rememberMeCheckBox,"Clicking remember me check box field "+rememberMeCheckBox);
	}
	
	public void clickOnForgotPasswordLink() throws Exception{
		CommonMethods.click(forgotPasswordLink,"Clicking forgot password link "+forgotPasswordLink);
	}
	
	public void clickOnRegisterLink() throws Exception{
		CommonMethods.click(registerLink,"Clicking register  link "+registerLink);
	}
	
	public void verifyLoginPage() throws Exception{
		CommonMethods.compareWithExpectedElement(verifyLoginText,"Comparing text="+verifyLoginText);
	}

}

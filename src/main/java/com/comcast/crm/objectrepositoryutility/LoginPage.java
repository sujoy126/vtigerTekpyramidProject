package com.comcast.crm.objectrepositoryutility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility{
	
	
	/**
	 * Rule 1 : create a separate java class
	 * rule 2 : Object Creation
	 * Rule 3 : Object Initialization
	 * Rule 4 : Object Encapsulation
	 * Rule 5 : Object Utilization
	 */
	
	
	@FindBy(name="user_name")
	private WebElement usernameEdBox;
	
	@FindBy(name="user_password")
	private WebElement passwordEdBox;
	
	
	@FindBy(id="submitButton")
	private WebElement loginButton;
	
	
	// Rule 3: Object Initialization
	
   WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements( driver, this);
	}
	
//	Rule 4 : Object Encapsulation
	
	private WebElement getUsernameEdBox() {
		return usernameEdBox;
	}	
	
	private WebElement getPasswordEdBox() {
		return passwordEdBox;
	}

	
	private WebElement getLoginButton() {
		return loginButton;
	}
	
//rule 5 : provide Action  Business Method
	public void loginToApp( String url,String userName, String password) {
		waitForPageToLoad(driver);
		getURL(driver, url);
		maximizeBrowser(driver);
		getUsernameEdBox().sendKeys(userName);
		getPasswordEdBox().sendKeys(password);
		getLoginButton().click();
	}
	
	
	

}

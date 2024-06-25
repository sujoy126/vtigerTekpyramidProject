package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
	public OrganizationInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className ="dvHeaderText")
	private WebElement orgInformation;
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement orgnameDisplayBox;
	
	@FindBy(id="mouseArea_Industry")
	private WebElement industryBox;
	
	


	@FindBy(id="mouseArea_Type")
	private WebElement industryType;
	
	
	
	public WebElement getOrgnameDisplayBox() {
		return orgnameDisplayBox;
	}



	public WebElement getPhDisplayBox() {
		return phDisplayBox;
	}

	@FindBy(id="mouseArea_Phone")
	private WebElement phDisplayBox;
	
	
	
	public WebElement getOrgInformation() {
		return orgInformation;
	}
	
	public WebElement getIndustryBox() {
		return industryBox;
	}

	public WebElement getIndustryType() {
		return industryType;
	}
	

	
	

}

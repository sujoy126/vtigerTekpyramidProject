package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	
	public ContactInformationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement contactInfoheder;
	
	
	@FindBy(id="mouseArea_Last Name") 
	private WebElement lastNameBox;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement orgNameBox;
	
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement datebox;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement endDate;
	

	
	
	public WebElement getEndDate() {
		return endDate;
	}

	public WebElement getDatebox() {
		return datebox;
	}

	public WebElement getOrgNameBox() {
		return orgNameBox;
	}

	public WebElement getContactInfoheder() {
		return contactInfoheder;
	}

	public WebElement getLastNameBox() {
		return lastNameBox;
	}

	

}

package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	
	public CreatingNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastNmBox;
	
	@FindBy(xpath = "//img[@alt='Select']")
	private WebElement orgimgContact;
	
	@FindBy(id="search_txt")
	private WebElement srchBoxChildWindow;
	
	@FindBy(xpath = "//input[@type='button']")
	private WebElement srchNowBtnChildWindow;
	
	@FindBy(xpath = "//a[@id='1']")
	private WebElement orgnameChildwindow;
	
	@FindBy(xpath = "//input[@value='Cancel  ']/preceding-sibling::input")
	private WebElement savebtm;
	
	@FindBy(name = "support_start_date")
	private WebElement supportStartDateBox;
	
	
	@FindBy(name = "support_end_date")
	private WebElement supportEndDateBox;

	
	
	public WebElement getSupportStartDateBox() {
		return supportStartDateBox;
	}

	public WebElement getSupportEndDateBox() {
		return supportEndDateBox;
	}

	
	public WebElement getSrchNowBtnChildWindow() {
		return srchNowBtnChildWindow;
	}


	public WebElement getSrchBoxChildWindow() {
		return srchBoxChildWindow;
	}

	
	public WebElement getOrgimgContact() {
		return orgimgContact;
	}

		

	public WebElement getSavebtm() {
		return savebtm;
	}

	public WebElement getLastNmBox() {
		return lastNmBox;
	}


	public WebElement getOrgnameChildwindow() {
		return orgnameChildwindow;
	}
	
	
	public void createContact(String lastName) {
		getLastNmBox().sendKeys(lastName);
		getSavebtm().click();
	}
	
	public void createContactWithSupportDate(String lastName, String startDate, String lastDate) {
		getLastNmBox().sendKeys(lastDate);
		getSupportStartDateBox().clear();
		getSupportStartDateBox().sendKeys(startDate);
		getSupportEndDateBox().clear();
		getSupportEndDateBox().sendKeys(lastDate);
		getSavebtm().click();
	}
	

}

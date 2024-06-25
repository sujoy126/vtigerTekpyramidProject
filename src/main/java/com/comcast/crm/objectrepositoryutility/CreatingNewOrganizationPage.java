package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {

	public CreatingNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "accountname")
	private WebElement orgName;

	public WebElement getOrgName() {
		return orgName;
	}

	@FindBy(xpath = "//input[@value='Cancel  ']/preceding-sibling::input")
	private WebElement saveButton;

	public WebElement getSaveButton() {
		return saveButton;
	}

	@FindBy(name = "industry")
	private WebElement industryDropDown;

	@FindBy(name = "accounttype")
	private WebElement industryType;

	@FindBy(id = "phone")
	private WebElement phoneNumberBox;

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getIndustryType() {
		return industryType;
	}

	public WebElement getPhoneNumberBox() {
		return phoneNumberBox;
	}

	public void createorg(String OrganizationName) {
		getOrgName().sendKeys(OrganizationName);
		getSaveButton().click();
	}

	public void createorg(String OrganizationName, String industryName) {
		getOrgName().sendKeys(OrganizationName);

		Select sl = new Select(industryDropDown);
		sl.selectByVisibleText(industryName);

		getSaveButton().click();

	}

	public void createOrgWithIndustry_Type(String OrganizationName, String industryName, String typeOfIndustry) {
		getOrgName().sendKeys(OrganizationName);

		Select sl1 = new Select(industryDropDown);
		sl1.selectByVisibleText(industryName);

		Select sl2 = new Select(industryType);
		sl2.selectByVisibleText(typeOfIndustry);

		getSaveButton().click();
	}

}

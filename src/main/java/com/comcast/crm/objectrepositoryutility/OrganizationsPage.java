package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	public OrganizationsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createOrganizationImg;
	
	
	@FindBy(name="search_text")
	private WebElement serchEdt;
	
	@FindBy(name ="search_field")
	private WebElement serchDD;
	
	@FindBy(name="submit")
	private WebElement srchNowButn;
	
	


	public WebElement getSerchDD() {
		return serchDD;
	}


	public WebElement getSerchEdt() {
		return serchEdt;
	}
	
	
	public WebElement getSrchNowButn() {
		return srchNowButn;
	}


	public WebElement getCreateOrganizationImg() {
		return createOrganizationImg;
	}


	
	

}

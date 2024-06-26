package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {
	@FindBy(xpath = "//input[@alt='Create Product...']")
	private WebElement createProductImgBtn;
	
	@FindBy(name="search")
	private WebElement ele2;
	
	@FindBy(name= "searchBtn")
	private WebElement ele3;
	
	public void m1() 
	{}
	
	

}

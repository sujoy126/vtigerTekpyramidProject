package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {
	@FindBy(xpath = "//input[@alt='Create Product...']")
	private WebElement createProductImgBtn;
	
	@FindBy(name="search")
	private WebElement ele2;
	
	// new Product button will launch  as soon as possible. 
	
	

}

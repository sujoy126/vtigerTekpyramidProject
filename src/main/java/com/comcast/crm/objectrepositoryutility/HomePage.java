package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactLink;

	@FindBy(linkText = "Opportunities")
	private WebElement opppertunatiesLink;

	@FindBy(linkText = "Products")
	private WebElement productsLink;

	@FindBy(linkText = "More")
	private WebElement moreLink;

	@FindBy(linkText = "Campaigns")
	private WebElement campaingnsLink;

	@FindBy(linkText = "Leads")
	private WebElement leadsLink;

	public WebElement getLeadsLink() {
		return leadsLink;
	}

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;

	@FindBy(linkText = "Sign Out")
	private WebElement signOutlink;
	
	@FindBy(linkText = "Invoice")
	private WebElement invoiceLink;

	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSignOutlink() {
		return signOutlink;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getOpppertunatiesLink() {
		return opppertunatiesLink;
	}

	public WebElement getProductsLink() {
		return productsLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getCampaingnsLink() {
		return campaingnsLink;
	}

	public WebElement getInvoiceLink() {
		return invoiceLink;
	}

	public void navigateToChampainsPage() {
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		campaingnsLink.click();
	}

	public void logout() {
		Actions act = new Actions(driver);
		act.moveToElement(adminImg).perform();
		signOutlink.click();

	}

}

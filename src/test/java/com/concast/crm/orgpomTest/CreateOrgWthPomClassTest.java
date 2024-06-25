package com.concast.crm.orgpomTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgWthPomClassTest extends BaseClass{
	@Test
	public void createOrgTest() throws Throwable {
//		/* Creation of Object */
//		PropertyFileUtility pLib = new PropertyFileUtility();
//		ExcelUtility eLib = new ExcelUtility();
//		JavaUtility javaLib = new JavaUtility();
//
//		/* get the property file methods from property libery */
//		String BROWSER = pLib.getDataFromPropertiesFile("Browser");
//		String URL = pLib.getDataFromPropertiesFile("Url");
//		String USERNAME = pLib.getDataFromPropertiesFile("UserName");
//		String PASSWORD = pLib.getDataFromPropertiesFile("Password");

	

//		// launch the Browser
//		WebDriver driver = null;
//
//		if (BROWSER.equals("chrome")) {
//			driver = new ChromeDriver();
//		} else if (BROWSER.equals("firefox")) {
//			driver = new FirefoxDriver();
//		} else if (BROWSER.equals("edge")) {
//			driver = new EdgeDriver();
//		}

		// Step 1: Login to app
//		driver.get(URL); 
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		LoginPage lp = PageFactory.initElements(driver, LoginPage.class);		
//		lp.getUsernameEdBox().sendKeys(USERNAME);
//		lp.getPasswordEdBox().sendKeys(PASSWORD);
//		lp.getLoginButton().click();

//		LoginPage lp = new LoginPage(driver);
//
//		lp.loginToApp(URL, USERNAME, PASSWORD);
		
		
		
		
		
		/* Read Test script data from Excel Utility */
		String OrganizationName = eLib.getDataFromExcel("orgdata", 0, 1) + javaLib.getRandomNumber();
		String industryName = eLib.getDataFromExcel("org", 4, 3);

		// step2 : navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
//		hp.navigateToChampainsPage();

		// step 3: create Organization
		OrganizationsPage orgp = new OrganizationsPage(driver);
		orgp.getCreateOrganizationImg().click();

		// step 4 : Enter Organization name And click on save Button
//		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
//		cnop.createorg(OrganizationName);

		// Enter Organization name With industry dropdown and save

		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createorg(OrganizationName, industryName);

		// verify Page header msg Expected Result
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		// oip.orgInfoValidation(OrganizationName);
		String actorgData = oip.getOrgInformation().getText();
		if (actorgData.contains(OrganizationName)) {
			System.out.println(OrganizationName + " is same as excel data==>pass");
		} else
			System.out.println(OrganizationName + " is not same as excel data ==> fail");

//		// step 5: Logout
//		hp.logout();
//
//		driver.quit();
	}

}

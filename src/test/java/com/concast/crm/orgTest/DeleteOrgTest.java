package com.concast.crm.orgTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {
	public static void main(String[] args) throws Throwable {
		/* Creation of Object  */
		PropertyFileUtility pLib = new PropertyFileUtility();
		ExcelUtility        eLib = new ExcelUtility();
		JavaUtility      javaLib = new JavaUtility();
		WebDriverUtility WLib = new WebDriverUtility();
		
		
		
		/* get the property file methods from property libery*/
		String BROWSER = pLib.getDataFromPropertiesFile("Browser");
		String URL     = pLib.getDataFromPropertiesFile("Url");
		String USERNAME= pLib.getDataFromPropertiesFile("UserName");
		String PASSWORD= pLib.getDataFromPropertiesFile("Password");
		

	
	/*Read Test script data from Excel Utility */
		String OrganizationName= eLib.getDataFromExcel("org",1,2)+javaLib.getRandomNumber();
	//	String industryName= eLib.getDataFromExcel("org",4,3);
		String orgDropDownData= eLib.getDataFromExcel("org", 10, 3);
		
		
	

	// launch the Browser
		WebDriver driver =null;
		
		if(BROWSER.equals("chrome")){
			driver = new ChromeDriver();
		}
		else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}
		

		
		
		
	// Step 1: Login to app
//		WLib.getURL(driver, URL);
//		WLib.getForPageToLoad(driver);

		
		LoginPage lp = new LoginPage(driver);
		
		lp.loginToApp(URL,USERNAME,PASSWORD);
		
		
		
	//step2 : navigate to organization module
		HomePage hp= new HomePage(driver);
		hp.getOrgLink().click();
//		hp.navigateToChampainsPage();
		
		
		
	//step 3: create Organization	
		
		OrganizationsPage orgp = new OrganizationsPage(driver);
		orgp.getCreateOrganizationImg().click();
		

	// step 4 : Enter  Organization name And click on save Button
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createorg(OrganizationName);

		
		
	//verify Page header msg Expected Result
		
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
	
		String actorgData = oip.getOrgInformation().getText();
		if(actorgData.contains(OrganizationName)) {
			System.out.println(OrganizationName +" is same as excel data==>pass");
		}
		else
			System.out.println(OrganizationName +" is not same as excel data ==> fail");
		
		//Go back to Organizations Page
		hp.getOrgLink().click();
		
//		//search for Organization
		orgp.getSerchEdt().sendKeys(OrganizationName);
		WLib.select(orgp.getSerchDD(),orgDropDownData);
		
		orgp.getSrchNowButn().click();
//		
//		
//		//In Dynamic WebTable select and delete org
		Thread.sleep(2000);
		// this xpth always get during runtime
		driver.findElement(By.xpath("//a[text()='"+OrganizationName+"']/../../td[8]/a[text()='del']")).click();
		
	// step 5: Logout
	//	hp.logout();
		
//		
//	//	driver.quit();
	}

}

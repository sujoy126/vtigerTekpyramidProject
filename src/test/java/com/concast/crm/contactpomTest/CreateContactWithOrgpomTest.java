package com.concast.crm.contactpomTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactWithOrgpomTest  {
	@Test
	public void createContactWithOrgTest() throws Throwable {
				
		/* Creation of Object  */
		PropertyFileUtility pLib = new PropertyFileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility javaLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		
		/* get the property file methods from property libery*/
		String BROWSER = pLib.getDataFromPropertiesFile("Browser");
		String URL     = pLib.getDataFromPropertiesFile("Url");
		String USERNAME= pLib.getDataFromPropertiesFile("UserName");
		String PASSWORD= pLib.getDataFromPropertiesFile("Password");
		
			
		
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
		
		
		LoginPage lp= new LoginPage(driver);
		lp.loginToApp(URL,USERNAME, PASSWORD);
	

		//Read Test script data from Excel Sheet	
		String OrganizationName= eLib.getDataFromExcel("contact", 7, 2)+javaLib.getRandomNumber();
		String contactLastName= eLib.getDataFromExcel("contact", 7, 3)+javaLib.getRandomNumber();

	//step2 : navigate to organization module	
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
	
	//step 3: create Organization
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrganizationImg().click();
		
		

	// step 4 : Enter All the details in  New Organization
		
	CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
	cnop.createorg(OrganizationName);
		
//	//step 5 : verify Page header msg Expected Result
	OrganizationInformationPage oip = new OrganizationInformationPage(driver);
	String actualData = oip.getOrgInformation().getText();
	
		if(actualData.contains(OrganizationName)) {
			System.out.println(OrganizationName+" header is verified == pass");
		}
		else {
			System.out.println(OrganizationName+"  header is not verified == fail");
		}
		
	// step 6 : navigate to contact module 	
		hp.getContactLink().click();
		
	
	//step 3: create Contacts	
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContatImg().click();
		

	// step 4 : Enter All the details in  New Contacts
		
		 CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		 cncp.getLastNmBox().sendKeys(contactLastName);

		 cncp.getOrgimgContact().click();
	// switch parent to child window
		 		 
		 wLib.switchParentWindowToChildWindow(driver);
		 System.out.println(driver.getCurrentUrl());
		 
	// enter data from Child window
		 
		 cncp.getSrchBoxChildWindow().sendKeys(OrganizationName);
		 cncp.getSrchNowBtnChildWindow().click();
		 Thread.sleep(2000);
		 cncp.getOrgnameChildwindow().click();
		 
				 
		// switch child to parent window
		 wLib.swithToTabOnTitle(driver,"Contacts&action");
		 System.out.println(driver.getTitle());
		 
		// save contact page	 
		 cncp.getSavebtm().click();
		
//		//verify Page header msg Expected Result
		 ContactInformationPage cip =new ContactInformationPage(driver);
		 String actpageHeader = cip.getContactInfoheder().getText();
		 if(actpageHeader.contains(contactLastName)) {
			 System.out.println(contactLastName+" is present in Contact == pass");
		 }
		 else
			 System.out.println(OrganizationName+" is  not present in Contact == fail");
			 
		 
		 	
//	//verify in contact  information Page has same Organization Name Expected Result

		 String actorgname = cip.getOrgNameBox().getText();
		if(actorgname.trim().equals(OrganizationName)) {
			System.out.println(OrganizationName+" is present in Contact == pass");
		}
		else {
			System.out.println(OrganizationName+" is  not present in Contact == fail");
		}
		
		
	// step 5: Logout
		hp.logout();

	}

}

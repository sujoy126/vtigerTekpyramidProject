package com.comcast.crm.contactModuleTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactTest extends BaseClass {
	@Test(groups ="SmokeTest", retryAnalyzer = com.comcast.crm.generic.listnerutility.RetryAnalyserClass.class)
	public void createContactTest() throws Throwable {

		// Read Test script data from Excel Sheet
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + javaLib.getRandomNumber();

		// step2 : navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3: create Contacts
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContatImg().click();

		// step 4 : Enter All the details in New Contacts
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact(lastName);

		// verify Page header msg Expected Result
		ContactInformationPage cip = new ContactInformationPage(driver);
		String actualinfoheder = cip.getContactInfoheder().getText();
		boolean status = actualinfoheder.contains(lastName);
		Assert.assertEquals(status, true);
//		if (actualinfoheder.contains(lastName)) {
//			System.out.println(lastName + " is present in Header of contact in ContactInformationPage ==>pass");
//		} else
//			System.out.println(lastName + " is not present in heder of contact in ContactInformationPage ==>fail");

			
		// Verify Header LastName info in contacts Expected Result
		String actLastnameData = cip.getLastNameBox().getText().trim();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastnameData, lastName);
		soft.assertAll();
		
//		if (actLastnameData.contains(lastName)) {
//			System.out.println(lastName + " is present in contactinfopage==>pass");
//		} else
//			System.out.println(lastName + " is not present in contactinfopage==> fail");
	}
	

	@Test(groups = "RegressionTest", retryAnalyzer = com.comcast.crm.generic.listnerutility.RetryAnalyserClass.class)
	public void createContactWithSupportDateTest() throws Throwable {
		
		// Get method from excel Utility and data from excel sheet
		String LastName = eLib.getDataFromExcel("contact", 4, 2) + javaLib.getRandomNumber(2000);

		// step2 : navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3: click on "create Contact" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContatImg().click();

		// step 4 : Enter All the details in New Contacts
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		String todayDate = javaLib.getSystemDateYYYYMMDD();
		String endDate = javaLib.getRequredDate(30);
		cncp.createContactWithSupportDate(LastName, todayDate, endDate);

		// Verify Support Start date in contacts Expected Result
		ContactInformationPage cip = new ContactInformationPage(driver);
		String actStartDate = cip.getDatebox().getText();
		Reporter.log(actStartDate, true);
		Assert.assertEquals(actStartDate, todayDate);

		// verify support end Date in contacts Expected Result
		String actEndtDate = cip.getEndDate().getText();	
		SoftAssert sf = new SoftAssert();
		sf.assertEquals(actEndtDate, endDate);
		Reporter.log(actEndtDate,true);
		sf.assertAll();
	}

	@Test(groups = "RegressionTest", retryAnalyzer = com.comcast.crm.generic.listnerutility.RetryAnalyserClass.class)
	public void createContactWithOrgTest() throws Throwable {

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
	boolean statuss =  actualData.contains(OrganizationName);
	Reporter.log(OrganizationName, true);
	SoftAssert sf = new SoftAssert();
	sf.assertEquals(statuss, true);
	 sf.assertAll();
	
//		if(actualData.contains(OrganizationName)) {
//			System.out.println(OrganizationName+" header is verified in OrganizationInformationPage == pass");
//		}
//		else {
//			System.out.println(OrganizationName+"  header is not verified in OrganizationInformationPage == fail");
//		}
		
	// step 6 : navigate to contact module 	
		hp.getContactLink().click();
		
	
	//step 3: create Contacts	
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContatImg().click();
		

	// step 4 : Enter the lastname and org name  in  New Contacts
		
		 CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		 cncp.getLastNmBox().sendKeys(contactLastName);

		 cncp.getOrgimgContact().click();
	// switch parent to child window
		 		 
		 wLib.switchParentWindowToChildWindow(driver);
		
		 
	// enter data in Child window
		 
		 cncp.getSrchBoxChildWindow().sendKeys(OrganizationName);
		 cncp.getSrchNowBtnChildWindow().click();
		 Thread.sleep(2000);
		 cncp.getOrgnameChildwindow().click();
		 			 
		// switch child to parent window
		 wLib.swithToTabOnTitle(driver,"Contacts&action");
	
		 
		// save contact page	 
		 cncp.getSavebtm().click();
		
//		//verify Page header msg Expected Result
		 ContactInformationPage cip =new ContactInformationPage(driver);
		 String actpageHeader = cip.getContactInfoheder().getText();
		boolean sts =   actpageHeader.contains(contactLastName);
		SoftAssert rt = new SoftAssert();
		Reporter.log(contactLastName);
		rt.assertTrue( true);
		 
//		 if(actpageHeader.contains(contactLastName)) {
//			 System.out.println(contactLastName+" is present in header of ContactInformationPage == pass");
//		 }
//		 else
//			 System.out.println(contactLastName+" is  not present in  header of ContactInformationPage == fail");
			 	 	
//	//verify in contact  information Page has same Organization Name Expected Result
		 String actorgname = cip.getOrgNameBox().getText();
		 boolean bo=  actorgname.trim().contains(OrganizationName);
		 Reporter.log(OrganizationName,true);
	     Assert.assertEquals(sts, bo);
//		if(actorgname.trim().equals(OrganizationName)) {
//			System.out.println(OrganizationName+" is present in Contact of ContactInformationPage == pass");
//		}
//		else {
//			System.out.println(OrganizationName+" is  not present in Contact of ContactInformationPage == fail");
//		}
	    
	}

}

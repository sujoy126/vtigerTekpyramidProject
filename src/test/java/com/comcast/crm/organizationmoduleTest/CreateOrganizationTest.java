package com.comcast.crm.organizationmoduleTest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

@Listeners(com.comcast.crm.generic.listnerutility.ListImpClass.class)
public class CreateOrganizationTest extends BaseClass {

	@Test(groups = "SmokeTest", retryAnalyzer = com.comcast.crm.generic.listnerutility.RetryAnalyserClass.class)
	public void createOrgTest() throws Throwable {

		/* Read Test script data from Excel Utility */
		// ListImpClass.test.log(Status.INFO, "read data from Excel"); // it will not
		// participate on parallel execution
		UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");

		String OrganizationName = eLib.getDataFromExcel("orgdata", 0, 1) + javaLib.getRandomNumber(); // change 1 to 2

		// step2 : navigate to organization module
		// ListImpClass.test.log(Status.INFO, "navigate to org page"); // it will not
		// participate on parallel execution
		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step 3: create Organization
//		ListImpClass.test.log(Status.INFO, "Navigate to create org page"); // it will not  participate on parallel execution
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create org page");

		OrganizationsPage orgp = new OrganizationsPage(driver);
		orgp.getCreateOrganizationImg().click();

		// Enter Organization name and save
//		ListImpClass.test.log(Status.INFO, " create new Organization"); // it will not  participate on parallel execution
		UtilityClassObject.getTest().log(Status.INFO, " create new Organization");
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createorg(OrganizationName);

		// verify Page header msg Expected Result
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);

		String actorgData = oip.getOrgInformation().getText();
		boolean acData = actorgData.contains(OrganizationName);
		Assert.assertEquals(acData, true);
	}

	@Test(groups = "RegressionTest", retryAnalyzer = com.comcast.crm.generic.listnerutility.RetryAnalyserClass.class)
	public void createOrgWithPhNumberTest() throws Throwable {

		String OrganizationName = eLib.getDataFromExcel("org", 7, 2) + javaLib.getRandomNumber();
		String PhoneNumber = eLib.getDataFromExcel("org", 7, 3);

		// step2 : navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step 3: create Organization
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrganizationImg().click();

		// step 4 : Enter All the details in New Organization

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.getOrgName().sendKeys(OrganizationName);
		cnop.getPhoneNumberBox().sendKeys(PhoneNumber);
		cnop.getSaveButton().click();

		// verify Page header msg Expected Result

		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actualData = oip.getOrgInformation().getText();
		boolean data = actualData.contains(OrganizationName);
		Assert.assertTrue(data);
//		if (actualData.contains(OrganizationName)) {
//			System.out.println(OrganizationName+" is present in header of createOrgWithPhNumberTest ===> pass");
//		} else {
//			System.out.println(OrganizationName+" is not present in header of createOrgWithPhNumberTest ===> Fail");
//		}

		// Verify orgNameinfo Expected Result
		String actOrgName = oip.getOrgnameDisplayBox().getText();
		boolean dt = actOrgName.contains(OrganizationName);
		Assert.assertEquals(dt, true);
//		if (actOrgName.contains(OrganizationName)) {
//			System.out.println(OrganizationName + " is present in orgBox of createOrgWithPhNumberTest ==pass");
//		} else
//			System.out.println(OrganizationName + " is not present in orgBox of createOrgWithPhNumberTest== fail");

		// Verify the phone number
		String actPhonenumber = oip.getPhDisplayBox().getText().trim();

		Assert.assertEquals(actPhonenumber, PhoneNumber);
//		if (actPhonenumber.contains(PhoneNumber)) {
//			System.out.println(PhoneNumber + " is created in phBox of createOrgWithPhNumberTest ==pass");
//		} else
//			System.out.println(PhoneNumber + " is not created in phBox of createOrgWithPhNumberTest == fail");
	}

	@Test(groups = "RegressionTest", retryAnalyzer = com.comcast.crm.generic.listnerutility.RetryAnalyserClass.class)
	public void createOrgWithIndustryAndIndTypeTest() throws Throwable {

		String OrganizationName = eLib.getDataFromExcel("org", 4, 2) + javaLib.getRandomNumber();
		String industryName = eLib.getDataFromExcel("org", 4, 3);
		String industryType = eLib.getDataFromExcel("org", 4, 4);

		// step2 : navigate to organization module

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step 3: create Organization
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrganizationImg().click();

		// step 4 : Enter All the details in New Organization

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrgWithIndustry_Type(OrganizationName, industryName, industryType);

		// verify Page header msg Expected Result
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);

		String actualData = oip.getOrgInformation().getText();
		boolean ac = actualData.contains(OrganizationName);
		Assert.assertEquals(ac, true);
//		if (actualData.contains(OrganizationName)) {
//			System.out.println(OrganizationName+" header is present in createOrgWithIndustryAndIndTypeTest == pass");
//		} else {
//			System.out.println(OrganizationName+" header is  not present in createOrgWithIndustryAndIndTypeTest == fail");
//		}

		// Verify orgNameinfo Expected Result
		String actOrgName = oip.getOrgnameDisplayBox().getText().trim();
		Assert.assertEquals(actOrgName, OrganizationName);
//		if (actOrgName.contains(OrganizationName)) {
//			System.out.println(OrganizationName + " is created in createOrgWithIndustryAndIndTypeTest == pass");
//		} else
//			System.out.println(OrganizationName + " is not created in createOrgWithIndustryAndIndTypeTest == fail");

		// Verify the industry
		String actualIndustries = oip.getIndustryBox().getText().trim();
		Assert.assertEquals(actualIndustries, industryName);
//		if (actualIndustries.equals(industryName)) {
//			System.out.println(industryName + " is created in createOrgWithIndustryAndIndTypeTest == pass");
//		} else
//			System.out.println(industryName + " is not created in createOrgWithIndustryAndIndTypeTest == fail");
		// type of industry
		String actType = oip.getIndustryType().getText().trim();
		Assert.assertEquals(actType, industryType);
//		if (actType.equals(industryType)) {
//			System.out.println(industryType + " is created in createOrgWithIndustryAndIndTypeTest == pass");
//		} else
//			System.out.println(industryType + " is not created in createOrgWithIndustryAndIndTypeTest == fail");
	}

}

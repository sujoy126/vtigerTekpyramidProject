package com.concast.crm.contactpomTest;

import java.io.FileInputStream;

import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.JsonUtility;
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

public class CreateContactPomTest extends BaseClass {
	@Test
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
		if (actualinfoheder.contains(lastName)) {
			System.out.println(lastName + " is present in Header of contact");
		} else
			System.out.println(lastName + " is not present in heder of contact");

		// Verify Header LastName info in contacts Expected Result
		String actLastnameData = cip.getLastNameBox().getText();
		if (actLastnameData.contains(lastName)) {
			System.out.println(lastName + " is present in contact info page==>pass");
		} else
			System.out.println(lastName + " is not present in contact info page==> fail");
	}

	

}

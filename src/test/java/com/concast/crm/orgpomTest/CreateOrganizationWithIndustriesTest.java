package com.concast.crm.orgpomTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationWithIndustriesTest {
	public static void main(String[] args) throws Throwable {
		/* create object */
		JavaUtility javaLib= new JavaUtility();
		PropertyFileUtility pLib = new PropertyFileUtility();
		ExcelUtility eLib= new ExcelUtility();
	
		
		
//		FileInputStream fis = new FileInputStream("./configAppData/commonData.properties");
//		Properties pobj = new Properties();
//		pobj.load(fis);
//		String BROWSER= pobj.getProperty("Browser");
//		String URL= pobj.getProperty("Url");
//		String USERNAME= pobj.getProperty("UserName");
//		String PASSWORD= pobj.getProperty("Password");
		
		String BROWSER = pLib.getDataFromPropertiesFile("Browser");
		String URL     = pLib.getDataFromPropertiesFile("Url");
		String USERNAME= pLib.getDataFromPropertiesFile("UserName");
		String PASSWORD= pLib.getDataFromPropertiesFile("Password");
		
		
		
//		// Random numbers
//		Random r = new Random();
//		 int num = 10000;
//		 int random = r.nextInt(num);
		
//	//Read Test script data from Excel Sheet
//		FileInputStream Fis = new FileInputStream("./testData/testScriptData.xlsx");
//		Workbook book = WorkbookFactory.create(Fis);
//		Sheet sh =book.getSheet("org");
//		
//		 String OrganizationName= sh.getRow(4).getCell(2).toString()+random;
//		 String industryName= sh.getRow(4).getCell(3).toString();
//		 String industryType= sh.getRow(4).getCell(4).toString();
//		 
//		book.close();
		
		String OrganizationName= eLib.getDataFromExcel("org", 4, 2)+ javaLib.getRandomNumber();
		String industryName= eLib.getDataFromExcel("org", 4, 3);
		String industryType= eLib.getDataFromExcel("org", 4, 4);
		
	
		
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
//		driver.get(URL); 
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
//		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//		driver.findElement(By.id("submitButton")).click();
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
		
		
	//step2 : navigate to organization module	
//		driver.findElement(By.linkText("Organizations")).click();
		
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
	
	//step 3: create Organization	
//		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrganizationImg().click();
		

	// step 4 : Enter All the details in  New Organization
		
//		 driver.findElement(By.className("detailedViewTextBox")).sendKeys(OrganizationName);
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		
		
//		WebElement industryDropDown= driver.findElement(By.name("industry"));
//		Select indDropDown = new Select(industryDropDown);
//		indDropDown.selectByVisibleText(industryName);
//		
//		WebElement accountDropDown= driver.findElement(By.name("accounttype"));
//		Select accDropDown = new Select(accountDropDown);
//		accDropDown.selectByVisibleText(industryType);
//		
//		driver.findElement(By.name("button")).click();
		
		cnop.createOrgWithIndustry_Type(OrganizationName, industryName, industryType);
		
	//verify Page header msg Expected Result
//		WebElement ele = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
//		String actualData = ele.getText();
		
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		
		String actualData = oip.getOrgInformation().getText();	
		System.out.println(actualData);
		if(actualData.contains(OrganizationName)) {
			System.out.println("Data is virified");
		}
		else {
			System.out.println("Varification Fail");
		}
		
	//VerifyHeader orgNameinfo Expected Result
//		 String actOrgName= driver.findElement(By.id("dtlview_Organization Name")).getText();
		 
		String actOrgName= oip.getOrgnameDisplayBox().getText();
		 if(actOrgName.contains(OrganizationName)) {
			 System.out.println(OrganizationName+" is created ==pass");
		 }
		 else
			 System.out.println(OrganizationName+" is not created== fail");
		
		 
	//Verify the industry 
//		 String actualIndustries= driver.findElement(By.id("dtlview_Industry")).getText();
		 
		 String actualIndustries= oip.getIndustryBox().getText();
		 if(actualIndustries.equals(industryName)) {
			 System.out.println(industryName+" is verified ==pass");
		 }
		 else
			 System.out.println(industryName+" is not verified== fail");
	//type of industry	 
//		 String actType= driver.findElement(By.id("dtlview_Type")).getText();
		 String actType=  oip.getIndustryType().getText();
		 if(actType.equals(industryType)) {
			 System.out.println(industryType+" is verified ==pass");
		 }
		 else
			 System.out.println(industryType+" is not verified== fail");
		 
		 
		 
		
	// step 5: Logout
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//td[@class='small' and @valign='bottom']")).click();
//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		hp.logout();
		
		driver.quit();
	}


}

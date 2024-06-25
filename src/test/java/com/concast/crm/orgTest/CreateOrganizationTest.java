package com.concast.crm.orgTest;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.FileStore;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;




public class CreateOrganizationTest {
	public static void main(String[] args) throws Throwable {
		/* Creation of Object  */
		PropertyFileUtility pLib = new PropertyFileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility javaLib = new JavaUtility();
		
		
		/* get the property file methods from property libery*/
		String BROWSER = pLib.getDataFromPropertiesFile("Browser");
		String URL     = pLib.getDataFromPropertiesFile("Url");
		String USERNAME= pLib.getDataFromPropertiesFile("UserName");
		String PASSWORD= pLib.getDataFromPropertiesFile("Password");
		

	
	/*Read Test script data from Excel Utility */
		String OrganizationName= eLib.getDataFromExcel("org", 1, 2)+javaLib.getRandomNumber();
		
		
		
	
//	// Give the inpout from keyboard
//		Scanner sc = new Scanner(System.in);
//		System.out.println("enter the Browser name");
//		String browser = sc.next();
//		System.out.println(browser);
		
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
		driver.get(URL); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
	//step2 : navigate to organization module	
		driver.findElement(By.linkText("Organizations")).click();
	
	//step 3: create Organization	
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		

	// step 4 : Enter All the details in  New Organization
		
		 driver.findElement(By.className("detailedViewTextBox")).sendKeys(OrganizationName);
		 driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();
		
	//verify Page header msg Expected Result
		WebElement ele = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		String actualData = ele.getText();
		System.out.println(actualData);
		if(actualData.contains(OrganizationName)) {
			System.out.println("Data is virified");
		}
		else {
			System.out.println("Varification Fail");
		}
		
	//VerifyHeader orgNameinfo Expected Result
		 String actOrgName= driver.findElement(By.id("dtlview_Organization Name")).getText();
		 if(actOrgName.contains(OrganizationName)) {
			 System.out.println(OrganizationName+" is created ==pass");
		 }
		 else
			 System.out.println(OrganizationName+" is not created== fail");
		 
		 
		 
		
	// step 5: Logout
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[@class='small' and @valign='bottom']")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
	}

}

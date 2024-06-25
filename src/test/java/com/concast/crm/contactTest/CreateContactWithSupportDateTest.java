package com.concast.crm.contactTest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactWithSupportDateTest {
	public static void main(String[] args) throws Throwable {
		/* create object*/
		JavaUtility javaLib = new JavaUtility();
		PropertyFileUtility pLib = new PropertyFileUtility();
		ExcelUtility eLib = new ExcelUtility();
		
		
		//get data from property utility 
		
		String BROWSER=  pLib.getDataFromPropertiesFile("Browser");
		String URL    =  pLib.getDataFromPropertiesFile("Url");
		String USERNAME=  pLib.getDataFromPropertiesFile("UserName");
		String PASSWORD=  pLib.getDataFromPropertiesFile("Password");
		
		
		// Get method from excele Utility and data from excel sheet
		
		String LastName=  eLib.getDataFromExcel("contact", 4, 2)+ javaLib.getRandomNumber(2000);
		
	
		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
	//step2 : navigate to Contact module	
		driver.findElement(By.linkText("Contacts")).click();
	
	//step 3: create Contacts	
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		

	// step 4 : Enter All the details in  New Contacts
		
		 driver.findElement(By.name("lastname")).sendKeys(LastName);
		 
//		 Date dateObj = new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			String startDate= sdf.format(dateObj);
//			
//			Calendar cal = sdf.getCalendar();
//			cal.add(Calendar.DAY_OF_MONTH, 30);
//			String endDate= sdf.format(cal.getTime());
		 
		 String startDate= javaLib.getSystemDateYYYYMMDD();
		 String endDate= javaLib.getRequredDate(30);
			
		 
		 driver.findElement(By.name("support_start_date")).clear();
		 driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		 
		 driver.findElement(By.name("support_end_date")).clear();
		 driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		 
		
		driver.findElement(By.name("button")).click();
		
	
		
	//Verify Support Start date  in contacts Expected Result
		 String actStartDate= driver.findElement(By.id("dtlview_Support Start Date")).getText();
		 if(actStartDate.contains(startDate)) {
			 System.out.println(startDate+" is created ==pass");
		 }
		 else
			 System.out.println(startDate+" is not created== fail");
		 
  // verify support end Date in contacts Expected Result
		 String actEndtDate= driver.findElement(By.id("dtlview_Support End Date")).getText();
		 if(actEndtDate.contains(endDate)) {
			 System.out.println(endDate+" is created ==pass");
		 }
		 else
			 System.out.println(endDate+" is not created== fail");
		 
		
		 
	 
		
	// step 5: Logout
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[@class='small' and @valign='bottom']")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
	}

}

package com.concast.crm.contactTest;

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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.JsonUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactTest {
	public static void main(String[] args) throws Throwable {
		
		/* Object Creation */
		PropertyFileUtility pLib = new PropertyFileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JsonUtility jLib = new JsonUtility();
		JavaUtility javaLib = new JavaUtility();
		
		
		
//	//Read data form Property file
//		String BROWSER=  pLib.getDataFromPropertiesFile("Browser");
//		String URL=      pLib.getDataFromPropertiesFile("Url");
//		String USERNAME= pLib.getDataFromPropertiesFile("UserName");
//		String PASSWORD= pLib.getDataFromPropertiesFile("Password");
		
	//Read data from Json utility
		String BROWSER= jLib.getDataFromJsonFile("Browser");
		String URL= jLib.getDataFromJsonFile("Url");
		String USERNAME= jLib.getDataFromJsonFile("UserName");
		String PASSWORD= jLib.getDataFromJsonFile("Password");
		
		
		
		
//		// Random numbers
//		Random r = new Random();
//		 int num = 10000;
//		 int random = r.nextInt(num);
		
	//Read Test script data from Excel Sheet
		String LastName= eLib.getDataFromExcel("contact", 1, 2)+ javaLib.getRandomNumber();
		
	
		
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
		
		driver.findElement(By.name("button")).click();
		
	//verify Page header msg Expected Result
		WebElement ele = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		String actualData = ele.getText();
		System.out.println(actualData);
		if(actualData.contains(LastName)) {
			System.out.println("Data is virified");
		}
		else {
			System.out.println("Varification Fail");
		}
		
	//Verify Header LastName info  in contacts Expected Result
		 String actOrgName= driver.findElement(By.id("dtlview_Last Name")).getText();
		 if(actOrgName.contains(LastName)) {
			 System.out.println(LastName+" is created ==pass");
		 }
		 else
			 System.out.println(LastName+" is not created== fail");
		
		 
	 
		
	// step 5: Logout
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[@class='small' and @valign='bottom']")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
	}
		
	

}

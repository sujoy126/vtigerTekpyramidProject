package com.concast.crm.orgTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

public class CreateOrgWithPhoneNumber {
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("./configAppData/commonData.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String BROWSER= pobj.getProperty("Browser");
		String URL= pobj.getProperty("Url");
		String USERNAME= pobj.getProperty("UserName");
		String PASSWORD= pobj.getProperty("Password");
		
		
		
		// Random numbers
		Random r = new Random();
		 int num = 10000;
		 int random = r.nextInt(num);
		
	//Read Test script data from Excel Sheet
		FileInputStream Fis = new FileInputStream("./testData/testScriptData.xlsx");
		Workbook book = WorkbookFactory.create(Fis);
		Sheet sh =book.getSheet("org");
		 String OrganizationName= sh.getRow(7).getCell(2).toString()+random;
		 
		 DataFormatter df = new DataFormatter();
		 String PhoneNumber= df.formatCellValue(sh.getRow(7).getCell(3));
		 
		 
		 
		book.close();
		
	
		
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
		
	//step2 : navigate to organization module	
		driver.findElement(By.linkText("Organizations")).click();
	
	//step 3: create Organization	
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		

	// step 4 : Enter All the details in  New Organization
		
		 driver.findElement(By.className("detailedViewTextBox")).sendKeys(OrganizationName);
		
		
		driver.findElement(By.id("phone")).sendKeys(PhoneNumber);
		
		
		driver.findElement(By.name("button")).click();
		
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
		
		 
		//Verify the phone number	 
		 String actPhonenumber= driver.findElement(By.id("dtlview_Phone")).getText();
		 if(actPhonenumber.equals(PhoneNumber)) {
			 System.out.println(PhoneNumber+" is verified ==pass");
		 }
		 else
			 System.out.println(PhoneNumber+" is not verified== fail");
		 
		 
		 
		
	// step 5: Logout
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[@class='small' and @valign='bottom']")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
	}
	

}

package practice.testNg;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderWithAmazonTest {
	@Test(dataProvider = "getData")
	public void getProductInfo(String branName, String productName) throws Throwable {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(branName, Keys.ENTER);
		
		
		// capture the price of product 
		
		
		Thread.sleep(2000);
		String path="//span[contains(.,'"+productName+"')]/ancestor::div[@class='puisg-col-inner']/descendant::span[@class='a-price-whole']";
		WebElement we =  driver.findElement(By.xpath(path));
		
		String price =  we.getText();
		System.out.println(price);
		
		driver.quit();
		
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] objArr = new Object[3][2];
		objArr[0][0] ="iphone";
		objArr[0][1] ="iPhone 15 (128 GB) - Black";
		
		objArr[1][0] ="iphone";
		objArr[1][1] ="iPhone 13 (128GB) - Starlight";
		
		objArr[2][0] ="iphone";
		objArr[2][1] ="iPhone 14 (128 GB) - Midnight";
		
			
		return objArr;
		
	}
	
	
	
	
	
	
	

}

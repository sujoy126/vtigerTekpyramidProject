package practice.testNg;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class DataProviderwithUtilityTest {

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
	public Object[][] getData() throws Throwable {
		
		ExcelUtility eLib = new ExcelUtility();
		int rowCount = eLib.geRowCount("product");
		Object[][] objArr = new Object[rowCount][2];
		
		for(int i=0; i<rowCount; i++) {
		objArr[i][0] =eLib.getDataFromExcel("product", i+1, 0);
		objArr[i][1] =eLib.getDataFromExcel("product", i+1, 1);
				
		}
			
		return objArr;
		
	}
}

package com.comcast.crm.baseTest;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.JsonUtility;
import com.comcast.crm.generic.fileutility.PropertyFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	/* Object Creation */
	public PropertyFileUtility pLib = new PropertyFileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JsonUtility jLib = new JsonUtility();
	public JavaUtility javaLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public DataBaseUtility dLib = new DataBaseUtility();

	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	@BeforeSuite(groups = { "SmokeTest", "RegressionTest" })
	public void configBeforeSuit() throws SQLException {
		System.out.println("====connect to DB, Report Config====");
		dLib.getConnection();

	}

//	@Parameters("BROWSER")
	@BeforeClass(groups = { "SmokeTest", "RegressionTest" })
	public void configBeforeClass(/* String browser */) throws Throwable {
		System.out.println("===Launch Browser===");

		// String BROWSER = browser;

		String BROWSER = pLib.getDataFromPropertiesFile("Browser");
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}
		sdriver = driver;
	}

	@BeforeMethod(groups = { "SmokeTest", "RegressionTest" })
	public void configBM() throws Throwable {
		System.out.println("==Login==");
		LoginPage lp = new LoginPage(driver);
		String URL = pLib.getDataFromPropertiesFile("Url");
		String USERNAME = pLib.getDataFromPropertiesFile("UserName");
		String PASSWORD = pLib.getDataFromPropertiesFile("Password");
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod(groups = { "SmokeTest", "RegressionTest" })
	public void createAM() {
		System.out.println("==LogOut==");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

	@AfterClass(groups = { "SmokeTest", "RegressionTest" })
	public void configAfterClass() {
		System.out.println("===Close The Browser===");
		driver.quit();
	}

	@AfterSuite(groups = { "SmokeTest", "RegressionTest" })
	public void configAfterSuit() throws SQLException {
		System.out.println("====close to DB, Report Backup====");
		dLib.closeDataBaseConnection();
	}

}

/*
 * 
 * // Spark report configuration spark = new
 * ExtentSparkReporter("./AdvanceReport/report.html");
 * spark.config().setDocumentTitle("Vtiger application testing");
 * spark.config().setReportName("Sujoy MOndal");
 * spark.config().setTheme(Theme.DARK);
 * 
 * //add env info & create test report = new ExtentReports();
 * report.attachReporter(spark); report.setSystemInfo("Operating System",
 * "windows-10"); report.setSystemInfo("BROWSER", "Chrome driver-100");
 * 
 */

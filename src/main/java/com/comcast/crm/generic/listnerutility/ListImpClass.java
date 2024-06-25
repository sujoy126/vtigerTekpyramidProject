package com.comcast.crm.generic.listnerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.google.common.io.Files;

public class ListImpClass implements ITestListener, ISuiteListener {

	public JavaUtility ju = new JavaUtility();
	public ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
	public static ExtentReports report;
	public static ExtentTest test;

	public void onStart(ISuite suite) { // configuration of Extends report
		String tesCaseName = suite.getName();
		// Spark report configuration
		String path = "./AdvanceReport/report" + tesCaseName + ju.generateDate() + ju.generateTime() + ".html";
		spark = new ExtentSparkReporter(path);
		spark.config().setDocumentTitle("Vtiger application testing");
		spark.config().setReportName("Funcanality check ");
		spark.config().setTheme(Theme.DARK);

		// add env info & create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Operating System", "windows-10");
		report.setSystemInfo("BROWSER", "Chrome driver-108");
	}

	public void onTestStart(ITestResult result) {

//		System.out.println("===  ======>"+ result.getMethod().getMethodName()+">===start==");
		String methodName = result.getName();
		test = report.createTest(methodName);
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, methodName + "====> started <====");

	}

	public void onTestSuccess(ITestResult result) {
//		System.out.println("===  ======>"+ result.getMethod().getMethodName()+">===end==");
		String testCaseName = result.getName();
		Reporter.log("Test case Executed Sucessfully " + testCaseName, true);
		test.log(Status.PASS, testCaseName + " Test case Executed Sucessfully ");

	}

	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String filePath = ts.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filePath, testName + "_" + time);
		test.log(Status.FAIL, testName + " Test case Failed ");
	}

	public void onTestSkipped(ITestResult result) {
		String testCaseName = result.getName();
		Reporter.log("Test Case Skipped " + testCaseName);
		test.log(Status.SKIP, testCaseName + " Skipped test case named ");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onFinish(ISuite suite) {
		System.out.println("Report BackUp");
		report.flush(); // storing the result
	}
}

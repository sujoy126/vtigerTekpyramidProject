package practice.testNg;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;

public class ForRetryAnalyser extends BaseClass {
	@Test(retryAnalyzer = com.comcast.crm.generic.listnerutility.RetryAnalyserClass.class)
	public void createInvoiceTest() {
		System.out.println("ExecuteCreateInvoiceTest");
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "asldkf");
		 System.out.println("step-1");
		 System.out.println("step-2");
		 System.out.println("step-3");
		 System.out.println("step-4");

		}

}

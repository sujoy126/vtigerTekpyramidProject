package practice.testNg;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;

//@Listeners(com.comcast.crm.generic.listnerutility.ListImpClass.class)
public class InvoiceTest  extends BaseClass {
	@Test
public void createInvoiceTest() {
	System.out.println("ExecuteCreateInvoiceTest");
	String actTitle = driver.getTitle();
	Assert.assertEquals(actTitle, "asldkf");
	 System.out.println("step-1");
	 System.out.println("step-2");
	 System.out.println("step-3");
	 System.out.println("step-4");

	}
	
	@Test
	public void createInvoiceWithContactactTest() {
		System.out.println("Excute createInvoiceWithContactactTest ");
		 System.out.println("step-1");
		 System.out.println("step-2");
		 System.out.println("step-3");
		 System.out.println("step-4");
	}


}
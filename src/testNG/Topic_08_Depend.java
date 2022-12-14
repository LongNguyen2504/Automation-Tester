package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_08_Depend {
	//TC02,03,04 must followed by TC_01 due to logical create -> view,update,edit
	//Real project  casually use dependsOnMethods than dependsOnGroups
	//By running this class,we have 3 status of test case : sucess,fail,skipped(TC_02,03,04 were skipped due to dependencies on TC_01)
	@Test
	public void TC_01_Create() {
		Assert.assertTrue(false);//this is intentionally set false to demo dependencies for following test cases
	}
	@Test(dependsOnMethods = "TC_01_Create") 
	public void TC_02_View() {
		Assert.assertTrue(false);//this is intentionally set false to demo dependencies for following test cases
	}

	@Test(dependsOnMethods = "TC_01_Create")
	public void TC_03_Update() {
		Assert.assertTrue(false);//this is intentionally set false to demo dependencies for following test cases
	}

	@Test(dependsOnMethods = "TC_01_Create")
	public void TC_04_Delelte() {
		Assert.assertTrue(false);//this is intentionally set false to demo dependencies for following test cases
	}


}

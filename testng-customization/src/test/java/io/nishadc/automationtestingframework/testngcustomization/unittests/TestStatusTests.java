package io.nishadc.automationtestingframework.testngcustomization.unittests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestStatusTests {
	
	@Test(enabled=true)
	public void pass_test() {

	}
	
	@Test(enabled=false,dataProvider="failData")
	public void fail_test(int value) {
		Assertions.assertThat(value).isLessThan(10);
	}
	
	@Test(enabled=false,dependsOnMethods= {"fail_test"})
	public void skip_test() {

	}
	
	@Test(enabled=true,successPercentage=50,dataProvider="data")
	public void passWithinSuccessPercentage_test(int value) {
		Assertions.assertThat(value).isLessThan(10);
	}
	
	@Test(enabled=false,timeOut=1000)
	public void timeout_test() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	@DataProvider(name="data")
	public Object[][] data() {
		return new Object[][] {{1},{2},{3},{11}};
	}
	
	@DataProvider(name="failData")
	public Object[][] failData() {
		return new Object[][] {{11}};
	}
}

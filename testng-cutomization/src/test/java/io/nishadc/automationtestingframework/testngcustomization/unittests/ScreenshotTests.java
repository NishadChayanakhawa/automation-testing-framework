package io.nishadc.automationtestingframework.testngcustomization.unittests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class ScreenshotTests {
	
	private static ThreadLocal<WebDriver> drivers=new ThreadLocal<>();
	
	@BeforeMethod
	public void setupTest() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new");
		WebDriver driver=WebDriverManager.chromedriver().capabilities(options).create();
		driver.get("https://www.selenium.dev/");
		ScreenshotTests.drivers.set(driver);
	}
	
	@AfterMethod
	public void tearDownTest() {
		WebDriver driver=ScreenshotTests.drivers.get();
		if(driver!=null) {
			driver.quit();
		}
		ScreenshotTests.drivers.remove();
	}
	
	@Test
	public void test_noDriver() {
		TestFactory.recordTest("Test with no driver");
		TestFactory.recordTestStep("Screenshot not attached", true);
	}
	
	@Test
	public void test_withDriver() {
		TestFactory.recordTest("Test with driver",ScreenshotTests.drivers.get());
		TestFactory.recordTestStep("Screenshot attached", true);
		TestFactory.recordTestStep("Screenshot not attached as flag is false", false);
		TestFactory.recordTestStep("Screenshot not attached as flag is false by default");
	}
	
	@Test(dataProvider="failData",successPercentage=50)
	public void autoScreenshot_withDriver(int value) {
		TestFactory.recordTest("Automatic screenshot with driver",ScreenshotTests.drivers.get());
		Assertions.assertThat(value).isLessThan(10);
	}
	
	@Test(dataProvider="failData",successPercentage=50)
	public void autoScreenshot_withoutDriver(int value) {
		TestFactory.recordTest("Automatic screenshot without driver");
		Assertions.assertThat(value).isLessThan(10);
	}
	
	@DataProvider(name="failData")
	public Object[][] failData() {
		return new Object[][] {{1},{5},{11}};
	}
}

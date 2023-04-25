package io.nishadc.automationtestingframework.testinginterface.webui.unittests;

import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseWebTest {
protected static ThreadLocal<WebDriver> drivers=new ThreadLocal<>();
	
	@BeforeMethod
	public void setupTest() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new");
		WebDriver driver=WebDriverManager.chromedriver().capabilities(options).create();
		BaseWebTest.drivers.set(driver);
	}
	
	@AfterMethod
	public void tearDownTest() {
		WebDriver driver=BaseWebTest.drivers.get();
		if(driver!=null) {
			driver.quit();
		}
		BaseWebTest.drivers.remove();
	}
	
	protected static String getTestPageUrl(String fileName) {
		String fileFullPath=String.format("src/test/resources/TestPages/%s.html", fileName);
		return String.format("file:///%s", Paths.get(fileFullPath).toAbsolutePath());
	}
}

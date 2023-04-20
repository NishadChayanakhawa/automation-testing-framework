package io.nishadc.automationtestingframework.testngcustomization.process;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHandling {
	private ScreenshotHandling() {
		
	}
	
	public static String takeBase64Screenshot(WebDriver driver) {
		TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
		return takesScreenshot.getScreenshotAs(OutputType.BASE64);
	}
}

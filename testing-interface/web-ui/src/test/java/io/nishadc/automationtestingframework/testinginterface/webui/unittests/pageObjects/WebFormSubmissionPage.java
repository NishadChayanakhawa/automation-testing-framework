package io.nishadc.automationtestingframework.testinginterface.webui.unittests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import io.nishadc.automationtestingframework.testinginterface.webui.ApplicationActions;

public class WebFormSubmissionPage extends ApplicationActions {
	
	private static final String MESSAGE_XPATH=
			"//h1[@class='display-6' and text()='Form submitted']";
	
	public WebFormSubmissionPage(WebDriver driver, int timeout) {
		super(driver, timeout);
		PageFactory.initElements(driver, this);
	}
	
	

}

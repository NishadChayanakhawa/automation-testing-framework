package io.nishadc.automationtestingframework.testinginterface.webui.unittests.pageObjects;

import org.openqa.selenium.WebDriver;

import io.nishadc.automationtestingframework.testinginterface.webui.ApplicationActions;

public class IndexPage extends ApplicationActions {

	private static final String WEB_FORM_BUTTON_XPATH="//a[text()='Web form']";
	
	public IndexPage(WebDriver driver, int timeout) {
		super(driver, timeout);
	}

	public void goToWebForm() {
		this.clickElement(WEB_FORM_BUTTON_XPATH);
	}
	
	public void goToWebFormAndRecordStep() {
		this.clickElement(WEB_FORM_BUTTON_XPATH,"Web Form navigation button");
	}
}

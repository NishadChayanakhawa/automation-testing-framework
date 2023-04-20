package io.nishadc.automationtestingframework.testinginterface.webui.unittests.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.nishadc.automationtestingframework.testinginterface.webui.ApplicationActions;

public class WebFormPage extends ApplicationActions {
	private static final String TEXT_INPUT_XPATH="//input[@id='my-text-id']";

	public WebFormPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@type='submit' and text()='Submit']")
	WebElement submitButton;
	
	@FindBy(xpath="//a[contains(text(),'Return to index')]")
	WebElement backToIndex;
	
	@FindBy(xpath=TEXT_INPUT_XPATH)
	WebElement textInput;
	
	public void submitForm() {
		super.clickElement(submitButton);
	}
	
	public void backToIndex() {
		super.clickElement(backToIndex, "Back to Index button");
	}
	
	public void updateTextInput() {
		super.sendText(textInput, "first");
		super.sendText(textInput, "Input", "Text Input textbox");
		super.sendText(TEXT_INPUT_XPATH, "Is");
		super.sendText(TEXT_INPUT_XPATH, "Complete", "Text Input textbox");
	}
	
	public void clearTextInputAndUpdate1() {
		super.clearValue(textInput);
		super.sendText(textInput, "1");
	}
	
	public void clearTextInputAndUpdate2() {
		super.clearValue(TEXT_INPUT_XPATH);
		super.sendText(textInput, "2");
	}
	
	public void clearTextInputAndUpdate3() {
		super.clearValue(textInput,"Input Text");
		super.sendText(textInput, "3");
	}
	
	public void clearTextInputAndUpdate4() {
		super.clearValue(TEXT_INPUT_XPATH,"Input text");
		super.sendText(textInput, "4");
	}
	
	public String getTextInputValue() {
		return super.getValue(textInput);
	}
	
	public String getTextInputValueByXPath() {
		return super.getValue(TEXT_INPUT_XPATH);
	}
	
}

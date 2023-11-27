package io.nishadc.automationtestingframework.testinginterface.webui.unittests.pageObjects;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.nishadc.automationtestingframework.testinginterface.webui.ApplicationActions;

public class ClickTestPage extends ApplicationActions {
	private static final String VALUE_XPATH="//input[@id='value']";
	private static final String BUTTON_XPATH="//button[@id='button']";
	private static final String DISABLED_XPATH="//input[@id='disabled']";
	private static final String INNER_TEXT_XPATH="//div[@id='innerTextTest']";
	private static final String HIDDEN_XPATH="//div[@id='hiddenTest']";
	private static final String SINGLE_DROPDOWN_XPATH="//select[@id='singleSelect']";
	private static final String MULTI_DROPDOWN_XPATH="//select[@id='multiSelect']";
	private static final String FORM_XPATH="//form[@id='submissionForm']";
	
	@FindBy(xpath=FORM_XPATH)
	WebElement formElement;
	
	@FindBy(xpath=VALUE_XPATH)
	WebElement valueElement;
	
	@FindBy(xpath=BUTTON_XPATH)
	WebElement buttonElement;
	
	@FindBy(xpath=DISABLED_XPATH)
	WebElement disabled;
	
	@FindBy(xpath=SINGLE_DROPDOWN_XPATH)
	WebElement singleDropdownElement;
	
	@FindBy(xpath=INNER_TEXT_XPATH)
	WebElement innerText;
	
	@FindBy(xpath=HIDDEN_XPATH)
	WebElement hidden;
	
	@FindBy(xpath=MULTI_DROPDOWN_XPATH)
	WebElement multiDropdownElement;
	
	public ClickTestPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public ClickTestPage(WebDriver driver,int timeOut) {
		super(driver,10);
		PageFactory.initElements(driver, this);
	}
	
	public void performClickThroughElement() {
		super.clickElement(buttonElement);
	}
	
	public void performClickThroughXPath() {
		super.clickElement(BUTTON_XPATH);
	}
	
	public void performClickThroughElementAndRecordStep() {
		super.clickElement(buttonElement,"Increment");
	}
	
	public void performClickThroughXPathAndRecordStep() {
		super.clickElement(BUTTON_XPATH,"Increment");
	}
	
	public String getValueThroughXPath() {
		return super.getValue(VALUE_XPATH);
	}
	
	public String getValueThroughElement() {
		return super.getValue(valueElement);
	}
	
	public void sendTextThroughElement(String text) {
		super.sendText(valueElement, text);
	}
	
	public void sendTextThroughXPath(String text) {
		super.sendText(VALUE_XPATH, text);
	}
	
	public void sendTextThroughElementAndRecord(String text) {
		super.sendText(valueElement, text,"Textbox");
	}
	
	public void sendTextThroughXPathAndRecord(String text) {
		super.sendText(VALUE_XPATH, text,"Textbox");
	}
	
	public void clearTextThroughElement() {
		super.clearValue(valueElement);
	}
	
	public void clearTextThroughXPath() {
		super.clearValue(VALUE_XPATH);
	}
	
	public void clearTextThroughElementAndRecord() {
		super.clearValue(valueElement,"Textbox");
	}
	
	public void clearTextThroughXPathAndRecord() {
		super.clearValue(VALUE_XPATH,"Textbox");
	}
	
	public String getSingleDropdownValue() {
		return super.getValue(singleDropdownElement);
	}
	
	public String getMultiDropdownValue() {
		return super.getValue(multiDropdownElement);
	}
	
	public void selectOneByElement() {
		super.selectDropDownByVisibleText(singleDropdownElement, "One");
	}
	
	public void selectTwoByXPath() {
		super.selectDropDownByVisibleText(SINGLE_DROPDOWN_XPATH, "Two");
	}
	
	public void selectThreeByElementAndRecordSteps() {
		super.selectDropDownByVisibleText(singleDropdownElement, "Three","Single Drop Down");
	}
	
	public void selectTwoByXPathAndRecordSteps() {
		super.selectDropDownByVisibleText(SINGLE_DROPDOWN_XPATH, "Two","Single Drop Down");
	}
	
	public void selectOneByElementValue() {
		super.selectDropDownByValue(singleDropdownElement, "1");
	}
	
	public void selectTwoByElementValueAndRecord() {
		super.selectDropDownByValue(singleDropdownElement, "2","Single Drop Down");
	}
	
	public void selectThreeByXPathValue() {
		super.selectDropDownByValue(SINGLE_DROPDOWN_XPATH, "3");
	}
	
	public void selectThreeByXPathValueAndRecord() {
		super.selectDropDownByValue(SINGLE_DROPDOWN_XPATH, "3");
	}
	
	public void multiSelect() {
		super.selectDropDownByVisibleText(multiDropdownElement, "One");
		super.selectDropDownByVisibleText(multiDropdownElement, "Two");
	}
	
	public void removeOneSelection() {
		super.deselectDropDownByVisibleText(multiDropdownElement, "One");
	}
	
	public void removeOneSelectionByXPath() {
		super.deselectDropDownByVisibleText(MULTI_DROPDOWN_XPATH, "One");
	}
	
	public void removeOneSelectionAndRecord() {
		super.deselectDropDownByVisibleText(multiDropdownElement, "One","Multi Select dropdown");
	}
	
	public void removeOneSelectionByXPathAndRecord() {
		super.deselectDropDownByVisibleText(MULTI_DROPDOWN_XPATH, "One","Multi Select dropdown");
	}
	
	public void remove2Selection() {
		super.deselectDropDownByValue(multiDropdownElement, "2");
	}
	
	public void remove2SelectionByXPath() {
		super.deselectDropDownByValue(MULTI_DROPDOWN_XPATH, "2");
	}
	
	public void remove2SelectionAndRecord() {
		super.deselectDropDownByValue(multiDropdownElement, "2","Multi Select dropdown");
	}
	
	public void remove2SelectionByXPathAndRecord() {
		super.deselectDropDownByValue(MULTI_DROPDOWN_XPATH, "2","Multi Select dropdown");
	}
	
	public void select3AndRecord() {
		super.selectDropDownByValue(SINGLE_DROPDOWN_XPATH, "3", "Single Drop Down");
	}
	
	public String getInnerTextByElement() {
		return super.getInnerText(innerText);
	}
	
	public String getInnerTextByXPath() {
		return super.getInnerText(INNER_TEXT_XPATH);
	}
	
	public String getClassByElement() {
		return super.getAttributeValue(innerText, "class");
	}
	
	public String getClassByXPath() {
		return super.getAttributeValue(INNER_TEXT_XPATH,"class");
	}
	
	public boolean isDisplayedByXPath() {
		return super.isDisplayed(INNER_TEXT_XPATH);
	}
	
	public boolean isDisplayedByElement() {
		return super.isDisplayed(hidden);
	}
	
	public boolean isEnabledByXPath() {
		return super.isEnabled(BUTTON_XPATH);
	}
	
	public boolean isEnabledByElement() {
		return super.isEnabled(disabled);
	}
	
	public void fillForm(Map<String,Object> values) {
		this.fillForm(this.formElement,values);
	}
}

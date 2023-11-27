package io.nishadc.automationtestingframework.testinginterface.webui;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.nishadc.automationtestingframework.logging.LoggerFactory;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

/**
 * <b>Class Name</b>: ApplicationActions.java<br>
 * <b>Description</b>: Provides an interface to work with Web UI application<br>
 * 
 * @author Nishad Chayanakhawa&lt;nishad.chayanakhawa@gmail.com&gt;
 *
 */
public class ApplicationActions {
	private static final Logger logger = LoggerFactory.create(ApplicationActions.class);
	private static final int TIMEOUT = 10;
	protected WebDriver driver;
	protected WebDriverWait driverWait;

	protected ApplicationActions(WebDriver driver) {
		this(driver, ApplicationActions.TIMEOUT);
	}

	protected ApplicationActions(WebDriver driver, int timeout) {
		ApplicationActions.logger.debug("Setting up driver with timeout {}", timeout);
		this.driver = driver;
		this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	}

	protected void waitForElementToBeInteractable(WebElement element) {
		this.driverWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/*
	 * Click interactions
	 */
	protected void clickElement(WebElement element) {
		this.waitForElementToBeInteractable(element);
		ApplicationActions.logger.debug("Clicking on webelement {}", element);
		element.click();
	}

	protected void clickElement(WebElement element, String elementName) {
		this.clickElement(element);
		TestFactory.recordTestStep(String.format("Clicked on %s", elementName));
	}

	protected void clickElement(String elementXPath) {
		ApplicationActions.logger.debug("Clicking on webelement identified by XPath {}", elementXPath);
		WebElement element = driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXPath)));
		this.clickElement(element);
	}

	protected void clickElement(String elementXPath, String elementName) {
		this.clickElement(elementXPath);
		TestFactory.recordTestStep(String.format("Clicked on %s", elementName));
	}

	/*
	 * GetInner Text
	 */

	protected String getInnerText(WebElement element) {
		this.waitForElementToBeInteractable(element);
		ApplicationActions.logger.debug("Extracting inner text for webelement {}", element);
		String innerText = element.getText();
		ApplicationActions.logger.debug("Extracted inner text {}", innerText);
		return innerText;
	}

	protected String getInnerText(String elementXPath) {
		ApplicationActions.logger.debug("Extracting inner text for webelement identified by XPath {}", elementXPath);
		WebElement element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		return this.getInnerText(element);
	}

	/*
	 * Get Attribute
	 */

	protected String getAttributeValue(WebElement element, String attributeName) {
		ApplicationActions.logger.debug("Extracting value of attribute {} for webelement {}", attributeName, element);
		String attributeValue = element.getAttribute(attributeName);
		ApplicationActions.logger.debug("Extracted inner text {}", attributeValue);
		return attributeValue;
	}

	protected String getAttributeValue(String elementXPath, String attributeName) {
		ApplicationActions.logger.debug("Extracting value of attribute {} for webelement identified by XPath {}",
				attributeName, elementXPath);
		WebElement element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		return this.getAttributeValue(element, attributeName);
	}

	/*
	 * Is Displayed
	 */

	protected boolean isDisplayed(WebElement element) {
		ApplicationActions.logger.debug("Checking if webelement {} is displayed", element);
		boolean isDisplayed = element.isDisplayed();
		ApplicationActions.logger.debug("Is Displayed: {}", isDisplayed);
		return isDisplayed;
	}

	protected boolean isDisplayed(String elementXPath) {
		ApplicationActions.logger.debug("Checking if webelement identified by XPath {} is displayed", elementXPath);
		WebElement element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		return this.isDisplayed(element);
	}

	/*
	 * Is Enabled
	 */

	protected boolean isEnabled(WebElement element) {
		ApplicationActions.logger.debug("Checking if webelement {} is enabled", element);
		boolean isEnabled = element.isEnabled();
		ApplicationActions.logger.debug("Is Enabled: {}", isEnabled);
		return isEnabled;
	}

	protected boolean isEnabled(String elementXPath) {
		ApplicationActions.logger.debug("Checking if webelement identified by XPath {} is enabled", elementXPath);
		WebElement element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		return this.isEnabled(element);
	}

	/*
	 * Send Text
	 */

	protected void sendText(WebElement element, String text) {
		this.waitForElementToBeInteractable(element);
		ApplicationActions.logger.debug("Sending text {} to webelement {}", text, element);
		element.sendKeys(text);
	}

	protected void sendText(WebElement element, String text, String elementName) {
		this.sendText(element, text);
		TestFactory.recordTestStep(String.format("Sending text %s to %s", text, elementName));
	}

	protected void sendText(String elementXPath, String text) {
		ApplicationActions.logger.debug("Sending text {} to webelement identified by XPath {}", text, elementXPath);
		WebElement element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		this.sendText(element, text);
	}

	protected void sendText(String elementXPath, String text, String elementName) {
		this.sendText(elementXPath, text);
		TestFactory.recordTestStep(String.format("Sending text %s to %s", text, elementName));
	}

	/*
	 * Read Text input
	 */

	protected String getValue(WebElement element) {
		ApplicationActions.logger.debug("Extracting value from webelement {}", element);
		String text = element.getAttribute("value");
		ApplicationActions.logger.debug("Value found as {}", text);
		return text;
	}

	protected String getValue(String elementXPath) {
		ApplicationActions.logger.debug("Extracting value from webelement identified by XPath {}", elementXPath);
		WebElement element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		return this.getValue(element);
	}

	/*
	 * Clear Text
	 */

	protected void clearValue(WebElement element) {
		this.waitForElementToBeInteractable(element);
		ApplicationActions.logger.debug("Clearing text value from webelement {}", element);
		element.clear();
	}

	protected void clearValue(String elementXPath) {
		ApplicationActions.logger.debug("Clearing text value from webelement identified by XPath {}", elementXPath);
		WebElement element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		this.clearValue(element);
	}

	protected void clearValue(WebElement element, String elementName) {
		this.clearValue(element);
		TestFactory.recordTestStep(String.format("Clearing value from %s", elementName));
	}

	protected void clearValue(String elementXPath, String elementName) {
		this.clearValue(elementXPath);
		TestFactory.recordTestStep(String.format("Clearing value from %s", elementName));
	}

	/*
	 * Drop down selection
	 */

	protected void selectDropDownByVisibleText(WebElement element, String visibleTextToSelect) {
		ApplicationActions.logger.debug("Selecting drop down value text {} for element {}", visibleTextToSelect,
				element);
		Select dropDownElement = new Select(element);
		dropDownElement.selectByVisibleText(visibleTextToSelect);
	}

	protected void selectDropDownByVisibleText(WebElement element, String visibleTextToSelect, String elementName) {
		this.selectDropDownByVisibleText(element, visibleTextToSelect);
		TestFactory.recordTestStep(String.format("Selected text %s for %s", visibleTextToSelect, elementName));
	}

	protected void selectDropDownByValue(WebElement element, String valueToSelect) {
		ApplicationActions.logger.debug("Selecting drop down value value {} for element {}", valueToSelect, element);
		Select dropDownElement = new Select(element);
		dropDownElement.selectByValue(valueToSelect);
	}

	protected void selectDropDownByValue(WebElement element, String valueToSelect, String elementName) {
		this.selectDropDownByValue(element, valueToSelect);
		TestFactory.recordTestStep(String.format("Selected value %s for %s", valueToSelect, elementName));
	}

	protected void deselectDropDownByVisibleText(WebElement element, String visibleTextToDeselect) {
		ApplicationActions.logger.debug("Deselecting drop down value text {} for element {}", visibleTextToDeselect,
				element);
		Select dropDownElement = new Select(element);
		dropDownElement.deselectByVisibleText(visibleTextToDeselect);
	}

	protected void deselectDropDownByVisibleText(WebElement element, String visibleTextToDeselect, String elementName) {
		this.deselectDropDownByVisibleText(element, visibleTextToDeselect);
		TestFactory.recordTestStep(String.format("Deselected text %s for %s", visibleTextToDeselect, elementName));
	}

	protected void deselectDropDownByValue(WebElement element, String visibleTextToDeselect) {
		ApplicationActions.logger.debug("Deselecting drop down value value {} for element {}", visibleTextToDeselect,
				element);
		Select dropDownElement = new Select(element);
		dropDownElement.deselectByValue(visibleTextToDeselect);
	}

	protected void deselectDropDownByValue(WebElement element, String visibleTextToDeselect, String elementName) {
		this.deselectDropDownByValue(element, visibleTextToDeselect);
		TestFactory.recordTestStep(String.format("Deslected value %s for %s", visibleTextToDeselect, elementName));
	}

	protected void selectDropDownByVisibleText(String elementXPath, String visibleTextToSelect) {
		ApplicationActions.logger.debug("Selecting drop down value text {} for element identified by XPath {}",
				visibleTextToSelect, elementXPath);
		WebElement element = this.driverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		this.selectDropDownByVisibleText(element, visibleTextToSelect);
	}

	protected void selectDropDownByVisibleText(String elementXPath, String visibleTextToSelect, String elementName) {
		this.selectDropDownByVisibleText(elementXPath, visibleTextToSelect);
		TestFactory.recordTestStep(String.format("Selected text %s for %s", visibleTextToSelect, elementName));
	}

	protected void selectDropDownByValue(String elementXPath, String valueToSelect) {
		ApplicationActions.logger.debug("Selecting drop down value value {} for element identified by XPath {}",
				valueToSelect, elementXPath);
		WebElement element = this.driverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		this.selectDropDownByValue(element, valueToSelect);
	}

	protected void selectDropDownByValue(String elementXPath, String valueToSelect, String elementName) {
		this.selectDropDownByValue(elementXPath, valueToSelect);
		TestFactory.recordTestStep(String.format("Selected value %s for element %s", valueToSelect, elementName));
	}

	protected void deselectDropDownByVisibleText(String elementXPath, String visibleTextToDeselect) {
		ApplicationActions.logger.debug("Deselecting drop down value text {} for element {}", visibleTextToDeselect,
				elementXPath);
		WebElement element = this.driverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		this.deselectDropDownByVisibleText(element, visibleTextToDeselect);
	}

	protected void deselectDropDownByVisibleText(String elementXPath, String visibleTextToDeselect,
			String elementName) {
		this.deselectDropDownByVisibleText(elementXPath, visibleTextToDeselect);
		TestFactory.recordTestStep(String.format("Deselected text %s for %s", visibleTextToDeselect, elementName));
	}

	protected void deselectDropDownByValue(String elementXPath, String visibleTextToDeselect) {
		ApplicationActions.logger.debug("Deselecting drop down value value {} for element {}", visibleTextToDeselect,
				elementXPath);
		WebElement element = this.driverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		this.deselectDropDownByValue(element, visibleTextToDeselect);
	}

	protected void deselectDropDownByValue(String elementXPath, String visibleTextToDeselect, String elementName) {
		this.deselectDropDownByValue(elementXPath, visibleTextToDeselect);
		TestFactory.recordTestStep(String.format("Deslected value %s for %s", visibleTextToDeselect, elementName));
	}

	/**
	 * Form fill-up
	 */

	protected void fillForm(WebElement formElement, Map<String, Object> formValues) {
		this.waitForElementToBeInteractable(formElement);
		for (Entry<String, Object> formValueEntry : formValues.entrySet()) {
			String elementName=formValueEntry.getKey();
			Object elementValue=formValueEntry.getValue();
			WebElement element = formElement.findElement(By.name(elementName));
			switch (element.getTagName().toUpperCase()) {
			case "INPUT":
				switch (element.getAttribute("type").toUpperCase()) {
				case "TEXT":
					this.clearValue(element);
					this.sendText(element, elementValue.toString(), elementName);
					break;
				}
				break;
			case "SELECT":
				if(elementValue instanceof List) {
					@SuppressWarnings("unchecked")
					List<String> valuesToSelect=(List<String>)elementValue;
					for(String value : valuesToSelect) {
						this.selectDropDownByValue(element, value);
					}
				} else {
					this.selectDropDownByValue(element, formValueEntry.getValue().toString());
				}
				break;
			}
		}
		WebElement submitButton = formElement.findElement(By.xpath("//button[@type='submit']"));
		this.clickElement(submitButton, "Submit");
	}
}

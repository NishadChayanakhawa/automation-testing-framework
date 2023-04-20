package io.nishadc.automationtestingframework.testinginterface.webui;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.nishadc.automationtestingframework.logging.LoggerFactory;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

/**
 * <b>Class Name</b>: ApplicationActions.java<br>
 * <b>Description</b>: Provides an interface to work with Web UI application<br>
 * @author Nishad Chayanakhawa&lt;nishad.chayanakhawa@gmail.com&gt;
 *
 */
public class ApplicationActions {
	private static final Logger logger = LoggerFactory.create(ApplicationActions.class);
	private static final int TIMEOUT=10;
	protected WebDriver driver;
	protected WebDriverWait driverWait;
	
	protected ApplicationActions(WebDriver driver) {
		this(driver,ApplicationActions.TIMEOUT);
	}
	protected ApplicationActions(WebDriver driver,int timeout) {
		ApplicationActions.logger.debug("Setting up driver with timeout {}",timeout);
		this.driver=driver;
		this.driverWait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
	}
	
	
	/*
	 * Click interactions
	 */
	protected void clickElement(WebElement element) {
		ApplicationActions.logger.debug("Clicking on webelement {}",element);
		driverWait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	protected void clickElement(WebElement element,String elementName) {
		this.clickElement(element);
		TestFactory.recordTestStep(String.format("Clicked on %s", elementName));
	}
	
	protected void clickElement(String elementXPath) {
		ApplicationActions.logger.debug("Clicking on webelement identified by XPath {}",elementXPath);
		WebElement element=driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXPath)));
		element.click();
	}
	
	protected void clickElement(String elementXPath,String elementName) {
		this.clickElement(elementXPath);
		TestFactory.recordTestStep(String.format("Clicked on %s", elementName));
	}
	
	/*
	 * Send Text
	 */
	
	protected void sendText(WebElement element, String text) {
		ApplicationActions.logger.debug("Sending text {} to webelement {}",text,element);
		driverWait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(text);
	}
	
	protected void sendText(WebElement element, String text,String elementName) {
		this.sendText(element, text);
		TestFactory.recordTestStep(String.format("Sending text %s to %s", text,elementName));
	}
	
	protected void sendText(String elementXPath, String text) {
		ApplicationActions.logger.debug("Sending text {} to webelement identified by XPath {}",text,elementXPath);
		WebElement element=driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		element.sendKeys(text);
	}
	
	protected void sendText(String elementXPath, String text,String elementName) {
		this.sendText(elementXPath, text);
		TestFactory.recordTestStep(String.format("Sending text %s to %s", text,elementName));
	}
	
	/*
	 * Read Text input
	 */

	protected String getValue(WebElement element) {
		ApplicationActions.logger.debug("Extracting text from webelement {}",element);
		driverWait.until(ExpectedConditions.visibilityOf(element));
		String text=element.getAttribute("value");
		ApplicationActions.logger.debug("Text found as {}",text);
		return text;
	}
	
	protected String getValue(String elementXPath) {
		ApplicationActions.logger.debug("Extracting text from webelement identified by XPath {}",elementXPath);
		WebElement element=driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		String text=element.getAttribute("value");
		ApplicationActions.logger.debug("Text found as {}",text);
		return text;
	}
	
	/*
	 * Clear Text
	 */
	
	protected void clearValue(WebElement element) {
		ApplicationActions.logger.debug("Clearing text value from webelement {}",element);
		driverWait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
	}
	
	protected void clearValue(String elementXPath) {
		ApplicationActions.logger.debug("Clearing text value from webelement identified by XPath {}",elementXPath);
		WebElement element=driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPath)));
		element.clear();
	}
	
	protected void clearValue(WebElement element,String elementName) {
		this.clearValue(element);
		TestFactory.recordTestStep(String.format("Clearing value from %s", elementName));
	}
	
	protected void clearValue(String elementXPath,String elementName) {
		this.clearValue(elementXPath);
		TestFactory.recordTestStep(String.format("Clearing value from %s", elementName));
	}
}

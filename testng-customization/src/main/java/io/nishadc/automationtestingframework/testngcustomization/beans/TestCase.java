package io.nishadc.automationtestingframework.testngcustomization.beans;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.nishadc.automationtestingframework.testngcustomization.process.DateTimeHelper;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonInclude(Include.NON_NULL)
public class TestCase extends JsonPropertyBaseClass{
	@JsonProperty
	private String testNGSuiteName;
	private String testNGTestName;
	private String name;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM/dd/yyyy HH:mm:ss")
	private Date startTimestamp;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM/dd/yyyy HH:mm:ss")
	private Date endTimestamp;
	private String elapsedTime;
	private TestStatus status;
	private List<TestStep> testSteps;
	private boolean passedDuringRetry;
	@JsonIgnore
	private WebDriver driver;
	public TestCase() {
		super();
		this.startTimestamp=new Date();
		this.testSteps=new ArrayList<>();
	}
	public TestCase(String testNGSuiteName, String testNGTestName, String name) {
		this();
		this.testNGSuiteName = testNGSuiteName;
		this.testNGTestName = testNGTestName;
		this.name = name;
	}
	public String getTestNGSuiteName() {
		return testNGSuiteName;
	}
	public void setTestNGSuiteName(String testNGSuiteName) {
		this.testNGSuiteName = testNGSuiteName;
	}
	public String getTestNGTestName() {
		return testNGTestName;
	}
	public void setTestNGTestName(String testNGTestName) {
		this.testNGTestName = testNGTestName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	public Date getEndTimestamp() {
		return endTimestamp;
	}
	public void setEndTimestamp(Date endTimestamp) {
		this.endTimestamp = endTimestamp;
	}
	public String getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public TestStatus getStatus() {
		return status;
	}
	public void setStatus(TestStatus status) {
		this.status = status;
	}
	public List<TestStep> getTestSteps() {
		return testSteps;
	}
	public void setTestSteps(List<TestStep> testSteps) {
		this.testSteps = testSteps;
	}
	public boolean isPassedDuringRetry() {
		return passedDuringRetry;
	}
	public void setPassedDuringRetry(boolean passedDuringRetry) {
		this.passedDuringRetry = passedDuringRetry;
	}
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void addTestStep(String stepText) {
		this.addTestStep(stepText, TestStatus.INFO,null);
	}
	public void addTestStep(String stepText,String base64Screenshot) {
		this.addTestStep(stepText, TestStatus.INFO,base64Screenshot);
	}
	public void addTestStep(String stepText,TestStatus stepStatus) {
		this.addTestStep(stepText, stepStatus,null);
	}
	public void addTestStep(String stepText,TestStatus stepStatus,String base64Screenshot) {
		TestStep testStep=new TestStep(stepText,stepStatus,base64Screenshot);
		this.testSteps.add(testStep);
	}
	public void endTest(TestStatus status) {
		this.endTimestamp=new Date();
		this.elapsedTime=DateTimeHelper.getElapsedTime(this.startTimestamp, this.endTimestamp);
		this.status=status;
	}
	public boolean isDriverAvailable() {
		return this.driver!=null;
	}	
}

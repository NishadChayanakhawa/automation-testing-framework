package io.nishadc.automationtestingframework.testngcustomization.beans;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TestSet extends JsonPropertyBaseClass{
	@JsonProperty
	private String suiteName;
	private String name;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM/dd/yyyy HH:mm:ss")
	private LocalDateTime startTimestamp;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM/dd/yyyy HH:mm:ss")
	private LocalDateTime endTimestamp;
	private String elapsedTime;
	private int totalTests;
	private int passedTests;
	private int conditionallyPassedTests;
	private int failedTests;
	private int skippedTests;
	private List<TestCase> tests;
	public TestSet() {
		super();
		this.tests=new ArrayList<>();
	}
	public TestSet(String suiteName, String name) {
		this();
		this.suiteName = suiteName;
		this.name = name;
	}
	public String getSuiteName() {
		return suiteName;
	}
	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(LocalDateTime startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	public LocalDateTime getEndTimestamp() {
		return endTimestamp;
	}
	public void setEndTimestamp(LocalDateTime endTimestamp) {
		this.endTimestamp = endTimestamp;
	}
	public String getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public int getTotalTests() {
		return totalTests;
	}
	public void setTotalTests(int totalTests) {
		this.totalTests = totalTests;
	}
	public int getPassedTests() {
		return passedTests;
	}
	public void setPassedTests(int passedTests) {
		this.passedTests = passedTests;
	}
	public int getFailedTests() {
		return failedTests;
	}
	public void setFailedTests(int failedTests) {
		this.failedTests = failedTests;
	}
	public int getSkippedTests() {
		return skippedTests;
	}
	public void setSkippedTests(int skippedTests) {
		this.skippedTests = skippedTests;
	}
	public List<TestCase> getTests() {
		return tests;
	}
	public void setTests(List<TestCase> tests) {
		this.tests = tests;
	}
	public int getConditionallyPassedTests() {
		return conditionallyPassedTests;
	}
	public void setConditionallyPassedTests(int conditionallyPassedTests) {
		this.conditionallyPassedTests = conditionallyPassedTests;
	}
	public void addTest(TestCase test) {
		this.tests.add(test);
	}
}

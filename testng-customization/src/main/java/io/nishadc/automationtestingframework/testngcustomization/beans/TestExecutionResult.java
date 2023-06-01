package io.nishadc.automationtestingframework.testngcustomization.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import java.util.ArrayList;

@JsonInclude(Include.NON_NULL)
public class TestExecutionResult extends JsonPropertyBaseClass{
	@JsonProperty
	private List<TestSet> testSets;
	private int totalTests;
	private int passedTests;
	private int conditionallyPassedTests;
	private int failedTests;
	private int skippedTests;
	private String actualTime;
	private String savedTime;
	private String actualTimeText;
	private String savedTimeText;
	private String savingPercent;
	
	public TestExecutionResult() {
		super();
		this.testSets=new ArrayList<>();
	}

	public List<TestSet> getTestSets() {
		return testSets;
	}

	public void setTestSets(List<TestSet> testSets) {
		this.testSets = testSets;
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

	public int getConditionallyPassedTests() {
		return conditionallyPassedTests;
	}

	public void setConditionallyPassedTests(int conditionallyPassedTests) {
		this.conditionallyPassedTests = conditionallyPassedTests;
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

	public String getActualTime() {
		return actualTime;
	}

	public void setActualTime(String actualTime) {
		this.actualTime = actualTime;
	}

	public String getSavedTime() {
		return savedTime;
	}

	public void setSavedTime(String savedTime) {
		this.savedTime = savedTime;
	}

	public String getActualTimeText() {
		return actualTimeText;
	}

	public void setActualTimeText(String actualTimeText) {
		this.actualTimeText = actualTimeText;
	}

	public String getSavedTimeText() {
		return savedTimeText;
	}

	public void setSavedTimeText(String savedTimeText) {
		this.savedTimeText = savedTimeText;
	}

	public String getSavingPercent() {
		return savingPercent;
	}

	public void setSavingPercent(String savingPercent) {
		this.savingPercent = savingPercent;
	}
}

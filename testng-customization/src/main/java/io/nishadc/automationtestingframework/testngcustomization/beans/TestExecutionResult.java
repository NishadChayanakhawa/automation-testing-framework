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
}

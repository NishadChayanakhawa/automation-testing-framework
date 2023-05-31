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
}

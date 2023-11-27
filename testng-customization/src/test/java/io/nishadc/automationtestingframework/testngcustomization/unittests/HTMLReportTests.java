package io.nishadc.automationtestingframework.testngcustomization.unittests;

import org.asynchttpclient.util.Assertions;
import org.testng.annotations.Test;

import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

public class HTMLReportTests {
	@Test
	public void recordedTest_noSteps_test() {
		TestFactory.recordTest("Recorded Test with no steps and test name is very very very very very very very ...................................... very long");
	}
	
	@Test
	public void recordedTest_withSteps_test() {
		TestFactory.recordTest("Recorded Test with steps");
		TestFactory.recordTestStep("Step w/o flag");
		TestFactory.recordTestStep("Step with false flag",false);
		TestFactory.recordTestStep("Step with true flag",true);
	}
}

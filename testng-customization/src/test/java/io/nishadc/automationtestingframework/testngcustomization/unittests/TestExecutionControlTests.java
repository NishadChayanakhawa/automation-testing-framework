package io.nishadc.automationtestingframework.testngcustomization.unittests;

import org.testng.annotations.Test;

import io.nishadc.automationtestingframework.testngcustomization.annotations.HealthCheck;
import io.nishadc.automationtestingframework.testngcustomization.annotations.RegressionTest;
import io.nishadc.automationtestingframework.testngcustomization.annotations.SmokeTest;

public class TestExecutionControlTests {
	@Test
	public void notAnnotedTest() {
		
	}
	
	@HealthCheck
	@Test
	public void healthCheckTest() {
		
	}
	
	@SmokeTest
	@Test
	public void smokeTest() {
		
	}
	
	@RegressionTest
	@Test
	public void regressionTest() {
		
	}
}

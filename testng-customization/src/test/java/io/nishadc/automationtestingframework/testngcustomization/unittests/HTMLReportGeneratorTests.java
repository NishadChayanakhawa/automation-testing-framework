package io.nishadc.automationtestingframework.testngcustomization.unittests;

import org.testng.annotations.Test;

import io.nishadc.automationtestingframework.testngcustomization.process.HTMLReportGenerator;

public class HTMLReportGeneratorTests {
	@Test
	public void invalidTemplate() {
		HTMLReportGenerator.generateHTMLReport("invalidTemplate", null, "Invalid");
	}
}

package io.nishadc.automationtestingframework.testinginterface.soapapi.stepdefinitions.unittests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/Features",
		glue = "io.nishadc.automationtestingframework.testinginterface.soapapi.stepdefinitions")
public class SOAPAPIBDDTests extends AbstractTestNGCucumberTests{
	
}

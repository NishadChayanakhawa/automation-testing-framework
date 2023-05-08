package io.nishadc.automationtestingframework.testinginterface.soapapi.stepdefinitions;

import org.apache.logging.log4j.Logger;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.nishadc.automationtestingframework.logging.LoggerFactory;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

public class HookSteps {
	private static final Logger logger=LoggerFactory.create(HookSteps.class);
	@Before
	public void setupBddTest(Scenario scenario) throws IllegalArgumentException {
		String scnerioName=scenario.getName();
		HookSteps.logger.debug("Strated scenario {}",scnerioName);
		TestFactory.recordTest(scnerioName);
	}
	
	@After
	public void tearDownBddTest(Scenario scenario) {
		String scnerioName=scenario.getName();
		HookSteps.logger.debug("Completed scenario {}",scnerioName);
		SOAPAPIComponents.releaseComponents();
	}
}

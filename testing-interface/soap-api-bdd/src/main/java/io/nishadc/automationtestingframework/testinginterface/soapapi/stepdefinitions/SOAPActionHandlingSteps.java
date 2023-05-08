package io.nishadc.automationtestingframework.testinginterface.soapapi.stepdefinitions;

import io.cucumber.java.en.Given;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

public class SOAPActionHandlingSteps {
	@Given("SOAP Action is {string}")
	public void soap_action_is(String soapAction) {
		SOAPAPIComponents.setSoapAction(soapAction);
		TestFactory.recordTestStep(String.format("SOAP Action is <b>%s</b>", soapAction));
	}
}

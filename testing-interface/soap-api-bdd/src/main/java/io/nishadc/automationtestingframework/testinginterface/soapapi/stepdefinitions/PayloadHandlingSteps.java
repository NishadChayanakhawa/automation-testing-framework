package io.nishadc.automationtestingframework.testinginterface.soapapi.stepdefinitions;

import io.cucumber.java.en.Given;
import io.nishadc.automationtestingframework.filehandling.FlatFileHandling;
import io.nishadc.automationtestingframework.filehandling.exceptions.FlatFileHandlingException;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

public class PayloadHandlingSteps {
	private static final String PAYLOAD_FILE_LOCATION="src/test/resources/PayloadTemplates/";
	
	@Given("Payload template is loaded from file {string}")
	public void payload_template_is_loaded_from_file(String payloadFileName) throws FlatFileHandlingException {
	    String payload=FlatFileHandling.getFileContents(PAYLOAD_FILE_LOCATION + payloadFileName);
	    SOAPAPIComponents.setPayload(payload);
	    TestFactory.recordTestStep(String.format("Payload template is loaded from file <b>%s</b>", payloadFileName));
	}
	
	@Given("In Payload template, replace {string} with {string}")
	public void in_payload_template_replace_with(String placeholder, String actualValue) {
		SOAPAPIComponents.setPayload(SOAPAPIComponents.payloads.get().replace(placeholder, actualValue));
		TestFactory.recordTestStep(String.format("In Payload template, replace <b>%s</b> with <b>%s</b>", placeholder, actualValue));
	}
}

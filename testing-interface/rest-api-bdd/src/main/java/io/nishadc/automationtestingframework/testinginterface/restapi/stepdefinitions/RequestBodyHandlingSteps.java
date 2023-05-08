package io.nishadc.automationtestingframework.testinginterface.restapi.stepdefinitions;

import io.cucumber.java.en.Given;
import io.nishadc.automationtestingframework.filehandling.FlatFileHandling;
import io.nishadc.automationtestingframework.filehandling.exceptions.FlatFileHandlingException;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

public class RequestBodyHandlingSteps {
	private static final String REQUEST_BODY_TEMPLATE_FILE_LOCATION=
			"src/test/resources/RequestBodyTemplates/";
	
	@Given("Request body template is loaded from file {string}")
	public void request_body_template_is_loaded_from_file(String requestBodyTemplateFileName) throws FlatFileHandlingException {
		String requestBodyTemplate=FlatFileHandling.getFileContents
				(REQUEST_BODY_TEMPLATE_FILE_LOCATION + requestBodyTemplateFileName);
		RESTAPIComponents.setRequestBodyTemplate(requestBodyTemplate);
		TestFactory.recordTestStep(String.format("Request body template is loaded from file <b>%s</b>", requestBodyTemplateFileName));
	}
	@Given("In request body template, replace {string} with {string}")
	public void in_request_body_template_replace_with(String placeholder, String actualValue) {
		RESTAPIComponents.setRequestBodyTemplate
			(RESTAPIComponents.requestBodyTemplates.get().replace(placeholder, actualValue));
		TestFactory.recordTestStep(String.format("In request body template, replace <b>%s</b> with <b>%s</b>",
				placeholder, actualValue));
	}
}

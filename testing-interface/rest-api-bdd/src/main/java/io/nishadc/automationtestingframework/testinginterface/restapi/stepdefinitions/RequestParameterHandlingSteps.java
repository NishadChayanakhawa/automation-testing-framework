package io.nishadc.automationtestingframework.testinginterface.restapi.stepdefinitions;

import java.util.Map;

import io.cucumber.java.en.Given;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

public class RequestParameterHandlingSteps {
	@Given("In request parameter, set {string} to {string}")
	public void as_request_parameter_set_to(String parameterName, String parameterValue) {
		RESTAPIComponents.addParameter(parameterName, parameterValue);
		TestFactory.recordTestStep(String.format("In request parameter, set <b>%s</b> to <b>%s</b>", parameterName, parameterValue));
	}
	@Given("Set request parameters from following table")
	public void set_request_parameters_from_following_table(io.cucumber.datatable.DataTable dataTable) {
		for(Map<String,String> dataTableRow : dataTable.asMaps()) {
			this.as_request_parameter_set_to(
					dataTableRow.get("Parameter Name"), dataTableRow.get("Parameter Value"));
		}
	}
}

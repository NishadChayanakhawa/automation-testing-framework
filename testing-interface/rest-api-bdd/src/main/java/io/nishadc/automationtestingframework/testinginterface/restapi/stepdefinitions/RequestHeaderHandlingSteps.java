package io.nishadc.automationtestingframework.testinginterface.restapi.stepdefinitions;

import java.util.Map;

import io.cucumber.java.en.Given;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

public class RequestHeaderHandlingSteps {
	
	@Given("In request header, set {string} to {string}")
	public void in_request_header_set_to(String headerName, String headerValue) {
		RESTAPIComponents.addHeader(headerName, headerValue);
		TestFactory.recordTestStep(String.format("In request header, set <b>%s</b> to <b>%s</b>", headerName, headerValue));
	}
	
	@Given("Set request headers from following table")
	public void set_request_headers_as_from_following_table(io.cucumber.datatable.DataTable dataTable) {
		for(Map<String,String> dataTableRow : dataTable.asMaps()) {
			this.in_request_header_set_to(
					dataTableRow.get("Header Name"), dataTableRow.get("Header Value"));
		}
	}
}

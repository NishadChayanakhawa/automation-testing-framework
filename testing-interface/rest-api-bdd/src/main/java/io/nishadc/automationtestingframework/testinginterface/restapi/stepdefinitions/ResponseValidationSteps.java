package io.nishadc.automationtestingframework.testinginterface.restapi.stepdefinitions;

import io.cucumber.java.en.Then;
import io.nishadc.automationtestingframework.testinginterface.restapi.RESTAPITestHelper;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;
import java.util.Map;
import java.util.HashMap;
public class ResponseValidationSteps {
	@Then("Response status code should be {int}")
	public void response_status_code_should_be(Integer expectedStatusCode) {
		TestFactory.recordTestStep(
				String.format("Response status code should be <b>%d</b>", expectedStatusCode));
	    RESTAPITestHelper.validateResponse
	    	(RESTAPIComponents.responses.get(),
	    			expectedStatusCode,null);
	}
	
	@Then("Validate that value at Json Path {string} in response is {string}")
	public void value_at_json_path_should_be(String jsonPath, String expectedValue) {
		TestFactory.recordTestStep(
				String.format("Validate that value at Json Path <b>%s</b> in response is <b>%s</b>", jsonPath,expectedValue));
		Map<String,Object> validationMap=new HashMap<>();
		validationMap.put(jsonPath, expectedValue);
		RESTAPITestHelper.validateResponse
    	(RESTAPIComponents.responses.get(),
    			0,validationMap);
	}
	
	@Then("Validate response body from following table")
	public void validate_response_body_from_following_table(io.cucumber.datatable.DataTable dataTable) {
		Map<String,Object> validationMap=new HashMap<>();
		String testStep="Validate response body from following table" + 
				"<br><table class='w3-small w3-table'><tr><th>Json Path</th><th>Expected Value</th></tr>";
		for(Map<String,String> dataTableRow : dataTable.asMaps()) {
			String jsonPath=dataTableRow.get("Json Path");
			String expectedValue=dataTableRow.get("Expected Value");
			testStep=String.format("%s<tr><td>%s</td><td>%s</td></tr>",testStep,jsonPath,expectedValue);
			validationMap.put(jsonPath,expectedValue);
		}
		testStep=String.format("%s</table>",testStep);
		TestFactory.recordTestStep(testStep);
		RESTAPITestHelper.validateResponse
    	(RESTAPIComponents.responses.get(),
    			0,validationMap);
	}
}

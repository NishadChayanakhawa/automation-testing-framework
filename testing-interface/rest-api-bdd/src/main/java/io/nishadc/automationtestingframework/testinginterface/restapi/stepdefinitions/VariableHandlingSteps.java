package io.nishadc.automationtestingframework.testinginterface.restapi.stepdefinitions;

import io.cucumber.java.en.Then;
import io.nishadc.automationtestingframework.testinginterface.restapi.RESTAPITestHelper;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

public class VariableHandlingSteps {
	@Then("Save value at Json Path {string} in response, to variable {string}")
	public void save_value_at_json_path_in_response_to_variable(String jsonPath, String variableName) {
		String variableValue=RESTAPITestHelper.query(
				RESTAPIComponents.responses.get(), jsonPath).toString();
		TestFactory.recordTestStep(
				String.format("Save value at Json Path <b>%s</b> in response, to variable <b>%s</b>. Value: %s",
						jsonPath, variableName,variableValue));
		this.set_variable_to_value(variableName, variableValue);
	}
	
	@Then("Set variable {string} to value {string}")
	public void set_variable_to_value(String variableName, String variableValue) {
		RESTAPIComponents.addVariable(variableName, variableValue);
		TestFactory.recordTestStep(
				String.format("Set variable <b>%s</b> to value <b>%s</b>.",
						variableName,variableValue));
		
	}
	@Then("Append {string} to value of variable {string}")
	public void append_to_value_of_variable(String valueToAppend, String variableName) {
		RESTAPIComponents.addVariable(variableName, RESTAPIComponents.getVariableValue(variableName) + valueToAppend);
		TestFactory.recordTestStep(
				String.format("Append <b>%s</b> to value of variable <b>%s</b>.",
						valueToAppend,variableName));
	}
	@Then("Prefix {string} to value of variable {string}")
	public void prefix_to_value_of_variable(String valueToPrefix, String variableName) {
		RESTAPIComponents.addVariable(variableName, valueToPrefix + RESTAPIComponents.getVariableValue(variableName));
		TestFactory.recordTestStep(
				String.format("Prefix <b>%s</b> to value of variable <b>%s</b>.",
						valueToPrefix,variableName));
	}
}

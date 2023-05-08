package io.nishadc.automationtestingframework.testinginterface.restapi.stepdefinitions;

import org.json.JSONObject;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.When;
import io.nishadc.automationtestingframework.testinginterface.restapi.RESTAPITestHelper;
import io.nishadc.automationtestingframework.testinginterface.restapi.RESTAPITestHelper.RequestMethod;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class RequestProcessingSteps {
	@ParameterType("(GET|POST|PUT|DELETE|PATCH|OPTIONS)")
	public RequestMethod method(String requestMethodString) {
		return RequestMethod.valueOf(requestMethodString);
	}
	
	@When("{method} request is submitted to {string}")
	public void request_is_submitted_to(RequestMethod requestMethod, String url) {
		TestFactory.recordTestStep(
				String.format("<b>%s</b> request is submitted to <b>%s</b>", requestMethod, url));
		JSONObject requestBody=RESTAPIComponents.requestBodyTemplates.get()==null?
				null:new JSONObject(RESTAPIComponents.requestBodyTemplates.get());
	    RequestSpecification request=RESTAPITestHelper.formRequest
	    		(RESTAPIComponents.headerMaps.get(),
	    				RESTAPIComponents.parameterMaps.get(),
	    				requestBody);
	    ValidatableResponse response=RESTAPITestHelper.getRespones(request, url, requestMethod);
	    RESTAPIComponents.responses.set(response);
	}
}

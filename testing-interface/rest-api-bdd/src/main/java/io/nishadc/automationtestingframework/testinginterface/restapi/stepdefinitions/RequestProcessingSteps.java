package io.nishadc.automationtestingframework.testinginterface.restapi.stepdefinitions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String fullUrl=formUrl(url);
		TestFactory.recordTestStep(String.format("<b>%s</b> request is submitted to <b>%s</b>", requestMethod, fullUrl));
		JSONObject requestBody = RESTAPIComponents.requestBodyTemplates.get() == null ? null
				: new JSONObject(formUrl(RESTAPIComponents.requestBodyTemplates.get()));
		RequestSpecification request = RESTAPITestHelper.formRequest(RESTAPIComponents.headerMaps.get(),
				RESTAPIComponents.parameterMaps.get(), requestBody);
		ValidatableResponse response = RESTAPITestHelper.getRespones(request, fullUrl, requestMethod);
		RESTAPIComponents.responses.set(response);
	}

	private String formUrl(String url) {
		String patternToMatch = "\\{\\{(.*?)}}";

		Pattern pattern = Pattern.compile(patternToMatch);
		Matcher matcher = pattern.matcher(url);
		StringBuffer result = new StringBuffer();
		while (matcher.find()) {
			String key = matcher.group(1); // Extract the key within {{}}
            String replacement = RESTAPIComponents.variableMap.getOrDefault(key, matcher.group()); // Use the map value or keep the original if not found
            matcher.appendReplacement(result, replacement);
		}
		matcher.appendTail(result);
		System.out.println("replacement: " + result.toString() );
		return result.toString();
	}
}

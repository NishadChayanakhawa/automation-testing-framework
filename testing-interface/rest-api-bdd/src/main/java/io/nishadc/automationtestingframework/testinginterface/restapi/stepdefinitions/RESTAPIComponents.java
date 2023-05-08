package io.nishadc.automationtestingframework.testinginterface.restapi.stepdefinitions;

import java.util.Map;
import java.util.HashMap;
import io.restassured.response.ValidatableResponse;

public class RESTAPIComponents {
	protected static ThreadLocal<Map<String, String>> headerMaps = new ThreadLocal<>();
	protected static ThreadLocal<Map<String, String>> parameterMaps = new ThreadLocal<>();
	protected static ThreadLocal<ValidatableResponse> responses=new ThreadLocal<>();
	protected static ThreadLocal<String> requestBodyTemplates=new ThreadLocal<>();
	protected static Map<String, String> variableMap = new HashMap<>();
	
	private RESTAPIComponents() {
		
	}

	protected static void addHeader(String headerName, String headerValue) {
		if (RESTAPIComponents.headerMaps.get() == null) {
			RESTAPIComponents.headerMaps.set(new HashMap<>());
		}
		RESTAPIComponents.headerMaps.get().put(headerName, headerValue);
	}
	
	protected static void addParameter(String parameterName, String parameterValue) {
		if (RESTAPIComponents.parameterMaps.get() == null) {
			RESTAPIComponents.parameterMaps.set(new HashMap<>());
		}
		RESTAPIComponents.parameterMaps.get().put(parameterName, parameterValue);
	}
	
	protected static void setRequestBodyTemplate(String requestBodyTemplate) {
		RESTAPIComponents.requestBodyTemplates.set(requestBodyTemplate);
	}
	
	protected static synchronized void addVariable(String variableName,String variableValue) {
		RESTAPIComponents.variableMap.put(variableName, variableValue);
	}
	
	protected static synchronized String getVariableValue(String variableName) {
		return RESTAPIComponents.variableMap.getOrDefault(variableName,"");
	}

	protected static void releaseComponents() {
		RESTAPIComponents.headerMaps.remove();
		RESTAPIComponents.parameterMaps.remove();
		RESTAPIComponents.responses.remove();
	}
}

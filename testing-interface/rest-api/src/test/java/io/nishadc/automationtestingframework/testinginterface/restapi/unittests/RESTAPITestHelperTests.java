package io.nishadc.automationtestingframework.testinginterface.restapi.unittests;

import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

import java.util.Map;
import java.util.HashMap;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import io.nishadc.automationtestingframework.testinginterface.restapi.RESTAPITestHelper;
import io.nishadc.automationtestingframework.testinginterface.restapi.RESTAPITestHelper.RequestMethod;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class RESTAPITestHelperTests {
	@SuppressWarnings("serial")
	private static final Map<String,String> headersMap=new HashMap<String, String>() {{
	    put("content-type", "application/json; charset=utf-8");
	}};
	@SuppressWarnings("serial")
	private static final Map<String,String> parametersMap=new HashMap<String, String>() {{
		put("Parameter1", "Value1");
		put("Parameter2", "Value2");
	}};
	private static final JSONObject requestBody=new JSONObject("{'id':1,'name':'Jane Doe'}");
	
	@Test
	public void requestWithParams_test() {
		TestFactory.recordTest("Request with parameters");
		
		RequestSpecification request=RESTAPITestHelper.formRequest(headersMap, parametersMap, null);
		
		ValidatableResponse response=RESTAPITestHelper.getRespones(request, "https://postman-echo.com/get/", RequestMethod.GET);
		
		Map<String,Object> validationsMap=new HashMap<String, Object>();
		validationsMap.put("args.Parameter1", "Value1");
		validationsMap.put("args.Parameter2", "*");
		validationsMap.put("headers.x-forwarded-proto", "http%%");
		
		RESTAPITestHelper.validateResponse(response, 200, validationsMap);
	}
	
	@Test
	public void requestWithBody_test() {
		TestFactory.recordTest("Request with Body");
		
		RequestSpecification request=RESTAPITestHelper.formRequest(headersMap, null,requestBody);
		
		ValidatableResponse response=RESTAPITestHelper.getRespones(request, "https://postman-echo.com/post/", RequestMethod.POST);
		
		Map<String,Object> validationsMap=new HashMap<String, Object>();
		validationsMap.put("data.id",1);
		
		int dataId=(int) RESTAPITestHelper.query(response, "data.id");
		Assertions.assertThat(dataId).isEqualTo(1);
		
		RESTAPITestHelper.validateResponse(response, 0, validationsMap);
	}
	
	@Test
	public void putRequest_test() {
		TestFactory.recordTest("PUT Method Test");
		
		RequestSpecification request=RESTAPITestHelper.formRequest(null, null,requestBody);
		
		ValidatableResponse response=RESTAPITestHelper.getRespones(request, "https://postman-echo.com/put/", RequestMethod.PUT);
		
		RESTAPITestHelper.validateResponse(response, 200, null);
	}
	
	@Test
	public void deleteRequest_test() {
		TestFactory.recordTest("Delete Method Test");
		
		RequestSpecification request=RESTAPITestHelper.formRequest(headersMap, null,requestBody);
		
		ValidatableResponse response=RESTAPITestHelper.getRespones(request, "https://postman-echo.com/delete/", RequestMethod.DELETE);
		
		RESTAPITestHelper.validateResponse(response, 200, null);
	}
	
	@Test
	public void patchRequest_test() {
		TestFactory.recordTest("Patch Method Test");
		
		RequestSpecification request=RESTAPITestHelper.formRequest(headersMap, null,requestBody);
		
		ValidatableResponse response=RESTAPITestHelper.getRespones(request, "https://postman-echo.com/patch/", RequestMethod.PATCH);
		
		RESTAPITestHelper.validateResponse(response, 200, null);
	}
	
	@Test
	public void optionsRequest_test() {
		TestFactory.recordTest("Options Method Test");
		
		RequestSpecification request=RESTAPITestHelper.formRequest(headersMap, null,requestBody);
		
		ValidatableResponse response=RESTAPITestHelper.getRespones(request, "https://postman-echo.com/options/", RequestMethod.OPTIONS);
		
		RESTAPITestHelper.validateResponse(response, 200, null);
	}
}

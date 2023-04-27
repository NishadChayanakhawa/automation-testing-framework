package io.nishadc.automationtestingframework.testinginterface.restapi;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.json.JSONObject;

import io.nishadc.automationtestingframework.logging.LoggerFactory;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

/**
 * <b>Class Name</b>: RESTAPITestHelper<br>
 * <b>Description</b>: Provides an interface to work with REST API.<br>
 * @author Nishad Chayanakhawa&lt;nishad.chayanakhawa@gmail.com&gt;
 *
 */
public class RESTAPITestHelper {
	private static final Logger logger=LoggerFactory.create(RESTAPITestHelper.class);
	public enum RequestMethod {GET,POST,PUT,DELETE,PATCH,OPTIONS}
	
	private RESTAPITestHelper() {
		//Do Nothing
		//Object will never be instantiated and methods are expected to be used through static references
	}
	
	/**
	 * <b>Method Name</b>: formRequest<br>
	 * <b>Description</b>: Provides an interface to create Rest Assured Request Specification 
	 * based on headers, parameters and request body<br>
	 * @since v1.0.0
	 * @param headersMap Headers Map as {@link java.util.Map Map&lt;String,String&gt;}
	 * @param parametersMap Parameters Map as {@link java.util.Map Map&lt;String,String&gt;}
	 * @param requestBody Request body as {@link org.json.JSONObject JSONObject}
	 * @return Request specification as {@link io.restassured.specification.RequestSpecification RequestSpecification}
	 */
	public static RequestSpecification formRequest(
			Map<String,String> headersMap,Map<String,String> parametersMap,JSONObject requestBody) {
		RESTAPITestHelper.logger.debug("Forming request for REST API call");
		
		//create Request Specification object
		RequestSpecification request=
				RestAssured
					.given()
					.urlEncodingEnabled(false);	//url encoding is disabled
		
		//set headers if non-null
		if(headersMap!=null) {
			request.headers(headersMap);
			RESTAPITestHelper.logger.debug("Request Headers: {}",headersMap);
			TestFactory.recordTestStep(String.format("Headers: <br><textarea>%s</textarea>",new JSONObject(headersMap).toString(1)));
		} else {
			RESTAPITestHelper.logger.debug("Request Headers passed are null and hence won't be attached");
			TestFactory.recordTestStep("No Headers attached");
		}
		
		//set parameters if non-null
		if(parametersMap!=null) {
			request.params(parametersMap);
			RESTAPITestHelper.logger.debug("Request Parameters: {}",parametersMap);
			TestFactory.recordTestStep(String.format("Parameters: <br><textarea>%s</textarea>",new JSONObject(parametersMap).toString(1)));
		} else {
			RESTAPITestHelper.logger.debug("Request Parameters passed are null and hence won't be attached");
			TestFactory.recordTestStep("No Parameters attached");
		}
		
		//set request body if non-null
		if(requestBody!=null) {
			request.body(requestBody.toString());
			RESTAPITestHelper.logger.debug("Request Body: {}",requestBody);
			TestFactory.recordTestStep(String.format("Request Body: <br><textarea>%s</textarea>",requestBody.toString(1)));
		} else {
			RESTAPITestHelper.logger.debug("Request Body passed is null and hence won't be attached");
			TestFactory.recordTestStep("No Request Body attached");
		}
		
		//return request specification created
		return request;
	}
	
	/**
	 * <b>Method Name</b>: getRespones<br>
	 * <b>Description</b>: Provides an interface to get submit API request and get validatable response.<br>
	 * @since v1.0.0
	 * @param request RestAssured request specification as {@link io.restassured.specification.RequestSpecification RequestSpecification}
	 * @param url URL as {@link java.lang.String String}
	 * @param requestMethod request method as {@link io.nishadc.automationtestingframework.testinginterface.restapi.RESTAPITestHelper.RequestMethod RequestMethod}
	 * @return Validatable response as {@link io.restassured.response.ValidatableResponse ValidatableResponse}
	 */
	public static ValidatableResponse getRespones(RequestSpecification request,String url,RequestMethod requestMethod) {
		RESTAPITestHelper.logger.debug("Submitting response as {} to {}",requestMethod,url);
		ValidatableResponse response=null;
		//call restassured method based on request method type
		switch(requestMethod) {
		case DELETE:
			response=request.delete(url).then();
			break;
		case GET:
			response=request.get(url).then();
			break;
		case OPTIONS:
			response=request.options(url).then();
			break;
		case PATCH:
			response=request.patch(url).then();
			break;
		case POST:
			response=request.post(url).then();
			break;
		case PUT:
			response=request.put(url).then();
			break;
		}
		
		//log responses
		RESTAPITestHelper.logger.debug("Response Status code: {}",response.extract().statusCode());
		RESTAPITestHelper.logger.debug("Response Headers: {}",response.extract().headers());
		RESTAPITestHelper.logger.debug("Response Body: {}",response.extract().body().asPrettyString());
		
		//Add test steps
		TestFactory.recordTestStep(String.format("Response status code: %d", response.extract().statusCode()));
		TestFactory.recordTestStep(String.format("Response headers: <br><textarea>%s</textarea>", response.extract().headers()));
		TestFactory.recordTestStep(String.format("Response body: <br><textarea>%s</textarea>", response.extract().body().asPrettyString()));
		
		//return response
		return response;
	}
	
	/**
	 * <b>Method Name</b>: validateResponse<br>
	 * <b>Description</b>: Provides an interface for validating api response.<br>
	 * @since v1.0.0
	 * @param response Validatable response as {@link io.restassured.response.ValidatableResponse ValidatableResponse}
	 * @param expectedStatusCode expected status code as integer. Set to 0 to bypass status code validation
	 * @param bodyValidationMap Validations map  as {@link java.util.Map Map&lt;String,String&gt;}. Set to null to bypass response body validations.
	 */
	public static void validateResponse(ValidatableResponse response,int expectedStatusCode,Map<String,Object> bodyValidationMap) {
		RESTAPITestHelper.logger.debug("Validating response");
		
		//validate response status code
		if(expectedStatusCode>0) {
			response
				.assertThat()
					.statusCode(expectedStatusCode);
		} else {
			RESTAPITestHelper.logger.debug("Expected status code is 0, response code validation skipped.");
		}
		
		//validate response body
		if(bodyValidationMap!=null) {
			for(Entry<String,Object> bodyValidationEntry : bodyValidationMap.entrySet()) {
				String jsonPath=bodyValidationEntry.getKey();
				Object expectedValue=bodyValidationEntry.getValue();
				
				if("*".equals(expectedValue)) {
					//check for non null when expected value is *
					RESTAPITestHelper.logger.debug("Validating if jsonpath {} has non-null value",jsonPath);
					response
						.assertThat()
							.body(jsonPath, Matchers.notNullValue());
				} else if(expectedValue.toString().contains("%%")) {
					//check for wildcard string
					String expectedWildCard=expectedValue.toString().replace("%%", "");
					response
					.assertThat()
						.body(jsonPath, Matchers.containsString(expectedWildCard));
				} else {
					//check for actual object value
					RESTAPITestHelper.logger.debug("Validating if jsonpath {} has value {}",jsonPath,expectedValue);
					response
					.assertThat()
						.body(jsonPath, Matchers.equalToObject(expectedValue));
				}
			}
		}
	}
}

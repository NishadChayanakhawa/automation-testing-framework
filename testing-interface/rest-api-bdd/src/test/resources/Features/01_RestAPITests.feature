Feature: REST API Tests
  Scenario: Simple Get Test
    Given In request header, set "Parameter1" to "Value1"
    And In request header, set "Parameter2" to "Value2"
    And Set request headers from following table
	    |Header Name	|Header Value	|
	    |Parameter3		|Value3				|
	    |Parameter4		|Value4				|
	  And In request parameter, set "RParameter1" to "RValue1"
	  And In request parameter, set "RParameter2" to "RValue2"
	  And Set request parameters from following table
	  	|Parameter Name	|Parameter Value	|
	    |RParameter3		|RValue3					|
	    |RParameter4		|RValue4					|
	  When GET request is submitted to "https://postman-echo.com/get/"
	  Then Response status code should be 200
	  And Validate that value at Json Path "args.RParameter1" in response is "RValue1"
	  And Validate response body from following table
	  	|Json Path						|Expected Value		|
	  	|args.RParameter2			|RValue2					|
	  	|args.RParameter3			|RValue3					|
	  	
	 Scenario: Simple POST Test
	 	Given In request header, set "content-type" to "application/json; charset=utf-8"
	 	And Request body template is loaded from file "simpleRequestTemplate.json"
	 	And In request body template, replace "${name}" with "Jane Doe"
	 	When POST request is submitted to "https://postman-echo.com/post/"
	 	Then Response status code should be 200
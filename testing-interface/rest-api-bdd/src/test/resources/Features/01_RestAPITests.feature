Feature: REST API Tests
  Scenario: Simple Get Test
    Given In request header, set "Parameter1" to "Value1"
    And In request header, set "Parameter2" to "Value2"
    And Set request headers from following table
	    |Header Name	|Header Value	|
	    |Parameter3		|Value3				|
	    |Parameter4		|Value4				|
	  And In request parameter, set "RParameter1" to "Jane%20Doe"
	  And In request parameter, set "RParameter2" to "RValue2"
	  And Set request parameters from following table
	  	|Parameter Name	|Parameter Value													|
	    |RParameter3		|application%2Fjson%3B%20charset%3Dutf-8	|
	    |RParameter4		|RValue4																	|
	  When GET request is submitted to "https://postman-echo.com/get/"
	  Then Response status code should be 200
	  And Validate that value at Json Path "args.RParameter1" in response is "Jane Doe"
	  And Validate response body from following table
	  	|Json Path						|Expected Value										|
	  	|args.RParameter2			|RValue2													|
	  	|args.RParameter3			|application/json; charset=utf-8	|
	  And Save value at Json Path "args.RParameter3" in response, to variable "ContentType"
	  And Save value at Json Path "args.RParameter1" in response, to variable "Name"
	  And Set variable "Job" to value "Tester" 
	  And Append " Jr." to value of variable "Name"
	  And Prefix "Ms. " to value of variable "Name" 
	  	
	 Scenario: Simple POST Test
	 	Given In request header, set "content-type" to value of variable "ContentType"
	 	And Request body template is loaded from file "simpleRequestTemplate.json"
	 	And In request body template, replace "${name}" with value of variable "Name"
	 	And In request body template, replace "${job}" with value of variable "Job"
	 	When POST request is submitted to "https://postman-echo.com/post/"
	 	Then Response status code should be 200
	 	And Validate that value at Json Path "data.name" in response is "Ms. Jane Doe Jr."
	 	And Validate that value at Json Path "data.job" in response is "Tester"
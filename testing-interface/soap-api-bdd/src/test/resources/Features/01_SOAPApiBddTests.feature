Feature: SOAP API BDD Tests

  Scenario: Submit SOAP Request
    Given In request header, set "Parameter1" to "Value1"
    And Set request headers from following table
	    |Header Name	|Header Value	|
	    |Parameter3		|Value3				|
	    |Parameter4		|Value4				|
	  And SOAP Action is "Random"
	  And Payload template is loaded from file "NumberToWords.xml"
	  And In Payload template, replace "${number}" with "500"
	  When SOAP request is sent to "https://www.dataaccess.com/webservicesserver/NumberConversion.wso"
	  And Namespace Prefix is "m"
	  And Namespace URI is "http://www.dataaccess.com/webservicesserver/"
	  Then Validate that the value at XPath expression "//m:NumberToWordsResult" in the SOAP response is equal to "five hundred ".
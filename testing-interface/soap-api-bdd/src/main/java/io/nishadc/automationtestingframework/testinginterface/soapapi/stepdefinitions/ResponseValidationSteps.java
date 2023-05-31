package io.nishadc.automationtestingframework.testinginterface.soapapi.stepdefinitions;

import jakarta.xml.soap.SOAPException;
import javax.xml.xpath.XPathExpressionException;

import org.testng.Assert;
import org.w3c.dom.DOMException;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.nishadc.automationtestingframework.testinginterface.soapapi.SOAPAPITestHelper;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

public class ResponseValidationSteps {
	@When("Namespace Prefix is {string}")
	public void namespace_prefix_is(String namespacePrefix) {
		SOAPAPIComponents.setNamespacePrefix(namespacePrefix);
		TestFactory.recordTestStep(String.format("Namespace Prefix is <b>%s</b>", namespacePrefix));
	}
	@When("Namespace URI is {string}")
	public void namespace_uri_is(String namespaceUri) {
		SOAPAPIComponents.setNamespaceUri(namespaceUri);
		TestFactory.recordTestStep(String.format("Namespace URI is <b>%s</b>", namespaceUri));
	}
	@Then("Validate that the value at XPath expression {string} in the SOAP response is equal to {string}.")
	public void validate_that_the_value_at_x_path_expression_in_the_soap_response_is_equal_to(String xPath, String expectedValue) 
			throws DOMException, XPathExpressionException, SOAPException {
	    String actualValue=SOAPAPITestHelper.query
	    		(SOAPAPIComponents.soapResponseMessages.get(),
	    				SOAPAPIComponents.namespacePrefixes.get(),
	    				SOAPAPIComponents.namespaceUris.get(), xPath).item(0).getTextContent();
	    Assert.assertEquals(actualValue, expectedValue);
	    TestFactory.recordTestStep(String.format(
	    		"Validate that the value at XPath expression <b>%s</b> in the SOAP response is equal to <b>%s</b>", xPath,expectedValue));
	}
}

package io.nishadc.automationtestingframework.testinginterface.soapapi.stepdefinitions;

import java.io.IOException;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import io.cucumber.java.en.When;
import io.nishadc.automationtestingframework.testinginterface.soapapi.SOAPAPITestHelper;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

public class RequestProcessingSteps {
	@When("SOAP request is sent to {string}")
	public void soap_request_is_sent_to(String endpointUrl) throws IOException, SOAPException {
		SOAPMessage soapRequestMessage=SOAPAPITestHelper.formRequest
			(SOAPAPIComponents.headers.get(),
					SOAPAPIComponents.soapActions.get(),
					SOAPAPIComponents.payloads.get());
		SOAPMessage soapResponseMessage=SOAPAPITestHelper.getResponse(soapRequestMessage, endpointUrl);
		SOAPAPIComponents.setSoapResponseMessage(soapResponseMessage);
		TestFactory.recordTestStep(String.format("SOAP request is sent to <b>%s</b>", endpointUrl));
	}
}

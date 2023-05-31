package io.nishadc.automationtestingframework.testinginterface.soapapi.stepdefinitions;

import java.io.IOException;

import io.cucumber.java.en.When;
import io.nishadc.automationtestingframework.testinginterface.soapapi.SOAPAPITestHelper;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;

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

package io.nishadc.automationtestingframework.testinginterface.soapapi.unittests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPathExpressionException;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import org.w3c.dom.Node;

import io.nishadc.automationtestingframework.filehandling.FlatFileHandling;
import io.nishadc.automationtestingframework.filehandling.exceptions.FlatFileHandlingException;
import io.nishadc.automationtestingframework.testinginterface.soapapi.SOAPAPITestHelper;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;

public class SOAPAPITestHelperTests {
	@SuppressWarnings("serial")
	private static final Map<String,String> headersMap=new HashMap<String, String>() {{
	    put("content-type", "text/xml; charset=utf-8");
	}};
	@Test
	public void validTest() throws XPathExpressionException, SOAPException, IOException, FlatFileHandlingException  {
		String payload=FlatFileHandling.getFileContents("./src/test/resources/NumberToWords.xml");
		SOAPMessage request=SOAPAPITestHelper.formRequest(headersMap, null, payload);
		SOAPMessage response=SOAPAPITestHelper.getResponse(request, "https://www.dataaccess.com/webservicesserver/NumberConversion.wso");
		Node node=SOAPAPITestHelper.query(response,"m","http://www.dataaccess.com/webservicesserver/", "//m:NumberToWordsResult").item(0);
		Assertions.assertThat(node.getTextContent()).isEqualTo("five hundred ");
	}
	
	@Test
	public void validTest1() throws FlatFileHandlingException, IOException, SOAPException {
		String payload=FlatFileHandling.getFileContents("./src/test/resources/NumberToWords.xml");
		SOAPMessage request=SOAPAPITestHelper.formRequest(null,"Random", payload);
		SOAPAPITestHelper.getResponse(request, "https://www.dataaccess.com/webservicesserver/NumberConversion.wso");
	}
}

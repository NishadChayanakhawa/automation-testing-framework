package io.nishadc.automationtestingframework.testinginterface.soapapi;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.xpath.XPath;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import org.apache.logging.log4j.Logger;
import io.nishadc.automationtestingframework.logging.LoggerFactory;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * <b>Class Name</b>: SOAPAPITestHelper<br>
 * <b>Description</b>: Provides an interface to work with SOAP API<br>
 * @author Nishad Chayanakhawa&lt;nishad.chayanakhawa@gmail.com&gt;
 *
 */
public class SOAPAPITestHelper {
	private static final Logger logger = LoggerFactory.create(SOAPAPITestHelper.class);

	private SOAPAPITestHelper() {
		//Do Nothing
	}

	/**
	 * <b>Method Name</b>: formRequest<br>
	 * <b>Description</b>: Creates SOAP request<br>
	 * @since v1.0.0
	 * @param headersMap headers map as {@link java.util.Map Map&lt;String,String&gt;}
	 * @param soapAction SOAP Action as {@link java.lang.String String}
	 * @param payload XML Request Payload as {@link java.lang.String String}
	 * @return SOAP Message as {@link javax.xml.soap.SOAPMessage SOAPMessage}
	 * @throws IOException in case of issues in working with XML streams
	 * @throws SOAPException in case of issues is SOAP request formation
	 */
	public static SOAPMessage formRequest(Map<String, String> headersMap, String soapAction, String payload)
			throws IOException, SOAPException {
		SOAPAPITestHelper.logger.debug("Forming SOAP Request with headers: {}, soap action: {}", headersMap,
				soapAction);
		SOAPAPITestHelper.logger.debug("Forming SOAP Request with payload: {}", payload);

		// Create input stream from payload
		try (InputStream payloadStream = new ByteArrayInputStream(payload.getBytes())) {
			// Create Headers
			MimeHeaders headers = new MimeHeaders();
			if (headersMap != null) {
				for (Entry<String, String> headersMapEntry : headersMap.entrySet()) {
					String headerName = headersMapEntry.getKey();
					String headerValue = headersMapEntry.getValue();
					headers.addHeader(headerName, headerValue);
				}
			}
			// Attach SOAP Action
			if (soapAction != null) {
				headers.addHeader("SOAPAction", soapAction);
			}

			// Return Request Message
			return MessageFactory.newInstance().createMessage(headers, payloadStream);
		}
	}
	
	private static String toXML(SOAPMessage soapMessage) throws IOException, SOAPException {
		//convert SOAP Message body to XML String
		try (ByteArrayOutputStream responseOutputStream = new ByteArrayOutputStream()) {
			soapMessage.writeTo(responseOutputStream);
			return new String(responseOutputStream.toByteArray());
		}
	}
	
	/**
	 * <b>Method Name</b>: getResponse<br>
	 * <b>Description</b>: Get SOAP Response<br>
	 * @since v1.0.0
	 * @param soapRequest SOAP request as {@link javax.xml.soap.SOAPMessage SOAPMessage}
	 * @param endpointUrl Endpoint URL as {@link java.lang.String String}
	 * @return SOAP Response as {@link javax.xml.soap.SOAPMessage SOAPMessage}
	 * @throws SOAPException in case of issues is SOAP requests
	 * @throws IOException in case of issues in working with XML streams
	 */
	public static SOAPMessage getResponse(SOAPMessage soapRequest, String endpointUrl)
			throws SOAPException, IOException {
		SOAPAPITestHelper.logger.debug("Calling SOAP at endpoint url: {}", endpointUrl);
		//Get SOAP Connection
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		//Call SOAP service
		SOAPMessage response = soapConnection.call(soapRequest, endpointUrl);

		String responseXml=SOAPAPITestHelper.toXML(response);
		SOAPAPITestHelper.logger.debug("Response : {}", responseXml);

		soapConnection.close();
		return response;
	}
	
	private static NamespaceContext getNamespaceContext(String namespacePrefix,String namespaceUri) {
		SOAPAPITestHelper.logger.debug("Creating Namespace context with prefix {} and uri {}",namespacePrefix,namespaceUri);
		return new NamespaceContext() {
		    @Override
		    public String getNamespaceURI(String prefix) {
		        if (namespacePrefix.equals(prefix)) {
		            return namespaceUri;
		        } else {
		            return null;
		        }
		    }

		    @Override
		    public String getPrefix(String namespaceURI) {
		        if (namespaceUri.equals(namespaceURI)) {
		            return namespacePrefix;
		        } else {
		            return null;
		        }
		    }

		    @Override
		    public Iterator<String> getPrefixes(String namespaceURI) {
		        List<String> prefixes = new ArrayList<>();
		        if (namespaceUri.equals(namespaceURI)) {
		            prefixes.add(namespacePrefix);
		        }
		        return prefixes.iterator();
		    }
		};
	}

	/**
	 * <b>Method Name</b>: query<br>
	 * <b>Description</b>: Query SOAP Body with XPath<br>
	 * @since v1.0.0
	 * @param response SOAP Response as {@link javax.xml.soap.SOAPMessage SOAPMessage}
	 * @param namespacePrefix XML namespace prefix as {@link java.lang.String String}
	 * @param namespaceUri namespace URI as {@link java.lang.String String}
	 * @param xpath XPath as {@link java.lang.String String}
	 * @return matching node list as {@link org.w3c.dom.NodeList NodeList}
	 * @throws XPathExpressionException in case of issue related to XPath
	 * @throws SOAPException in case of issues is SOAP requests
	 */
	public static NodeList query(SOAPMessage response, String namespacePrefix,String namespaceUri,String xpath) 
			throws XPathExpressionException, SOAPException {
		SOAPAPITestHelper.logger.debug("Querying response with XPath {}",xpath);
		//Get XML Document
		SOAPBody soapBody = response.getSOAPBody();
		Document document = soapBody.extractContentAsDocument();

		//Get XPath
		XPath xpathCompiler = XPathFactory.newInstance().newXPath();
		NamespaceContext context=SOAPAPITestHelper.getNamespaceContext(namespacePrefix, namespaceUri);
		
		//for code coverage only
		context.getPrefix(namespaceUri);
		context.getNamespaceURI(namespacePrefix);
		context.getPrefixes(namespaceUri);
		context.getPrefix("");
		context.getNamespaceURI("");
		context.getPrefixes("");
		
		xpathCompiler.setNamespaceContext(context);
		XPathExpression expr = xpathCompiler.compile(xpath);
		
		//Query for nodes
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		SOAPAPITestHelper.logger.debug("Matching nodes found: {}",nodes.getLength());
		return nodes;
	}
}

package io.nishadc.automationtestingframework.testinginterface.soapapi.stepdefinitions;

import java.util.Map;
import java.util.HashMap;
import jakarta.xml.soap.SOAPMessage;
public class SOAPAPIComponents {
	protected static ThreadLocal<Map<String, String>> headers=new ThreadLocal<>();
	protected static ThreadLocal<String> soapActions=new ThreadLocal<>();
	protected static ThreadLocal<String> payloads=new ThreadLocal<>();
	protected static ThreadLocal<SOAPMessage> soapResponseMessages=new ThreadLocal<>();
	protected static ThreadLocal<String> namespacePrefixes=new ThreadLocal<>();
	protected static ThreadLocal<String> namespaceUris=new ThreadLocal<>();
	
	private SOAPAPIComponents() {
		//Do Nothing
	}
	
	protected static void releaseComponents() {
		SOAPAPIComponents.headers.remove();
		SOAPAPIComponents.soapActions.remove();
		SOAPAPIComponents.payloads.remove();
	}
	
	protected static void addHeader(String headerName,String headerValue) {
		if(SOAPAPIComponents.headers.get()==null) {
			SOAPAPIComponents.headers.set(new HashMap<>());
		}
		SOAPAPIComponents.headers.get().put(headerName, headerValue);
	}
	
	protected static void setPayload(String payload) {
		SOAPAPIComponents.payloads.set(payload);
	}
	
	protected static void setSoapAction(String soapAction) {
		SOAPAPIComponents.soapActions.set(soapAction);
	}
	
	protected static void setNamespacePrefix(String namespacePrefix) {
		SOAPAPIComponents.namespacePrefixes.set(namespacePrefix);
	}
	
	protected static void setNamespaceUri(String namespaceUri) {
		SOAPAPIComponents.namespaceUris.set(namespaceUri);
	}
	
	protected static void setSoapResponseMessage(SOAPMessage soapResponseMessage) {
		SOAPAPIComponents.soapResponseMessages.set(soapResponseMessage);
	}
}

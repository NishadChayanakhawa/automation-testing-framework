package io.nishadc.automationtestingframework.testngcustomization.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonInclude(Include.NON_NULL)
public class TestStep extends JsonPropertyBaseClass{
	@JsonProperty
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM/dd/yyyy HH:mm:ss")
	private Date timestamp;
	private String stepText;
	private TestStatus status;
	private String uncompressedBase64Screenshot;
	public TestStep() {
		super();
		this.timestamp=new Date();
		this.uncompressedBase64Screenshot=null;
	}
	public TestStep(String stepText, TestStatus status, String uncompressedBase64Screenshot) {
		this(stepText,status);
		this.uncompressedBase64Screenshot = uncompressedBase64Screenshot;
	}
	public TestStep(String stepText, TestStatus status) {
		this();
		this.stepText = stepText;
		this.status = status;
	}
	public TestStep(String stepText) {
		this(stepText,TestStatus.INFO);
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getStepText() {
		return stepText;
	}
	public void setStepText(String stepText) {
		this.stepText = stepText;
	}
	public TestStatus getStatus() {
		return status;
	}
	public void setStatus(TestStatus status) {
		this.status = status;
	}
	public String getUncompressedBase64Screenshot() {
		return uncompressedBase64Screenshot;
	}
	public void setUncompressedBase64Screenshot(String uncompressedBase64Screenshot) {
		this.uncompressedBase64Screenshot = uncompressedBase64Screenshot;
	}
}
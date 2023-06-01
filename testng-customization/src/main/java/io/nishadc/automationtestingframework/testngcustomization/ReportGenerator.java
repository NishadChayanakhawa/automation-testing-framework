package io.nishadc.automationtestingframework.testngcustomization;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import io.nishadc.automationtestingframework.testngcustomization.exceptions.ReportGenerationException;
import io.nishadc.automationtestingframework.testngcustomization.beans.TestExecutionResult;
import io.nishadc.automationtestingframework.testngcustomization.beans.TestStatus;
import io.nishadc.automationtestingframework.testngcustomization.process.HTMLReportGenerator;
import io.nishadc.automationtestingframework.testngcustomization.process.RetryAnalyzer;
import io.nishadc.automationtestingframework.logging.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReportGenerator implements ITestListener,IReporter {
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		IReporter.super.generateReport(xmlSuites, suites, outputDirectory);
		
		ReportGenerator.logger.debug("Test Results:\n{}",TestFactory.getCompletedTests());
		TestExecutionResult testExecutionReport=TestFactory.getExecutionResult();
		ReportGenerator.logger.debug("Test Results:\n{}",testExecutionReport);
		
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			objectMapper
				.writerWithDefaultPrettyPrinter()
				.writeValue(Paths.get("./target/testResults.json").toFile(), testExecutionReport);
		} catch (IOException e) {
			throw (ReportGenerationException)new ReportGenerationException(e.getMessage()).initCause(e);
		}
		
		HTMLReportGenerator.generateHTMLReport("TestExecutionReportSampleTemplate_v2.0", testExecutionReport.toMap(),"TestExecutionReport");
		HTMLReportGenerator.generateHTMLReport("MailableSummaryTemplate_v1.0", testExecutionReport.toMap(),"MailableSummary");
	}
	
	private static final Logger logger=LoggerFactory.create(ReportGenerator.class);
	
	private static String getTestSuiteName(ISuite suite) {
		return suite.getName();
	}
	
	private static String getTestSuiteName(ITestContext context) {
		return ReportGenerator.getTestSuiteName(context.getSuite());
	}
	
	private static String getTestSuiteName(ITestResult result) {
		return ReportGenerator.getTestSuiteName(result.getTestContext());
	}
	
	private static String getTestNGTestName(ITestContext context) {
		return context.getName();
	}
	
	private static String getTestNGTestName(ITestResult result) {
		return ReportGenerator.getTestNGTestName(result.getTestContext());
	}
	
	private static String getTestNGMethodName(ITestResult result) {
		return result.getMethod().getMethodName();
	}
	
	private static void recordTest(ITestResult result) {
		String testNGSuiteName=ReportGenerator.getTestSuiteName(result);
		String testNGTestName=ReportGenerator.getTestNGTestName(result);
		String testNGMethodName=ReportGenerator.getTestNGMethodName(result);
		
		TestFactory.addTest(testNGSuiteName, testNGTestName, testNGMethodName);
		
		ReportGenerator.logger.debug("Test Started: {}:{}:{}",testNGSuiteName, testNGTestName, testNGMethodName);
	}
	
	private static void completeTest(ITestResult result,TestStatus status) {
		try {
			TestFactory.completeTest(status,result.getThrowable());
		} catch(NullPointerException e) {
			ReportGenerator.recordTest(result);
			TestFactory.completeTest(status,result.getThrowable());
		}
	}
	
	/*
	 * ITestListener Overrides
	 */
	
	@Override
	public void onTestStart(ITestResult result) {
		ITestListener.super.onTestStart(result);
		ReportGenerator.recordTest(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ITestListener.super.onTestSuccess(result);
		RetryAnalyzer.resetRetryCounter();
		ReportGenerator.completeTest(result,TestStatus.PASS);
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		ITestListener.super.onTestSkipped(result);
		ReportGenerator.completeTest(result,TestStatus.SKIP);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ITestListener.super.onTestFailure(result);
		ReportGenerator.completeTest(result,TestStatus.FAIL);
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
		ReportGenerator.completeTest(result,TestStatus.CONDITIONAL_PASS);
	}
	
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		ReportGenerator.completeTest(result,TestStatus.TIMEOUT);
	}
}

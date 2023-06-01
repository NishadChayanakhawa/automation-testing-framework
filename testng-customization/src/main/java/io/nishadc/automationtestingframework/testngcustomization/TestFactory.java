package io.nishadc.automationtestingframework.testngcustomization;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import io.nishadc.automationtestingframework.testngcustomization.beans.TestCase;
import io.nishadc.automationtestingframework.testngcustomization.beans.TestSet;
import io.nishadc.automationtestingframework.testngcustomization.beans.TestExecutionResult;
import io.nishadc.automationtestingframework.testngcustomization.beans.TestStatus;
import io.nishadc.automationtestingframework.testngcustomization.process.DateTimeHelper;
import io.nishadc.automationtestingframework.testngcustomization.process.ScreenshotHandling;
import io.nishadc.automationtestingframework.logging.LoggerFactory;

public class TestFactory {
	// logger
	private static final Logger logger = LoggerFactory.create(TestFactory.class);

	private static ThreadLocal<TestCase> tests = new ThreadLocal<>();
	private static List<TestCase> completedTests = new ArrayList<>();

	private TestFactory() {
		
	}
	
	protected static void addTest(String testNGSuiteName, String testNGTestName, String name) {
		TestCase test = new TestCase(testNGSuiteName, testNGTestName, name);
		TestFactory.logger.debug("Adding Test: {}", test);
		TestFactory.tests.set(test);
	}

	/**
	 * <b>Method Name</b>: recordTest<br>
	 * <b>Description</b>: Record test step<br>
	 * @since v1.0.0
	 * @param name Test name as {@link java.lang.String String}
	 */
	public static void recordTest(String name) {
		TestFactory.recordTest(name, null);
	}

	/**
	 * <b>Method Name</b>: recordTestStep<br>
	 * <b>Description</b>: Record test step<br>
	 * @since v1.0.0
	 * @param stepText Step text as {@link java.lang.String String}
	 */
	public static void recordTestStep(String stepText) {
		TestFactory.recordTestStep(stepText, false);
	}

	/**
	 * <b>Method Name</b>: recordTestStep<br>
	 * <b>Description</b>: Record test step along with screenshot.<br>
	 * @since v1.0.0
	 * @param stepText Step text as {@link java.lang.String String}
	 * @param screenshotFlag as boolean
	 */
	public static void recordTestStep(String stepText, boolean screenshotFlag) {
		String base64Screenshot = null;
		if (screenshotFlag && TestFactory.tests.get().isDriverAvailable()) {
			base64Screenshot = ScreenshotHandling.takeBase64Screenshot(TestFactory.tests.get().getDriver());
		}
		TestFactory.tests.get().addTestStep(stepText, base64Screenshot);
	}

	/**
	 * <b>Method Name</b>: recordTest<br>
	 * <b>Description</b>: Record Test<br>
	 * @since v1.0.0
	 * @param name Test name as {@link java.lang.String String}
	 * @param driver Webdriver as {@link org.openqa.selenium.WebDriver WebDriver}
	 */
	public static void recordTest(String name, WebDriver driver) {
		TestFactory.tests.get().setDriver(driver);
		TestFactory.tests.get().setName(name);
	}

	public static void completeTest(TestStatus status, Throwable error) {
		TestCase test = TestFactory.tests.get();
		TestFactory.logger.debug("Completing Test {} with status {}", test, status);
		TestFactory.logger.info("[{}] - [{}]", test.getName(), status);
		test.endTest(status);

		if (status != TestStatus.PASS) {
			if (test.isDriverAvailable()) {
				String base64Screenshot = ScreenshotHandling.takeBase64Screenshot(test.getDriver());
				test.addTestStep(error != null ? error.getMessage() : "Step failed.", TestStatus.FAIL,
						base64Screenshot);
			} else {
				test.addTestStep(error != null ? error.getMessage() : "Step failed.", TestStatus.FAIL);
			}
		}
		
		if (test.getTestSteps().isEmpty()) {
			test.addTestStep("Test steps were not recorded. Please use TestFactory.recordTestStep function to record the same",TestStatus.WARNING);
		}

		TestFactory.tests.remove();
		TestFactory.completedTests.add(test);
	}

	protected static List<TestCase> getCompletedTests() {
		return TestFactory.completedTests;
	}

	protected static TestExecutionResult getExecutionResult() {
		Map<String, TestSet> testSets = new HashMap<>();
		for (TestCase test : TestFactory.completedTests) {
			String key = String.format("%s:%s", test.getTestNGSuiteName(), test.getTestNGTestName());
			if (testSets.keySet().contains(key)) {
				testSets.get(key).addTest(test);
			} else {
				TestSet testSet = new TestSet(test.getTestNGSuiteName(), test.getTestNGTestName());
				testSet.addTest(test);
				testSets.put(key, testSet);
			}
		}
		
		for(Map.Entry<String,TestSet> entry : testSets.entrySet()) {
			String key=entry.getKey();
			TestSet testSet=entry.getValue();
		
			int totalTestsCount=testSet.getTests().size();
			int passedTestsCount=testSet.getTests().stream()
					.filter(test -> test.getStatus()==TestStatus.PASS)
					.toList()
					.size();
			int conditionallyPassedTestsCount=testSet.getTests().stream()
					.filter(test -> test.getStatus()==TestStatus.CONDITIONAL_PASS)
					.toList()
					.size();
			int failedTestsCount=testSet.getTests().stream()
					.filter(test -> test.getStatus()==TestStatus.FAIL || test.getStatus()==TestStatus.TIMEOUT)
					.toList()
					.size();
			int skippedTestsCount=testSet.getTests().stream()
					.filter(test -> test.getStatus()==TestStatus.SKIP)
					.toList()
					.size();
			testSets.get(key).setTotalTests(totalTestsCount);
			testSets.get(key).setPassedTests(passedTestsCount);
			testSets.get(key).setConditionallyPassedTests(conditionallyPassedTestsCount);
			testSets.get(key).setFailedTests(failedTestsCount);
			testSets.get(key).setSkippedTests(skippedTestsCount);
			
			//timestamps
			LocalDateTime minimumTimestamp=testSet.getTests().stream()
					.map(TestCase :: getStartTimestamp)
					.min(LocalDateTime::compareTo)
					.orElse(LocalDateTime.now());
			LocalDateTime maximumTimestamp=testSet.getTests().stream()
					.map(TestCase :: getEndTimestamp)
					.max(LocalDateTime::compareTo)
					.orElse(LocalDateTime.now());
			testSets.get(key).setStartTimestamp(minimumTimestamp);
			testSets.get(key).setEndTimestamp(maximumTimestamp);
			testSets.get(key).setElapsedTime(DateTimeHelper.getElapsedTime(minimumTimestamp, maximumTimestamp));
		}
		TestExecutionResult testExecutionResult=new TestExecutionResult();
		testExecutionResult.setTestSets(testSets.values().stream().toList());
		
		//populate test counts
		testExecutionResult.setTotalTests(testExecutionResult.getTestSets().stream()
											.mapToInt(TestSet::getTotalTests).sum());
		testExecutionResult.setPassedTests(testExecutionResult.getTestSets().stream()
				.mapToInt(TestSet::getPassedTests).sum());
		testExecutionResult.setFailedTests(testExecutionResult.getTestSets().stream()
				.mapToInt(TestSet::getFailedTests).sum());
		testExecutionResult.setConditionallyPassedTests(testExecutionResult.getTestSets().stream()
				.mapToInt(TestSet::getConditionallyPassedTests).sum());
		testExecutionResult.setSkippedTests(testExecutionResult.getTestSets().stream()
				.mapToInt(TestSet::getSkippedTests).sum());
		
		List<TestCase> allTests=TestFactory.getCompletedTests();
		Duration seriesDuration=Duration.ZERO;
		for(TestCase testCase : allTests) {
			Duration elapsedTime=Duration.between(testCase.getStartTimestamp(), testCase.getEndTimestamp());
			seriesDuration=seriesDuration.plus(elapsedTime);
		}
		
		LocalDateTime minimumStartTimestamp=allTests.stream()
				.map(TestCase :: getStartTimestamp)
				.min(LocalDateTime::compareTo)
				.orElse(LocalDateTime.now());
		LocalDateTime maximumEndTimestamp=allTests.stream()
				.map(TestCase :: getEndTimestamp)
				.max(LocalDateTime::compareTo)
				.orElse(LocalDateTime.now());
		Duration actualDuration=Duration.between(minimumStartTimestamp, maximumEndTimestamp);
		
		long seriesDurationMillis=seriesDuration.toMillis();
		long normalizedSeriesDurationMillis=(long) (seriesDurationMillis*1.525201613);
		long actualDurationMillis=actualDuration.toMillis();
		long savedDurationMillis=normalizedSeriesDurationMillis>actualDurationMillis?(normalizedSeriesDurationMillis - actualDurationMillis):0;
		float savingPercent=((float)savedDurationMillis/normalizedSeriesDurationMillis)*100;
		
		testExecutionResult.setActualTime(String.format("%d", actualDurationMillis));
		testExecutionResult.setActualTimeText(DateTimeHelper.formatDuration(actualDuration));
		testExecutionResult.setSavedTime(String.format("%d", savedDurationMillis));
		testExecutionResult.setSavedTimeText(DateTimeHelper.formatDuration(Duration.ofMillis(savedDurationMillis)));
		testExecutionResult.setSavingPercent(String.format("%.1f", savingPercent));
		return testExecutionResult;
	}
}

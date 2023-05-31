package io.nishadc.automationtestingframework.testngcustomization.unittests;

import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import io.nishadc.automationtestingframework.logging.LoggerFactory;
import io.nishadc.automationtestingframework.testngcustomization.annotations.Retry;

public class RetryAnalyzerTests {
	private static Logger logger=LoggerFactory.create(RetryAnalyzerTests.class);
	private static int validationValue=0;
	
	@Test(priority=11)
	public void withRetry() {
		RetryAnalyzerTests.logger.debug("Validate {} against 1",++RetryAnalyzerTests.validationValue);
		Assertions.assertThat(RetryAnalyzerTests.validationValue).isEqualByComparingTo(1);
		RetryAnalyzerTests.logger.info("withRetry.........Passed");
	}
	
	@Retry(0)
	@Test(priority=12)
	public void declaredZeroRetry() {
		RetryAnalyzerTests.logger.debug("Validate {} against 2",++RetryAnalyzerTests.validationValue);
		Assertions.assertThat(RetryAnalyzerTests.validationValue).isEqualTo(2);
		RetryAnalyzerTests.logger.info("declaredZeroRetry.........Passed");
	}
	
	@Retry(3)
	@Test(priority=13)
	public void passAfterThreeRetry() {
		RetryAnalyzerTests.logger.debug("Validate {} against 6",++RetryAnalyzerTests.validationValue);
		Assertions.assertThat(RetryAnalyzerTests.validationValue).isEqualTo(6);
		RetryAnalyzerTests.logger.info("passAfterThreeRetry.........Passed");
	}
	
	@Retry(1)
	@Test(priority=14)
	public void passAfterOneRetry() {
		RetryAnalyzerTests.logger.debug("Validate {} against 8",++RetryAnalyzerTests.validationValue);
		Assertions.assertThat(RetryAnalyzerTests.validationValue).isEqualTo(8);
		RetryAnalyzerTests.logger.info("passAfterOneRetry.........Passed");
	}
}

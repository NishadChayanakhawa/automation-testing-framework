package io.nishadc.automationtestingframework.testngcustomization.process;

import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


import io.nishadc.automationtestingframework.logging.LoggerFactory;
import io.nishadc.automationtestingframework.testngcustomization.annotations.Retry;
public class RetryAnalyzer implements IRetryAnalyzer{
	private static Logger logger=LoggerFactory.create(RetryAnalyzer.class);
	private static ThreadLocal<Integer> retryCount=ThreadLocal.withInitial(() -> 0);
	
	public static void resetRetryCounter() {
		RetryAnalyzer.retryCount.remove();
	}

	@Override
	public boolean retry(ITestResult result) {
		Retry retry=result
					.getMethod()
					.getConstructorOrMethod()
					.getMethod()
					.getAnnotation(Retry.class);
		int declaredRetryCount=retry==null?0:retry.value();
		
		RetryAnalyzer.logger.info("Checking retry eligibility. Requested retry iteration: {}",declaredRetryCount);
		
		if(RetryAnalyzer.retryCount.get() < declaredRetryCount) {
			RetryAnalyzer.retryCount.set(RetryAnalyzer.retryCount.get()+1);
			RetryAnalyzer.logger.debug("Retry count is #{}. Retry will be performed.",RetryAnalyzer.retryCount.get());
			return true;
		} else {
			RetryAnalyzer.logger.debug("Retry count is #{}. No retry will be performed.",RetryAnalyzer.retryCount.get());
			RetryAnalyzer.resetRetryCounter();
			return false;
		}
	}

}

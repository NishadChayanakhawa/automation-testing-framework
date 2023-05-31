package io.nishadc.automationtestingframework.testngcustomization;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import io.nishadc.automationtestingframework.testngcustomization.annotations.RegressionTest;
import io.nishadc.automationtestingframework.testngcustomization.annotations.Retry;
import io.nishadc.automationtestingframework.testngcustomization.annotations.SmokeTest;
import io.nishadc.automationtestingframework.testngcustomization.process.RetryAnalyzer;

public class ExecutionController implements IAnnotationTransformer {
	
	private static final String TEST_TYPE_PROPERTY_NAME="testType";
	private static final String TEST_TYPE_HEALTH_CHECK="healthCheck";
	private static final String TEST_TYPE_SMOKE="smoke";
	private static final String TEST_TYPE_REGRESSION="regression";
	
	private static String testTypeSelected=System.getProperty(TEST_TYPE_PROPERTY_NAME, TEST_TYPE_REGRESSION);

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		if(!doesTestTypeMatch(testMethod)) {
			annotation.setEnabled(false);
			return;
		}
		
		Retry retryAnnotation=testMethod.getAnnotation(Retry.class);
		if(retryAnnotation!=null) {
			annotation.setRetryAnalyzer(RetryAnalyzer.class);
		}
		IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);
	}
	
	private boolean doesTestTypeMatch(Method testMethod) {		
		return !((TEST_TYPE_HEALTH_CHECK.equals(testTypeSelected) && 
				(testMethod.isAnnotationPresent(SmokeTest.class) || 
						testMethod.isAnnotationPresent(RegressionTest.class))) ||
				(TEST_TYPE_SMOKE.equals(testTypeSelected) && testMethod.isAnnotationPresent(RegressionTest.class)));
	}

}

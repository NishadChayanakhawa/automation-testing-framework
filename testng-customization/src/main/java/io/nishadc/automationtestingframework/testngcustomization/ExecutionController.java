package io.nishadc.automationtestingframework.testngcustomization;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import io.nishadc.automationtestingframework.testngcustomization.annotations.Retry;
import io.nishadc.automationtestingframework.testngcustomization.process.RetryAnalyzer;

public class ExecutionController implements IAnnotationTransformer {

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		Retry retryAnnotation=testMethod.getAnnotation(Retry.class);
		if(retryAnnotation!=null) {
			annotation.setRetryAnalyzer(RetryAnalyzer.class);
		}
		IAnnotationTransformer.super.transform(annotation, testClass, testConstructor, testMethod);
	}

}

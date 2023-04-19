package io.nishadc.automationtestingframework.logging.unittests;

import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import io.nishadc.automationtestingframework.logging.LoggerFactory;
import io.nishadc.automationtestingframework.logging.exceptions.LoggerInitializationException;


public class LoggerFactoryTests extends LoggerFactory{
	@Test(priority=11)
	public void createLogger_invalidSettingFile() {
		LoggerFactory.log4j2SettingFileName="INVALID.xml";
		try {
			LoggerFactory.create(LoggerFactoryTests.class);
		} catch(LoggerInitializationException e) {
			Assertions.assertThat(e).isExactlyInstanceOf(LoggerInitializationException.class);
		}
	}
	
	@Test(priority=12)
	public void createLogger_validSettingFile() {
		LoggerFactory.log4j2SettingFileName="log4j2.xml";
		Logger logger=LoggerFactory.create(LoggerFactoryTests.class);
		logger.debug("Logger statement");
	}
	
	@Test(priority=13)
	public void createLogger_anotherInstance() {
		Logger logger=LoggerFactory.create(LoggerFactoryTests.class);
		logger.debug("Another logger statement");
	}
}

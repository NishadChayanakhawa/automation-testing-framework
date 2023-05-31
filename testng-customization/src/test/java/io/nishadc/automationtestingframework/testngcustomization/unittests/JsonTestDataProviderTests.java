package io.nishadc.automationtestingframework.testngcustomization.unittests;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class JsonTestDataProviderTests {
	@Test(dataProvider="getTestDataFromJson",
			dataProviderClass=io.nishadc.automationtestingframework.testngcustomization.TestDataProvider.class)
	public void getTestDataFromJson_test(Map<String,Object> testData) {
		switch(testData.get("Parameter1").toString()) {
		case "Value1":
			Assertions.assertThat(testData).containsEntry("Parameter2", 1);
			Assertions.assertThat(testData).containsEntry("Parameter3", false);
			Assertions.assertThat(testData).containsEntry("Parameter4", new BigDecimal(4.400000,MathContext.DECIMAL32));
			Assertions.assertThat(testData).containsEntry("Parameter5", new BigDecimal(5.555000,MathContext.DECIMAL32));
			break;
		case "Value2":
			Assertions.assertThat(testData).containsEntry("Parameter2", 2);
			Assertions.assertThat(testData).containsEntry("Parameter3", true);
			break;
		case "Value3":
			Assertions.assertThat(testData).containsEntry("Parameter2", 3);
			break;
		}
	}
	
	@Test(dataProvider="getTestDataFromJson",
			dataProviderClass=io.nishadc.automationtestingframework.testngcustomization.TestDataProvider.class,
			enabled=false)
	public void getTestDataFromJson_noFile_test(Map<String,Object> testData) {
		
	}
	
	@Test(dataProvider="getTestDataFromJson",
			dataProviderClass=io.nishadc.automationtestingframework.testngcustomization.TestDataProvider.class,
			enabled=false)
	public void getTestDataFromJson_corruptedJson_test(Map<String,Object> testData) {
		
	}
	
	@Test(dataProvider="getTestDataFromJson",
			dataProviderClass=io.nishadc.automationtestingframework.testngcustomization.TestDataProvider.class,
			enabled=false)
	public void getTestDataFromJson_incorrectFormat_test(Map<String,Object> testData) {
		
	}
}

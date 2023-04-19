package io.nishadc.automationtestingframework.testngcustomization.unittests;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class ExcelTestDataProviderTests {
	@Test(dataProvider="getTestDataFromExcel",
			dataProviderClass=io.nishadc.automationtestingframework.testngcustomization.TestDataProvider.class)
	public void getTestDataFromExcel_xls_test(Map<String,Object> testData) {
		switch(testData.get("Parameter1").toString()) {
		case "Value1":
			Assertions.assertThat(testData).containsEntry("Parameter2", 1.0);
			Assertions.assertThat(testData).containsEntry("Parameter3", true);
			break;
		case "Value2":
			Assertions.assertThat(testData).containsEntry("Parameter2", 2.0);
			Assertions.assertThat(testData).containsEntry("Parameter3", "A");
			break;
		case "Value3":
			Assertions.assertThat(testData).containsEntry("Parameter2", "");
			Assertions.assertThat(testData).containsEntry("Parameter3", "");
			break;
		}
	}
	
	@Test(dataProvider="getTestDataFromExcel",
			dataProviderClass=io.nishadc.automationtestingframework.testngcustomization.TestDataProvider.class)
	public void noSheet_test(Map<String,Object> testData) {
		
	}
}

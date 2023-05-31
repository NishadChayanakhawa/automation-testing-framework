package io.nishadc.automationtestingframework.testngcustomization.unittests;

import java.util.Map;

import org.testng.annotations.Test;

public class NoExcelFileTest {
	@Test(dataProvider="getTestDataFromExcel",
			dataProviderClass=io.nishadc.automationtestingframework.testngcustomization.TestDataProvider.class)
	public void noFile_test(Map<String,Object> testData) {
		
	}
}

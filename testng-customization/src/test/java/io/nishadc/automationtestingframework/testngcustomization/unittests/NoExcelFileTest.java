package io.nishadc.automationtestingframework.testngcustomization.unittests;

import java.util.Map;

import org.testng.annotations.Test;

import io.nishadc.automationtestingframework.filehandling.exceptions.ExcelFileHandlingException;

public class NoExcelFileTest {
	@Test(dataProvider="getTestDataFromExcel",
			dataProviderClass=io.nishadc.automationtestingframework.testngcustomization.TestDataProvider.class,
			expectedExceptions = {ExcelFileHandlingException.class},
			enabled=false)
	public void noFile_test(Map<String,Object> testData) {
		
	}
}

package io.nishadc.automationtestingframework.testngcustomization.unittests;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class XlsxTestDataProviderTests {
	@Test(dataProvider="getTestDataFromExcel",
			dataProviderClass=io.nishadc.automationtestingframework.testngcustomization.TestDataProvider.class)
	public void getTestDataFromExcel_xlsx_test(Map<String,Object> testData) {
		switch(testData.get("Parameter1").toString()) {
		case "Value1":
			Assertions.assertThat(testData).containsEntry("Parameter2", 1.11);
			Assertions.assertThat(testData).containsEntry("Parameter3", 2.2);
			
//			Assertions.assertThat(testData.get("Parameter2")).isEqualTo(1.11);
//			Assertions.assertThat(testData.get("Parameter3")).isEqualTo(2.2);
			break;
		case "Value2":
			Assertions.assertThat(testData).containsEntry("Parameter2", 3.3100000000000005);
			Assertions.assertThat(testData).containsEntry("Parameter3", 2.4420000000000006);
			
//			Assertions.assertThat(testData.get("Parameter2")).isEqualTo(3.3100000000000005);
//			Assertions.assertThat(testData.get("Parameter3")).isEqualTo(2.4420000000000006);
			break;
		}
	}
}

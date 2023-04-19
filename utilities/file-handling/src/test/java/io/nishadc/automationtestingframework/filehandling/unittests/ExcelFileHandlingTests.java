package io.nishadc.automationtestingframework.filehandling.unittests;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import io.nishadc.automationtestingframework.filehandling.ExcelFileHandling;
import io.nishadc.automationtestingframework.filehandling.exceptions.ExcelFileHandlingException;

public class ExcelFileHandlingTests {
	@Test
	public void getExcelSheetContent_invalidFileType_test() {
		try {
			ExcelFileHandling.getExcelSheetContent("./src/test/resources/TestJsonFile.json", null);
		} catch (ExcelFileHandlingException e) {
			Assertions.assertThat(e).isExactlyInstanceOf(ExcelFileHandlingException.class);
		}
	}
	
	@Test
	public void getExcelSheetContent_invalidFile_test() {
		try {
			ExcelFileHandling.getExcelSheetContent("./src/test/resources/Invalid.xls", null);
		} catch (ExcelFileHandlingException e) {
			Assertions.assertThat(e).isExactlyInstanceOf(ExcelFileHandlingException.class);
		}
	}

	@Test
	public void getExcelSheetContent_corruptFile_test() {
		try {
			ExcelFileHandling.getExcelSheetContent("./src/test/resources/CorruptedExcel.xls", null);
		} catch (ExcelFileHandlingException e) {
			Assertions.assertThat(e).isExactlyInstanceOf(ExcelFileHandlingException.class);
		}
	}

	@Test
	public void getExcelSheetContent_invalidSheet_test() {
		try {
			ExcelFileHandling.getExcelSheetContent("./src/test/resources/TestData.xls", "Invalid Sheet");
		} catch (ExcelFileHandlingException e) {
			Assertions.assertThat(e).isExactlyInstanceOf(ExcelFileHandlingException.class);
		}
	}

	@Test
	public void getExcelSheetContent_xls_test() throws ExcelFileHandlingException {
		List<Map<String,Object>> testData=ExcelFileHandling.getExcelSheetContent("./src/test/resources/TestData.xls", "Data");
		Assertions.assertThat(testData).hasSize(4);
		Assertions.assertThat(testData.get(0).get("Parameter1")).isEqualTo("Value1");
		Assertions.assertThat(testData.get(0).get("Parameter2")).isEqualTo("Value2");
		Assertions.assertThat(testData.get(0).get("Parameter3")).isEqualTo("Value3");
		Assertions.assertThat(testData.get(1).get("Parameter1")).isEqualTo(1.0);
		Assertions.assertThat(testData.get(1).get("Parameter2")).isEqualTo(2.0);
		Assertions.assertThat(testData.get(1).get("Parameter3")).isEqualTo(3.0);
		Assertions.assertThat(testData.get(2).get("Parameter1")).isEqualTo(true);
		Assertions.assertThat(testData.get(2).get("Parameter2")).isEqualTo(false);
		Assertions.assertThat(testData.get(2).get("Parameter3")).isEqualTo(true);
		Assertions.assertThat(testData.get(3).get("Parameter1")).isEqualTo(1.1);
		Assertions.assertThat(testData.get(3).get("Parameter2")).isEqualTo(2.22);
		Assertions.assertThat(testData.get(3).get("Parameter3")).isEqualTo(3.333);
	}
	
	@Test
	public void getExcelSheetContent_xlsx_test() throws ExcelFileHandlingException {
		List<Map<String,Object>> testData=ExcelFileHandling.getExcelSheetContent("./src/test/resources/TestData.xlsx", "Data");
		Assertions.assertThat(testData).hasSize(8);
		Assertions.assertThat(testData.get(0).get("Parameter1")).isEqualTo("Value1");
		Assertions.assertThat(testData.get(0).get("Parameter2")).isEqualTo("Value2");
		Assertions.assertThat(testData.get(0).get("Parameter3")).isEqualTo("Value3");
		Assertions.assertThat(testData.get(1).get("Parameter1")).isEqualTo(1.0);
		Assertions.assertThat(testData.get(1).get("Parameter2")).isEqualTo(2.0);
		Assertions.assertThat(testData.get(1).get("Parameter3")).isEqualTo(3.0);
		Assertions.assertThat(testData.get(2).get("Parameter1")).isEqualTo(true);
		Assertions.assertThat(testData.get(2).get("Parameter2")).isEqualTo(false);
		Assertions.assertThat(testData.get(2).get("Parameter3")).isEqualTo(true);
		Assertions.assertThat(testData.get(3).get("Parameter1")).isEqualTo(1.1);
		Assertions.assertThat(testData.get(3).get("Parameter2")).isEqualTo(2.22);
		Assertions.assertThat(testData.get(3).get("Parameter3")).isEqualTo(3.333);
		Assertions.assertThat(testData.get(4).get("Parameter1")).isEqualTo(true);
		Assertions.assertThat(testData.get(4).get("Parameter2")).isEqualTo("Value1Value2");
		Assertions.assertThat(testData.get(4).get("Parameter3")).isEqualTo(6.653);
		Assertions.assertThat(testData.get(5).get("Parameter1")).isEqualTo("");
		Assertions.assertThat(testData.get(5).get("Parameter2")).isEqualTo("");
		Assertions.assertThat(testData.get(5).get("Parameter3")).isEqualTo("");
		Assertions.assertThat(testData.get(6).get("Parameter1")).isEqualTo("");
		Assertions.assertThat(testData.get(6).get("Parameter2")).isEqualTo("Not Null");
		Assertions.assertThat(testData.get(6).get("Parameter3")).isEqualTo("");
		Assertions.assertThat(testData.get(7).get("Parameter1")).isEqualTo(" ");
	}
}

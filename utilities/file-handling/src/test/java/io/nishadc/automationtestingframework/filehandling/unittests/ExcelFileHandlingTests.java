package io.nishadc.automationtestingframework.filehandling.unittests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import io.nishadc.automationtestingframework.filehandling.ExcelFileHandling;
import io.nishadc.automationtestingframework.filehandling.exceptions.ExcelFileHandlingException;

public class ExcelFileHandlingTests {
	@Test
	public void getExcelSheetContent_invalidFileType_test() throws IOException {
		try {
			ExcelFileHandling.getExcelSheetContent("./src/test/resources/TestJsonFile.json", null);
		} catch (ExcelFileHandlingException e) {
			Assertions.assertThat(e).isExactlyInstanceOf(ExcelFileHandlingException.class);
		}
	}
	
	@Test
	public void getExcelSheetContent_invalidFile_test() throws IOException {
		try {
			ExcelFileHandling.getExcelSheetContent("./src/test/resources/Invalid.xls", null);
		} catch (ExcelFileHandlingException e) {
			Assertions.assertThat(e).isExactlyInstanceOf(ExcelFileHandlingException.class);
		}
	}

	@Test
	public void getExcelSheetContent_corruptFile_test() throws IOException {
		try {
			ExcelFileHandling.getExcelSheetContent("./src/test/resources/CorruptedExcel.xls", null);
		} catch (ExcelFileHandlingException e) {
			Assertions.assertThat(e).isExactlyInstanceOf(ExcelFileHandlingException.class);
		}
	}

	@Test
	public void getExcelSheetContent_invalidSheet_test() throws IOException {
		try {
			ExcelFileHandling.getExcelSheetContent("./src/test/resources/TestData.xls", "Invalid Sheet");
		} catch (ExcelFileHandlingException e) {
			Assertions.assertThat(e).isExactlyInstanceOf(ExcelFileHandlingException.class);
		}
	}

	@Test
	public void getExcelSheetContent_xls_test() throws ExcelFileHandlingException,IOException {
		List<Map<String,Object>> testData=ExcelFileHandling.getExcelSheetContent("./src/test/resources/TestData.xls", "Data");
		Assertions.assertThat(testData).hasSize(4);
		Assertions.assertThat(testData.get(0)).containsEntry("Parameter1", "Value1");
		Assertions.assertThat(testData.get(0)).containsEntry("Parameter2", "Value2");
		Assertions.assertThat(testData.get(0)).containsEntry("Parameter3", "Value3");
		
		Assertions.assertThat(testData.get(1)).containsEntry("Parameter1", 1.0);
		Assertions.assertThat(testData.get(1)).containsEntry("Parameter2", 2.0);
		Assertions.assertThat(testData.get(1)).containsEntry("Parameter3", 3.0);
		
		Assertions.assertThat(testData.get(2)).containsEntry("Parameter1", true);
		Assertions.assertThat(testData.get(2)).containsEntry("Parameter2", false);
		Assertions.assertThat(testData.get(2)).containsEntry("Parameter3", true);
		
		Assertions.assertThat(testData.get(3)).containsEntry("Parameter1", 1.1);
		Assertions.assertThat(testData.get(3)).containsEntry("Parameter2", 2.22);
		Assertions.assertThat(testData.get(3)).containsEntry("Parameter3", 3.333);
	}
	
	@Test
	public void getExcelSheetContent_xlsx_test() throws ExcelFileHandlingException,IOException {
		List<Map<String,Object>> testData=ExcelFileHandling.getExcelSheetContent("./src/test/resources/TestData.xlsx", "Data");
		Assertions.assertThat(testData).hasSize(8);
		Assertions.assertThat(testData.get(0)).containsEntry("Parameter1", "Value1");
		Assertions.assertThat(testData.get(0)).containsEntry("Parameter2", "Value2");
		Assertions.assertThat(testData.get(0)).containsEntry("Parameter3", "Value3");
		
		Assertions.assertThat(testData.get(1)).containsEntry("Parameter1", 1.0);
		Assertions.assertThat(testData.get(1)).containsEntry("Parameter2", 2.0);
		Assertions.assertThat(testData.get(1)).containsEntry("Parameter3", 3.0);
		
		Assertions.assertThat(testData.get(2)).containsEntry("Parameter1", true);
		Assertions.assertThat(testData.get(2)).containsEntry("Parameter2", false);
		Assertions.assertThat(testData.get(2)).containsEntry("Parameter3", true);
		
		Assertions.assertThat(testData.get(3)).containsEntry("Parameter1", 1.1);
		Assertions.assertThat(testData.get(3)).containsEntry("Parameter2", 2.22);
		Assertions.assertThat(testData.get(3)).containsEntry("Parameter3", 3.333);
		
		Assertions.assertThat(testData.get(4)).containsEntry("Parameter1", true);
		Assertions.assertThat(testData.get(4)).containsEntry("Parameter2", "Value1Value2");
		Assertions.assertThat(testData.get(4)).containsEntry("Parameter3", 6.653);
		
		Assertions.assertThat(testData.get(5)).containsEntry("Parameter1", "");
		Assertions.assertThat(testData.get(5)).containsEntry("Parameter2", "");
		Assertions.assertThat(testData.get(5)).containsEntry("Parameter3", "");
		
		Assertions.assertThat(testData.get(6)).containsEntry("Parameter1", "");
		Assertions.assertThat(testData.get(6)).containsEntry("Parameter2", "Not Null");
		Assertions.assertThat(testData.get(6)).containsEntry("Parameter3", "");
		
		Assertions.assertThat(testData.get(7)).containsEntry("Parameter1", " ");
	}
}

package io.nishadc.automationtestingframework.filehandling.unittests;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.nishadc.automationtestingframework.filehandling.JsonFileHandling;
import io.nishadc.automationtestingframework.filehandling.exceptions.FlatFileHandlingException;
import io.nishadc.automationtestingframework.filehandling.exceptions.JsonFileHandlingException;

public class JsonFileHandlingTests {
	@Test
	public void getJsonFileContent_test() throws FlatFileHandlingException, JsonFileHandlingException {
		JSONObject testFileJson=JsonFileHandling.getJsonFileContent("./src/test/resources/TestJsonFile.json");
		Assertions.assertThat(testFileJson.getString("name")).isEqualTo("Jane");
		Assertions.assertThat(testFileJson.getInt("id")).isEqualTo(1);
	}
	
	@Test
	public void getJsonFileContent_invalidFile_test() throws JsonFileHandlingException {
		try {
			JsonFileHandling.getJsonFileContent("./src/test/resources/Invalid.json");
		} catch (FlatFileHandlingException e) {
			Assertions.assertThat(e).isExactlyInstanceOf(FlatFileHandlingException.class);
		}
	}
	
	@Test
	public void getJsonFileContent_invalidFileContent_test() throws FlatFileHandlingException {
		try {
			JsonFileHandling.getJsonFileContent("./src/test/resources/TestFile");
		} catch (JsonFileHandlingException e) {
			Assertions.assertThat(e).isExactlyInstanceOf(JsonFileHandlingException.class);
		}
	}
}

package io.nishadc.automationtestingframework.filehandling.unittests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import io.nishadc.automationtestingframework.filehandling.FlatFileHandling;
import io.nishadc.automationtestingframework.filehandling.exceptions.FlatFileHandlingException;

public class FlatFileHandlingTests {
	@Test
	public void getFileContents_test() throws FlatFileHandlingException {
		String flatFileContent=FlatFileHandling.getFileContents("./src/test/resources/TestFile");
		Assertions.assertThat(flatFileContent)
			.isEqualTo("This is a test file.\nThat contains 2 lines of data.\n");
	}
	
	@Test
	public void getFileContents_invalidFile_test() {
		try {
			FlatFileHandling.getFileContents("./src/test/resources/InvalidFile");
		} catch (FlatFileHandlingException e) {
			Assertions.assertThat(e).isExactlyInstanceOf(FlatFileHandlingException.class);
		}
	}
}


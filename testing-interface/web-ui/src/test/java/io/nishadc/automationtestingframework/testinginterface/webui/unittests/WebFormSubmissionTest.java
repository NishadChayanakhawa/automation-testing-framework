package io.nishadc.automationtestingframework.testinginterface.webui.unittests;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import io.nishadc.automationtestingframework.testinginterface.webui.unittests.pageObjects.WebFormPage;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;
import io.nishadc.automationtestingframework.testinginterface.webui.unittests.pageObjects.IndexPage;

public class WebFormSubmissionTest extends BaseWebTest {
	private static final String INDEX_URL=
			"https://bonigarcia.dev/selenium-webdriver-java/index.html";
	
	@Test
	public void submitForm() {
		WebDriver driver=BaseWebTest.drivers.get();
		TestFactory.recordTest("Submit Form", driver);
		driver.get(WebFormSubmissionTest.INDEX_URL);
		
		IndexPage indexPage=new IndexPage(driver,10);
		indexPage.goToWebForm();
		
		WebFormPage webFormPage=new WebFormPage(driver);
		webFormPage.backToIndex();
		
		indexPage=new IndexPage(driver,10);
		indexPage.goToWebFormAndRecordStep();
		
		webFormPage=new WebFormPage(driver);
		webFormPage.updateTextInput();
		TestFactory.recordTestStep("Input added", true);
		String inputText=webFormPage.getTextInputValue();
		
		Assertions.assertThat(inputText).isEqualTo("firstInputIsComplete");
		
		inputText=webFormPage.getTextInputValueByXPath();
		Assertions.assertThat(inputText).isEqualTo("firstInputIsComplete");
		
		webFormPage.clearTextInputAndUpdate1();
		inputText=webFormPage.getTextInputValueByXPath();
		Assertions.assertThat(inputText).isEqualTo("1");
		
		webFormPage.clearTextInputAndUpdate2();
		inputText=webFormPage.getTextInputValueByXPath();
		Assertions.assertThat(inputText).isEqualTo("2");
		
		webFormPage.clearTextInputAndUpdate3();
		inputText=webFormPage.getTextInputValueByXPath();
		Assertions.assertThat(inputText).isEqualTo("3");
		
		webFormPage.clearTextInputAndUpdate4();
		inputText=webFormPage.getTextInputValueByXPath();
		Assertions.assertThat(inputText).isEqualTo("4");
		
		webFormPage.submitForm();
	}
}

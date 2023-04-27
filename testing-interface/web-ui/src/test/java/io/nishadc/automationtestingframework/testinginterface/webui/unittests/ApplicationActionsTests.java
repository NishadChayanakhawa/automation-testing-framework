package io.nishadc.automationtestingframework.testinginterface.webui.unittests;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import io.nishadc.automationtestingframework.testinginterface.webui.unittests.pageObjects.ClickTestPage;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

public class ApplicationActionsTests  extends BaseWebTest {
	@Test
	public void clickTest() {
		WebDriver driver=BaseWebTest.drivers.get();
		String url=BaseWebTest.getTestPageUrl("ClickTestPage");
		driver.get(url);
		
		ClickTestPage clickTestPage=new ClickTestPage(driver,10);
		
		clickTestPage.performClickThroughElement();
		Assertions.assertThat(clickTestPage.getValueThroughElement()).isEqualTo("2");
		
		clickTestPage.performClickThroughXPath();
		Assertions.assertThat(clickTestPage.getValueThroughXPath()).isEqualTo("3");
		
		clickTestPage.performClickThroughElementAndRecordStep();;
		Assertions.assertThat(clickTestPage.getValueThroughElement()).isEqualTo("4");
		
		clickTestPage.performClickThroughXPathAndRecordStep();
		Assertions.assertThat(clickTestPage.getValueThroughElement()).isEqualTo("5");
	}
	
	@Test
	public void sendTextTest() {
		WebDriver driver=BaseWebTest.drivers.get();
		String url=BaseWebTest.getTestPageUrl("ClickTestPage");
		driver.get(url);
		
		ClickTestPage clickTestPage=new ClickTestPage(driver);
		
		clickTestPage.clearTextThroughElement();
		clickTestPage.sendTextThroughElement("101");
		Assertions.assertThat(clickTestPage.getValueThroughElement()).isEqualTo("101");
		
		clickTestPage.clearTextThroughXPath();
		clickTestPage.sendTextThroughXPath("201");
		Assertions.assertThat(clickTestPage.getValueThroughElement()).isEqualTo("201");
		
		clickTestPage.clearTextThroughElementAndRecord();
		clickTestPage.sendTextThroughElementAndRecord("301");
		Assertions.assertThat(clickTestPage.getValueThroughElement()).isEqualTo("301");
		
		clickTestPage.clearTextThroughXPathAndRecord();
		clickTestPage.sendTextThroughXPathAndRecord("401");
		Assertions.assertThat(clickTestPage.getValueThroughElement()).isEqualTo("401");
	}
	
	@Test
	public void dropDownTest() {
		WebDriver driver=BaseWebTest.drivers.get();
		String url=BaseWebTest.getTestPageUrl("ClickTestPage");
		driver.get(url);
		
		ClickTestPage clickTestPage=new ClickTestPage(driver);
		
		clickTestPage.selectOneByElement();
		Assertions.assertThat(clickTestPage.getSingleDropdownValue()).isEqualTo("1");
		
		clickTestPage.selectTwoByXPath();
		Assertions.assertThat(clickTestPage.getSingleDropdownValue()).isEqualTo("2");
		
		clickTestPage.selectThreeByElementAndRecordSteps();
		Assertions.assertThat(clickTestPage.getSingleDropdownValue()).isEqualTo("3");
		
		clickTestPage.selectTwoByXPathAndRecordSteps();
		Assertions.assertThat(clickTestPage.getSingleDropdownValue()).isEqualTo("2");
		
		clickTestPage.selectOneByElementValue();
		Assertions.assertThat(clickTestPage.getSingleDropdownValue()).isEqualTo("1");
		
		clickTestPage.selectThreeByXPathValueAndRecord();
		Assertions.assertThat(clickTestPage.getSingleDropdownValue()).isEqualTo("3");
		
		clickTestPage.selectTwoByElementValueAndRecord();
		Assertions.assertThat(clickTestPage.getSingleDropdownValue()).isEqualTo("2");
		
		clickTestPage.selectThreeByXPathValue();
		Assertions.assertThat(clickTestPage.getSingleDropdownValue()).isEqualTo("3");
		
		clickTestPage.select3AndRecord();
		Assertions.assertThat(clickTestPage.getSingleDropdownValue()).isEqualTo("3");
	}
	
	@Test
	public void dropDownDeselectTest() {
		WebDriver driver=BaseWebTest.drivers.get();
		TestFactory.recordTest("Deselect Tests", driver);
		String url=BaseWebTest.getTestPageUrl("ClickTestPage");
		driver.get(url);
		
		ClickTestPage clickTestPage=new ClickTestPage(driver);
		
		clickTestPage.multiSelect();
		clickTestPage.removeOneSelection();
		Assertions.assertThat(clickTestPage.getMultiDropdownValue()).isEqualTo("2");
		
		clickTestPage.multiSelect();
		clickTestPage.removeOneSelectionByXPath();
		Assertions.assertThat(clickTestPage.getMultiDropdownValue()).isEqualTo("2");
		
		clickTestPage.multiSelect();
		clickTestPage.removeOneSelectionAndRecord();
		Assertions.assertThat(clickTestPage.getMultiDropdownValue()).isEqualTo("2");
		
		clickTestPage.multiSelect();
		clickTestPage.removeOneSelectionByXPathAndRecord();
		Assertions.assertThat(clickTestPage.getMultiDropdownValue()).isEqualTo("2");
		
		clickTestPage.multiSelect();
		clickTestPage.remove2Selection();
		Assertions.assertThat(clickTestPage.getMultiDropdownValue()).isEqualTo("1");
		
		clickTestPage.multiSelect();
		clickTestPage.remove2SelectionAndRecord();
		Assertions.assertThat(clickTestPage.getMultiDropdownValue()).isEqualTo("1");
		
		clickTestPage.multiSelect();
		clickTestPage.remove2SelectionByXPath();
		Assertions.assertThat(clickTestPage.getMultiDropdownValue()).isEqualTo("1");
		
		clickTestPage.multiSelect();
		clickTestPage.remove2SelectionByXPathAndRecord();
		Assertions.assertThat(clickTestPage.getMultiDropdownValue()).isEqualTo("1");
		
	}
	
	@Test
	public void getTextTest() {
		WebDriver driver=BaseWebTest.drivers.get();
		TestFactory.recordTest("Deselect Tests", driver);
		String url=BaseWebTest.getTestPageUrl("ClickTestPage");
		driver.get(url);
		
		ClickTestPage clickTestPage=new ClickTestPage(driver);
		Assertions.assertThat(clickTestPage.getInnerTextByElement()).isEqualTo("This is inner text");
		Assertions.assertThat(clickTestPage.getInnerTextByXPath()).isEqualTo("This is inner text");
		Assertions.assertThat(clickTestPage.getClassByElement()).isEqualTo("classTest");
		Assertions.assertThat(clickTestPage.getClassByXPath()).isEqualTo("classTest");
		Assertions.assertThat(clickTestPage.isDisplayedByXPath()).isTrue();
		Assertions.assertThat(clickTestPage.isDisplayedByElement()).isFalse();
		
		Assertions.assertThat(clickTestPage.isEnabledByXPath()).isTrue();
		Assertions.assertThat(clickTestPage.isEnabledByElement()).isFalse();
	}
}

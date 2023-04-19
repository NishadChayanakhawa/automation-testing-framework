package io.nishadc.automationtestingframework.testngcustomization;

//logger
import org.apache.logging.log4j.Logger;

//json
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//testNG
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;

import io.nishadc.automationtestingframework.filehandling.ExcelFileHandling;
import io.nishadc.automationtestingframework.filehandling.JsonFileHandling;
import io.nishadc.automationtestingframework.filehandling.exceptions.ExcelFileHandlingException;
import io.nishadc.automationtestingframework.filehandling.exceptions.FlatFileHandlingException;
import io.nishadc.automationtestingframework.filehandling.exceptions.JsonFileHandlingException;
import io.nishadc.automationtestingframework.logging.LoggerFactory;

//java util
import java.util.Map;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


//JSON Handling


/**
* <b>Class Name</b>: TestDataProvider<br>
* <b>Description</b>: Provides an interface to inject test data into TestNG test methods.<br>
* @author Nishad Chayanakhawa &lt;nishad.chayanakhawa@gmail.com&gt;
*
*/
public class TestDataProvider {
	//logger
	private static final Logger logger=LoggerFactory.create(TestDataProvider.class);
	//base location for test data
	private static final String TEST_DATA_BASE_LOCATION=
			"./src/test/resources/TestData/";
	//test data json array key
	private static final String TEST_DATA_JSON_KEY=
			"testData";
	
	/**
	 * <b>Method Name</b>: getClassName<br>
	 * <b>Description</b>: Returns class name from calling testNG method.<br>
	 * @since v1.0.0
	 * @param testNGMethod TestNG method as {@link org.testng.ITestNGMethod ITestMethod}
	 * @return Class name as {@link java.lang.String String}
	 */
	private static String getClassName(ITestNGMethod testNGMethod) {
		return testNGMethod.getRealClass().getSimpleName();
	}
	
	/**
	 * <b>Method Name</b>: getMethodName<br>
	 * <b>Description</b>: Returns method name from calling testNG method.<br>
	 * @since v1.0.0
	 * @param testNGMethod TestNG method as {@link org.testng.ITestNGMethod ITestMethod}
	 * @return Method name as {@link java.lang.String String}
	 */
	private static String getMethodName(ITestNGMethod testNGMethod) {
		return testNGMethod.getMethodName();
	}
	
	/**
	 * <b>Method Name</b>: toObjectArray<br>
	 * <b>Description</b>: Returns JSONObject as two-dimensional object array.<br>
	 * @since v1.0.0
	 * @param json JSON as {@link org.json.JSONObject JSONObject} 
	 * @return Two dimensional array of {@link java.lang.Object Object}
	 * @throws JsonFileHandlingException in case of any failures while reading JSON file.
	 */
	private static Object[][] toObjectArray(JSONObject json) throws JsonFileHandlingException {
		JSONArray jsonArray;
		try {
			jsonArray=json.getJSONArray(TestDataProvider.TEST_DATA_JSON_KEY);
		} catch(JSONException e) {
			throw (JsonFileHandlingException)new JsonFileHandlingException("Incorrect test data json file format: " + e.getMessage()).initCause(e);
		}
		Object[][] objectArray=new Object[jsonArray.length()][1];
		for(int iArrayCounter=0;iArrayCounter<jsonArray.length();iArrayCounter++) {
			JSONObject testDataRowJson=jsonArray.getJSONObject(iArrayCounter);
			Map<String,Object> testDataRow=new HashMap<>();
			for(String key : testDataRowJson.keySet()) {
				testDataRow.put(key, testDataRowJson.get(key));
			}
			objectArray[iArrayCounter][0]=testDataRow;
		}
		return objectArray;
	}
	
	/**
	 * <b>Method Name</b>: toObjectArray<br>
	 * <b>Description</b>: Returns Excel Data as two-dimensional object array.<br>
	 * <b>Returns</b>: Object[][] <br>
	 * @since 
	 * @param excelData excel data as List&lt;Map&lt;String, Object&gt;&gt;
	 * @return Two dimensional array of {@link java.lang.Object Object}
	 */
	private static Object[][] toObjectArray(List<Map<String,Object>> excelData) {
		Object[][] objectArray=new Object[excelData.size()][1];
		for(int iArrayCounter=0;iArrayCounter<excelData.size();iArrayCounter++) {
			objectArray[iArrayCounter][0]=excelData.get(iArrayCounter);
		}
		return objectArray;
	}
	
	@DataProvider(name="getTestDataFromJson",parallel=true)
	/**
	 * <b>Method Name</b>: getTestDataFromJson<br>
	 * <b>Description</b>: TestNG DataProvider to inject test data from JSON file.<br>
	 * @since v1.0.0
	 * @param testNGMethod Auto-injected TestNG method as {@link org.testng.ITestNGMethod ITestMethod}
	 * @return Two dimensional array of {@link java.lang.Object Object}
	 * @throws FlatFileHandlingException in case there is any issue while opening file.
	 * @throws JsonFileHandlingException in case there is any issue while reading from JSON file.
	 */
	public Object[][] getTestDataFromJson(ITestNGMethod testNGMethod) throws FlatFileHandlingException, JsonFileHandlingException {
		String className=TestDataProvider.getClassName(testNGMethod);
		String methodName=TestDataProvider.getMethodName(testNGMethod);
		TestDataProvider.logger.debug("DataProvider::getTestDataFromJson for class {} and method {}",className,methodName);
		
		String jsonTestDataFileFullPath=
				String.format("%s%s/%s.json", TestDataProvider.TEST_DATA_BASE_LOCATION,className,methodName);
		TestDataProvider.logger.debug("Reading json test data file {}",jsonTestDataFileFullPath);
		JSONObject testDataJson=null;
		try {
			testDataJson = JsonFileHandling.getJsonFileContent(jsonTestDataFileFullPath);
			TestDataProvider.logger.debug("JSON Raw Test Data: {}",testDataJson);
			return TestDataProvider.toObjectArray(testDataJson);
		} catch (FlatFileHandlingException e) {
			TestDataProvider.logger.error("{}:{} Test will be skipped As {} file was not found.",className,methodName,jsonTestDataFileFullPath);
			throw e;
		} catch (JsonFileHandlingException e) {
			TestDataProvider.logger.error("{}:{} Test will be skipped As {} json file has invalid data.",className,methodName,jsonTestDataFileFullPath);
			throw e;
		}
	}
	
	
	
	@DataProvider(name="getTestDataFromExcel",parallel=true)
	/**
	 * <b>Method Name</b>: getTestDataFromExcel<br>
	 * <b>Description</b>: TestNG DataProvider to inject test data from Excel file.<br>
	 * @since v1.0.0
	 * @param testNGMethod Auto-injected TestNG method as {@link org.testng.ITestNGMethod ITestMethod}
	 * @return Two dimensional array of {@link java.lang.Object Object}
	 * @throws ExcelFileHandlingException in case of issues in opening Excel file
	 */
	public Object[][] getTestDataFromExcel(ITestNGMethod testNGMethod) throws ExcelFileHandlingException, IOException {
		String className=TestDataProvider.getClassName(testNGMethod);
		String methodName=TestDataProvider.getMethodName(testNGMethod);
		TestDataProvider.logger.debug("DataProvider::getTestDataFromExcel for class {} and method {}",className,methodName);
		String xlsTestDataFileFullPath=
				String.format("%s%s.xls", TestDataProvider.TEST_DATA_BASE_LOCATION,className);
		List<Map<String,Object>> testDataList=null;
		try {
			testDataList=ExcelFileHandling.getExcelSheetContent(xlsTestDataFileFullPath, methodName);
			return TestDataProvider.toObjectArray(testDataList);
		} catch (ExcelFileHandlingException e) {
			TestDataProvider.logger.error("{}:{} {} file was not found. Will retry for xlsx file",className,methodName,xlsTestDataFileFullPath);
			String xlsxTestDataFileFullPath=
					String.format("%s%s.xlsx", TestDataProvider.TEST_DATA_BASE_LOCATION,className);
			try {
				testDataList=ExcelFileHandling.getExcelSheetContent(xlsxTestDataFileFullPath, methodName);
				return TestDataProvider.toObjectArray(testDataList);
			} catch (ExcelFileHandlingException e1) {
				TestDataProvider.logger.error("{}:{} Test will be skipped As {} xlsx file was not found as well.",className,methodName,xlsxTestDataFileFullPath);
				throw e1;
			}
		}
	}
}
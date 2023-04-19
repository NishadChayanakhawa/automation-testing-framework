package io.nishadc.automationtestingframework.filehandling;

import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import io.nishadc.automationtestingframework.filehandling.exceptions.FlatFileHandlingException;
import io.nishadc.automationtestingframework.filehandling.exceptions.JsonFileHandlingException;
import io.nishadc.automationtestingframework.logging.LoggerFactory;

/**
 * <b>Class Name</b>: JsonFileHandling<br>
 * <b>Description</b>: Provides an interface to work with Json files<br>
 * @author Nishad Chayanakhawa&lt;nishad.chayanakhawa@gmail.com&gt;
 *
 */
public class JsonFileHandling {
	//logger
	private static final Logger logger=LoggerFactory.create(JsonFileHandling.class);
		
	private JsonFileHandling() {
		//Do Nothing
	}
	
	/**
	 * <b>Method Name</b>: getJsonFileContent<br>
	 * <b>Description</b>: Reads content of flat json file and returns JSON Object.<br>
	 * <b>Returns</b>: JSONObject parsed Json content as {@link org.json.JSONObject JSONObject} <br>
	 * @since v1.0.0
	 * @param fullFilePath full file path as {@link java.lang.String String}
	 * @return JSONObject parsed Json content as {@link org.json.JSONObject JSONObject}
	 * @throws FlatFileHandlingException in case of any issues while reading the JSON file.
	 * @throws JsonFileHandlingException in case of any issues while parsing into JSON object
	 */
	public static JSONObject getJsonFileContent(String fullFilePath) throws FlatFileHandlingException, JsonFileHandlingException {
		JsonFileHandling.logger.debug("getiing json content from file {}",fullFilePath);
		String fileContent=FlatFileHandling.getFileContents(fullFilePath);
		try {
			return new JSONObject(fileContent);
		} catch(JSONException e) {
			JsonFileHandling.logger.error("Error while parsing content as json: {}",e.getMessage(),e);
			throw (JsonFileHandlingException)new JsonFileHandlingException(e.getMessage()).initCause(e);
		}
	}
}
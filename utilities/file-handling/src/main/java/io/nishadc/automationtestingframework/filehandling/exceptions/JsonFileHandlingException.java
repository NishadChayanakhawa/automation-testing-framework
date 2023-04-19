package io.nishadc.automationtestingframework.filehandling.exceptions;

/**
 * <b>Class Name</b>: JsonFileHandlingException<br>
 * <b>Description</b>: Thrown in case of any error while parsing string into Json<br>
 * @author Nishad Chayanakhawa&lt;nishad.chayanakhawa@gmail.com&gt;
 *
 */
public class JsonFileHandlingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JsonFileHandlingException(String exceptionMessage) {
		super(exceptionMessage);
	}
}

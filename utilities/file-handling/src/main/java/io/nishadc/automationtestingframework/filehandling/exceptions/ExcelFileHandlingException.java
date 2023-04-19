package io.nishadc.automationtestingframework.filehandling.exceptions;

/**
 * <b>Class Name</b>: ExcelFileHandlingException.java<br>
 * <b>Description</b>: Thrown in case of issue while reading excel files.<br>
 * @author Nishad Chayanakhawa&lt;nishad.chayanakhawa@gmail.com&gt;
 *
 */
public class ExcelFileHandlingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExcelFileHandlingException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
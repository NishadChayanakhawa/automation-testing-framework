package io.nishadc.automationtestingframework.filehandling.exceptions;

/**
 * <b>Class Name</b>: FlatFileHelperTests.java<br>
 * <b>Description</b>: Thrown in case of any issues while reading flat file.<br>
 * @author Nishad Chayanakhawa &lt;nishad.chayanakhawa@gmail.com&gt;
 *
 */
public class FlatFileHandlingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public FlatFileHandlingException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
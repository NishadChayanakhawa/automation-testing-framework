package io.nishadc.automationtestingframework.logging.exceptions;

/**
 * <b>Exception Name</b>: LoggerInitializationException<br>
 * <b>Description</b>: Exception is runtime and is thrown when log4j setting file is not read successfully.<br>
 * @author Nishad Chayanakhawa &lt;nishad.chayanakhawa@gmail.com&gt;
 *
 */
public class LoggerInitializationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public LoggerInitializationException(String exceptionMessage) {
		super(exceptionMessage);
	}
}

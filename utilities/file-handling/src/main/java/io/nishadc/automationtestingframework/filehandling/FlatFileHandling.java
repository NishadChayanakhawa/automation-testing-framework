package io.nishadc.automationtestingframework.filehandling;

//io packages
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.Logger;

import io.nishadc.automationtestingframework.filehandling.exceptions.FlatFileHandlingException;
import io.nishadc.automationtestingframework.logging.LoggerFactory;

import java.io.BufferedReader;
//logger


/**
* <b>Class Name</b>: FlatFileHelper<br>
* <b>Description</b>: Provides an interface to work with flat files.<br>
* @author Nishad Chayanakhawa&lt;nishad.chayanakhawa@gmail.com&gt;
*
*/
public class FlatFileHandling {
	//logger
	private static final Logger logger=LoggerFactory.create(FlatFileHandling.class);
	
	private FlatFileHandling() {
		//Do Nothing. Class will never be instantiated.
	}
	
	/**
	 * <b>Method Name</b>: getFileContents<br>
	 * <b>Description</b>: Returns text content of flat file.<br>
	 * @since v1.0.0
	 * @param fullFilePath full path to text file as {@link java.lang.String String}
	 * @return File content as {@link java.lang.String String}
	 * @throws FlatFileHandlingException in case of any issue while reading from flat file.
	 */
	public static String getFileContents(String fullFilePath) throws FlatFileHandlingException {
		FlatFileHandling.logger.debug("Reading flat file: {}",fullFilePath);
		
		StringBuilder contentBuilder=new StringBuilder();
		try(BufferedReader bufferedReader=new BufferedReader(new FileReader(fullFilePath))) {
			String fileContentLine;
			while((fileContentLine=bufferedReader.readLine()) != null) {
				contentBuilder.append(fileContentLine).append("\n");
			}
			return contentBuilder.toString();
		} catch (IOException e) {
			FlatFileHandling.logger.error("Error accessing file: {}",fullFilePath,e);
			throw (FlatFileHandlingException)new FlatFileHandlingException(e.getMessage()).initCause(e);
		}
	}
}


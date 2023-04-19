package io.nishadc.automationtestingframework.logging;

//io packages
import java.io.IOException;
import java.io.InputStream;
//log4j packages
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
//exceptions

import io.nishadc.automationtestingframework.logging.exceptions.LoggerInitializationException;

/**
* <b>Class Name</b>: LoggerFactory<br>
* <b>Description</b>: 
* Provides an interface to create Log4j Logger instances. Has custom setting file pre-loaded which sets the logger to 
* publish all levels to a file and info and up levels to console.<br>
* @author Nishad Chayanakhawa &lt;nishad.chayanakhawa@gmail.com&gt;
*
*/
public class LoggerFactory {
	//flag to determine if custom setting loading was successful
	private static boolean isLoggerCustomSettingLoaded=false;
	//custom setting file name
	protected static String log4j2SettingFileName="log4j2.xml";
	
	protected LoggerFactory() {
		//Do Nothing. Object will never be instantiated as all methods are static.
	}
	
	/**
	 * <b>Method Name</b>: setupLogger<br>
	 * <b>Description</b>: Sets up logger based on setting file LOG4j2_SETTING_FILE_NAME="log4j2.xml"<br>
	 * @since v1.0.0
	 * @throws {@link io.nishadc.automationFramework.utilities.logging.exceptions.LoggerInitializationException LoggerInitializationException} 
	 * in case there is error reading custom settings file.
	 */
	private static void setupLogger() {
		//execute only when logger custom setting is not successfully loaded
		if(!LoggerFactory.isLoggerCustomSettingLoaded) {
			//open input stream
			try(InputStream logSettingsFileStream=
					LoggerFactory.class.getClassLoader().getResourceAsStream(log4j2SettingFileName)) {
				//Configure Log4j
				ConfigurationSource configurationSource=new ConfigurationSource(logSettingsFileStream);
				Configurator.initialize(null,configurationSource);
				//set custom setting successfully loaded flag to true
				LoggerFactory.isLoggerCustomSettingLoaded=true;
			} catch (NullPointerException | IOException e) {
				//set custom setting successfully loaded flag to false
				LoggerFactory.isLoggerCustomSettingLoaded=false;
				//throw runtime exception 
				String exceptionMessage=String.format("Failed to read log4j2 setting file '%s'. %s", log4j2SettingFileName,e.getMessage());
				throw (LoggerInitializationException)new LoggerInitializationException(exceptionMessage).initCause(e);
			}
		}
	}
	
	/**
	 * <b>Method Name</b>: create<br>
	 * <b>Description</b>: Sets up log4j logger and return Logger instance as well. Only info and above logs will be
	 * printed to console. However, all levels are written to file <i>target/execution.log</i><br>
	 * <b>Returns</b>: {@link org.apache.logging.log4j.Logger Logger} <br>
	 * @since v1.0.0
	 * @param classObject Class object as {@link java.lang.Class Class&lt;?&gt;}
	 * @return
	 */
	public static Logger create(Class<?> classObject) {
		//setup logger
		LoggerFactory.setupLogger();
		//return logger instance
		return LogManager.getLogger(classObject);
	}
}

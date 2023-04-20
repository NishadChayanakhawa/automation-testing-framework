package io.nishadc.automationtestingframework.testngcustomization.process;

import java.util.Date;

public class DateTimeHelper {
	private DateTimeHelper() {
		
	}
	
	public static String getElapsedTime(Date startTimestamp,Date endTimestamp) {
		long elapsedTime=endTimestamp.getTime()-startTimestamp.getTime();
		long elapsedTimeInSeconds=elapsedTime/1000 % 60;
		long elapsedTimeInMinutes=elapsedTime/(1000 * 60) % 60;
		long elapsedTimeInHours=elapsedTime/(1000 * 60 * 60) % 24;
		return String.format("%02d:%02d:%02d", elapsedTimeInHours,elapsedTimeInMinutes,elapsedTimeInSeconds);
	}
}

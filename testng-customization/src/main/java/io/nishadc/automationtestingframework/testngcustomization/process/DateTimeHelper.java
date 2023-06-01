package io.nishadc.automationtestingframework.testngcustomization.process;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateTimeHelper {
	private DateTimeHelper() {
		
	}
	
	public static String getElapsedTime(LocalDateTime startTimestamp,LocalDateTime endTimestamp) {
		Duration elapsedDuration=Duration.between(startTimestamp, endTimestamp);
		return DateTimeHelper.formatDuration(elapsedDuration);
	}
	
	public static String formatDuration(Duration duration) {
		return String.format("%02d:%02d:%02d", duration.toHours(),duration.toMinutesPart(),duration.toSecondsPart());
	}
}

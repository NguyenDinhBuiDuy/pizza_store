package cybersoft.java11.group8.pizza_store.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	public static final String 	DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
	
	public static String toString (LocalDateTime date) {
		return date.format(formatter);
	}
	public static LocalDateTime toDate (String date) {
		return LocalDateTime.parse(date, formatter);
	}
}

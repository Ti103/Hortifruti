package br.com.tipy.hortifruti.datetime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
	public static String getDate() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return localDate.format(dateFormatter);
	}
	
	public static String getTime() {
		LocalTime localTime = LocalTime.now();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return localTime .format(dateFormatter);
	}
	
	public static String getDateTime() {
		return getDate() + "às" + getTime();
	}
}

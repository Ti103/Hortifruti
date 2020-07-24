package br.com.tipy.hortifruti.datetime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
	public static String getDate() {
		LocalDate lt = LocalDate.now();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return lt.format(df);
	}
	
	public static String getTime() {
		LocalTime lt = LocalTime.now();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
		return lt.format(df);
	}
}

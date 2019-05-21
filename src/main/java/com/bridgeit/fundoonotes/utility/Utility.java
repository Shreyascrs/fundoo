package com.bridgeit.fundoonotes.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class Utility {

	public static String todayDate()
	{
		LocalDateTime time=LocalDateTime.now();
		DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
		String datetime=time.format(format);
		return datetime;
		
	}
}

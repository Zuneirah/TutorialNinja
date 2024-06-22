package com.tutorials.Ninja.qa.utils;

import java.util.Date;

public class Utilities {
	
	public static String generateTimeStamp()
	{
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":","_");
		return "abdul"+timestamp+"@gmail.com";
	}

}

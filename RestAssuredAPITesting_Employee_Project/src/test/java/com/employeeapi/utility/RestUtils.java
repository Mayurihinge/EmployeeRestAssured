package com.employeeapi.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public static String empName()
	{
		String generatedstring= RandomStringUtils.randomAlphabetic(1);
		return("John" +generatedstring);
	}

	public static String empSal()
	{
		String generatedstring = RandomStringUtils.randomNumeric(4);
		return(generatedstring);
	}
	
	
	public static String empAge()
	{
		String generatedsString = RandomStringUtils.randomNumeric(2);
		return(generatedsString);
	}
	
}

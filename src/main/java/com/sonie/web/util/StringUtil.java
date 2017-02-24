package com.sonie.web.util;

import java.util.Arrays;
import java.util.List;

public class StringUtil {
	
	private StringUtil() {
		
	}
	
	public static boolean isNullOrEmpty(String string) {
		if (string == null || string.length() == 0) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isNotNullOrEmpty(String string) {
		return !isNullOrEmpty(string);
	}

	public static String listToString(List<String> users) {
		StringBuilder listString = new StringBuilder();
		Separator separator = new Separator();
		
		for (String user : users) {
			listString.append(separator.addSep());
			listString.append(user);
		}
		
		return listString.toString();
	}
	
	public static List<String> stringToList(String string) {
		String[] strings = string.split(",");
		
		return Arrays.asList(strings);
	}

}

class Separator {
	private String sep = "";
	
	public String addSep() {
		String separator = sep;
		this.sep = ",";
		return separator;
	}
}

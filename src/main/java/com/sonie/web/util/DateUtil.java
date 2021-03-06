/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static String convert12UTFTo24WithTimeZone(String time, int timeZone) throws ParseException {
		SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm:ss a");

		String adjustedTime = adjustTime(time, timeZone);
		Date date = parseFormat.parse(adjustedTime);

		return displayFormat.format(date);
	}

	private static String adjustTime(String time, int timeAdjust) {
		String[] times = time.split(":");
		StringBuilder convertedTime = new StringBuilder();
		if (timeAdjust > 0) {
			convertedTime.append(Integer.parseInt(times[0]) + timeAdjust);
		} else {
			int hour = Integer.parseInt(times[0]);
			convertedTime.append(hour-Math.abs(timeAdjust));
		}
		convertedTime.append(":");
		convertedTime.append(times[1]);
		convertedTime.append(":");
		convertedTime.append(times[2]);
		return convertedTime.toString();
	}

	public static String getWeekday(Date date) {
		SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		newDateFormat.applyPattern("EEEE");
		return newDateFormat.format(date);
	}

	public static boolean isWeekday(String day) {
		switch (day) {
		case "Saturday":
		case "Sunday":
			return false;
		default:
			return true;
		}
	}

	public static String getCronDate(String date) {
		String[] strings = date.split(":");

		Calendar calendar = Calendar.getInstance();
		String cron = Integer.parseInt(strings[2]) + " " + Integer.parseInt(strings[1]) + " "
				+ Integer.parseInt(strings[0]) + " " + calendar.get(Calendar.DAY_OF_MONTH) + " "
				+ (calendar.get(Calendar.MONTH) + 1) + " *";
		return cron;
	}

	public static String addOrRemoveMinutes(String set, int minutesBeforeSunset) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date d = df.parse(set);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MINUTE, minutesBeforeSunset);
		
		return df.format(cal.getTime());
	}
	
	public static String getCurrentW3cDateTime() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
		return format.format(new Date()).replaceAll("(.*)(\\d\\d)$", "$1:$2");
	}
}

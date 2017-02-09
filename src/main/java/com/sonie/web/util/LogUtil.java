package com.sonie.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;

public class LogUtil {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	private LogUtil() {
		
	}
	
	public static void logWithTime(final Logger LOG, String message) {
		String formated = "[{}]: " + message;
		LOG.info(formated, dateFormat.format(new Date()));
	}
}

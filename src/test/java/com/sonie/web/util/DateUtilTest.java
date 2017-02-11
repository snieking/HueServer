package com.sonie.web.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class DateUtilTest {
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void testWeekday() throws ParseException {
		assertEquals("Monday", DateUtil.getWeekday(format.parse("06/02/2017")));
		assertEquals("Tuesday", DateUtil.getWeekday(format.parse("07/02/2017")));
		assertEquals("Wednesday", DateUtil.getWeekday(format.parse("08/02/2017")));
		assertEquals("Thursday", DateUtil.getWeekday(format.parse("09/02/2017")));
		assertEquals("Friday", DateUtil.getWeekday(format.parse("10/02/2017")));
		assertEquals("Saturday", DateUtil.getWeekday(format.parse("11/02/2017")));
		assertEquals("Sunday", DateUtil.getWeekday(format.parse("12/02/2017")));
	}

	@Test
	public void isWeekday() {
		assertFalse(DateUtil.isWeekday("Saturday"));
		assertFalse(DateUtil.isWeekday("Sunday"));
		assertTrue(DateUtil.isWeekday("Monday"));
		assertTrue(DateUtil.isWeekday("Tuesday"));
		assertTrue(DateUtil.isWeekday("Wednesday"));
		assertTrue(DateUtil.isWeekday("Thursday"));
		assertTrue(DateUtil.isWeekday("Friday"));
	}

	@Test
	public void time12UTFTo24hAdjusted() throws ParseException {
		assertEquals("12:05:02", DateUtil.convert12UTFTo24WithTimeZone("2:05:02 PM", -2));
		assertEquals("23:05:02", DateUtil.convert12UTFTo24WithTimeZone("1:05:02 AM", -2));
		assertEquals("01:05:02", DateUtil.convert12UTFTo24WithTimeZone("11:05:02 PM", 2));
		assertEquals("01:05:02", DateUtil.convert12UTFTo24WithTimeZone("11:05:01 PM", 2));
	}
	
	@Test
	public void minutesBefore() throws ParseException {
		assertEquals("12:00:00", DateUtil.addOrRemoveMinutes("13:00:00", -60));
		assertEquals("23:59:00", DateUtil.addOrRemoveMinutes("01:00:00", -61));
		assertEquals("00:10:00", DateUtil.addOrRemoveMinutes("23:50:00", 20));
	}

	@Test
	public void cronJobDate() {
		Calendar calendar = Calendar.getInstance();
		assertEquals("0 14 19 " + calendar.get(Calendar.DAY_OF_MONTH) + " " + (calendar.get(Calendar.MONTH) + 1) + " *",
				DateUtil.getCronDate("19:14:00"));
	}
}

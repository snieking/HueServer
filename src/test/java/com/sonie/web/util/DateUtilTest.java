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
	public void time12To24h() throws ParseException {
		assertEquals("12:05:02", DateUtil.convert12To24("2:05:02 PM", -2));
	}

	@Test
	public void cronJobDate() {
		Calendar calendar = Calendar.getInstance();
		assertEquals("0 14 19 " + calendar.get(Calendar.DAY_OF_MONTH) + " " + (calendar.get(Calendar.MONTH) + 1) + " *",
				DateUtil.getCronDate("19:14:00"));
	}
}

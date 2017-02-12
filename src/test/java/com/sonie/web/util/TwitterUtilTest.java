package com.sonie.web.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;

public class TwitterUtilTest {
	private static final String LIRIK_LIVE = ".*[(L I V E)*(LIVE)*(Live)*(live)*(l i v e)*][^HOUR|hour|Hour]+";

	@Test
	public void testRegex() {
		assertTrue(Pattern.matches(".*[AboveAndBeyond]+.*", "Gogo #AboveAndBeyond!!"));
		assertFalse(Pattern.matches(".*Saturday.*", "What a lovely day this is"));
		assertFalse(Pattern.matches(LIRIK_LIVE, "L I V E IN AN HOUR"));
		assertTrue(Pattern.matches(LIRIK_LIVE, "L I V E"));
		assertTrue(Pattern.matches(LIRIK_LIVE, "LIVE"));
		assertTrue(Pattern.matches(LIRIK_LIVE, "WE ARE LIVE!"));
	}
}

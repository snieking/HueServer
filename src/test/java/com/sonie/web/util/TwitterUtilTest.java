package com.sonie.web.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TwitterUtilTest {
	private static final String LIRIK_LIVE = "(?!.*hour)(.*?(live|l i v e).*)";

	@Test
	public void testRegex() {
		assertTrue(Pattern.matches(".*[AboveAndBeyond]+.*", "Gogo #AboveAndBeyond!!"));
		assertFalse(Pattern.matches(".*Saturday.*", "What a lovely day this is"));
		assertTrue(getMatcher(getString("LIVE \nILLEGALTEST")).matches());
		assertFalse(getMatcher(getString("L I V E IN AN HOUR").replaceAll("\n", " ")).matches());
		assertTrue(getMatcher(getString("l i v e")).matches());
		assertTrue(getMatcher(getString("LIVE")).matches());
		assertTrue(getMatcher(getString("WE ARE LIVE!")).matches());
		assertFalse(getMatcher(getString("Live in an hour")).matches());
		assertFalse(getMatcher(getString("I liked a @YouTube video http://youtu.be/0p83F7NU6mI?a  Call on me for honor")).matches());
	}
	
	private Matcher getMatcher(String message) {
		Pattern pattern = Pattern.compile(LIRIK_LIVE, Pattern.CASE_INSENSITIVE);
		return pattern.matcher(message);
	}
	
	private String getString(String input) {
		return input.replaceAll("\n", " ");
	}
}

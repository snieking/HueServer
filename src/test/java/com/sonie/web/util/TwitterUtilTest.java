package com.sonie.web.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;

public class TwitterUtilTest {

	@Test
	public void testRegex() {
		assertTrue(Pattern.matches(".*[AboveAndBeyond]+.*", "Gogo #AboveAndBeyond!!"));
		assertFalse(Pattern.matches(".*Saturday.*", "What a lovely day this is"));
	}
}

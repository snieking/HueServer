package com.sonie.web.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;

public class TwitterUtilTest {

	@Test
	public void testRegex() {
		assertTrue(Pattern.matches(".*[AboveAndBeyond]+.*", "Heja #AboveAndBeyond!!"));
		assertFalse(Pattern.matches(".*Saturday.*", "went with @SonieTV for a fun night rides after a first snow"));
	}
}

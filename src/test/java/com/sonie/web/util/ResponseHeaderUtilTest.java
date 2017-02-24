package com.sonie.web.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sonie.web.resources.internal.ResponseHeader;

public class ResponseHeaderUtilTest {
	private ResponseHeader responseHeader;
	
	@Before
	public void setup() {
		responseHeader = ResponseBuilder.createSuccessfulResponse();
	}
	
	@Test
	public void createSuccessfulResponseHeader() {
		assertEquals(9000, responseHeader.getCode());
		assertEquals("Executed Successfully", responseHeader.getResult());
	}
}

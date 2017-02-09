package com.sonie.web.util;

import resources.internal.ResponseHeader;

public class ResponseBuilder {
	private static final String SUCCESS = "Executed Successfuly";
	private static final int SUCCESS_CODE = 9000;

	private ResponseBuilder() {
		
	}

	public static ResponseHeader createSuccessfulResponse() {
		ResponseHeader header = new ResponseHeader();
		header.setCode(SUCCESS_CODE);
		header.setResult(SUCCESS);

		return header;
	}

}

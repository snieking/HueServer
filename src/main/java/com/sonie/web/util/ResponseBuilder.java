/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.util;

import com.sonie.web.resources.internal.ResponseHeader;

public class ResponseBuilder {
	private static final String SUCCESS = "Executed Successfully";
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

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.resources.hue;

import com.sonie.web.resources.internal.ResponseHeader;

/**
 * Used for response from set scene.
 * 
 * @author viktorplane
 */
public class HueSetSceneResponse {
	private ResponseHeader responseHeader;

	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}

}

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.util;

import org.springframework.web.client.RestTemplate;

public class RequestUtil {
	
	/**
	 * Used to put generic requests to localhost url.
	 * 
	 * @param url
	 * @param request
	 */
	public static <T> void localhostPut(String url, T request) {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.put("http://localhost:9000" + url, request);
	}
}

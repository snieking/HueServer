package com.sonie.web.util;

import org.springframework.web.client.RestTemplate;

public class RequestUtil {
	
	public static <T> void asyncLocalhostPut(String url, T request) {
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.put("http://localhost:9000" + url, request);
	}
}

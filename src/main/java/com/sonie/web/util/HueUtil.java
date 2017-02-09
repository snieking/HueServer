package com.sonie.web.util;

import org.springframework.web.client.RestTemplate;

import resources.internal.HueSetSceneRequest;

public class HueUtil {
	
	private HueUtil() {
		
	}
	
	public static void putGroupRequest(HueSetSceneRequest request, String ip, String user) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put("http://192.168.1.69/api/" + user + "/groups/"
				+ request.getGroup() + "/action", request);
	}
}

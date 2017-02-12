/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.util;

import org.springframework.web.client.RestTemplate;

import resources.internal.HueSetSceneRequest;

public class HueUtil {
	
	private HueUtil() {
		
	}
	
	public static void putGroupRequest(HueSetSceneRequest request, String ip, String user) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(ip + "/api/" + user + "/groups/"
				+ request.getGroup() + "/action", request);
	}

	public static void blinkLights(String ip, String user, String group) {
		RestTemplate restTemplate = new RestTemplate();
		HueSetSceneRequest request = new HueSetSceneRequest();
		request.setAlert("select");

		String url = "http://" + ip + "/api/" + user + "/groups/" + group + "/action";
		restTemplate.put(url, request);
	}
}

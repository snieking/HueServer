/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.util;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import resources.internal.HueAddNewUserRequest;
import resources.internal.HueAddNewUserResponse;
import resources.internal.HueIP;
import resources.internal.HueSetSceneRequest;

public class HueUtil {

	private HueUtil() {

	}

	public static void putGroupRequest(HueSetSceneRequest request, String ip, String user) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(ip + "/api/" + user + "/groups/" + request.getGroup() + "/action", request);
	}

	public static void blinkLights(String ip, String user, String group) {
		RestTemplate restTemplate = new RestTemplate();
		HueSetSceneRequest request = new HueSetSceneRequest();
		request.setAlert("select");

		String url = "http://" + ip + "/api/" + user + "/groups/" + group + "/action";
		restTemplate.put(url, request);
	}

	/**
	 * Get the IP address of the Hue Bridge.
	 * 
	 * @return
	 */
	public static String getIpRequest() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<HueIP>> hueIPResponse = restTemplate.exchange("https://www.meethue.com/api/nupnp", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<HueIP>>() {
				});
		List<HueIP> results = hueIPResponse.getBody();

		return results.get(0).getInternalipaddress();
	}

	public static String getNewUser(String ip) throws NullPointerException {
		RestTemplate restTemplate = new RestTemplate();
		HueAddNewUserRequest request = new HueAddNewUserRequest();
		request.setDevicetype("hueserver");

		HueAddNewUserResponse[] response = restTemplate.postForObject("http://" + ip + "/api", request, HueAddNewUserResponse[].class);
		
		return response[0].getSuccess().getUsername();
	}
}

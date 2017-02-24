/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sonie.web.resources.config.ApplicationConfiguration;
import com.sonie.web.resources.hue.HueAddNewUserRequest;
import com.sonie.web.resources.hue.HueAddNewUserResponse;
import com.sonie.web.resources.hue.HueCreateGroupRequest;
import com.sonie.web.resources.hue.HueIP;
import com.sonie.web.resources.hue.HueLight;
import com.sonie.web.resources.hue.HueSetSceneRequest;
import com.sonie.web.resources.view.hue.HueGroup;

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

	public static Map<String, String> getScenesWithId(ApplicationConfiguration config) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> scenes = new HashMap<>();
		
		String url = "http://" + config.getHue().getIp() + "/api/" + config.getHue().getUser() + "/scenes";
		ResponseEntity<String> hueScenesResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		});
		
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(hueScenesResponse.getBody()).getAsJsonObject();
		
		for (Map.Entry<String,JsonElement> entry : jsonObject.entrySet()) {
		    scenes.put(entry.getKey(), entry.getValue().getAsJsonObject().get("name").toString());
		}
		
		return scenes;
	}

	public static Map<String, HueGroup> getGroupsWithId(ApplicationConfiguration config) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, HueGroup> groups = new HashMap<>();
		
		String url = "http://" + config.getHue().getIp() + "/api/" + config.getHue().getUser() + "/groups";
		ResponseEntity<String> hueGroupsResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		});
		
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(hueGroupsResponse.getBody()).getAsJsonObject();
		
		for (Map.Entry<String,JsonElement> entry : jsonObject.entrySet()) {
			String id = entry.getKey();
			HueGroup group = new HueGroup();
			List<String> lights = new ArrayList<>();
			group.setName(entry.getValue().getAsJsonObject().get("name").toString());
			
			JsonArray jsonArray = entry.getValue().getAsJsonObject().get("lights").getAsJsonArray();
			
			for (JsonElement obj : jsonArray) {
				lights.add(obj.toString());
			}
			
			group.setLights(lights);
			groups.put(id, group);
		}
		
		return groups;
	}
	
	public static Map<String, HueLight> getLightsWithId(ApplicationConfiguration config) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, HueLight> lights = new HashMap<>();
		
		String url = "http://" + config.getHue().getIp() + "/api/" + config.getHue().getUser() + "/lights";
		ResponseEntity<String> hueGroupsResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		});
		
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(hueGroupsResponse.getBody()).getAsJsonObject();
		
		for (Map.Entry<String,JsonElement> entry : jsonObject.entrySet()) {
			String id = entry.getKey();
			HueLight light = new HueLight();
			
			light.setName(entry.getValue().getAsJsonObject().get("name").toString());
			light.setType(entry.getValue().getAsJsonObject().get("type").toString());

			lights.put(id, light);
		}
		
		return lights;
	}
	
	public static void deleteSceneWithId(String id, ApplicationConfiguration config) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://" + config.getHue().getIp() + "/api/" + config.getHue().getUser() + "/scenes/" + id; 
		
		restTemplate.delete(url);
	}

	public static void deleteGroupWithId(String id, ApplicationConfiguration config) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://" + config.getHue().getIp() + "/api/" + config.getHue().getUser() + "/groups/" + id; 
		
		restTemplate.delete(url);
	}

	public static void createGroupWithId(String name, String lights, ApplicationConfiguration config) {
		RestTemplate restTemplate = new RestTemplate();
		
		HueCreateGroupRequest request = new HueCreateGroupRequest();
		request.setName(name);
		request.setLights(StringUtil.stringToList(lights));
		request.setType("LightGroup");
		restTemplate.postForObject("http://" + config.getHue().getIp() + "/api/" + config.getHue().getUser() + "/groups", request, String.class);
		// TODO: handle response
	}


}

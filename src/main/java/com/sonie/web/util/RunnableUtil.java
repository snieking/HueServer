package com.sonie.web.util;

import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;

import resources.internal.HueSetSceneRequest;

public class RunnableUtil {
	
	public static Runnable setSunSet(final Logger LOG, final String user) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				LogUtil.logWithTime(LOG, "Sunset occured!");
				
				RestTemplate restTemplate = new RestTemplate();

				HueSetSceneRequest request = new HueSetSceneRequest();
				request.setGroup("1");
				request.setScene("93yv8JekmAneCU9");

				restTemplate.put("http://192.168.1.69/api/" + user + "/groups/"
						+ request.getGroup() + "/action", request);
			}
		};

		return runnable;
	}
	
	public static Runnable setSunRise(final Logger LOG, final String user) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				LogUtil.logWithTime(LOG, "Sunrise occured!");
				
				RestTemplate restTemplate = new RestTemplate();

				HueSetSceneRequest request = new HueSetSceneRequest();
				request.setGroup("1");
				request.setOn(false);

				restTemplate.put("http://192.168.1.69/api/" + user + "/groups/"
						+ request.getGroup() + "/action", request);
			}
		};

		return runnable;
	}

	public static Runnable setGoodMorning(final Logger LOG, final String user) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				LogUtil.logWithTime(LOG, "Good morning!");
				
				RestTemplate restTemplate = new RestTemplate();

				HueSetSceneRequest request = new HueSetSceneRequest();
				request.setGroup("1");
				request.setScene("goodmorning");

				restTemplate.put("http://192.168.1.69/api/" + user + "/groups/"
						+ request.getGroup() + "/action", request);
			}
		};

		return runnable;
	}

	public static Runnable printMessage(final Logger LOG) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				LogUtil.logWithTime(LOG, "Test message");
			}
		};

		return runnable;
	}
}

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.util;

import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;

import resources.internal.Hue;
import resources.internal.Hue.Scene.Evening;
import resources.internal.Hue.Scene.GoodMorning;
import resources.internal.Hue.Scene.GoodNight;
import resources.internal.Hue.Scene.Sunstatus;
import resources.internal.HueSetSceneRequest;

public class RunnableUtil {

	public static Runnable setSunSet(final Logger LOG, final Hue hue) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Sunstatus sunstatus = hue.getScene().getSunstatus();
				LogUtil.logWithTime(LOG, "Sunset occured!");

				RestTemplate restTemplate = new RestTemplate();

				HueSetSceneRequest request = new HueSetSceneRequest();
				request.setGroup(sunstatus.getGroup());
				request.setScene(sunstatus.getId());

				restTemplate.put("http://" + hue.getIp() + "/api/" + hue.getUser() + "/groups/" + request.getGroup() + "/action",
						request);
			}
		};

		return runnable;
	}

	public static Runnable setSunRise(final Logger LOG, final Hue hue) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Sunstatus sunstatus = hue.getScene().getSunstatus();
				LogUtil.logWithTime(LOG, "Sunrise occured!");

				RestTemplate restTemplate = new RestTemplate();

				HueSetSceneRequest request = new HueSetSceneRequest();
				request.setGroup(sunstatus.getGroup());
				request.setOn(false);

				restTemplate.put("http://" + hue.getIp() + "/api/" + hue.getUser() + "/groups/" + request.getGroup() + "/action",
						request);
			}
		};

		return runnable;
	}

	public static Runnable setGoodMorning(final Logger LOG, final Hue hue) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				GoodMorning goodMorning = hue.getScene().getGoodMorning();
				LogUtil.logWithTime(LOG, "Good morning!");

				RestTemplate restTemplate = new RestTemplate();

				HueSetSceneRequest request = new HueSetSceneRequest();
				request.setGroup(goodMorning.getGroup());
				request.setScene(goodMorning.getId());

				restTemplate.put("http://" + hue.getIp() + "/api/" + hue.getUser() + "/groups/" + request.getGroup() + "/action",
						request);
			}
		};

		return runnable;
	}

	public static Runnable setGoodNight(final Logger LOG, final Hue hue) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				GoodNight goodNight = hue.getScene().getGoodNight();
				LogUtil.logWithTime(LOG, "Good night!");

				RestTemplate restTemplate = new RestTemplate();

				HueSetSceneRequest request = new HueSetSceneRequest();
				request.setGroup(goodNight.getGroup());
				request.setOn(false);

				restTemplate.put("http://" + hue.getIp() + "/api/" + hue.getUser() + "/groups/" + request.getGroup() + "/action",
						request);
			}
		};

		return runnable;
	}

	public static Runnable setEvening(final Logger LOG, final Hue hue) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Evening evening = hue.getScene().getEvening();
				LogUtil.logWithTime(LOG, "Good evening!");

				RestTemplate restTemplate = new RestTemplate();

				HueSetSceneRequest request = new HueSetSceneRequest();
				request.setGroup(evening.getGroup());
				request.setScene(evening.getId());

				restTemplate.put("http://" + hue.getIp() + "/api/" + hue.getUser() + "/groups/" + request.getGroup() + "/action",
						request);
			}
		};

		return runnable;
	}
}

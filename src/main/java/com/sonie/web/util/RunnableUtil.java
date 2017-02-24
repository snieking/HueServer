/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.util;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sonie.web.events.LightEvent;
import com.sonie.web.events.LightEventRepository;
import com.sonie.web.resources.hue.Hue;
import com.sonie.web.resources.hue.HueSetSceneRequest;
import com.sonie.web.resources.hue.Hue.Scene.Evening;
import com.sonie.web.resources.hue.Hue.Scene.GoodMorning;
import com.sonie.web.resources.hue.Hue.Scene.GoodNight;
import com.sonie.web.resources.hue.Hue.Scene.Sunstatus;

@Service
public class RunnableUtil {
	
	@Autowired
	private LightEventRepository lightEventRepository;

	public Runnable setSunSet(final Logger LOG, final Hue hue) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Sunstatus sunstatus = hue.getScene().getSunstatus();
				
				LogUtil.logWithTime(LOG, "Sunset occured!");
				lightEventRepository.save(new LightEvent(DateUtil.getCurrentW3cDateTime(), "Sunset"));
				
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

	public Runnable setSunRise(final Logger LOG, final Hue hue) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Sunstatus sunstatus = hue.getScene().getSunstatus();
				
				LogUtil.logWithTime(LOG, "Sunrise occured!");
				lightEventRepository.save(new LightEvent(DateUtil.getCurrentW3cDateTime(), "Sunrise"));
				
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

	public Runnable setGoodMorning(final Logger LOG, final Hue hue) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				GoodMorning goodMorning = hue.getScene().getGoodMorning();
				
				LogUtil.logWithTime(LOG, "Good morning!");
				lightEventRepository.save(new LightEvent(DateUtil.getCurrentW3cDateTime(), "Morning"));

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

	public Runnable setGoodNight(final Logger LOG, final Hue hue) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				GoodNight goodNight = hue.getScene().getGoodNight();
				
				LogUtil.logWithTime(LOG, "Good night!");
				lightEventRepository.save(new LightEvent(DateUtil.getCurrentW3cDateTime(), "Night"));

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

	public Runnable setEvening(final Logger LOG, final Hue hue) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Evening evening = hue.getScene().getEvening();
				
				LogUtil.logWithTime(LOG, "Good evening!");
				lightEventRepository.save(new LightEvent(DateUtil.getCurrentW3cDateTime(), "Evening"));

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

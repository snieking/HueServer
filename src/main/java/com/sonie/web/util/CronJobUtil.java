package com.sonie.web.util;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.client.RestTemplate;

import com.sonie.web.resources.weather.SunStatusResponse;

import resources.internal.Hue;
import resources.internal.Hue.Scene.GoodNight;
import resources.internal.HueSetSceneRequest;

@EnableScheduling
public class CronJobUtil {
	private static final Logger LOG = LoggerFactory.getLogger(CronJobUtil.class);

	private CronJobUtil() {

	}

	public static void setDailySunJobs(TaskScheduler scheduler, Hue hue) throws ParseException {
		if (hue.getScene().getSunstatus().isEnabled()) {
			RestTemplate restTemplate = new RestTemplate();
			SunStatusResponse response = restTemplate.getForObject(
					"http://api.sunrise-sunset.org/json?lat=59.200197&lng=17.828536&date=today",
					SunStatusResponse.class);

			String set = DateUtil.convert12To24(response.getResults().getSunset());
			LOG.info("Sunset scheduled for [{}]", set);
			scheduler.schedule(RunnableUtil.setSunSet(LOG, hue), new CronTrigger(DateUtil.getCronDate(set)));

			String rise = DateUtil.convert12To24(response.getResults().getSunrise());
			LOG.info("Sunrise scheduled for [{}]", rise);
			scheduler.schedule(RunnableUtil.setSunRise(LOG, hue), new CronTrigger(DateUtil.getCronDate(rise)));
		}
	}

	public static void setSnow(Hue hue) {
		LogUtil.logWithTime(LOG, "Set snow");

		HueSetSceneRequest request = new HueSetSceneRequest();
		request.setGroup("1");
		request.setOn(true);
		request.setScene("93yv8JekmAneCU9");

		HueUtil.putGroupRequest(request, hue.getUser(), hue.getIp());
	}

	@Scheduled
	public static void turnOffAllLights(Hue hue) {
		GoodNight goodNight = hue.getScene().getGoodNight();
		if (goodNight.isEnabled()) {
			LogUtil.logWithTime(LOG, "Turn off all lights");

			HueSetSceneRequest request = new HueSetSceneRequest();
			request.setGroup(goodNight.getGroup());
			request.setOn(false);

			HueUtil.putGroupRequest(request, hue.getUser(), hue.getIp());
		}
	}

	public static void setGoodMorningJob(TaskScheduler scheduler, Hue hue) throws ParseException {
		if (hue.getScene().getGoodMorning().isEnabled()) {
			if (DateUtil.isWeekday(DateUtil.getWeekday(new Date()))) {
				scheduler.schedule(RunnableUtil.setGoodMorning(LOG, hue),
						new CronTrigger(DateUtil.getCronDate("04:59:00")));
			}
		}
	}

}

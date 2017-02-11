package com.sonie.web.util;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.client.RestTemplate;

import com.sonie.web.resources.weather.SunStatusResponse;
import com.sonie.web.usecase.TwitterUseCase;

import resources.internal.Configuration;
import resources.internal.General;
import resources.internal.Hue;
import resources.internal.Hue.Scene.Sunstatus;
import resources.internal.HueSetSceneRequest;

@EnableScheduling
public class CronJobUtil {
	private static final Logger LOG = LoggerFactory.getLogger(CronJobUtil.class);

	@Autowired
	private static TwitterUseCase twitterUseCase;

	private CronJobUtil() {

	}

	public static void setDailySunJobs(TaskScheduler scheduler, Configuration config) throws ParseException {
		Sunstatus sunstatus = config.getHue().getScene().getSunstatus();
		General general = config.getGeneral();

		if (sunstatus.isEnabled()) {
			RestTemplate restTemplate = new RestTemplate();
			SunStatusResponse response = restTemplate.getForObject("http://api.sunrise-sunset.org/json?lat="
					+ general.getLatitude() + "&lng=" + general.getLongitude() + "&date=today",
					SunStatusResponse.class);

			String set = DateUtil.convert12UTFTo24WithTimeZone(response.getResults().getSunset(),
					general.getTimeZone());
			String adjustedSunsetTime = DateUtil.addOrRemoveMinutes(set,
					config.getHue().getScene().getSunstatus().getSunsetAdjustedMinutes());

			LOG.info("Sunset scheduled for [{}]", adjustedSunsetTime);
			scheduler.schedule(RunnableUtil.setSunSet(LOG, config.getHue()),
					new CronTrigger(DateUtil.getCronDate(adjustedSunsetTime)));

			String rise = DateUtil.convert12UTFTo24WithTimeZone(response.getResults().getSunrise(),
					general.getTimeZone());
			String adjustedSunriseTime = DateUtil.addOrRemoveMinutes(rise, sunstatus.getSunriseAdjustedMinutes());

			LOG.info("Sunrise scheduled for [{}]", adjustedSunriseTime);
			scheduler.schedule(RunnableUtil.setSunRise(LOG, config.getHue()),
					new CronTrigger(DateUtil.getCronDate(adjustedSunriseTime)));
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

	public static void setGoodNight(TaskScheduler scheduler, Hue hue) {
		if (hue.getScene().getGoodNight().isEnabled()) {
			scheduler.schedule(RunnableUtil.setGoodNight(LOG, hue),
					new CronTrigger(DateUtil.getCronDate(hue.getScene().getGoodNight().getTime())));
		}
	}

	public static void setGoodMorningJob(TaskScheduler scheduler, Hue hue) throws ParseException {
		if (hue.getScene().getGoodMorning().isEnabled()) {
			if (DateUtil.isWeekday(DateUtil.getWeekday(new Date()))) {
				scheduler.schedule(RunnableUtil.setGoodMorning(LOG, hue),
						new CronTrigger(DateUtil.getCronDate(hue.getScene().getGoodMorning().getTime())));
			}
		}
	}

	public static void setEvening(TaskScheduler scheduler, Hue hue) {
		if (hue.getScene().getEvening().isEnabled()) {
			scheduler.schedule(RunnableUtil.setEvening(LOG, hue),
					new CronTrigger(DateUtil.getCronDate(hue.getScene().getEvening().getTime())));
		}
	}

}

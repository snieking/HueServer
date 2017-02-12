/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.util;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.client.RestTemplate;

import com.sonie.web.resources.weather.SunStatusResponse;

import resources.internal.Configuration;
import resources.internal.General;
import resources.internal.Hue;
import resources.internal.Hue.Scene.Sunstatus;

@EnableScheduling
public class CronJobUtil {
	private static final Logger LOG = LoggerFactory.getLogger(CronJobUtil.class);

	private CronJobUtil() {

	}

	/**
	 * Sets the daily job for the sunset and sunrise.
	 * 
	 * @param scheduler
	 * @param config
	 * @throws ParseException
	 */
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

	/**
	 * Sets the daily job for the night.
	 * 
	 * @param scheduler
	 * @param hue
	 */
	public static void setGoodNightJob(TaskScheduler scheduler, Hue hue) {
		if (hue.getScene().getGoodNight().isEnabled()) {
			scheduler.schedule(RunnableUtil.setGoodNight(LOG, hue),
					new CronTrigger(DateUtil.getCronDate(hue.getScene().getGoodNight().getTime())));
		}
	}

	/**
	 * Sets the daily job for the morning.
	 * 
	 * @param scheduler
	 * @param hue
	 * @throws ParseException
	 */
	public static void setGoodMorningJob(TaskScheduler scheduler, Hue hue) throws ParseException {
		if (hue.getScene().getGoodMorning().isEnabled()) {
			if (DateUtil.isWeekday(DateUtil.getWeekday(new Date()))) {
				LOG.info("Good night time is set for: [{}]", hue.getScene().getGoodNight().getTime());
				scheduler.schedule(RunnableUtil.setGoodMorning(LOG, hue),
						new CronTrigger(DateUtil.getCronDate(hue.getScene().getGoodMorning().getTime())));
			}
		}
	}

	/**
	 * Sets the daily job for the evening.
	 * 
	 * @param scheduler
	 * @param hue
	 */
	public static void setEveningJob(TaskScheduler scheduler, Hue hue) {
		if (hue.getScene().getEvening().isEnabled()) {
			LOG.info("Evening time is set for: [{}]", hue.getScene().getEvening().getTime());
			scheduler.schedule(RunnableUtil.setEvening(LOG, hue),
					new CronTrigger(DateUtil.getCronDate(hue.getScene().getEvening().getTime())));
		}
	}
	
	/**
	 * Used to schedule all the daily jobs.
	 * 
	 * @param scheduler
	 * @param config
	 */
	public static void setDailyJobs(TaskScheduler scheduler, Configuration config) {
		try {
			setEveningJob(scheduler, config.getHue());
			setGoodMorningJob(scheduler, config.getHue());
			setGoodNightJob(scheduler, config.getHue());
			setDailySunJobs(scheduler, config);
		} catch (ParseException e) {
			SonarUtil.swallowException(e);
			LOG.warn("Could not parse a time which means that the daily jobs could not get scheduled. Check your config file that the times are in the right format.");
		}
	}

}

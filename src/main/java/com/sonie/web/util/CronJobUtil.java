/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.util;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sonie.web.resources.config.ApplicationConfiguration;
import com.sonie.web.resources.config.General;
import com.sonie.web.resources.hue.Hue;
import com.sonie.web.resources.hue.Hue.Scene.Sunstatus;
import com.sonie.web.resources.weather.SunStatusResponse;

@EnableScheduling
@Service
public class CronJobUtil {
	private static final Logger LOG = LoggerFactory.getLogger(CronJobUtil.class);
	
	@Autowired
	private RunnableUtil runnableUtil;

	private CronJobUtil() {

	}

	/**
	 * Sets the daily job for the sunset and sunrise.
	 * 
	 * @param scheduler
	 * @param config
	 * @throws ParseException
	 */
	public void setDailySunJobs(TaskScheduler scheduler, ApplicationConfiguration config) throws ParseException {
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
			scheduler.schedule(runnableUtil.setSunSet(LOG, config.getHue()),
					new CronTrigger(DateUtil.getCronDate(adjustedSunsetTime)));

			String rise = DateUtil.convert12UTFTo24WithTimeZone(response.getResults().getSunrise(),
					general.getTimeZone());
			String adjustedSunriseTime = DateUtil.addOrRemoveMinutes(rise, sunstatus.getSunriseAdjustedMinutes());

			LOG.info("Sunrise scheduled for [{}]", adjustedSunriseTime);
			scheduler.schedule(runnableUtil.setSunRise(LOG, config.getHue()),
					new CronTrigger(DateUtil.getCronDate(adjustedSunriseTime)));
		}
	}

	/**
	 * Sets the daily job for the night.
	 * 
	 * @param scheduler
	 * @param hue
	 */
	public void setGoodNightJob(TaskScheduler scheduler, Hue hue) {
		if (hue.getScene().getGoodNight().isEnabled()) {
			scheduler.schedule(runnableUtil.setGoodNight(LOG, hue),
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
	public void setGoodMorningJob(TaskScheduler scheduler, Hue hue) throws ParseException {
		if (hue.getScene().getGoodMorning().isEnabled()) {
			if (DateUtil.isWeekday(DateUtil.getWeekday(new Date()))) {
				LOG.info("Good night time is set for: [{}]", hue.getScene().getGoodNight().getTime());
				scheduler.schedule(runnableUtil.setGoodMorning(LOG, hue),
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
	public void setEveningJob(TaskScheduler scheduler, Hue hue) {
		if (hue.getScene().getEvening().isEnabled()) {
			LOG.info("Evening time is set for: [{}]", hue.getScene().getEvening().getTime());
			scheduler.schedule(runnableUtil.setEvening(LOG, hue),
					new CronTrigger(DateUtil.getCronDate(hue.getScene().getEvening().getTime())));
		}
	}
	
	/**
	 * Used to schedule all the daily jobs.
	 * 
	 * @param scheduler
	 * @param config
	 */
	public void setDailyJobs(TaskScheduler scheduler, ApplicationConfiguration config) {
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

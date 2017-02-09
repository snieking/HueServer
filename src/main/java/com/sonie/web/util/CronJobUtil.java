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

import resources.internal.HueSetSceneRequest;

@EnableScheduling
public class CronJobUtil {
	private static final Logger LOG = LoggerFactory.getLogger(CronJobUtil.class);
	
	private CronJobUtil() {
		
	}

	public static void setDailySunJobs(TaskScheduler scheduler, String user) throws ParseException {
		RestTemplate restTemplate = new RestTemplate();
		SunStatusResponse response = restTemplate.getForObject(
				"http://api.sunrise-sunset.org/json?lat=59.200197&lng=17.828536&date=today", SunStatusResponse.class);

		String set = DateUtil.convert12To24(response.getResults().getSunset());
		LOG.info("Sunset scheduled for [{}]", set);
		scheduler.schedule(RunnableUtil.setSunSet(LOG, user), new CronTrigger(DateUtil.getCronDate(set)));

		String rise = DateUtil.convert12To24(response.getResults().getSunrise());
		LOG.info("Sunrise scheduled for [{}]", rise);
		scheduler.schedule(RunnableUtil.setSunRise(LOG, user), new CronTrigger(DateUtil.getCronDate(rise)));
	}
	
	public static void setSnow(String user) {
		LogUtil.logWithTime(LOG, "Set snow");

		HueSetSceneRequest request = new HueSetSceneRequest();
		request.setGroup("1");
		request.setOn(true);
		request.setScene("93yv8JekmAneCU9");

		HueUtil.putGroupRequest(request, user);
	}
	
	
	
	@Scheduled
	public static void turnOffAllLights(String user) {
		LogUtil.logWithTime(LOG, "Turn off all lights");
		
		HueSetSceneRequest request = new HueSetSceneRequest();
		request.setGroup("6");
		request.setOn(false);
		
		HueUtil.putGroupRequest(request, user);
	}

	public static void setGoodMorningJob(TaskScheduler scheduler, String user) throws ParseException {
		if(DateUtil.isWeekday(DateUtil.getWeekday(new Date()))) {
			scheduler.schedule(RunnableUtil.setGoodMorning(LOG, user), new CronTrigger(DateUtil.getCronDate("04:59:00")));
		}
	}
	
	/**
	 * For testing purposes only.
	 * 
	 * @param scheduler
	 * @throws ParseException
	 */
	public static void printMessage(TaskScheduler scheduler) throws ParseException {
		CronTrigger trigger = new CronTrigger(DateUtil.getCronDate("19:20:00"));
		scheduler.schedule(RunnableUtil.printMessage(LOG), trigger);
	}
}
package com.sonie.web;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.sonie.web.util.CronJobUtil;

import resources.internal.Hue;

@SpringBootApplication(scanBasePackages={"resources.internal","com.sonie.web"})
@EnableScheduling
public class App {
	
	@Autowired
	private Hue hue;
	
	@Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("poolScheduler");
        scheduler.setPoolSize(10);
        return scheduler;
    }

	@Scheduled(cron = "0 0 01 * * *")
	public void dailyJob() throws ParseException {
		CronJobUtil.setDailySunJobs(poolScheduler(), hue.getUser(), hue.getIp());
		CronJobUtil.setGoodMorningJob(poolScheduler(), hue.getUser(), hue.getIp());
	}

	@Scheduled(cron = "0 0 02 * * *")
	public void dailyTurnOff() {
		CronJobUtil.turnOffAllLights(hue.getUser(), hue.getIp());
	}

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(App.class, args);
	}

}

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web;

import java.text.ParseException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.sonie.web.resources.twitter.TwitterScanRequest;
import com.sonie.web.util.CronJobUtil;
import com.sonie.web.util.RequestUtil;
import com.sonie.web.util.TwitterUtil;

import resources.internal.Configuration;
import resources.internal.StringUtil;

@SpringBootApplication(scanBasePackages = { "resources.internal", "com.sonie.web" })
@EnableScheduling
public class App {

	@Autowired
	private Configuration configuration;

	@Bean
	public TaskScheduler poolScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setThreadNamePrefix("poolScheduler");
		scheduler.setPoolSize(10);
		return scheduler;
	}

	@PostConstruct
	public void dailyAfterStartup() throws ParseException {
		if (StringUtil.isNotNullOrEmpty(configuration.getHue().getIp())) {
			CronJobUtil.setDailyJobs(poolScheduler(), configuration);
		}
	}

	@Scheduled(cron = "1 0 0 * * *")
	public void dailyJob() throws ParseException {
		if (StringUtil.isNotNullOrEmpty(configuration.getHue().getIp())) {
			CronJobUtil.setDailyJobs(poolScheduler(), configuration);
		}
	}

	@Scheduled(fixedRate = 90000, initialDelay = 60000)
	public void scanTwitter() {
		if (configuration.getTwitter().isEnabled()) {
			TwitterScanRequest twitterScanRequest = TwitterUtil.createTwitterRequest(configuration);
			RequestUtil.localhostPut("/twitter/tweets", twitterScanRequest);
		}
	}

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(App.class, args);
	}
}

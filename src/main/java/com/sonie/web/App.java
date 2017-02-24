/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web;

import java.text.ParseException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.sonie.web.resources.config.ApplicationConfiguration;
import com.sonie.web.resources.twitter.TwitterScanRequest;
import com.sonie.web.util.CronJobUtil;
import com.sonie.web.util.RequestUtil;
import com.sonie.web.util.StringUtil;
import com.sonie.web.util.TwitterUtil;

@SpringBootApplication(scanBasePackages = { "com.sonie.web" })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableScheduling
@ComponentScan
@EnableCouchbaseRepositories
public class App {

	@Autowired
	private ApplicationConfiguration configuration;
	
	@Autowired
	private CronJobUtil cronJobUtil;

	/**
	 * Initiates a bean used for scheduling CronJobs.
	 * 
	 * @return
	 */
	@Bean
	public TaskScheduler poolScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setThreadNamePrefix("poolScheduler");
		scheduler.setPoolSize(10);
		return scheduler;
	}

	/**
	 * After startup run a daily scheduling job.
	 * 
	 * @throws ParseException
	 */
	@PostConstruct
	public void dailyAfterStartup() throws ParseException {
		if (StringUtil.isNotNullOrEmpty(configuration.getHue().getIp())) {
			cronJobUtil.setDailyJobs(poolScheduler(), configuration);
		}
	}

	/**
	 * Run a daily CronJob at a set time.
	 * 
	 * @throws ParseException
	 */
	@Scheduled(cron = "1 0 0 * * *")
	public void dailyJob() throws ParseException {
		if (StringUtil.isNotNullOrEmpty(configuration.getHue().getIp())) {
			cronJobUtil.setDailyJobs(poolScheduler(), configuration);
		}
	}

	/**
	 * Attempt to scan twitter if it is enabled.
	 */
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

package com.sonie.web;

import java.text.ParseException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.sonie.web.util.CronJobUtil;

import resources.internal.Configuration;
import resources.internal.Hue;

@SpringBootApplication(scanBasePackages={"resources.internal","com.sonie.web"})
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
		CronJobUtil.setDailySunJobs(poolScheduler(), configuration);
		CronJobUtil.setGoodMorningJob(poolScheduler(), getHue());
		CronJobUtil.setGoodNight(poolScheduler(), configuration.getHue());
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
	  PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
	  YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
	  yaml.setResources(new ClassPathResource("config.yml"));
	  propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
	  return propertySourcesPlaceholderConfigurer;
	}

	@Scheduled(cron = "0 0 1 * * *")
	public void dailyJob() throws ParseException {
		CronJobUtil.setDailySunJobs(poolScheduler(), configuration);
		CronJobUtil.setGoodMorningJob(poolScheduler(), getHue());
		CronJobUtil.setGoodNight(poolScheduler(), configuration.getHue());
	}

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(App.class, args);
	}
	
	private Hue getHue() {
		return configuration.getHue();
	}

}

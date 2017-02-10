package com.sonie.web;

import java.text.ParseException;

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
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
	  PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
	  YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
	  yaml.setResources(new ClassPathResource("config.yml"));
	  propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
	  return propertySourcesPlaceholderConfigurer;
	}

	@Scheduled(cron = "0 0 01 * * *")
	public void dailyJob() throws ParseException {
		CronJobUtil.setDailySunJobs(poolScheduler(), getHue());
		CronJobUtil.setGoodMorningJob(poolScheduler(), getHue());
	}

	@Scheduled(cron = "0 0 02 * * *")
	public void dailyTurnOff() {
		CronJobUtil.turnOffAllLights(getHue());
	}

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(App.class, args);
	}
	
	private Hue getHue() {
		return configuration.getHue();
	}

}

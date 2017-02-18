package com.sonie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sonie.web.App;

import resources.internal.Configuration;
import resources.internal.DailySchedule;
import resources.internal.Hue.Scene;

@Controller
public class DailySchedulesController {
	
	@Autowired
	private Configuration configuration;
	
	@Autowired
	private App app;

	@RequestMapping(value = "/dailySchedules", method = RequestMethod.POST)
	String setupForm(@ModelAttribute(value="dailySchedules") DailySchedule dailySchedule, Model model) {
		
		Scene scene = configuration.getHue().getScene();
		scene.getGoodMorning().setTime(dailySchedule.getMorningTime());
		scene.getGoodNight().setTime(dailySchedule.getNightTime());
		scene.getSunstatus().setSunsetAdjustedMinutes(dailySchedule.getSunsetTime());
		scene.getSunstatus().setSunriseAdjustedMinutes(dailySchedule.getSunriseTime());
		
		scene.getGoodMorning().setEnabled(dailySchedule.isMorningEnabled());
		scene.getGoodNight().setEnabled(dailySchedule.isNightEnabled());
		scene.getSunstatus().setEnabled(dailySchedule.isSunstatusEnabled());
		
		addSchedulesToModel(model);
		return "dailySchedules";
	}
	
	@RequestMapping(value = "/dailySchedules", method = RequestMethod.GET)
	String index(Model model) {
		addSchedulesToModel(model);
		return "dailySchedules";
	}
	
	private void addSchedulesToModel(Model model) {
		Scene scene = configuration.getHue().getScene();
		DailySchedule dailySchedule = new DailySchedule();
		
		dailySchedule.setMorningTime(scene.getGoodMorning().getTime());
		dailySchedule.setNightTime(scene.getGoodNight().getTime());
		dailySchedule.setSunsetTime(scene.getSunstatus().getSunsetAdjustedMinutes());
		dailySchedule.setSunriseTime(scene.getSunstatus().getSunriseAdjustedMinutes());
		
		dailySchedule.setMorningEnabled(scene.getGoodMorning().isEnabled());
		dailySchedule.setNightEnabled(scene.getGoodNight().isEnabled());
		dailySchedule.setSunstatusEnabled(scene.getSunstatus().isEnabled());
		
		model.addAttribute("dailySchedule", dailySchedule);
	}
}

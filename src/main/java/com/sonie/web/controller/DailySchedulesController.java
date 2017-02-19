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
		
		scene.getGoodMorning().setId(dailySchedule.getMorningScene());
		scene.getGoodMorning().setGroup(dailySchedule.getMorningGroup());
		scene.getGoodNight().setGroup(dailySchedule.getNightGroup());
		scene.getSunstatus().setId(dailySchedule.getSunsetScene());
		scene.getSunstatus().setGroup(dailySchedule.getSunstatusGroup());
		
		scene.getEvening().setEnabled(dailySchedule.isEveningEnabled());
		scene.getEvening().setGroup(dailySchedule.getEveningGroup());
		scene.getEvening().setId(dailySchedule.getEveningScene());
		scene.getEvening().setTime(dailySchedule.getEveningTime());
		
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
		
		dailySchedule.setMorningScene(scene.getGoodMorning().getId());
		dailySchedule.setMorningGroup(scene.getGoodMorning().getGroup());
		dailySchedule.setNightGroup(scene.getGoodNight().getGroup());
		dailySchedule.setSunsetScene(scene.getSunstatus().getId());
		dailySchedule.setSunstatusGroup(scene.getSunstatus().getGroup());
		
		dailySchedule.setEveningEnabled(scene.getEvening().isEnabled());
		dailySchedule.setEveningGroup(scene.getEvening().getGroup());
		dailySchedule.setEveningScene(scene.getEvening().getId());
		dailySchedule.setEveningTime(scene.getEvening().getTime());
		
		model.addAttribute("dailySchedule", dailySchedule);
	}
}

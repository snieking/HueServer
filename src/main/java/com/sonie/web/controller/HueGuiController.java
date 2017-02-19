package com.sonie.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sonie.web.util.HueUtil;

import resources.internal.Configuration;
import resources.internal.HueGroup;
import resources.internal.HueLight;

@Controller
public class HueGuiController {
	
	@Autowired
	private Configuration config;
	
	@RequestMapping(value = "/hue/lights", method = RequestMethod.GET)
	String lights(Model model) {
		populateLightsInModel(model);
		return "lights";
	}
	

	@RequestMapping(value = "/hue/groups", method = RequestMethod.GET)
	String groups(Model model) {
		populateGroupsInModel(model);
		return "groups";
	}
	
	@RequestMapping(value = "/hue/groups/create", method = RequestMethod.POST)
	String groups(HttpServletRequest request, Model model) {
		String name = request.getParameter("groupName");
		String lights = request.getParameter("groupLights");
		HueUtil.createGroupWithId(name, lights, config);
		
		populateGroupsInModel(model);
		return "groups";
	}
	
	@RequestMapping(value = "/hue/groups/delete", method = RequestMethod.POST)
	String deleteGroup(@ModelAttribute(value="deleteId") String id, Model model) {
		HueUtil.deleteGroupWithId(id, config);
		
		populateGroupsInModel(model);
		return "groups";
	}

	@RequestMapping(value = "/hue/scenes", method = RequestMethod.GET)
	String scenes(Model model) {
		populateScenesInModel(model);
		return "scenes";
	}
	
	@RequestMapping(value = "/hue/scenes/delete", method = RequestMethod.POST)
	String deleteScene(@ModelAttribute(value="deleteId") String id, Model model) {
		HueUtil.deleteSceneWithId(id, config);
		
		populateScenesInModel(model);
		return "scenes";
	}
	
	private void populateLightsInModel(Model model) {
		Map<String, HueLight> lights = HueUtil.getLightsWithId(config);
		model.addAttribute("lights", lights);
	}
	
	private void populateGroupsInModel(Model model) {
		Map<String, HueGroup> groups = HueUtil.getGroupsWithId(config);
		model.addAttribute("groups", groups);
	}

	private void populateScenesInModel(Model model) {
		Map<String, String> scenes = HueUtil.getScenesWithId(config);
		model.addAttribute("scenes", scenes);
	}
}

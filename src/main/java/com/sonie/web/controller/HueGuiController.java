package com.sonie.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sonie.web.resources.config.ApplicationConfiguration;
import com.sonie.web.resources.hue.HueLight;
import com.sonie.web.resources.view.hue.HueGroup;
import com.sonie.web.util.HueUtil;

/**
 * Controller for the Hue GUI.
 * 
 * @author viktorplane
 */
@Controller
public class HueGuiController {
	
	@Autowired
	private ApplicationConfiguration config;
	
	/**
	 * Renders the template for displaying the lights.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hue/lights", method = RequestMethod.GET)
	String lights(Model model) {
		populateLightsInModel(model);
		return "lights";
	}
	
	/**
	 * Renders the template for displaying the groups.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hue/groups", method = RequestMethod.GET)
	String groups(Model model) {
		populateGroupsInModel(model);
		return "groups";
	}
	
	/**
	 * Post method for creating a new group containing lights.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hue/groups/create", method = RequestMethod.POST)
	String groups(HttpServletRequest request, Model model) {
		String name = request.getParameter("groupName");
		String lights = request.getParameter("groupLights");
		HueUtil.createGroupWithId(name, lights, config);
		
		populateGroupsInModel(model);
		return "groups";
	}
	
	/**
	 * Post method for deleting a group.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hue/groups/delete", method = RequestMethod.POST)
	String deleteGroup(@ModelAttribute(value="deleteId") String id, Model model) {
		HueUtil.deleteGroupWithId(id, config);
		
		populateGroupsInModel(model);
		return "groups";
	}

	/**
	 * Renders the template for displaying the available scenes.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hue/scenes", method = RequestMethod.GET)
	String scenes(Model model) {
		populateScenesInModel(model);
		return "scenes";
	}
	
	/**
	 * Post method for deleting a scene.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hue/scenes/delete", method = RequestMethod.POST)
	String deleteScene(@ModelAttribute(value="deleteId") String id, Model model) {
		HueUtil.deleteSceneWithId(id, config);
		
		populateScenesInModel(model);
		return "scenes";
	}
	
	/**
	 * Add all the lights to the model so that the template can display them.
	 * 
	 * @param model
	 */
	private void populateLightsInModel(Model model) {
		Map<String, HueLight> lights = HueUtil.getLightsWithId(config);
		model.addAttribute("lights", lights);
	}
	
	/**
	 * Add all the groups to the model so that the template can display them.
	 * 
	 * @param model
	 */
	private void populateGroupsInModel(Model model) {
		Map<String, HueGroup> groups = HueUtil.getGroupsWithId(config);
		model.addAttribute("groups", groups);
	}

	/**
	 * Add all the scenes to the model so that the template can display them.
	 * 
	 * @param model
	 */
	private void populateScenesInModel(Model model) {
		Map<String, String> scenes = HueUtil.getScenesWithId(config);
		model.addAttribute("scenes", scenes);
	}
}

package com.sonie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sonie.web.usecase.HueUseCase;
import com.sonie.web.util.SonarUtil;

import resources.internal.Configuration;
import resources.internal.StringUtil;

@Controller
public class IndexController {

	@Autowired
	private Configuration configuration;

	@Autowired
	private HueUseCase hueUseCase;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String index() {

		if (StringUtil.isNullOrEmpty(configuration.getHue().getIp()) || StringUtil.isNullOrEmpty(configuration.getHue().getUser())) {
			return "redirect:/setup";
		}
		
		return "redirect:/dailySchedules";
	}

	@RequestMapping(value = "/setup", method = RequestMethod.GET)
	String setup() {
		hueUseCase.addIp();

		return "setup";
	}

	@RequestMapping(value = "/link", method = RequestMethod.GET)
	String link() {
		try {
			hueUseCase.linkBridgeWithNewUser();
		} catch (NullPointerException e) {
			// User did not press the Bridge button.
			SonarUtil.swallowException(e);
			return "setup";
		}

		return "redirect:/configdata";
	}
	
	@RequestMapping(value = "/configdata", method = RequestMethod.GET)
	String done(Model model) {
		model.addAttribute("ip", configuration.getHue().getIp());
		model.addAttribute("user", configuration.getHue().getUser());
		
		return "configdata";
	}

}

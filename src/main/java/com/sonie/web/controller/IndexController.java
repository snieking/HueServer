package com.sonie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sonie.web.resources.config.ApplicationConfiguration;
import com.sonie.web.usecase.HueUseCase;
import com.sonie.web.util.SonarUtil;
import com.sonie.web.util.StringUtil;

/**
 * The basic index controller for rendering a couple of templates.
 * 
 * @author viktorplane
 */
@Controller
public class IndexController {

	@Autowired
	private ApplicationConfiguration configuration;

	@Autowired
	private HueUseCase hueUseCase;

	/**
	 * If the Hue IP and user has not been configured yet it will redirect to
	 * the setup page. Else it will display the Hue scheduling configuration.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	String index() {
		if (StringUtil.isNullOrEmpty(configuration.getHue().getIp())
				|| StringUtil.isNullOrEmpty(configuration.getHue().getUser())) {
			return "redirect:/setup";
		}

		return "redirect:/dailySchedules";
	}

	/**
	 * Renders the setup template.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/setup", method = RequestMethod.GET)
	String setup() {
		hueUseCase.addIp();

		return "setup";
	}

	/**
	 * Links the bridge after the user has pressed the Philips Hue big button.
	 * Adds the username and IP to the configuration.
	 * 
	 * @return
	 */
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

	/**
	 * Renders the config data template for displaying some config data.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/configdata", method = RequestMethod.GET)
	String done(Model model) {
		model.addAttribute("ip", configuration.getHue().getIp());
		model.addAttribute("user", configuration.getHue().getUser());

		return "configdata";
	}

}

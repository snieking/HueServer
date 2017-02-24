package com.sonie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sonie.web.resources.config.ApplicationConfiguration;
import com.sonie.web.resources.config.Twitter;
import com.sonie.web.resources.view.twitter.TwitterConfig;
import com.sonie.web.util.StringUtil;

/**
 * GUI controller for Twitter Configuration for the Hue system.
 * 
 * @author viktorplane
 */
@Controller
public class TwitterHueController {

	@Autowired
	private ApplicationConfiguration config;

	/**
	 * Post request for saving and updating the Twitter configuration.
	 * 
	 * @param twitterConfig
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/twitterconfig", method = RequestMethod.POST)
	String setupForm(@ModelAttribute(value = "twitterconfig") TwitterConfig twitterConfig, Model model) {

		Twitter twitter = config.getTwitter();
		twitter.setEnabled(twitterConfig.isEnabled());
		twitter.setGroup(twitterConfig.getGroup());
		twitter.setRegex(twitterConfig.getRegex());
		twitter.setUsers(StringUtil.stringToList(twitterConfig.getUsers()));

		addTwitterInfoToModel(model);
		return "twitter";
	}

	/**
	 * Renders the template for configuring Twitter such as what it should scan
	 * and who.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/twitterconfig", method = RequestMethod.GET)
	String twitterConfig(Model model) {
		addTwitterInfoToModel(model);
		return "twitter";
	}

	/**
	 * Adds the Twitter configuration to the model so that the template can
	 * render the data.
	 * 
	 * @param model
	 */
	private void addTwitterInfoToModel(Model model) {
		Twitter twitter = config.getTwitter();
		TwitterConfig twitterConfig = new TwitterConfig();

		twitterConfig.setEnabled(twitter.isEnabled());
		twitterConfig.setRegex(twitter.getRegex());
		twitterConfig.setGroup(twitter.getGroup());
		twitterConfig.setUsers(StringUtil.listToString(twitter.getUsers()));

		model.addAttribute("twitter", twitterConfig);
	}

}

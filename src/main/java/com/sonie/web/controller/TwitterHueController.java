package com.sonie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import resources.internal.Configuration;
import resources.internal.StringUtil;
import resources.internal.Twitter;
import resources.internal.TwitterConfig;

@Controller
public class TwitterHueController {
	
	@Autowired
	private Configuration config;
	
	@RequestMapping(value = "/twitterconfig", method = RequestMethod.POST)
	String setupForm(@ModelAttribute(value="twitterconfig") TwitterConfig twitterConfig, Model model) {
		
		Twitter twitter = config.getTwitter();
		twitter.setEnabled(twitterConfig.isEnabled());
		twitter.setGroup(twitterConfig.getGroup());
		twitter.setRegex(twitterConfig.getRegex());
		twitter.setUsers(StringUtil.stringToList(twitterConfig.getUsers()));
		
		addTwitterInfoToModel(model);
		return "twitter";
	}
	
	@RequestMapping(value = "/twitterconfig", method = RequestMethod.GET)
	String twitterConfig(Model model) {	
		addTwitterInfoToModel(model);
		return "twitter";
	}

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

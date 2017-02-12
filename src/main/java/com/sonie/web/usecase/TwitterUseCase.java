/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.usecase;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import com.sonie.web.resources.twitter.TwitterScanRequest;
import com.sonie.web.util.HueUtil;

import resources.internal.Configuration;

@Service
public class TwitterUseCase {
	private static final Logger LOG = LoggerFactory.getLogger(TwitterUseCase.class);
	private Twitter twitter;
	private static HashSet<String> messages = new HashSet<>();

	@Autowired
	private Configuration config;

	@Inject
	public TwitterUseCase(Twitter twitter) {
		this.twitter = twitter;
	}

	/**
	 * Scans the tweets based on data in provided {@link TwitterUseCase}.
	 * 
	 * @param request
	 */
	public void scanTweets(TwitterScanRequest request) {
		List<String> users = request.getUsers();
		String regex = request.getRegex();
		int size = messages.size();

		if (users != null && !users.isEmpty()) {
			scanUsersForMessage(users, regex);
		} else {
			LOG.warn("Twitter enabled but no user to search for, check your configuration.");
		}

		if (messages.size() > size) {
			LOG.info("Found a new twitter match, blinking lights.");
			HueUtil.blinkLights(config.getHue().getIp(), config.getHue().getUser(), config.getTwitter().getGroup());
		}
	}

	/**
	 * Scans messages for users and uses {@link #checkTweets(List, String)} to
	 * check if the regex matches it.
	 * 
	 * @param users
	 * @param regex
	 */
	private void scanUsersForMessage(List<String> users, String regex) {
		for (String user : users) {
			List<Tweet> tweets = twitter.timelineOperations().getUserTimeline(user);
			checkTweets(tweets, regex);
		}
	}

	/**
	 * Checks the tweets to see if they match the provided regex.
	 * 
	 * @param tweets
	 * @param regex
	 */
	private void checkTweets(List<Tweet> tweets, String regex) {
		for (Tweet tweet : tweets) {
			String msg = tweet.getText();

			if (Pattern.matches(regex, msg)) {
				messages.add(msg);
			}
		}
	}
}

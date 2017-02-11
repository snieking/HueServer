package com.sonie.web.usecase;

import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import com.sonie.web.resources.twitter.TwitterScanRequest;
import com.sonie.web.util.HueUtil;

import resources.internal.Configuration;

@Service
public class TwitterUseCase {
	private Twitter twitter;
	private static HashSet<String> messages = new HashSet<>();
	
	@Autowired
	private Configuration config;
	
	@Inject
	public TwitterUseCase(Twitter twitter) {
		this.twitter = twitter;
	}
	
	public void scanTweets(TwitterScanRequest request) {
		List<String> users = request.getUsers();
		String regex = request.getRegex();
		int size = messages.size();
		
		if (users != null && !users.isEmpty()) {
			scanUsersForMessage(users, regex);
		} else {
			scanMyWall(regex);
		}
		
		if (messages.size() > size) {
			HueUtil.blinkLights(config.getHue().getIp(), config.getHue().getUser(), config.getTwitter().getGroup());
		}
	}

	private void scanMyWall(String regex) {
		List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline(20);
		checkTweets(tweets, regex);
	}

	private void scanUsersForMessage(List<String> users, String regex) {
		for (String user : users) {
			List<Tweet> tweets = twitter.timelineOperations().getUserTimeline(user);
			checkTweets(tweets, regex);
		}
	}
	
	private void checkTweets(List<Tweet> tweets, String regex) {
		for (Tweet tweet : tweets) {
			String msg = tweet.getText();
			
			if (Pattern.matches(regex, msg)) {
				messages.add(msg);
			}
		}
	}
}

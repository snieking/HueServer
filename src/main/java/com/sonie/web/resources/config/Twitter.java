/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.resources.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Holds the Twitter configuration data.
 * @author viktorplane
 *
 */
@ConfigurationProperties(prefix = "application.twitter")
public class Twitter {
	private boolean enabled;
	private List<String> users;
	private String regex;
	private String group;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}

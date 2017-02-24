package com.sonie.web.resources.view.twitter;

/**
 * Used by the template while storing and populating Twitter config.
 * 
 * @author viktorplane
 */
public class TwitterConfig {
	private String regex;
	private String users;
	private String group;

	private boolean enabled;

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

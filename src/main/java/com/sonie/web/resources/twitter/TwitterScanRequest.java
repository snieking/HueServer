package com.sonie.web.resources.twitter;

import java.util.List;

public class TwitterScanRequest {
	private List<String> users;
	private String regex;

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

}

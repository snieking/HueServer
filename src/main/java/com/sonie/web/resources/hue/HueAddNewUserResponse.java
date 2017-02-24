package com.sonie.web.resources.hue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Response from the sent request for adding a new API user to the Philips Hue
 * Bridge.
 * 
 * @author viktorplane
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HueAddNewUserResponse {
	private Success success;

	public Success getSuccess() {
		return success;
	}

	public void setSuccess(Success success) {
		this.success = success;
	}

	public class Success {
		private String username;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

	}
}

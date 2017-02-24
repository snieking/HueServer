package com.sonie.web.resources.hue;

/**
 * Request for adding a new API user to the Philips Hue bridge.
 * 
 * @author viktorplane
 */
public class HueAddNewUserRequest {
	private String devicetype;

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

}

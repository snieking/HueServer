/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.resources.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Stores the general config data.
 * 
 * @author viktorplane
 */
@ConfigurationProperties(prefix = "application.general")
public class General {
	private int timeZone;
	private String latitude;
	private String longitude;

	public int getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(int timeZone) {
		this.timeZone = timeZone;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}

package com.sonie.web.resources.hue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Used to store a light when requesting the lights.
 * 
 * @author viktorplane
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HueLight {
	private String name;
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

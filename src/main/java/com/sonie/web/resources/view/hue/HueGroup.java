package com.sonie.web.resources.view.hue;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Used by the templates to display Hue light groups.
 * 
 * @author viktorplane
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HueGroup {
	private String name;
	private List<String> lights;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getLights() {
		return lights;
	}

	public void setLights(List<String> lights) {
		this.lights = lights;
	}

}

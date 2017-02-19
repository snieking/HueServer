package resources.internal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

package resources.internal;

import java.util.List;

public class HueCreateGroupRequest {

	private List<String> lights;
	private String type;
	private String name;

	public List<String> getLights() {
		return lights;
	}

	public void setLights(List<String> lights) {
		this.lights = lights;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

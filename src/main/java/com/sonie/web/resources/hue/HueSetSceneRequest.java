/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.resources.hue;

/**
 * Used for setting a scene request.
 * 
 * @author viktorplane
 */
public class HueSetSceneRequest {
	private String id;
	private String group;
	private String scene;
	private String effect;
	private String alert;
	private Boolean on;
	private Integer bri;
	private Integer hue;
	private Integer sat;

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public Integer getBri() {
		return bri;
	}

	public void setBri(Integer bri) {
		this.bri = bri;
	}

	public Integer getHue() {
		return hue;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public void setHue(Integer hue) {
		this.hue = hue;
	}

	public Integer getSat() {
		return sat;
	}

	public void setSat(Integer sat) {
		this.sat = sat;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Boolean isOn() {
		return on;
	}

	public void setOn(Boolean on) {
		this.on = on;
	}

}

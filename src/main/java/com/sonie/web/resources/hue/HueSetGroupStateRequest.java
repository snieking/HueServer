package com.sonie.web.resources.hue;

public class HueSetGroupStateRequest {
	private boolean on;
	private String scene;
	private String effect;
	private int bri;
	private int sat;
	private int hue;

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public int getBri() {
		return bri;
	}

	public void setBri(int bri) {
		this.bri = bri;
	}

	public int getSat() {
		return sat;
	}

	public void setSat(int sat) {
		this.sat = sat;
	}

	public int getHue() {
		return hue;
	}

	public void setHue(int hue) {
		this.hue = hue;
	}

}

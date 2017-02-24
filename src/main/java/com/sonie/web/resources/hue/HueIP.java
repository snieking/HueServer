package com.sonie.web.resources.hue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Stores the received Hue IP when requesting that.
 * 
 * @author viktorplane
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HueIP {
	private String id;
	private String internalipaddress;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInternalipaddress() {
		return internalipaddress;
	}

	public void setInternalipaddress(String internalipaddress) {
		this.internalipaddress = internalipaddress;
	}

}

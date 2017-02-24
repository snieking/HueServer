package com.sonie.web.events;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class LightEvent {

	@Id
	private String id;
	private String time;
	private String event;

	public LightEvent(String time, String event) {
		this.time = time;
		this.event = event;
		this.id = "hue.light:" + UUID.randomUUID().toString();
	}

	public String getTime() {
		return time;
	}

	public String getEvent() {
		return event;
	}

	public String getId() {
		return id;
	}

}

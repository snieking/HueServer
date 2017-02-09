package com.sonie.web.resources.weather;


public class SunStatusResponse {
	private String status;
	private SunResults results;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SunResults getResults() {
		return results;
	}

	public void setResults(SunResults results) {
		this.results = results;
	}

}

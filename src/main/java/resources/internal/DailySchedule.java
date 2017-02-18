package resources.internal;

public class DailySchedule {
	private String morningTime;
	private String nightTime;
	private int sunsetTime;
	private int sunriseTime;

	private boolean morningEnabled;
	private boolean nightEnabled;
	private boolean sunstatusEnabled;

	public String getMorningTime() {
		return morningTime;
	}

	public void setMorningTime(String morningTime) {
		this.morningTime = morningTime;
	}

	public String getNightTime() {
		return nightTime;
	}

	public void setNightTime(String nightTime) {
		this.nightTime = nightTime;
	}

	public int getSunsetTime() {
		return sunsetTime;
	}

	public void setSunsetTime(int sunsetTime) {
		this.sunsetTime = sunsetTime;
	}

	public int getSunriseTime() {
		return sunriseTime;
	}

	public void setSunriseTime(int sunriseTime) {
		this.sunriseTime = sunriseTime;
	}

	public boolean isMorningEnabled() {
		return morningEnabled;
	}

	public void setMorningEnabled(boolean morningEnabled) {
		this.morningEnabled = morningEnabled;
	}

	public boolean isNightEnabled() {
		return nightEnabled;
	}

	public void setNightEnabled(boolean nightEnabled) {
		this.nightEnabled = nightEnabled;
	}

	public boolean isSunstatusEnabled() {
		return sunstatusEnabled;
	}

	public void setSunstatusEnabled(boolean sunstatusEnabled) {
		this.sunstatusEnabled = sunstatusEnabled;
	}

}

package resources.internal;

public class DailySchedule {
	private String morningTime;
	private String nightTime;
	private String eveningTime;
	private int sunsetTime;
	private int sunriseTime;

	private boolean morningEnabled;
	private boolean nightEnabled;
	private boolean sunstatusEnabled;
	private boolean eveningEnabled;

	private String morningScene;
	private String morningGroup;
	private String nightGroup;
	private String sunsetScene;
	private String sunstatusGroup;
	private String eveningScene;
	private String eveningGroup;

	public String getEveningTime() {
		return eveningTime;
	}

	public void setEveningTime(String eveningTime) {
		this.eveningTime = eveningTime;
	}

	public boolean isEveningEnabled() {
		return eveningEnabled;
	}

	public void setEveningEnabled(boolean eveningEnabled) {
		this.eveningEnabled = eveningEnabled;
	}

	public String getEveningScene() {
		return eveningScene;
	}

	public void setEveningScene(String eveningScene) {
		this.eveningScene = eveningScene;
	}

	public String getEveningGroup() {
		return eveningGroup;
	}

	public void setEveningGroup(String eveningGroup) {
		this.eveningGroup = eveningGroup;
	}

	public String getMorningScene() {
		return morningScene;
	}

	public void setMorningScene(String morningScene) {
		this.morningScene = morningScene;
	}

	public String getMorningGroup() {
		return morningGroup;
	}

	public void setMorningGroup(String morningGroup) {
		this.morningGroup = morningGroup;
	}

	public String getNightGroup() {
		return nightGroup;
	}

	public void setNightGroup(String nightGroup) {
		this.nightGroup = nightGroup;
	}

	public String getSunsetScene() {
		return sunsetScene;
	}

	public void setSunsetScene(String sunsetScene) {
		this.sunsetScene = sunsetScene;
	}

	public String getSunstatusGroup() {
		return sunstatusGroup;
	}

	public void setSunstatusGroup(String sunstatusGroup) {
		this.sunstatusGroup = sunstatusGroup;
	}

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

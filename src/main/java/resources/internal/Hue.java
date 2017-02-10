package resources.internal;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.hue")
public class Hue {
	private String user;
	private String ip;
	private Scene scene;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@Override
	public String toString() {
		return "app {" + "user='" + user + "'" + "ip='" + ip + "'" + "scenes='" + scene + "'";
	}

	public static class Scene {
		private Sunstatus sunstatus;
		private GoodMorning goodMorning;
		private GoodNight goodNight;

		public Sunstatus getSunstatus() {
			return sunstatus;
		}

		public void setSunstatus(Sunstatus sunstatus) {
			this.sunstatus = sunstatus;
		}

		public GoodMorning getGoodMorning() {
			return goodMorning;
		}

		public void setGoodMorning(GoodMorning goodMorning) {
			this.goodMorning = goodMorning;
		}

		public GoodNight getGoodNight() {
			return goodNight;
		}

		public void setGoodNight(GoodNight goodNight) {
			this.goodNight = goodNight;
		}

		public static class Sunstatus {
			private boolean enabled;
			private String id;
			private String group;

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
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
		}

		public static class GoodMorning {
			private boolean enabled;
			private String time;
			private String id;
			private String group;

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}

			public String getTime() {
				return time;
			}

			public void setTime(String time) {
				this.time = time;
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

		}

		public static class GoodNight {
			private boolean enabled;
			private String time;
			private String id;
			private String group;

			public boolean isEnabled() {
				return enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}

			public String getTime() {
				return time;
			}

			public void setTime(String time) {
				this.time = time;
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
		}
	}
}

package resources.internal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "hue")
public class Hue {
	private String user;
	private Scenes scenes;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Scenes getScene() {
		return scenes;
	}

	public void setScene(Scenes scenes) {
		this.scenes = scenes;
	}

	public class Scenes {
		private Sunstatus sunstatus;
		private Disable disable;

		public Sunstatus getSunstatus() {
			return sunstatus;
		}

		public void setSunstatus(Sunstatus sunstatus) {
			this.sunstatus = sunstatus;
		}

		public Disable getDisable() {
			return disable;
		}

		public void setDisable(Disable disable) {
			this.disable = disable;
		}

		public class Sunstatus {
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

		public class Disable {
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

	}

}

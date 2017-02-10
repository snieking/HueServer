package resources.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@RefreshScope
@Service
@EnableConfigurationProperties({Hue.class, General.class})
public class Configuration {
	@Autowired
	private Hue hue;

	@Autowired
	private General general;

	public void setHue(Hue hue) {
		this.hue = hue;
	}

	public Hue getHue() {
		return hue;
	}

	public General getGeneral() {
		return general;
	}

	public void setGeneral(General general) {
		this.general = general;
	}

}

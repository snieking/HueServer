/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package resources.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@RefreshScope
@Service
@EnableConfigurationProperties({ Hue.class, Twitter.class, General.class })
public class Configuration {
	@Autowired
	private Hue hue;

	@Autowired
	private General general;

	@Autowired
	private Twitter twitter;

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

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

}

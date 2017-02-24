/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.util;

import com.sonie.web.resources.config.ApplicationConfiguration;
import com.sonie.web.resources.twitter.TwitterScanRequest;

public class TwitterUtil {
	
	public static TwitterScanRequest createTwitterRequest(ApplicationConfiguration config) {
		TwitterScanRequest request = new TwitterScanRequest();
		
		request.setUsers(config.getTwitter().getUsers());
		request.setRegex(config.getTwitter().getRegex());
		
		return request;
	}

}

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.util;

import com.sonie.web.resources.twitter.TwitterScanRequest;

import resources.internal.Configuration;

public class TwitterUtil {
	
	public static TwitterScanRequest createTwitterRequest(Configuration config) {
		TwitterScanRequest request = new TwitterScanRequest();
		
		request.setUsers(config.getTwitter().getUsers());
		request.setRegex(config.getTwitter().getRegex());
		
		return request;
	}

}

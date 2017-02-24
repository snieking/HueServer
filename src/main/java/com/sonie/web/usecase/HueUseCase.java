/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.sonie.web.resources.config.ApplicationConfiguration;
import com.sonie.web.resources.hue.HueSetSceneRequest;
import com.sonie.web.resources.hue.HueSetSceneResponse;
import com.sonie.web.util.HueUtil;
import com.sonie.web.util.ResponseBuilder;

@Service
public class HueUseCase {
	
	private ApplicationConfiguration configuration;
	
	@Autowired
	public HueUseCase(ApplicationConfiguration configuration) {
		this.configuration = configuration;
	}
	
	public DeferredResult<HueSetSceneResponse> setSceneUseCase(HueSetSceneRequest request) {
		DeferredResult<HueSetSceneResponse> result = new DeferredResult<>();
		HueSetSceneResponse response = new HueSetSceneResponse();
		
		String user = configuration.getHue().getUser();
		String ip = configuration.getHue().getIp();
		
		HueUtil.putGroupRequest(request, user, ip);
		
		response.setResponseHeader(ResponseBuilder.createSuccessfulResponse());
		result.setResult(response);
		
		return result;
	}

	public void addIp() {
		configuration.getHue().setIp(HueUtil.getIpRequest());
	}

	public void linkBridgeWithNewUser() throws NullPointerException {
		String user = HueUtil.getNewUser(configuration.getHue().getIp());
		configuration.getHue().setUser(user);
	}
}

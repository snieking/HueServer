package com.sonie.web.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.sonie.web.util.HueUtil;
import com.sonie.web.util.ResponseBuilder;

import resources.internal.Configuration;
import resources.internal.HueSetSceneRequest;
import resources.internal.HueSetSceneResponse;

@Service
public class HueUseCase {
	private static final Logger LOG = LoggerFactory.getLogger(HueUseCase.class);
	
	@Autowired
	private Configuration configuration;
	
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
}

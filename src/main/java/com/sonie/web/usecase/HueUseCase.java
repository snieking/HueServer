package com.sonie.web.usecase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.sonie.web.util.HueUtil;
import com.sonie.web.util.ResponseBuilder;

import resources.internal.Hue;
import resources.internal.HueSetSceneRequest;
import resources.internal.HueSetSceneResponse;

@Service
public class HueUseCase {
	private static final Logger LOG = LoggerFactory.getLogger(HueUseCase.class);
	
	@Autowired
	private Hue hue;
	
	public DeferredResult<HueSetSceneResponse> setSceneUseCase(HueSetSceneRequest request) {
		DeferredResult<HueSetSceneResponse> result = new DeferredResult<>();
		HueSetSceneResponse response = new HueSetSceneResponse();
		
		String user = hue.getUser();
		LOG.info("User was [{}]", user);
		HueUtil.putGroupRequest(request, user);
		
		response.setResponseHeader(ResponseBuilder.createSuccessfulResponse());
		result.setResult(response);
		
		return result;
	}
}

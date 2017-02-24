/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.sonie.web.resources.hue.HueSetSceneRequest;
import com.sonie.web.resources.hue.HueSetSceneResponse;
import com.sonie.web.usecase.HueUseCase;

/**
 * Rest controller for Philips Hue.
 * 
 * @author viktorplane
 *
 */
@RestController
public class HueController {
	
	@Autowired
	private HueUseCase hueUseCase;
	
	/**
	 * Sets a scene based on the parameter received in the request.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/hue/setscene")
	public DeferredResult<HueSetSceneResponse> setScene(@RequestBody HueSetSceneRequest request) {
		return hueUseCase.setSceneUseCase(request);
	}
	
}

/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.sonie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.sonie.web.resources.weather.SunStatusResponse;
import com.sonie.web.usecase.WeatherUseCase;

/**
 * Rest controller for weather data.
 * 
 * @author viktorplane
 */
@RestController
public class WeatherController {

	@Autowired
	public WeatherUseCase weatherUseCase;

	/**
	 * Get the sun status; when sun goes up and down.
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/weather/sunstatus")
	public DeferredResult<SunStatusResponse> dailySunStatus() {

		return weatherUseCase.getSunStatusUseCase();
	}
}

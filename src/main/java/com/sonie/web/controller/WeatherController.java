package com.sonie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.sonie.web.resources.weather.SunStatusResponse;
import com.sonie.web.usecase.WeatherUseCase;

@RestController
public class WeatherController {
	
	@Autowired
	public WeatherUseCase weatherUseCase;
	
	@RequestMapping(method = RequestMethod.GET, value = "/weather/sunstatus")
	public DeferredResult<SunStatusResponse> dailySunStatus() {
		
		return weatherUseCase.getSunStatusUseCase();
	}
}

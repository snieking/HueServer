package com.sonie.web.usecase;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import com.sonie.web.resources.weather.SunStatusResponse;

@Service
public class WeatherUseCase {
	
	public DeferredResult<SunStatusResponse> getSunStatusUseCase() {
		DeferredResult<SunStatusResponse> result = new DeferredResult<>();
		RestTemplate restTemplate = new RestTemplate();
		
		SunStatusResponse response = restTemplate.getForObject("http://api.sunrise-sunset.org/json?lat=59.200197&lng=17.828536&date=today", SunStatusResponse.class);
		result.setResult(response);
		
		return result;
	}
}

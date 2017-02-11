package com.sonie.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sonie.web.resources.twitter.TwitterScanRequest;
import com.sonie.web.usecase.TwitterUseCase;

@RestController
public class TwitterController {
	
	@Autowired
	private TwitterUseCase twitterUseCase;
	
	@RequestMapping(method = RequestMethod.PUT, value ="/twitter/tweets")
	public void getTweets(@RequestBody @Valid TwitterScanRequest request) {
		twitterUseCase.scanTweets(request);
	}

}
